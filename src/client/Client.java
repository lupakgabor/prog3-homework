package client;

public class Client {
	private int id;
	private String firstname;
	private String lastname;
	private String identifier;
	private String drivingLicense;
	private String birthDate;
	private String address;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getDrivingLicense() {
		return drivingLicense;
	}
	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Client(int id, String firstname, String lastname, String identifier, String drivingLicense, String birthDate, String address) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.identifier = identifier;
		this.drivingLicense = drivingLicense;
		this.birthDate = birthDate;
		this.address = address;
	}

	
}
