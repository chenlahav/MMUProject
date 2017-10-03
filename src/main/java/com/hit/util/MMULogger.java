package com.hit.util;

import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class MMULogger {

	public final static String DEFAULT_FILE_NAME = "log.txt";
	private FileHandler handler;
	
	private MMULogger(){
		//complete the rest
	}
	
	public synchronized void write(String command, Level level){
		//complete the rest
	}
	
	public class OnlyMessageFormatter extends Formatter
	{
		public OnlyMessageFormatter() { super();}

		@Override
		public String format(LogRecord record) {
			return record.getMessage();
		}
		
	}
}
