package com.hit.driver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.hit.algorithm.IAlgoCache;
import com.hit.algorithm.LRUAlgoCacheImpl;
import com.hit.algorithm.NFUAlgoCacheImpl;
import com.hit.algorithm.RandomAlgoCacheImpl;
import com.hit.memoryunits.MemoryManagementUnit;
import com.hit.processes.ProcessCycles;
import com.hit.processes.RunConfiguration;
import com.hit.processes.Process;

public class MMUDriver {
	private static final String CONFIG_FILE = "src/main/resources/com/hit/config/Configuration.json";
	public MMUDriver() {
	}

	public static List<Process> createProcesses(List<ProcessCycles> appliocationsScenarios, MemoryManagementUnit mmu) {
		List<Process> processesList = new ArrayList<Process>();
		int id= 1;
		for (ProcessCycles processCycles : appliocationsScenarios) {
			Process p1 = new Process(++id, mmu, processCycles);
			processesList.add(p1);
		}
		return processesList;
	}

	public static void main(String[] args) {
		CLI cli = new CLI(System.in,System.out);
		new Thread(cli).start();
	}

	public static void start(String[] command) throws InterruptedException, ExecutionException {
		int capacity =  Integer.parseInt(command[1]);
		IAlgoCache<Long, Long> algo = ConvertToAlgo(command[0], capacity);
		
		MemoryManagementUnit mmu = new MemoryManagementUnit(capacity, algo);
		RunConfiguration runConfig = readConfigurationFile();
		List<ProcessCycles> processCycles = runConfig.getProcessCycle();
		List<Process> processes = createProcesses(processCycles, mmu);
		runProcesses(processes);
	}

	public static RunConfiguration readConfigurationFile() {
		RunConfiguration rc = null;
		FileReader configFile = null;

		try {
			configFile = new FileReader(CONFIG_FILE);
			Gson g = new Gson();
			rc = g.fromJson(new JsonReader(configFile), RunConfiguration.class);
		} catch (FileNotFoundException | JsonIOException | JsonSyntaxException e) {
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
			System.out.printf("process %d: %s",applications.get(i).getId(),futures[i].get());
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
}
