package resources;

import java.util.Date;
import java.util.Scanner;
import dbAccess.CustomerDao;
import dbAccess.DebitAccountDao;

public class DebitAccount extends Account {

	private double depositAmount;
	private int duration;
	private double interest;
	private double amountAfterInterest;
	private Date created;
	
	public DebitAccount() {

		super();
		this.depositAmount = 0;
		this.duration = 0;
		this.interest = 0;
		this.amountAfterInterest = 0;
		this.created = new Date();
	}
	public DebitAccount(String accountId, String customerId, double depositAmount, int duration, double interest,
			double amountAfterInterest, Date created) {

		super(accountId, customerId);
		this.depositAmount = depositAmount;
		this.duration = duration;
		this.interest = interest;
		this.amountAfterInterest = amountAfterInterest;
		this.created = created;
	}
	
	public static void extractMoney() {
		
		// write here
	}
	
	public static void addAccount() {
		
		int saved = 0;
		DebitAccount newAccount = new DebitAccount();
		newAccount.enterAccountData();
		saved = DebitAccountDao.saveAccount(newAccount);
		if (saved == 1) {
			System.out.println("   We have a new account!");
		} else {
			System.out.println("   Something went wrong!");
		}
	}
	
	private DebitAccount enterAccountData() {
		
		Scanner keyboard = new Scanner(System.in);
		Customer oneCustomer = new Customer();
		oneCustomer.setId(0);
		
		// Recognize customer
		String customerId = "";
		System.out.println("   Check if customer is in system!");
		System.out.print("   Customer ID: ");
		customerId = keyboard.nextLine();
		
		oneCustomer = CustomerDao.retrieveCustomer(customerId);
		
		if (oneCustomer.getId() != 0) {
		
			// Deposit or no, menu
			int option = -1;
			do {
				System.out.println("\n   Make initial deposit?");
				System.out.println("   1 - Yes");
				System.out.println("   2 - No");
				System.out.print(">> ");
				option = keyboard.nextInt();
				keyboard.nextLine();
				switch (option) {
				case 1: {
					System.out.print("\n   Amount to deposit: ");
					this.setDepositAmount(keyboard.nextDouble());
					keyboard.nextLine();
					
					System.out.print("\n   How long you going to store this money? ");
					this.setDuration(keyboard.nextInt());
					keyboard.nextLine();
					
					this.setInterest(5);
					
					this.setAmountAfterInterest( this.getDepositAmount() + ( this.getDepositAmount() * (this.getInterest() / 100) ) * this.getDuration() );
					option = -1;				
					break;
				}
				case 2: {
					this.setDepositAmount(0);
					option = -1;
					break;
				}
				default: {
					option = 0;
					System.out.println("   Enter a valid option!");
				}
				}
			} while(option != -1);
			this.setCustomerId(oneCustomer.getCustomerId());
			this.setAccountId(this.generateAccount());
		} else {
			
			System.out.println(" Customer not in system!");
		}
		return this;
	}
	
	public double getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
	public double getAmountAfterInterest() {
		return amountAfterInterest;
	}
	public void setAmountAfterInterest(double amountAfterInterest) {
		this.amountAfterInterest = amountAfterInterest;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
}
