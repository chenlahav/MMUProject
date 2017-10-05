package com.hit.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;


public class MMULogger {

	private static MMULogger instance = null;
	public final static String DEFAULT_FILE_NAME = "logs/log.txt";
	private FileHandler handler;
	
	private MMULogger(){
		try {
			handler = new FileHandler(DEFAULT_FILE_NAME);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		handler.setFormatter(new OnlyMessageFormatter());
	}
	
	public static MMULogger getInstance()
	{
		if(instance==null){
			instance=new MMULogger();
		}
		return instance;
	}
	
	public synchronized void write(String command, Level level){
		handler.publish(new LogRecord(level, command));
		
	}
	
	public class OnlyMessageFormatter extends Formatter
	{
		public OnlyMessageFormatter() { super();}

		@Override
		public String format(LogRecord record) {
			return record.getMessage()+"\n";
		}
		
	}
}
