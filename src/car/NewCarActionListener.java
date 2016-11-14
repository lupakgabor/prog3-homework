package car;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;

import main.DatabaseConnection;

public class NewCarActionListener implements ActionListener {
	private JPanel parentPanel;
	
	public NewCarActionListener(JPanel parentPanel){
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
		JLabel title = new JLabel("Add new car");
		title.setFont(new Font("Serif", Font.BOLD, 20));
		parentPanel.add(title, c);
	    
	    // Row identifier
		c.gridy = 1;
		c.gridx = 0;
		parentPanel.add(new JLabel("Identifier:"), c);
		c.gridx = 1;
		JTextField identifierField = new JTextField(20);
		parentPanel.add(identifierField, c);

	    // Row brand
		c.gridy = 2;
		c.gridx = 0;
		parentPanel.add(new JLabel("Brand:"), c);
	    JTextField brandField = new JTextField();
	    c.gridx = 1;
	    parentPanel.add(brandField, c);
	    
	    // Row type
		c.gridy = 3;
		c.gridx = 0;
		parentPanel.add(new JLabel("Type:"), c);
	    JTextField typeField = new JTextField();
	    c.gridx = 1;
	    parentPanel.add(typeField, c);
	    
	    // Row color
	    c.gridy = 4;
		c.gridx = 0;
		parentPanel.add(new JLabel("Color:"),c);
		c.gridx = 1;
	    JTextField colorField = new JTextField();
	    parentPanel.add(colorField,c);

	    // Row caution
	    c.gridy = 5;
		c.gridx = 0;
		parentPanel.add(new JLabel("Caution:"),c);
	    c.gridx = 1;
	    JTextField cautionField = new JTextField();
	    parentPanel.add(cautionField,c);

	    // Row demage
	    c.gridy = 6;
		c.gridx = 0;
		parentPanel.add(new JLabel("Demage:"),c);
	    c.gridx = 1;
	    JTextField demageField = new JTextField();
	    parentPanel.add(demageField,c);
	    
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
				if(!identifierField.getText().equals("") && !brandField.getText().equals("") && !typeField.getText().equals("")){
					List<String> fields = Arrays.asList("identifier", "brand", "type", "color", "caution", "demage");
					List<String> values = Arrays.asList(
						identifierField.getText(),
						brandField.getText(),
						typeField.getText(),
						colorField.getText(),
						cautionField.getText(),
						demageField.getText()
					);
					DatabaseConnection db = DatabaseConnection.getInstance();
					db.insertData("cars", fields, values);
					message.setBackground(Color.green);
					message.setText("Success!");
					brandField.setBackground(Color.WHITE);
					typeField.setBackground(Color.WHITE);
					identifierField.setBackground(Color.WHITE);

					identifierField.setBackground(Color.WHITE);
				} else {
					String required = "";
					if(identifierField.getText().equals("")){
						identifierField.setBackground(Color.red);
						required += "identifier, ";
					} else {
						identifierField.setBackground(Color.WHITE);
					}
					if(brandField.getText().equals("")){
						brandField.setBackground(Color.red);
						required += "brand, ";
					} else {
						brandField.setBackground(Color.WHITE);
					}
					if(typeField.getText().equals("")){
						typeField.setBackground(Color.red);
						required += "type, ";
					} else {
						typeField.setBackground(Color.WHITE);
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
