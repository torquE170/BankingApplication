package resources;

import java.util.Scanner;

public class Address {

	private String text;
	private String streetName;
	private int streetNumber;
	private String building;
	private String entrance;
	private int appartment;
	private int floor;
	private String otherInfo;
	
	public Address() {
		
		this.text = "";
		this.streetName = "";
		this.streetNumber = 0;
		this.appartment = 0;
		this.floor = 0;
		this.otherInfo = "";
	}
	public Address(String streetName, int streetNumber, String otherInfo) {

		this.text = "Str. " + streetName + " Nr. " + Integer.toString(streetNumber);
		if (!otherInfo.trim().equals("")) {
			this.text += " Details " + otherInfo;
		}
		
	}
	public Address(String streetName, int streetNumber, String building, String entrance, int appartment, int floor, String otherInfo) {
	
		this.text = "Str. " + streetName + " Nr. " + streetNumber + " Building " + building + ", Entrance " + entrance + ", Ap. " + appartment + " Fl. " + floor;
		if (!otherInfo.equals("")) {
			this.text += " Details: " + otherInfo;
		}
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.building = building;
		this.entrance = entrance;
		this.appartment = appartment;
		this.floor = floor;
		this.otherInfo = otherInfo;
	}
	
	/**
	 * Calls all methods for a valid address gathering
	 * @return
	 */
	public static Address addAddress() {
		
		Address newAdress = enterAdressData();
		return newAdress;
	}
	
	/**
	 * Gathers address info in order for a Customer registration
	 * @return a Address object
	 */
	private static Address enterAdressData() {
		
		String streetName = "", otherInfo = "", building = "", entrance = "";
		int streetNumber = 0, appartment = 0, floor = 0;
		Address newAdress = new Address();
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Street Name: ");
		streetName = keyboard.nextLine();
		
		System.out.print("\nStreet Number: ");
		streetNumber = keyboard.nextInt();
		keyboard.nextLine();
				
		System.out.print("\nBuilding Number: ");
		building = keyboard.nextLine();
		
		System.out.print("\nEntrance Number: ");
		entrance = keyboard.nextLine();
		
		System.out.print("\nAppartment Number: ");
		appartment = keyboard.nextInt();
		keyboard.nextLine();
		
		System.out.println("\n1st floor is ground floor!");
		System.out.print("Floor: ");
		floor = keyboard.nextInt();
		keyboard.nextLine();
		
		System.out.print("Other Details: ");
		otherInfo = keyboard.nextLine();
		
		if (appartment == 0) {
			newAdress = new Address(streetName, streetNumber, otherInfo);
		} else if (floor != 0) {
			newAdress = new Address(streetName, streetNumber, building, entrance, appartment, floor, otherInfo);
		}
		return newAdress;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
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
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getEntrance() {
		return entrance;
	}
	public void setEntrance(String entrance) {
		this.entrance = entrance;
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
