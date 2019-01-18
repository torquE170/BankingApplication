package resources;

import java.util.Scanner;

import dbAccess.CustomerDao;

public class Customer {

	private int id;
	private int customerId;
	private String firstName;
	private String lastName;
	private String DOB;
	private Adress adress;
	
	public Customer() {
		
		this.id = 0;
		this.customerId = 0;
		this.firstName = "";
		this.lastName = "";
		this.DOB = "";
		this.adress = new Adress();
	}
	public Customer(int id, int customerId, String firstName, String lastName, String dOB, Adress adress) {
		
		this.id = id;
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.DOB = dOB;
		this.adress = adress;
	}
	
	public static void addCustomer() {
		
		Customer newCustomer = enterCustomerData();
		CustomerDao.createCustomer(newCustomer);
	}
 	
	private static Customer enterCustomerData() {
		
		Customer newCustomer = new Customer();
		Scanner keyboard = new Scanner(System.in);
		System.out.println("\nCarefully enter data for a new customer!");
		System.out.print("Customer ID: ");
		newCustomer.setCustomerId(keyboard.nextInt());
		keyboard.nextLine();
		
		System.out.print("\nFirst Name: ");
		newCustomer.setFirstName(keyboard.nextLine());
		
		System.out.print("\nLast Name: ");
		newCustomer.setLastName(keyboard.nextLine());
		
		System.out.println("\nPlease follow date format!");
		System.out.print("Date of Birth (DD-MM-YYY): ");
		newCustomer.setDOB(keyboard.nextLine());
		
		System.out.println("\nAdress: ");
		newCustomer.setAdress(Adress.addAdress());
		
		System.out.println();
		
		return newCustomer;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String DOB) {
		this.DOB = DOB;
	}
	public Adress getAdress() {
		return adress;
	}
	public void setAdress(Adress adress) {
		this.adress = adress;
	}
}
