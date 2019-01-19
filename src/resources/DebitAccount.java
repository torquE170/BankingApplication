package resources;

public class DebitAccount extends Account{

	private double amountOriginal;
	private double amountToBePaid;
	private double currentAmount;
	private String duration;
	private double monthlyRate;
	private double interestRate;
	
	public DebitAccount() {

		this.amountOriginal = 0;
		this.amountToBePaid = 0;
		this.currentAmount = 0;
		this.duration = "";
		this.monthlyRate = 0;
		this.interestRate = 0;
	}
	public DebitAccount(double amountOriginal, double amountToBePaid,
			double currentAmount, String duration, double monthlyRate, double interestRate) {

		this.amountOriginal = amountOriginal;
		this.amountToBePaid = amountToBePaid;
		this.currentAmount = currentAmount;
		this.duration = duration;
		this.monthlyRate = monthlyRate;
		this.interestRate = interestRate;
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
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
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
