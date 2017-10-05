package com.hit.processes;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.logging.Level;

import com.hit.memoryunits.MemoryManagementUnit;
import com.hit.memoryunits.Page;
import com.hit.util.MMULogger;

public class Process implements Callable<Boolean> {
	private int id;
	private MemoryManagementUnit mmu;
	private ProcessCycles processCycles;

	public Process(int id, MemoryManagementUnit mmu, ProcessCycles processCycles) {
		this.id = id;
		this.mmu = mmu;
		this.processCycles = processCycles;
	}

	@Override
	public Boolean call() throws Exception {
		try {
			for (ProcessCycle cycle : this.processCycles.getProcessCycles()) {	
				Object pagesObject[] = cycle.getPages().toArray();
				Long[] pagesIds = Arrays.copyOf(pagesObject, pagesObject.length, Long[].class);
				Page<byte[]>[] pages = this.mmu.getPages(pagesIds);
				for (int i = 0; i < pages.length; i++) {
					pages[i].setContent(cycle.getData().get(i));
					MMULogger.getInstance().write("GP:"+this.getId()+" "+Arrays.toString(pages[i].getContent()), Level.INFO);
				}

				Thread.sleep(cycle.getSleepMs());
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
