package com.hit.processes;

import java.util.List;

public class RunConfiguration {
	private List<ProcessCycles> processCycle;

	public RunConfiguration(List<ProcessCycles> processCycle) {
		super();
		this.processCycle = processCycle;
	}

	public List<ProcessCycles> getProcessCycle() {
		return processCycle;
	}

	public void setProcessCycle(List<ProcessCycles> processCycle) {
		this.processCycle = processCycle;
	}

	@Override
	public String toString() {
		return "RunConfiguration [processCycle=" + processCycle + "]";
	}
}
