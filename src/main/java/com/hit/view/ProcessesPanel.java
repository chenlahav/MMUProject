package com.hit.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class ProcessesPanel extends JPanel implements ListSelectionListener{
	private MMUView view;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ProcessesPanel(int numOfProcesses, MMUView view) {
		this.view = view;
		DefaultListModel model = new DefaultListModel();
		JList list = new JList(model);
		JLabel label = new JLabel("Processes:");

		JScrollPane scrollPane = new JScrollPane(list);
		this.add(scrollPane, BorderLayout.CENTER);

		for (int i = 1; i <=numOfProcesses; i++)
			model.addElement("Process " + i);

		Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
		list.setBorder(lineBorder);
		list.setFont(new Font("Arial",Font.BOLD,16));
		label.setFont(new Font("Arial",Font.ITALIC,18));

		list.addListSelectionListener(this);
		this.add(label);
		this.add(scrollPane, BorderLayout.CENTER);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if(arg0.getValueIsAdjusting()==false){
			JList source = (JList) arg0.getSource();
			@SuppressWarnings("unchecked")
			List<Object> values = source.getSelectedValuesList();
			HashMap<String,Integer> processes = new HashMap<String,Integer>();
			for (int i = 0; i < values.size(); i++) {
				String processnum = (String) values.get(i);
				processes.put(processnum.substring(processnum.indexOf(" ")+1), Integer.parseInt(processnum.substring(processnum.indexOf(" ")+1)));
			}
			view.setProcessesSelected(processes);
		}
		
	}

}
