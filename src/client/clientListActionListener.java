package client;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;



public class clientListActionListener implements ActionListener {

	private JPanel parentPanel;
	
	public clientListActionListener(JPanel parentPanel){
		this.parentPanel = parentPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Listing in progress...");
		parentPanel.removeAll();
		parentPanel.setLayout(new BorderLayout());
		ClientStore clients = new ClientStore();
		JTable table = new JTable();
		table.setModel(clients);
		JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);        
        parentPanel.add(scrollPane, BorderLayout.CENTER);
        parentPanel.updateUI();
     }

}
