package client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class newClientActionListener implements ActionListener {
	private JPanel parentPanel;
	
	public newClientActionListener(JPanel parentPanel){
		this.parentPanel = parentPanel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		parentPanel.removeAll();
		parentPanel.updateUI();
	}

}
