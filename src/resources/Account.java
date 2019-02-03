package resources;

import java.util.concurrent.ThreadLocalRandom;

import dbAccess.AccountDao;

public class Account {
	
	private int id;
	private String accountId;
	private String customerId;
	
	public Account() {
		
		this.id = 0;
		this.accountId = "";
		this.customerId = "";
	}
	public Account(String accountId, String customerId) {
		
		this.id = 0;
		this.accountId = accountId;
		this.customerId = customerId;
	}
	public Account(int id, String accountId, String customerId) {
	
		this.id = id;
		this.accountId = accountId;
		this.customerId = customerId;
	}
	
	public String generateAccount() {
		
		String accountNumber = "";
		boolean isUnique = false;		
		do {
			int afterRo = (int)(Math.random() * 89 + 11);
//			int afterRo = ThreadLocalRandom.current().nextInt(10, 99);
			int firstThree = (int)(Math.random() * 899 + 101);
			int secondThree = (int)(Math.random() * 899 + 101);
			int lastThree = (int)(Math.random() * 899 + 101);
			int lastTwo = (int)(Math.random() * 89 + 11);
			
			accountNumber = "RO" + Integer.toString(afterRo) + "EBAS420SV" + Integer.toString(firstThree) + Integer.toString(secondThree) + 
																			 Integer.toString(lastThree) + Integer.toString(lastTwo);
			if (AccountDao.uniqueAccount(accountNumber)) {
				isUnique = true;
			}
		} while (!isUnique);
		return accountNumber;
	}
	
	// and here
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}	
}
