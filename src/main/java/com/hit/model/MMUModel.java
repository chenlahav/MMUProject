package com.hit.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.hit.algorithm.IAlgoCache;
import com.hit.algorithm.LRUAlgoCacheImpl;
import com.hit.algorithm.NFUAlgoCacheImpl;
import com.hit.algorithm.RandomAlgoCacheImpl;
import com.hit.memoryunits.MemoryManagementUnit;
import com.hit.processes.Process;
import com.hit.processes.ProcessCycles;
import com.hit.processes.RunConfiguration;
import com.hit.util.MMULogger;

public class MMUModel extends Observable implements Model {

	private static final String CONFIG_FILE = "src/main/resources/com/hit/config/Configuration.json";
	public int numProcesses;
	public int ramCapacity;
	private List<String> configuration;
	private List<String> logFile;
	
	
	
	public MMUModel(List<String> configuration) {
		this.configuration = configuration;
	}
	
	public static List<Process> createProcesses(List<ProcessCycles> appliocationsScenarios, MemoryManagementUnit mmu) {
		List<Process> processesList = new ArrayList<Process>();
		int id= 0;
		for (ProcessCycles processCycles : appliocationsScenarios) {
			Process p1 = new Process(++id, mmu, processCycles);
			processesList.add(p1);
		}
		return processesList;
	}
	
	public static RunConfiguration readConfigurationFile() {
		RunConfiguration rc = null;
		FileReader configFile = null;

		try {
			configFile = new FileReader(CONFIG_FILE);
			Gson g = new Gson();
			rc = g.fromJson(new JsonReader(configFile), RunConfiguration.class);
		} catch (FileNotFoundException | JsonIOException | JsonSyntaxException e) {
			MMULogger.getInstance().write(e.getMessage(), Level.SEVERE);
			System.err.println(e.getMessage());
		}

		return rc;
	}	

	public static void runProcesses(List<Process> applications) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newCachedThreadPool();
		@SuppressWarnings("unchecked")
		Future<Boolean> futures[] = new Future[applications.size()];
		for (int i=0; i<applications.size(); i++){
			futures[i] = executor.submit(applications.get(i));
		}
		executor.shutdown();
		for (int i=0; i<applications.size(); i++){
			System.out.printf("process %d: %s\n",applications.get(i).getId(),futures[i].get());
		}
	}
	
	 private static IAlgoCache<Long, Long> ConvertToAlgo (String algoString, Integer capacity){
		
		IAlgoCache<Long,Long > algo = null ;
		
		switch (algoString) {
			case "LRU":{
				algo = new LRUAlgoCacheImpl<>(capacity);
				break;
			}
				
			case "NFU":{
				algo = new NFUAlgoCacheImpl<>(capacity);
				break;
			}
			
			case "RANDOM":{
				algo = new RandomAlgoCacheImpl<>(capacity);
				break;
			}
		}
		return algo;
	 }

	@Override
	public void start() {
		int capacity =  Integer.parseInt(configuration.get(1));
		IAlgoCache<Long, Long> algo = ConvertToAlgo(configuration.get(0), capacity);
		
		MemoryManagementUnit mmu = new MemoryManagementUnit(capacity, algo);
		RunConfiguration runConfig = readConfigurationFile();
		List<ProcessCycles> processCycles = runConfig.getProcessCycle();
		MMULogger.getInstance().write("PN:"+processCycles.size(), Level.SEVERE);
		List<Process> processes = createProcesses(processCycles, mmu);
		try {
			runProcesses(processes);
		} catch (InterruptedException | ExecutionException e) {
			MMULogger.getInstance().write(e.getStackTrace().toString() , Level.SEVERE);
			e.printStackTrace();
		}
		
		readLogFile();
		
		setChanged();
		notifyObservers(this.logFile);
		MMULogger.getInstance().close();
	}
	
	public void setConfiguration(List<String> configuration){
		this.configuration = configuration;
	}
	
	public void readLogFile(){
		Scanner sc;
		try {
			sc = new Scanner(new FileReader("Logs/log.txt"));
			this.logFile = new ArrayList<>();
			while (sc.hasNextLine()) {
				logFile.add(sc.nextLine());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			MMULogger.getInstance().write(e.getStackTrace().toString() , Level.SEVERE);
		}
	}

}
