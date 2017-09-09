package com.hit.processes;

import java.util.List;

public class ProcessCycle {
	private List<Long> pages;
	private int sleepMs;
	private List<byte[]> data;
	
	public ProcessCycle(List<Long> pages, int sleepMs, List<byte[]> data) {
		super();
		this.pages = pages;
		this.sleepMs = sleepMs;
		this.data = data;
	}

	public List<Long> getPages() {
		return pages;
	}

	public void setPages(List<Long> pages) {
		this.pages = pages;
	}

	public int getSleepMs() {
		return sleepMs;
	}

	public void setSleepMs(int sleepMs) {
		this.sleepMs = sleepMs;
	}

	public List<byte[]> getData() {
		return data;
	}

	public void setData(List<byte[]> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ProcessCycle [pages=" + pages + ", sleepMs=" + sleepMs + ", data=" + data + "]";
	}
}
