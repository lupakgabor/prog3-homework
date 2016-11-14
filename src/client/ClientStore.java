package client;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import main.DatabaseConnection;

public class ClientStore extends AbstractTableModel  {

	private static final long serialVersionUID = 1L;
	private List<Client> clients = new ArrayList<Client>();
		
	public ClientStore(){
		System.out.println("Client store created!");
		DatabaseConnection db = DatabaseConnection.getInstance();
		List<String> fields = Arrays.asList("id", "firstname", "lastname", "identifier", "drivingLicense", "birthDate", "address");
		ResultSet rs = db.selectData("clients", fields);
		try {
			while(rs.next()){
				addClient(
						rs.getInt("id"),
						rs.getString("firstname"), 
						rs.getString("lastname"), 
						rs.getString("identifier"),
						rs.getString("drivingLicense"),
						rs.getString("birthDate"),
						rs.getString("address")
					);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addClient(int id, String firstname, String lastname, String identifier, String drivingLicense, String birthDate,  String address) {
		Client newClient = new Client(id, firstname, lastname, identifier, drivingLicense, birthDate,address );
		clients.add(newClient);
		super.fireTableRowsUpdated(0,clients.size());
	}
	
	@Override
	public int getRowCount() {
		return clients.size();
	}

	@Override
	public int getColumnCount() {
		return 6;
	}
	
	@Override
	public String getColumnName(int columnIndex){
		switch(columnIndex){
			case 0:
				return "Firstname";
			case 1:
				return "Lastname";
			case 2:
				return "Identifier";
			case 3:
				return "Driving license";
			case 4:
				return "Birth date";
			case 5:
				return "Address";
			default:
				return "Unknown";
		}
	}
	
	@Override
	public boolean isCellEditable(int row, int col){
		if( col == 0 || col == 1){
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		 Client client = clients.get(rowIndex);
		 switch(columnIndex) {
			 case 0: return client.getFirstname();
			 case 1: return client.getLastname();
			 case 2: return client.getIdentifier();
			 case 3: return client.getDrivingLicense();
			 case 4: return client.getBirthDate();
			 case 5: return client.getAddress();
			 default: return "NaN";
		 }
	 } 
	
	@Override
	public void setValueAt(Object newValue, int rowIndex, int columnIndex){
		DatabaseConnection db = DatabaseConnection.getInstance();
		Client client = this.clients.get(rowIndex);
		String newVal = (String) newValue;
		switch(columnIndex){
		case 0:
			client.setFirstname(newVal);
			db.updateData(client.getId(), "clients", "firstname", newVal);
			break;
		case 1:
			client.setLastname(newVal);
			db.updateData(client.getId(), "clients", "lastname", newVal);
			break;
		case 2:
			client.setIdentifier(newVal);
			db.updateData(client.getId(), "clients", "identifier", newVal);
			break;
		case 3:
			client.setDrivingLicense(newVal);
			db.updateData(client.getId(), "clients", "drivingLicense", newVal);
			break;
		case 4:
			client.setBirthDate(newVal);
			db.updateData(client.getId(), "clients", "birthDate", newVal);
			break;
		case 5:
			client.setAddress(newVal);
			db.updateData(client.getId(), "clients", "address", newVal);
			break;
		default:
			return;				
		}
	}
	
	@Override
	public Class<String> getColumnClass(int columnIndex){
		return String.class;
	}
	
}
