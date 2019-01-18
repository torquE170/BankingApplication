package resources;

import java.util.Scanner;

public class Adress {

	private String text;
	private String streetName;
	private int streetNumber;
	private int appartment;
	private int floor;
	private String otherInfo;
	
	public Adress() {
		
		this.text = "";
		this.streetName = "";
		this.streetNumber = 0;
		this.appartment = 0;
		this.floor = 0;
		this.otherInfo = "";
	}
	public Adress(String streetName, int streetNumber, String otherInfo) {

		this.text = "Str. " + streetName + " Nr. " + Integer.toString(streetNumber);
		if (!otherInfo.trim().equals("")) {
			this.text += " Details " + otherInfo;
		}
		
	}
	public Adress(String streetName, int streetNumber, int appartment, int floor, String otherInfo) {
	
		this.text = "Str. " + streetName + " Nr. " + streetNumber + " Ap. " + appartment + " Fl. " + floor;
		if (!otherInfo.equals("")) {
			this.text += " Details " + otherInfo;
		}
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.appartment = appartment;
		this.floor = floor;
		this.otherInfo = otherInfo;
	}
	
	public static Adress addAdress() {
		
		Adress newAdress = enterAdressData();
		return newAdress;
	}
	
	private static Adress enterAdressData() {
		
		String streetName = "", otherInfo = "";
		int streetNumber = 0, appartment = 0, floor = 0;
		Adress newAdress = new Adress();
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Street Name: ");
		streetName = keyboard.nextLine();
		
		System.out.print("\nStreet Number: ");
		streetNumber = keyboard.nextInt();
				
		System.out.print("\nAppartment Number: ");
		appartment = keyboard.nextInt();
		
		System.out.println("\n1st floor is ground floor!");
		System.out.print("Floor: ");
		floor = keyboard.nextInt();
		keyboard.nextLine();
		
		System.out.print("Other Details: ");
		otherInfo = keyboard.nextLine();
		
		if (appartment == 0) {
			newAdress = new Adress(streetName, streetNumber, otherInfo);
		} else if (floor != 0) {
			newAdress = new Adress(streetName, streetNumber, appartment, floor, otherInfo);
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
