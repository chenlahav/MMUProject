package com.hit.view;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PageFaultReplacementAmountPanel extends JPanel{
	protected static JTextField PageFault;
	protected static JTextField PageReplacement;
	
	public PageFaultReplacementAmountPanel() {
		PageFault = new JTextField("0",2);
		PageReplacement = new JTextField("0",2);
		PageFault.setFont(new Font("Arial",Font.ITALIC,12));
		PageReplacement.setFont(new Font("Arial",Font.ITALIC,12));

		JLabel PageFaultLabel = new JLabel("Page Fault Amount");
		PageFaultLabel.setFont(new Font("Arial",Font.BOLD,14));
		JLabel PageReplacementLabel = new JLabel("Page Replacement Amount");
		PageReplacementLabel.setFont(new Font("Arial",Font.BOLD,14));
		
		this.add(PageFaultLabel);
		this.add(PageFault);
		this.add(PageReplacementLabel);
		this.add(PageReplacement);
	}

	public static int getPageReplacementCount() {
		return Integer.parseInt(PageReplacement.getText());
	}
	
	public static int getPageFaultCount() {
		return Integer.parseInt(PageFault.getText());
	}
	
	public static void setPageFaultCount(int count) {
		PageFault.setText(String.valueOf(count));
	}

	public static void setPageReplacementCount(int count) {
		PageReplacement.setText(String.valueOf(count));
	}

}
