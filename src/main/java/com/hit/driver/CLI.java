package com.hit.driver;

import java.io.InputStream;
import java.io.OutputStream;

public class CLI implements Runnable{
	public static final String LRU = "LRU";
	public static final String NFU = "NFU";
	public static final String RANDOM = "RANDOM";
	public static final String START = "start";
	public static final String STOP = "stop";
	private InputStream in;
	private OutputStream out;

	public CLI(InputStream in, OutputStream out) {
		super();
		this.in = in;
		this.out = out;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public void write(String s){
		//TODO
	}

}
