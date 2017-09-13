package com.hit.memoryunits;

import org.junit.Test;

import com.hit.processes.RunConfiguration;
public class MMUDriverTest {

	@Test
	public void test() {
		RunConfiguration rc = com.hit.driver.MMUDriver.readConfigurationFile();
	}

}
