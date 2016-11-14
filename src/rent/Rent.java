package rent;

public class Rent {
	private int id;
	private String clientId;
	private String carId;
	private String date;
	private String expired;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getExpired() {
		return expired;
	}
	public void setExpired(String expired) {
		this.expired = expired;
	}
	public Rent(int id, String clientId, String carId, String date, String expired) {
		super();
		this.id = id;
		this.clientId = clientId;
		this.carId = carId;
		this.date = date;
		this.expired = expired;
	}
	
	
}
