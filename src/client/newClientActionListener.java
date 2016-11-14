package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.DatabaseConnection;

public class NewClientActionListener implements ActionListener {
	private JPanel parentPanel;
	
	public NewClientActionListener(JPanel parentPanel){
		this.parentPanel = parentPanel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		parentPanel.removeAll();
		parentPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		// Row title
		c.gridx = 0;
		c.gridy = 0;
		JLabel title = new JLabel("Add new client");
		title.setFont(new Font("Serif", Font.BOLD, 20));
		parentPanel.add(title, c);
	    
	    // Row firstname
		c.gridy = 1;
		c.gridx = 0;
		parentPanel.add(new JLabel("Firstname:"), c);
		c.gridx = 1;
		JTextField fistnameField = new JTextField(20);
		parentPanel.add(fistnameField, c);

	    // Row lastname
		c.gridy = 2;
		c.gridx = 0;
		parentPanel.add(new JLabel("Lastname:"), c);
	    JTextField lastnameField = new JTextField();
	    c.gridx = 1;
	    parentPanel.add(lastnameField, c);
	    
	    // Row Identifier
		c.gridy = 3;
		c.gridx = 0;
		parentPanel.add(new JLabel("Identifier:"), c);
	    JTextField identifierField = new JTextField();
	    c.gridx = 1;
	    parentPanel.add(identifierField, c);
	    
	    // Row drivingLicense
	    c.gridy = 4;
		c.gridx = 0;
		parentPanel.add(new JLabel("Driving license:"),c);
		c.gridx = 1;
	    JTextField drivingLicenseField = new JTextField();
	    parentPanel.add(drivingLicenseField,c);

	    // Row birthDate
	    c.gridy = 5;
		c.gridx = 0;
		parentPanel.add(new JLabel("Birth date:"),c);
	    c.gridx = 1;
	    JTextField birthDateField = new JTextField();
	    parentPanel.add(birthDateField,c);

	    // Row address
	    c.gridy = 6;
		c.gridx = 0;
		parentPanel.add(new JLabel("Address:"),c);
	    c.gridx = 1;
	    JTextField addressField = new JTextField();
	    parentPanel.add(addressField,c);
	    
	    // Row save
	    c.gridy = 7;
		JButton saveButton = new JButton("Save");
		c.gridx = 1;
		JLabel message = new JLabel();
		message.setForeground(Color.darkGray);
		parentPanel.add(message, c);
		message.setOpaque(true);
		message.setHorizontalAlignment(SwingConstants.CENTER);
		saveButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!fistnameField.getText().equals("") && !lastnameField.getText().equals("") && !identifierField.getText().equals("")){
					List<String> fields = Arrays.asList("firstname", "lastname", "identifier", "drivingLicense", "birthDate", "address");
					List<String> values = Arrays.asList(
						fistnameField.getText(),
						lastnameField.getText(),
						identifierField.getText(),
						drivingLicenseField.getText(),
						birthDateField.getText(),
						addressField.getText()
					);
					DatabaseConnection db = DatabaseConnection.getInstance();
					db.insertData("clients", fields, values);
					message.setBackground(Color.green);
					message.setText("Success!");
					lastnameField.setBackground(Color.WHITE);
					identifierField.setBackground(Color.WHITE);
					fistnameField.setBackground(Color.WHITE);

					fistnameField.setBackground(Color.WHITE);
				} else {
					String required = "";
					if(fistnameField.getText().equals("")){
						fistnameField.setBackground(Color.red);
						required += "Fistname, ";
					} else {
						fistnameField.setBackground(Color.WHITE);
					}
					if(lastnameField.getText().equals("")){
						lastnameField.setBackground(Color.red);
						required += "Lastname, ";
					} else {
						lastnameField.setBackground(Color.WHITE);
					}
					if(identifierField.getText().equals("")){
						identifierField.setBackground(Color.red);
						required += "Identifier, ";
					} else {
						identifierField.setBackground(Color.WHITE);
					}
					required = required.substring(0, required.length()-2);
					message.setBackground(Color.red);
					message.setText( required + " required!");
				}
			}
			
		});
		c.gridx = 0;
		parentPanel.add(saveButton, c);
	    
		parentPanel.updateUI();
	}

}