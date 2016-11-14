package rent;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class RentListActionListener implements ActionListener {
	private JPanel parentPanel;
	
	public RentListActionListener(JPanel parentPanel){
		this.parentPanel = parentPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Listing rents in progress...");
		parentPanel.removeAll();
		parentPanel.setLayout(new BorderLayout());
		RentStore rents = new RentStore();
		JTable table = new JTable();
		table.setModel(rents);
		JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);        
        parentPanel.add(scrollPane, BorderLayout.CENTER);
        parentPanel.updateUI();
     }

}