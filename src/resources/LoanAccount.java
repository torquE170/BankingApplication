package resources;

import java.util.Scanner;

import dbAccess.CustomerDao;
import dbAccess.LoanAccountDao;

public class LoanAccount extends Account{

	private double amountOriginal;
	private double amountToBePaid;
	private double currentAmount;
	private int duration;
	private double monthlyRate;
	private double interestRate;
	
	public LoanAccount() {

		super();
		this.amountOriginal = 0;
		this.amountToBePaid = 0;
		this.currentAmount = 0;
		this.duration = 0;
		this.monthlyRate = 0;
		this.interestRate = 0;
	}
	public LoanAccount(String accountId, String customerId, double amountOriginal, double amountToBePaid,
			double currentAmount, int duration, double monthlyRate, double interestRate) {

		super(accountId, customerId);
		this.amountOriginal = amountOriginal;
		this.amountToBePaid = amountToBePaid;
		this.currentAmount = currentAmount;
		this.duration = duration;
		this.monthlyRate = monthlyRate;
		this.interestRate = interestRate;
	}
	
	public static void addAccount() {
		
		int saved = 0;
		LoanAccount newAccount = new LoanAccount();
		newAccount.enterAccountData();
		saved = LoanAccountDao.saveAccount(newAccount);
		if (saved == 1) {
			System.out.println("   We have a new account!");
		} else {
			System.out.println("   Something went wrong!");
		}
	}
	
	private LoanAccount enterAccountData() {
		
		Scanner keyboard = new Scanner(System.in);
		Customer oneCustomer = new Customer();

		// Recognize customer
		String customerId = "";
		System.out.println("   Check if customer is in system!");
		System.out.print("   Customer ID: ");
		customerId = keyboard.nextLine();
		
		oneCustomer = CustomerDao.retrieveCustomer(customerId);
		
		if ( oneCustomer.getId() != 0) {
			
			System.out.print(" Amount to be loaned: ");
			this.setAmountOriginal(keyboard.nextDouble());
			keyboard.nextLine();
			
			System.out.print("  Duration of the loan: ");
			this.setDuration(keyboard.nextInt());
			keyboard.nextLine();
			
			this.setMonthlyRate(2.5);
			this.setInterestRate(5);
			
			this.setAmountToBePaid( this.getAmountOriginal() + ( this.getAmountOriginal() * (this.getInterestRate() / 100) ) * this.getDuration() );
		}
		this.setCustomerId(oneCustomer.getCustomerId());
		this.setAccountId(this.generateAccount());
		
		return this;
	}
	
	public double getAmountOriginal() {
		return amountOriginal;
	}
	public void setAmountOriginal(double amountOriginal) {
		this.amountOriginal = amountOriginal;
	}
	public double getAmountToBePaid() {
		return amountToBePaid;
	}
	public void setAmountToBePaid(double amountToBePaid) {
		this.amountToBePaid = amountToBePaid;
	}
	public double getCurrentAmount() {
		return currentAmount;
	}
	public void setCurrentAmount(double currentAmount) {
		this.currentAmount = currentAmount;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public double getMonthlyRate() {
		return monthlyRate;
	}
	public void setMonthlyRate(double monthlyRate) {
		this.monthlyRate = monthlyRate;
	}
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
}
