package com.hit.processes;

import java.util.concurrent.Callable;

import com.hit.memoryunits.MemoryManagementUnit;

public class Process implements Callable<Boolean>{
	private int id;
	private MemoryManagementUnit mmu;
	private ProcessCycles processCycles;

	public Process(int id, MemoryManagementUnit mmu, ProcessCycles processCycles) {
		super();
		this.id = id;
		this.mmu = mmu;
		this.processCycles = processCycles;
	}

	@Override
	public Boolean call() throws Exception {
		// TODO The process business logic method
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
