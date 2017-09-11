package com.hit.processes;

import java.util.List;

public class RunConfiguration {
	private List<ProcessCycles> processesCycles;

	public RunConfiguration(List<ProcessCycles> processCycle) {
		super();
		this.processesCycles = processCycle;
	}

	public List<ProcessCycles> getProcessCycle() {
		return processesCycles;
	}

	public void setProcessCycle(List<ProcessCycles> processCycle) {
		this.processesCycles = processCycle;
	}

	@Override
	public String toString() {
		return "RunConfiguration [processCycle=" + processesCycles + "]";
	}
}
