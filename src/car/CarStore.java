package car;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.mysql.jdbc.ResultSet;

import client.Client;
import main.DatabaseConnection;

public class CarStore extends AbstractTableModel{
	private List<Car> cars = new ArrayList<Car>();

	public CarStore(){
		DatabaseConnection db = DatabaseConnection.getInstance();
		List<String> fields = Arrays.asList("id", "identifier", "brand", "type", "color", "caution", "demage");
		ResultSet rs = (ResultSet) db.selectData("cars", fields);
		try {
			while(rs.next()){
				addCar(
						rs.getInt("id"),
						rs.getString("identifier"), 
						rs.getString("brand"), 
						rs.getString("type"),
						rs.getString("color"),
						rs.getString("caution"),
						rs.getString("demage")
					);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void addCar(int id, String identifier, String brand, String type, String color, String caution,  String demage) {
		Car newCar = new Car(id, identifier, brand, type, color, caution, demage );
		cars.add(newCar);
	}

	@Override
	public int getRowCount() {
		return cars.size();
	}

	@Override
	public int getColumnCount() {
		return 6;
	}

	@Override
	public String getColumnName(int columnIndex){
		switch(columnIndex){
			case 0:
				return "Identifier";
			case 1:
				return "Brand";
			case 2:
				return "Type";
			case 3:
				return "Color";
			case 4:
				return "Cautione";
			case 5:
				return "Demage";
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
		 Car car = cars.get(rowIndex);
		 switch(columnIndex) {
			 case 0: return car.getIdentifier();
			 case 1: return car.getBrand();
			 case 2: return car.getType();
			 case 3: return car.getColor();
			 case 4: return car.getCaution();
			 case 5: return car.getDemage();
			 default: return "NaN";
		 }
	}

	@Override
	public void setValueAt(Object newValue, int rowIndex, int columnIndex){
		DatabaseConnection db = DatabaseConnection.getInstance();
		Car car = this.cars.get(rowIndex);
		String newVal = (String) newValue;
		switch(columnIndex){
		case 0:
			car.setIdentifier(newVal);
			db.updateData(car.getId(), "cars", "identifier", newVal);
			break;
		case 1:
			car.setBrand(newVal);
			db.updateData(car.getId(), "cars", "brand", newVal);
			break;
		case 2:
			car.setType(newVal);
			db.updateData(car.getId(), "cars", "type", newVal);
			break;
		case 3:
			car.setColor(newVal);
			db.updateData(car.getId(), "cars", "color", newVal);
			break;
		case 4:
			car.setCaution(newVal);
			db.updateData(car.getId(), "cars", "caution", newVal);
			break;
		case 5:
			car.setDemage(newVal);
			db.updateData(car.getId(), "cars", "demage", newVal);
			break;
		default:
			return;				
		}
	} 

}
