package com.hit.memoryunits;

import static org.junit.Assert.*;

import java.io.IOException;import java.util.logging.Level;

import org.junit.Test;

import com.hit.util.MMULogger;


public class LoggerTest {

	@Test
	public void test() {
		try {
			MMULogger logger = MMULogger.getInstance();
			for(int i=0; i<3;i++)
				logger.write("test1", Level.INFO);
			assertEquals(0, 0);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
