package com.hit.memoryunits;
import java.io.IOException;
import java.lang.*;
import com.hit.algorithm.*;

public class MemoryManagementUnit {
	private int ramCapacity;
	private IAlgoCache<Long, Long> algo;
	
	public MemoryManagementUnit(int ramCapacity, IAlgoCache<Long, Long> algo){
		this.ramCapacity=ramCapacity;
		this.algo=algo;
	}
	
	//TODO
	public Page<byte[]>[] getPages(Long[] pageIds) throws IOException{
		return null;
	}
}
