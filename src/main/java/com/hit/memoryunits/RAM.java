package com.hit.memoryunits;

import java.util.*;
import java.util.Map;
import java.util.HashMap;


public class RAM {
	private int initialCapacity;
	private Map<Long,Page<byte[]>> pagesMap;
	
	public RAM(int initialCapacity) {
		this.setInitialCapacity(initialCapacity);
		this.pagesMap = new HashMap<Long,Page<byte[]>>();
	}
	
	public void addPage(Page<byte[]> addPage){
		this.pagesMap.put(addPage.getPageId() , addPage);
	}
	
	public void addPages(Page<byte[]>[] addPages){
		for(int i=0;i<addPages.length;i++)
			this.pagesMap.put(addPages[i].getPageId() , addPages[i]);
	}

	public int getInitialCapacity() {
		return initialCapacity;
	}

	public void setInitialCapacity(int initialCapacity) {
		this.initialCapacity = initialCapacity;
	}
	
	public Page<byte[]> getPage(Long idPage){
		return this.pagesMap.get(idPage);
	}
	

	public Map<Long, Page<byte[]>> getPages(){
		return this.pagesMap;
	}
	
	public Page<byte[]>[] getPages(Long[] pageIds){
		Page<byte[]> pagesToReturn[] = new Page[pageIds.length];
		for(int i= 0; i<pageIds.length; i++)
		{
			pagesToReturn[i] = pagesMap.get(pageIds[i]);
		}
		return pagesToReturn;
	}
	
	public void removePage(Page<byte[]> removePage){
		this.pagesMap.remove(removePage.getPageId());
	}
	
	public void removePages(Page<byte[]>[] removePages){
		for(int i= 0; i<removePages.length; i++)
		{
			this.pagesMap.remove(removePages[i].getPageId());
		}
	}
	
	public void setPages(Map<Long,Page<byte[]>> pages){
		this.pagesMap = pages;
	}
}
