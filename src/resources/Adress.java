package resources;

public class Adress {

	private String streetName;
	private int streetNumber;
	private int appartment;
	private int floor;
	private String otherInfo;
		
	public Adress() {
		
		this.streetName = "";
		this.streetNumber = 0;
		this.appartment = 0;
		this.floor = 0;
		this.otherInfo = "";
	}
	public Adress(String streetName, int streetNumber, int appartment, int floor, String otherInfo) {
	
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.appartment = appartment;
		this.floor = floor;
		this.otherInfo = otherInfo;
	}	
	
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public int getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}
	public int getAppartment() {
		return appartment;
	}
	public void setAppartment(int appartment) {
		this.appartment = appartment;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public String getOtherInfo() {
		return otherInfo;
	}
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}	
}
