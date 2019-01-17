package resources;

public class Account {
	
	private int id;
	private int accountId;
	private int customerId;
	
	public Account() {
		
		this.id = 0;
		this.accountId = 0;
		this.customerId = 0;
	}
	public Account(int id, int accountId, int customerId) {
	
		this.id = id;
		this.accountId = accountId;
		this.customerId = customerId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}	
}
