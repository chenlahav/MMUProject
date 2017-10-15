package com.hit.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.hit.util.MMULogger;

public class MMUView extends Observable implements View {
	private List<String> logFile;
	private int numOfProcesses;
	private HashMap<String,Integer> processesSelected;
	private int i;
	
	public MMUView() {
	}

	private void createAndShowGUI() {
		//Create and set up the window.
		JFrame Mframe = new JFrame("MMU Simulator");
		Mframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Mframe.setPreferredSize(new Dimension(1000,500));
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim =tk.getScreenSize();
		int xPos = (dim.width/4)-(Mframe.getWidth()/4);
		int yPos = (dim.height/4)-(Mframe.getHeight()/4);
		Mframe.setLocation(xPos, yPos);
		
		
		Container container = Mframe.getContentPane();
		
		TablePanel tablePanel = new TablePanel();   	//Table panel 
		//tablePanel.setBounds(10, 10, 670, 175);
		container.add(tablePanel,BorderLayout.CENTER);
		i=1;
		setNumOfProcesses(logFile.get(i));
		i++;
		
		ProcessesPanel processesPanel = new ProcessesPanel(numOfProcesses,this);    	//Processes list panel 
		processesPanel.setBounds(700, 100, 150, 200);
		container.add(processesPanel,BorderLayout.EAST);	
		
		ButtonsPlayPanel playpanel = new ButtonsPlayPanel(this);   			//Play and Play all buttons
		playpanel.setBounds(30, 200, 200, 200);
		playpanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		container.add(playpanel,BorderLayout.SOUTH);
		
		PageFaultReplacementAmountPanel PFRFPanel = new PageFaultReplacementAmountPanel(); // PF/PR counters panel 
		//PFRFPanel.setBounds(690,50,200,150);
		container.add(PFRFPanel,BorderLayout.EAST);
		
		Mframe.pack();
		Mframe.setVisible(true);
	}
	
	@Override
	public void start() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
	public void setLogFile(List<String> log){
		this.logFile =log;
	}
	
	public void setNumOfProcesses(String numOfProcesses){
		String s = numOfProcesses.substring(3);
		this.numOfProcesses = Integer.parseInt(s);
	}
	
	public void setProcessesSelected(HashMap<String,Integer> processesSelected){
		this.processesSelected = processesSelected;
	}
	
	public void playLog(){
		if (i==logFile.size()){
			System.out.println("No more commands");
			return;
		}
		if(processesSelected==null){
			System.out.println("No Process was selected");
			return;
		}
		if (logFile.get(i).contains("PF")){		//page fault
			PageFaultReplacementAmountPanel.setPageFaultCount(PageFaultReplacementAmountPanel.getPageFaultCount()+1);  //changing the Page-Fault count
			
		}else if (logFile.get(i).contains("PR")){	//page replacement
			PageFaultReplacementAmountPanel.setPageReplacementCount(PageFaultReplacementAmountPanel.getPageReplacementCount()+1);  //changing the Page-replacement count
			String pagetoremove = logFile.get(i).substring(logFile.get(i).indexOf(" ")+1,logFile.get(i).indexOf("MTR")-1);//Extracting the MTH page
			TablePanel.editColumn(Integer.parseInt(pagetoremove)," ","0,0,0,0,0");
			
			
		}else if(logFile.get(i).contains("GP")){	//get pages
			String processnum = logFile.get(i).substring(logFile.get(i).indexOf("P")+3,logFile.get(i).indexOf(" ")); //extracting the process number from the string
			String pagenum = logFile.get(i).substring(logFile.get(i).indexOf(" ")+1,logFile.get(i).indexOf("[")-1);  //extracting the page number from the string
			if(processesSelected.containsKey(processnum)){	//if it is a process the user wish to see
				TablePanel.editColumn(Integer.parseInt(pagenum),pagenum,logFile.get(i).substring(logFile.get(i).indexOf("[")+1, logFile.get(i).indexOf("]")));
			}
				
		}
		i++;
	}
	
	public void playAllLog() {
		if(i==logFile.size()){
			System.out.println("No more commands");
			return;
		}
		if(processesSelected==null){
			System.out.println("No Process was selected");
			return;
		}
		for(;i<logFile.size();i++) {
			if (logFile.get(i).contains("PF")){		//page fault
				PageFaultReplacementAmountPanel.setPageFaultCount(PageFaultReplacementAmountPanel.getPageFaultCount()+1);  //changing the Page-Fault count
				
			}else if (logFile.get(i).contains("PR")){	//page replacement
				PageFaultReplacementAmountPanel.setPageReplacementCount(PageFaultReplacementAmountPanel.getPageReplacementCount()+1);  //changing the Page-replacement count
				String pagetoremove = logFile.get(i).substring(logFile.get(i).indexOf(" ")+1,logFile.get(i).indexOf("MTR")-1);//Extracting the MTH page
				TablePanel.editColumn(Integer.parseInt(pagetoremove)," ","0,0,0,0,0");
				
				
			}else if(logFile.get(i).contains("GP")){	//get pages
				String processnum = logFile.get(i).substring(logFile.get(i).indexOf("P")+3,logFile.get(i).indexOf(" ")); //extracting the process number from the string
				String pagenum = logFile.get(i).substring(logFile.get(i).indexOf(" ")+1,logFile.get(i).indexOf("[")-1);  //extracting the page number from the string
				if(processesSelected.containsKey(processnum)){	//if it is a process the user wish to see
					TablePanel.editColumn(Integer.parseInt(pagenum),pagenum,logFile.get(i).substring(logFile.get(i).indexOf("[")+1, logFile.get(i).indexOf("]")));
				}
					
			}
		}

	}
}
