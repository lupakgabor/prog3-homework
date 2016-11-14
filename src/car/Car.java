package car;

public class Car {
	private int id;
	private String identifier;
	private String brand;
	private String type;
	private String color;
	private String caution;
	private String demage;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getCaution() {
		return caution;
	}
	public void setCaution(String caution) {
		this.caution = caution;
	}
	public String getDemage() {
		return demage;
	}
	public void setDemage(String demage) {
		this.demage = demage;
	}
	public Car(int id, String identifier, String brand, String type, String color, String caution, String demage) {
		this.identifier = identifier;
		this.brand = brand;
		this.type = type;
		this.color = color;
		this.caution = caution;
		this.demage = demage;
	}
	
	
}
