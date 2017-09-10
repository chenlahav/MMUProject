package com.hit.driver;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.hit.algorithm.IAlgoCache;
import com.hit.algorithm.LRUAlgoCacheImpl;
import com.hit.algorithm.NFUAlgoCacheImpl;
import com.hit.algorithm.RandomAlgoCacheImpl;
import com.hit.memoryunits.MemoryManagementUnit;
import com.hit.processes.ProcessCycles;
import com.hit.processes.RunConfiguration;
import com.hit.algorithm.IAlgoCache;
import com.hit.processes.Process;

public class MMUDriver {
	public MMUDriver() {
		// TODO Auto-generated constructor stub
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

	public static void start(String[] command) {
		
		int capacity =  Integer.parseInt(command[1]);
		IAlgoCache<Long, Long> algo = ConvertToAlgo(command[0], capacity);
		
		MemoryManagementUnit mmu = new MemoryManagementUnit(capacity, algo);
		RunConfiguration runConfig = readConfigurationFile();
		List<ProcessCycles> processCycles = runConfig.getProcessCycle();
		List<Process> processes = createProcesses(processCycles, mmu);
		runProcesses(processes);
	}

	public static RunConfiguration readConfigurationFile() {
		return null;
	}	

	public static void runProcesses(List<Process> applications) {

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
