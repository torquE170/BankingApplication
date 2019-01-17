package resources;

public class LoanAccount extends Account{

	private int id;
	private double depositAmount;
	private String duration;
	private double interest;
	
	public LoanAccount() {

		this.id = 0;
		this.depositAmount = 0;
		this.duration = "";
		this.interest = 0;
	}
	public LoanAccount(int id, double depositAmount, String duration, double interest) {

		this.id = id;
		this.depositAmount = depositAmount;
		this.duration = duration;
		this.interest = interest;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
