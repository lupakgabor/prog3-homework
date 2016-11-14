package rent;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import main.DatabaseConnection;

public class RentStore extends AbstractTableModel {
	private List<Rent> rents = new ArrayList<Rent>();
		
	public RentStore(){
		DatabaseConnection db = DatabaseConnection.getInstance();
		List<String> fields = Arrays.asList("r.id", "CONCAT(CONCAT(cl.firstname , ' '),cl.lastname) AS clientId", "CONCAT(CONCAT(c.brand, '-'), c.type) AS carId", "date", "expired");
		ResultSet rs = db.selectData("rents r INNER JOIN cars c ON c.id = r.carId INNER JOIN clients cl ON cl.id = r.clientId", fields);
		try {
			while(rs.next()){
				addRent(
						rs.getInt("id"),
						rs.getString("clientId"), 
						rs.getString("carId"), 
						rs.getString("date"),
						rs.getString("expired")
					);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addRent(int id, String clientId, String carId, String date, String expired) {
		Rent newrent = new Rent(id, clientId, carId, date, expired);
		rents.add(newrent);
		super.fireTableRowsUpdated(0,rents.size());
	}
	
	@Override
	public int getRowCount() {
		return rents.size();
	}

	@Override
	public int getColumnCount() {
		return 4;
	}
	
	@Override
	public String getColumnName(int columnIndex){
		switch(columnIndex){
			case 0:
				return "Client";
			case 1:
				return "Car";
			case 2:
				return "Date";
			case 3:
				return "Expired";
			default:
				return "Unknown";
		}
	}
	
	@Override
	public boolean isCellEditable(int row, int col){
		if( col == 0 || col == 1 || col == 2){
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		 Rent rent = rents.get(rowIndex);
		 switch(columnIndex) {
			 case 0: return rent.getClientId();
			 case 1: return rent.getCarId();
			 case 2: return rent.getDate();
			 case 3: return rent.getExpired();
			 default: return "NaN";
		 }
	 } 
	
	@Override
	public void setValueAt(Object newValue, int rowIndex, int columnIndex){
		DatabaseConnection db = DatabaseConnection.getInstance();
		Rent rent = this.rents.get(rowIndex);
		String newVal = (String) newValue;
		switch(columnIndex){
		case 3:
			rent.setExpired(newVal);
			db.updateData(rent.getId(), "rents", "Expired", newVal);
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
