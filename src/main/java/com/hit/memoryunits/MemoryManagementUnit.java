package com.hit.memoryunits;
import java.io.IOException;
import java.lang.*;
import com.hit.algorithm.*;

public class MemoryManagementUnit {
	//private int ramCapacity;
	private IAlgoCache<Long, Long> algo;
	private RAM ram;
	
	public MemoryManagementUnit(int ramCapacity, IAlgoCache<Long, Long> algo){
//		this.ramCapacity=ramCapacity;
		this.algo=algo;
		this.ram = new RAM(ramCapacity);
	}
	//TODO
	public Page<byte[]>[] getPages(Long[] pageIds) throws IOException
	{
		HardDisk hd = HardDisk.getInstance();
		Page<byte[]>[] pageToReturn = new Page[pageIds.length];
		Page<byte[]> moveToHdPage = null;
		Long idPage = null;
		
		for(int i=0; i<pageIds.length;i++)
		{
			if(algo.getElement(pageIds[i]) == null)
			{ 
				//if RAM is not full
				if(ram.getUsage()<=ram.getInitialCapacity())
					ram.addPage(hd.pageFault(pageIds[i]));
				
				//else: Do logic of full RAM
				else
				{
					idPage = (algo.putElement(pageIds[i],pageIds[i]));
					moveToHdPage = ram.getPage(idPage);
					ram.addPage(hd.pageReplacement(moveToHdPage, pageIds[i]));
				}
			}
				pageToReturn[i] = ram.getPage(pageIds[i]);
		}
		return null;
	}
}