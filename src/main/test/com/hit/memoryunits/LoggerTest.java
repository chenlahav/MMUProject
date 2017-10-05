package com.hit.memoryunits;

import static org.junit.Assert.*;

import java.util.logging.Level;

import org.junit.Test;

import com.hit.util.MMULogger;


public class LoggerTest {

	@Test
	public void test() {
		MMULogger.getInstance().write("test1", Level.INFO);
		assertEquals(0, 0);
	}

}
