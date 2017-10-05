package com.hit.memoryunits;


import org.junit.Test;

import com.hit.view.MMUView;

public class MMUViewTest {

	@Test
	public void test() {
		MMUView mmuView = new MMUView();
		mmuView.start();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
