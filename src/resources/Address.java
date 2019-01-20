package resources;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			this.text += " Details: " + otherInfo;
		}
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.otherInfo = otherInfo;
	}
	public Address(String streetName, int streetNumber, String building, String entrance, int appartment, int floor, String otherInfo) {
	
		this.text = "Str. " + streetName + " Nr. " + streetNumber + " Building " + building + ", Entrance " + entrance + ", Ap. " + appartment + " Fl. " + floor;
		if (!otherInfo.trim().equals("")) {
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
	 * Calls all methods for a address gathering
	 * @return A Address object
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
		
		Scanner keyboard = new Scanner(System.in);
		Address newAdress = new Address();
		int option = -1;
		do {
			System.out.println("  1 - Short Address (Street, Number and Details)");
			System.out.println("  2 - Long Address (Street, Number, Building, Entrance, Floor and Details)\n");
			System.out.print(">> ");
			option = keyboard.nextInt();
			keyboard.nextLine();
			switch (option) {
			case 1: {
				// Short Address form
				String streetName = "", otherInfo = "";
				int streetNumber = 0;

				System.out.print("Street Name: ");
				streetName = keyboard.nextLine();
				
				System.out.print("\nStreet Number: ");
				streetNumber = keyboard.nextInt();
				keyboard.nextLine();
				
				System.out.println("Enter one space to pass");
				System.out.print("Other Details: ");
				otherInfo = keyboard.nextLine();
				
				newAdress = new Address(streetName, streetNumber, otherInfo);
				option = -1;
				break;
			}
			case 2: {
				// Long Address form				
				String streetName = "", otherInfo = "", building = "", entrance = "";
				int streetNumber = 0, appartment = 0, floor = 0;

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
				
				newAdress = new Address(streetName, streetNumber, building, entrance, appartment, floor, otherInfo);
				option = -1;
				break;				
			}
			default: {
				option = 0;
				System.out.println("   Enter a valid option!");
				break;
			}
			}
		} while(option != -1);
		return newAdress;
	}
	
	/**
	 * Validates a Address object to contain valid data
	 * @return		True for a valid object.
	 * 				False for anything else.
	 */
	public static boolean validateAddress(Address newAddress) {
		
		boolean checks = false;
		if ( validateStreetName(newAddress.getStreetName()) && newAddress.getStreetNumber() > 0 )
		{
			checks = true;
		} else {
			System.out.println("   Address invalid!");
		}
		return checks;
	}
	
	/**
	 * Validates a street name
	 * @param streetName
	 * @return True for a street name starting with capital letter and no numbers
	 * 			False for anything else
	 */
	private static boolean validateStreetName(String streetName) {

		if(streetName.length()>=3)
		{
			Pattern UpperCase = Pattern.compile("[A-Z]");
			Pattern LowerCase = Pattern.compile("[a-z]");
			Pattern digit = Pattern.compile("[0-9]");

			Matcher hasUpperCase = UpperCase.matcher(streetName);
			Matcher hasLowerCase = LowerCase.matcher(streetName);
			Matcher hasDigit = digit.matcher(streetName);

			return hasUpperCase.find() && hasLowerCase.find() && !hasDigit.find();

		} else {
			return false;
		}
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
