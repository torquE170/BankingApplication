package resources;

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
	
	// here
	
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
