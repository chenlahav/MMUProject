package com.hit.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.hit.driver.CLI;
import com.hit.model.MMUModel;
import com.hit.model.Model;
import com.hit.view.MMUView;
import com.hit.view.View;

public class MMUController implements Controller,Observer {
	private Model model;
	private View view;

	public MMUController(Model model , View view) {
		this.model = model;
		this.view = view;
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof CLI){
			String[] configuration =(String[])arg;
			((MMUModel)model).setConfiguration(Arrays.asList(configuration));
			model.start();
		}
		if(o instanceof Model){
			@SuppressWarnings("unchecked")
			List<String> logFile = (List<String>)arg;
			((MMUView)view).setLogFile(logFile);
			view.start();
		}else if(o instanceof View){
			//TODO
		}
		
	}

}
