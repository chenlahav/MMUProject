package com.hit.memoryunits;

import org.junit.Test;

import com.hit.driver.CLI;

public class Clitest {

	@Test
	public void test() {
		CLI cli = new CLI(System.in,System.out);
		cli.run();
	}

}
