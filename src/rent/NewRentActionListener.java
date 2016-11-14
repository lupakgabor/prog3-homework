package rent;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.util.Properties;
import java.util.TreeMap;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyStore.Entry;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import main.DatabaseConnection;

public class NewRentActionListener implements ActionListener {
	private JPanel parentPanel;
	private DatabaseConnection db;
	
	public NewRentActionListener(JPanel parentPanel){
		this.parentPanel = parentPanel;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		parentPanel.removeAll();
		parentPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		db = DatabaseConnection.getInstance();
		
		// Row title
		c.gridx = 0;
		c.gridy = 0;
		JLabel title = new JLabel("Add new rent");
		title.setFont(new Font("Serif", Font.BOLD, 20));
		parentPanel.add(title, c);
	    
	    // Row clientId
		c.gridy = 1;
		c.gridx = 0;
		parentPanel.add(new JLabel("Client:"), c);
		c.gridx = 1;
		TreeMap <String, String> clientsMap = new TreeMap<String, String>();
		JComboBox clientIdField = new JComboBox();
		List<String> clientFields = Arrays.asList("id", "firstname", "lastname", "identifier");
		ResultSet clients = db.selectData("clients", clientFields);
       	try {
			while(clients.next()){
			    String name = clients.getString("firstname") + " " + clients.getString("lastname") + "(" + clients.getString("identifier") + ")";;
			    clientIdField.addItem(name);
			    clientsMap.put(name, new Integer(clients.getInt("id")).toString());
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}   		
		parentPanel.add(clientIdField, c);

	    // Row carId
		c.gridy = 2;
		c.gridx = 0;
		parentPanel.add(new JLabel("Car:"), c);
	    TreeMap <String, String> carsMap = new TreeMap<String, String>();
		JComboBox carIdField = new JComboBox();
		List<String> carFields = Arrays.asList("id", "brand", "type", "identifier");
		ResultSet cars = db.selectData("cars", carFields);
       	try {
			while(cars.next()){
			    String name = cars.getString("brand") + "-" + cars.getString("type") + "(" + cars.getString("identifier") + ")";
			    carIdField.addItem(name);
			    carsMap.put(name, new Integer(cars.getInt("id")).toString());
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
	    c.gridx = 1;
	    parentPanel.add(carIdField, c);
	    
	    // Row date
		c.gridy = 3;
		c.gridx = 0;
		parentPanel.add(new JLabel("date:"), c);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
	    JTextField dateField = new JTextField(dateFormat.format(date));
	    dateField.setEditable(false);
	    dateField.setBackground(Color.decode("#e6e6e6"));
	    c.gridx = 1;
	    parentPanel.add(dateField, c);
	  
	    // Row expired
	    c.gridy = 5;
		c.gridx = 0;
		parentPanel.add(new JLabel("expired:"),c);
	    c.gridx = 1;
	    UtilDateModel model = new UtilDateModel();
	    Properties p = new Properties();
	    p.put("text.today", "Today");
	    p.put("text.month", "Month");
	    p.put("text.year", "Year");
	    JDatePanelImpl datePanel = new JDatePanelImpl(model,p);
	    JDatePickerImpl expiredField = new JDatePickerImpl(datePanel, new DateLabelFormatter());
	    parentPanel.add(expiredField,c);

	    // Row save
	    c.gridy = 6;
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
				if(!clientIdField.getSelectedItem().equals("") && !carIdField.getSelectedItem().equals("") && !dateField.getText().equals("")){
					List<String> fields = Arrays.asList("clientId", "carId", "date", "expired");
					String expired = new Integer(expiredField.getModel().getYear()).toString() + "-" +
							new Integer(expiredField.getModel().getMonth() + 1).toString() + "-" +  
							new Integer(expiredField.getModel().getDay()).toString();
					System.out.println(expired);
					List<String> values = Arrays.asList(
						clientsMap.get(clientIdField.getSelectedItem()),
						carsMap.get(carIdField.getSelectedItem()),
						dateField.getText(),
						expired
					);
					db.insertData("rents", fields, values);
					message.setBackground(Color.green);
					message.setText("Success!");
					carIdField.setBackground(Color.WHITE);
					clientIdField.setBackground(Color.WHITE);
					clientIdField.setBackground(Color.WHITE);
				} else {
					String required = "";
					if(clientIdField.getSelectedItem().equals("")){
						clientIdField.setBackground(Color.red);
						required += "clientId, ";
					} else {
						clientIdField.setBackground(Color.WHITE);
					}
					if(carIdField.getSelectedItem().equals("")){
						carIdField.setBackground(Color.red);
						required += "carId, ";
					} else {
						carIdField.setBackground(Color.WHITE);
					}
					if(dateField.getText().equals("")){
						dateField.setBackground(Color.red);
						required += "date, ";
					} else {
						dateField.setBackground(Color.WHITE);
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
	
	private int getKeyForValue(String value, TreeMap <Integer, String> data) {
	    for (java.util.Map.Entry<Integer, String> entry : data.entrySet()) {
	         if (entry.getValue().equals(value)) {
	             return entry.getKey();
	         }
	     }
	    return 0;
	}
	
	public class DateLabelFormatter extends AbstractFormatter {

	    private String datePattern = "yyyy-MM-dd";
	    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

	    @Override
	    public Object stringToValue(String text) throws ParseException {
	        return dateFormatter.parseObject(text);
	    }

	    @Override
	    public String valueToString(Object value) throws ParseException {
	        if (value != null) {
	            Calendar cal = (Calendar) value;
	            return dateFormatter.format(cal.getTime());
	        }

	        return "";
	    }

	}
}
