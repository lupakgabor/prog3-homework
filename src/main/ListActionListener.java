package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class ListActionListener implements ActionListener {

	private JPanel parentPanel;
	private AbstractTableModel data;
	
	public ListActionListener(JPanel parentPanel, AbstractTableModel data){
		this.parentPanel = parentPanel;
		this.data = data;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Listing in progress...");
		parentPanel.removeAll();
		parentPanel.setLayout(new BorderLayout());
		JTable table = new JTable();
		table.setModel(data);
		JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);        
        parentPanel.add(scrollPane, BorderLayout.CENTER);
        parentPanel.updateUI();
     }


}
