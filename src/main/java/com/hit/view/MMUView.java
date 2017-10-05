package com.hit.view;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;


import javax.swing.JFrame;

public class MMUView extends Observable implements View {
	private List<String> logFile;
	private int numOfProcesses;
	private HashMap<String,Integer> processesSelected;
	private int i;
	
	public MMUView() {
		// TODO Auto-generated constructor stub
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
		tablePanel.setBounds(10, 10, 670, 175);
		container.add(tablePanel);
		
		ProcessesPanel processesPanel = new ProcessesPanel(numOfProcesses,this);    	//Processes list panel 
		processesPanel.setBounds(700, 100, 150, 200);
		container.add(processesPanel);
		i=0;
		
		ButtonsPlayPanel playpanel = new ButtonsPlayPanel(this);   			//Play and Play all buttons
		playpanel.setBounds(30, 200, 200, 200);
		playpanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		container.add(playpanel);
		
		PageFaultReplacementAmountPanel PFRFPanel = new PageFaultReplacementAmountPanel(); // PF/PR counters panel 
		PFRFPanel.setBounds(690,50,200,150);
		container.add(PFRFPanel);
		
		Mframe.pack();
		Mframe.setVisible(true);
	}
	
	public static void main(String[] args) {
		MMUView mf = new MMUView();
		mf.setNumOfProcesses(5);
		mf.start();

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
	
	public void setNumOfProcesses(int numOfProcesses){
		this.numOfProcesses = numOfProcesses;
	}
	
	public void setProcessesSelected(HashMap<String,Integer> processesSelected){
		this.processesSelected = processesSelected;
	}
	
	public void playLog()
	{	
		if(i == numOfProcesses){
			System.out.println("No more commands");//need to be change to a panel!!!!!!!
		}
		else if(processesSelected==null)
			System.out.println("No Process was selected");
		else
		{
			if(logFile.get(i).contains("GP")) //if there was a Get Page line
			{
				String processnum = logFile.get(i).substring(logFile.get(i).indexOf("P")+3,logFile.get(i).indexOf(" ")); //extracting the process number from the string
				String pagenum = logFile.get(i).substring(logFile.get(i).indexOf(" ")+1,logFile.get(i).indexOf("["));  //extracting the page number from the string
				if(processesSelected.containsKey(processnum))//if it is a process the user wish to see
				{
					TablePanel.editColumn(Integer.parseInt(pagenum),pagenum,logFile.get(i).substring(logFile.get(i).indexOf("[")+1, logFile.get(i).indexOf("]")));
				}
			}
			else if(logFile.get(i).contains("PR"))
			{
				PageFaultReplacementAmountPanel.setPageReplacementCount(PageFaultReplacementAmountPanel.getPageReplacementCount()+1);  //incrementing the Page-Replacement count
				String pagetoremove = logFile.get(i).substring(logFile.get(i).indexOf(" ")+1,logFile.get(i).indexOf("MTR")-1);//Extracting the MTH page
				String pageinsidetable = (String) TablePanel.table.getTableHeader().getColumnModel().getColumn(Integer.parseInt(pagetoremove)).getHeaderValue();  //getting the value at that place on the table
				if(pagetoremove.equals(pageinsidetable)) //if the page is inside the table , we will clear it's column.
				{
					TablePanel.editColumn(Integer.parseInt(pagetoremove)," ","0,0,0,0,0");
				}

			}
			else if(logFile.get(i).contains("PF"))
			{PageFaultReplacementAmountPanel.setPageFaultCount(PageFaultReplacementAmountPanel.getPageFaultCount()+1);}  //changing the Page-Fault count

			i++;
		}
	}
	
	public void playAllLog()
	{
		if(i==logFile.size())
			System.out.println("No more commands");//need to be change to a panel!!!!!!!
		else if(processesSelected==null)
			System.out.println("No Process was selected");
		else
		{
			for(;i<logFile.size();i++)
			{
				if(logFile.get(i).contains("GP")) //if there was a Get Page line
				{
					String processnum = logFile.get(i).substring(logFile.get(i).indexOf("P")+3,logFile.get(i).indexOf(" ")); //extracting the process number from the string
					String pagenum = logFile.get(i).substring(logFile.get(i).indexOf(" ")+1,logFile.get(i).indexOf("["));  //extracting the page number from the string
					if(processesSelected.containsKey(processnum))//if it is a process the user wish to see
					{
						TablePanel.editColumn(Integer.parseInt(pagenum),pagenum,logFile.get(i).substring(logFile.get(i).indexOf("[")+1, logFile.get(i).indexOf("]")));
					}
				}
				if(logFile.get(i).contains("PR"))
				{
					PageFaultReplacementAmountPanel.setPageReplacementCount(PageFaultReplacementAmountPanel.getPageReplacementCount()+1);  //changing the Page-Replacement count
					String pagetoremove = logFile.get(i).substring(logFile.get(i).indexOf(" ")+1,logFile.get(i).indexOf("MTR")-1);//Extracting the MTH page
					String pageinsidetable = (String) TablePanel.table.getTableHeader().getColumnModel().getColumn(Integer.parseInt(pagetoremove)).getHeaderValue();  //getting the value at that place on the table
					if(pagetoremove.equals(pageinsidetable)) //if the page is inside the table , we will clear it's column.
					{
						TablePanel.editColumn(Integer.parseInt(pagetoremove)," ","0,0,0,0,0");
					}

				}
				else if(logFile.get(i).contains("PF"))
				{PageFaultReplacementAmountPanel.setPageFaultCount(PageFaultReplacementAmountPanel.getPageFaultCount()+1);}

			}
		}

	}

}
