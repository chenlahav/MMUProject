package com.hit.memoryunits;
import java.io.IOException;
import com.hit.algorithm.*;

public class MemoryManagementUnit {
	private IAlgoCache<Long, Long> algo;
	private RAM ram;
	
	public MemoryManagementUnit(int ramCapacity, IAlgoCache<Long, Long> algo){
		this.algo=algo;
		this.ram = new RAM(ramCapacity);
	}

	public Page<byte[]>[] getPages(Long[] pageIds) throws IOException
	{
		HardDisk hd = HardDisk.getInstance();
		@SuppressWarnings("unchecked")
		Page<byte[]>[] pageToReturn = new Page[pageIds.length];
		Page<byte[]> moveToHdPage = null;
		Long idPageReplace = null;
		
		for(int i=0; i<pageIds.length;i++)
		{
			if(algo.getElement(pageIds[i]) == null)
			{ 
				//if RAM is not full
				if(ram.getUsage()<=ram.getInitialCapacity())
				{
					algo.putElement(pageIds[i],pageIds[i]);
					ram.addPage(hd.pageFault(pageIds[i]));
				}
				
				//else: Do logic of full RAM
				else
				{
					idPageReplace = algo.putElement(pageIds[i],pageIds[i]);
					moveToHdPage = ram.getPage(idPageReplace);
					ram.addPage(hd.pageReplacement(moveToHdPage, pageIds[i]));
				}
			}
			pageToReturn[i] = ram.getPage(pageIds[i]);
		}
		return pageToReturn;
	}
}