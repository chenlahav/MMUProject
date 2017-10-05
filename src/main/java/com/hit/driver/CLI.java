package com.hit.driver;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Scanner;
import java.util.logging.Level;

import com.hit.util.MMULogger;
import com.hit.view.View;

public class CLI extends Observable implements Runnable,View{
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
		start();
	}
	
	@Override
	public void start() {
		String[] algoAndCapacity = null;

		//write("please press 'start' to start");
		String buffer = " ";
		
		while (!buffer.toLowerCase().equals(STOP)){
			write("please press 'start' to start");
			buffer = in.nextLine();
			if(buffer.equals(STOP))
				break;
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
				MMULogger.getInstance().write("CLI: not a valid command entered", Level.SEVERE);
				write("not a valid command \nplease enter valid algorithm and capacity");
				buffer = in.nextLine();
				algoAndCapacity = buffer.split(" ");
			}
			write("Thank you");
			MMULogger.getInstance().write("RC: "+algoAndCapacity[1], Level.INFO);
			setChanged();
			notifyObservers(algoAndCapacity);
		}
		System.out.println("stopped");
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
