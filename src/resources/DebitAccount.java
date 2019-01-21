package resources;

import java.util.Scanner;

import dbAccess.DebitAccountDao;

public class DebitAccount extends Account{

	private double depositAmount;
	private String duration;
	private double interest;
	
	public DebitAccount() {

		super();
		this.depositAmount = 0;
		this.duration = "";
		this.interest = 0;
	}
	public DebitAccount(String accountId, String customerId, double depositAmount, String duration, double interest) {

		super(accountId, customerId);
		this.depositAmount = depositAmount;
		this.duration = duration;
		this.interest = interest;
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
				
				System.out.println("\n   How long you going to store this money? ");
				this.setDuration(keyboard.nextLine());
				
				this.setInterest(2.18);
				
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
		
		return this;
	}
	
	public double getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
}
