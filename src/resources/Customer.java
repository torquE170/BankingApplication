package resources;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dbAccess.CustomerDao;

public class Customer {

	private int id;
	private String customerId;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private Address address;
	
	public Customer() {
		
		this.id = 0;
		this.customerId = "";
		this.firstName = "";
		this.lastName = "";
		this.dateOfBirth = "";
		this.address = new Address();
	}
	public Customer(String customerId, String firstName, String lastName, String dateOfBirth, Address address) {
		
		this.id = 0;
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
	}
	public Customer(int id, String customerId, String firstName, String lastName, String dateOfBirth, Address address) {
		
		this.id = id;
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
	}
	
	/**
	 * Calls all methods in order for a valid Customer registration
	 */
	public static void addCustomer() {
		
		Scanner keyboard = new Scanner(System.in);
		Customer newCustomer = new Customer();
		System.out.print("Customer ID: ");
		newCustomer.setCustomerId(keyboard.nextLine());
		if (CustomerDao.uniqueUsername(newCustomer.getCustomerId())) {
			
			newCustomer = newCustomer.enterCustomerData();
			if (newCustomer.validateCustomer()) {
				newCustomer.registerCustomer();
			}
		} else {
			System.out.println("   CNP already in system!\n");
		}		
	}
	
	/**
	 * Gathers customer info for registration
	 * @return a Customer with all data except id
	 */
	private Customer enterCustomerData() {
		
		
		Scanner keyboard = new Scanner(System.in);
		
		System.out.print("\nFirst Name: ");
		this.setFirstName(keyboard.nextLine());
		
		System.out.print("\nLast Name: ");
		this.setLastName(keyboard.nextLine());
		
		System.out.println("\nPlease follow date format! (DD-MM-YYYY)");
		System.out.print("Date of Birth: ");
		this.setDateOfBirth(keyboard.nextLine());
		
		System.out.println("\nAddress");
		this.setAddress(Address.addAddress());	
		System.out.println();
		
		return this;
	}
	
	/**
	 * Receives and sends variables to the method with database connection
	 * @return int 1 if customer got saved to database.
	 * 			int 0 if customer didn't got saved to database.
	 */
	private void registerCustomer() {
		
		int saved = 0;
		saved = CustomerDao.createCustomer(this);
		if (saved == 0) {
			System.out.println("  Operation didn't complete with success!\n");
		} else {
			System.out.println("  We have a new customer!\n");
		}
	}
	
	/**
	 * Validates every single info about a Customer, except address
	 * @return True for a valid & ready for registration Customer
	 * 			False for anything else
	 */			
	private boolean validateCustomer() {
		
		boolean checks = false;
		if (validateDateOfBirth(this.getDateOfBirth()))
		{
			if (validateId(this.getCustomerId()))
			{
				if (CustomerDao.uniqueUsername(this.getCustomerId()))
				{
					if ( validateUsername(this.getFirstName()) && validateUsername(this.getLastName()) )
					{
						if ( Address.validateAddress(this.getAddress()) )
						{
							checks = true;
						}
						
					} else {
						System.out.println("   Invalid Names!\n");
					}
					
				} else {
					System.out.println("   Another Customer is registered with this ID!\n");
				}
				
			} else {
				System.out.println("   Invalid ID!\n");
			}
			
		} else {
			System.out.println("   Invalid Date Of Birth!\n");
		}
		return checks;
	}
	
	/**
	 * Validates a customer id to meet required form
	 * @param customerId
	 * @return	True for 13 digits
	 * 			False for anything else
	 */
	private static boolean validateId(String customerId) {

		if(customerId.length() == 13)
		{
			Pattern digit = Pattern.compile("[0-9]");

			Matcher hasDigit = digit.matcher(customerId);

			return hasDigit.find();

		} else {
			return false;
		}
	}
		
	/**
	 * Validates a name to meet certain standards
	 * @param name
	 * @return
	 * 		True for a name that begins with capital letter, and has no digits inside
	 * 		False for anything else
	 */
	private static boolean validateUsername(String name) {

		if(name.length()>=3)
		{
			Pattern UpperCase = Pattern.compile("^[A-Z]");
			Pattern LowerCase = Pattern.compile("[a-z]");
			Pattern digit = Pattern.compile("[0-9]");

			Matcher hasUpperCase = UpperCase.matcher(name);
			Matcher hasLowerCase = LowerCase.matcher(name);
			Matcher hasDigit = digit.matcher(name);

			return hasUpperCase.find() && hasLowerCase.find() && !hasDigit.find();

		} else {
			return false;
		}
	}
	
	/**
	 * Validates the format of a date of birth also checks for the age 18
	 * @param dateOfBirth
	 * @return  True for correct format (DD-MM-YYYY) and correct date numbers
	 * 			False for anything else
	 */
	private static boolean validateDateOfBirth(String dateOfBirth) {

		boolean checks = false;
		if(dateOfBirth.length() == 10)
		{
			Pattern digit = Pattern.compile("[0-9]");
			Pattern dashes = Pattern.compile("-");

			Matcher hasDigit = digit.matcher(dateOfBirth);
			Matcher hasDashes = dashes.matcher(dateOfBirth);

			if ( hasDigit.find() && hasDashes.find() )
			{
				String[] date = dateOfBirth.split("-");
				if ( Integer.parseInt(date[0]) <= 31 ) {
					if ( Integer.parseInt(date[1]) <= 12 ) {
						if ( Integer.parseInt(date[2]) <= 2001 ) {
							checks = true;
						}
					}
				}
			}

		} else {
			checks = false;
		}
		return checks;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
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
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
}
