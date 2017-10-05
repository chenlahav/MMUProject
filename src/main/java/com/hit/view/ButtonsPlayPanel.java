package com.hit.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ButtonsPlayPanel extends JPanel implements ActionListener{
	protected JButton b1,b2;
	protected MMUView mainFrame;
	
	public ButtonsPlayPanel(MMUView mainFrame) {
		this.mainFrame = mainFrame;
		b1 = new JButton("PLAY");
		b1.setVerticalTextPosition(AbstractButton.CENTER);
		b1.setHorizontalTextPosition(AbstractButton.CENTER);
		b1.setActionCommand("pressedPLAY");
		b1.setFont(new Font("Arial",Font.BOLD,16));
		
		b2 = new JButton("PLAY ALL");
		b2.setVerticalTextPosition(AbstractButton.CENTER);
		b2.setHorizontalTextPosition(AbstractButton.CENTER);
		b2.setActionCommand("pressedPLAYALL");
		b2.setFont(new Font("Arial",Font.BOLD,16));
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		this.add(b1);
		this.add(b2);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(((JButton)arg0.getSource()).getText().equals("PLAY"))
		{
			this.mainFrame.playLog();
		}
		else if ("pressedPLAYALL".equals(arg0.getActionCommand()))
		{
			this.mainFrame.playAllLog();
		}
		
	}

}
