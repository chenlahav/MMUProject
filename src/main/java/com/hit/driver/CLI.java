package com.hit.driver;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class CLI implements Runnable{
	public static final String LRU = "LRU";
	public static final String NFU = "NFU";
	public static final String RANDOM = "RANDOM";
	public static final String START = "start";
	public static final String STOP = "stop";
	private Scanner in;
	private PrintWriter out;

	public CLI(InputStream in, OutputStream out) {
		super();
		this.in = new Scanner(in);
		this.out = new PrintWriter(out);
	}
	
	@Override
	public void run() {
		String[] algoAndCapacity = null;

		write("please press 'start' to start");
		String buffer = in.nextLine();
		
		while (!buffer.toLowerCase().equals(STOP)){
			while(!buffer.toLowerCase().equals(START)){
				write("not a valid command \nplease enter 'start' to start");
				buffer = in.nextLine();
			}
		
			do{
				write("please enter required algorithm and RAM capacity");
				buffer = in.nextLine();
				algoAndCapacity = buffer.split(" ");
			}while(!(algoAndCapacity.length == 2));

			while((!is_valid_algo(algoAndCapacity[0])) || (!is_integer(algoAndCapacity[1]))){
				write("not a valid command \nplease enter valid algorithm and capacity");
				buffer = in.nextLine();
				algoAndCapacity = buffer.split(" ");
			}
			try {
				MMUDriver.start(algoAndCapacity);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		write("Thank you");
		in.close();
		out.close();
		return;
	}
	
	public void write(String s){	
		out.println(s);
		out.flush();
	}
	
	public boolean is_valid_algo(String s){
		if(s.toUpperCase().equals(LRU)|| s.toUpperCase().equals(NFU) || s.toUpperCase().equals(RANDOM)){
			return true;
		}
		return false;
	}
	
	public boolean is_integer(String s){
		try{
			Integer.parseInt(s);
			return true;
		}catch (Exception e){}
		return false;
	}

}
