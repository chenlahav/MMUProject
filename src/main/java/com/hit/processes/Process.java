package com.hit.processes;

import java.util.concurrent.Callable;

import com.hit.memoryunits.MemoryManagementUnit;
import com.hit.memoryunits.Page;

public class Process implements Callable<Boolean>{
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
		try{
			for(ProcessCycle cycle : this.processCycles.getProcessCycles()){
				Long[] pagesIds =(Long[]) cycle.getPages().toArray();
				Page<byte[]>[] pages = this.mmu.getPages(pagesIds);
				int i=0;
				for (Page<byte[]> page : pages){
					page.setContent(cycle.getData().get(i));
					i++;
				}
				
				Thread.sleep(cycle.getSleepMs());
			}
			return true;
		}catch(Exception e){
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
