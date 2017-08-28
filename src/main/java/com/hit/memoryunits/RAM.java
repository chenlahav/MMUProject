package com.hit.memoryunits;

import java.util.*;

public class RAM {
	private int initialCapacity;
	
	public RAM(int initialCapacity) {
		this.setInitialCapacity(initialCapacity);
	}
	
	//TODO
	public void addPage(Page<byte[]> addPage){
		
	}
	
	//TODO
	public void addPages(Page<byte[]>[] addPages){
		
	}

	public int getInitialCapacity() {
		return initialCapacity;
	}

	public void setInitialCapacity(int initialCapacity) {
		this.initialCapacity = initialCapacity;
	}
	
	//TODO
	public Page<byte[]> getPage(Long idPage){
		return null;
	}
	
	//TODO
	public Map<Long, Page<byte[]>> getPages(){
		return null;
	}
	
	//TODO
	public Page<byte[]>[] getPages(Long[] pageIds){
		return null;
	}
	
	//TODO
	public void removePage(Page<byte[]> removePage){
	}
	
	//TODO
	public void removePages(Page<byte[]>[] removePages){
	}
	
	//TODO
	public void setPages(Map<Long,Page<byte[]>> pages){
	}
}
