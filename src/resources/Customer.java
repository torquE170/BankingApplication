package resources;

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
