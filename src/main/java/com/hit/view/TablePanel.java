package com.hit.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

@SuppressWarnings("serial")
public class TablePanel extends JPanel{
	public static JTable table;
	
	public TablePanel() {
		this.setLayout(new BorderLayout());
		table= new JTable(5,25);
		table.setFont(new Font("Arial",Font.BOLD,10));
		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
		JTableHeader th = table.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		
		for(int i=0;i<25;i++)
		{
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(" ");
			th.repaint();
			for(int j=0;j<5;j++)
			{
				table.setValueAt(0, j, i);
			}
		}

		table.setRowHeight(30);

		JScrollPane scrollPane =  new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension(670,300));
		this.add(scrollPane);
	}

	@SuppressWarnings("resource")
	public static void editColumn(int column, String columnValue, String data) {
		JTableHeader th = table.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		TableColumn tc = tcm.getColumn(column);
		tc.setHeaderValue(columnValue);
		th.repaint();
		Scanner sc = new Scanner(data);
		sc.useDelimiter(",");
		for(int j=0;j<5;j++)
		{
			table.setValueAt(sc.next(), j, column);
		}
	}

}
