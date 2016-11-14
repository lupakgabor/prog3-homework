package car;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CarListActionListener implements ActionListener {
	private JPanel parentPanel;
	
	public CarListActionListener(JPanel parentPanel){
		this.parentPanel = parentPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Listing cars in progress...");
		parentPanel.removeAll();
		parentPanel.setLayout(new BorderLayout());
		CarStore cars = new CarStore();
		JTable table = new JTable();
		table.setModel(cars);
		JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);        
        parentPanel.add(scrollPane, BorderLayout.CENTER);
        parentPanel.updateUI();
     }

}
