package dbAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import resources.LoanAccount;

public class LoanAccountDao {

	public static int saveAccount(LoanAccount newAccount) {
		
		int status = 0;
		try {
			Connection con = DB.getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO loan_accounts (accountId, customerId, ammountOriginal, amountToBePaid, currentAmount, duration, monthlyRate,"
					+ " interestRate) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, newAccount.getAccountId());
			ps.setString(2, newAccount.getCustomerId());
			ps.setDouble(3, newAccount.getAmountOriginal());
			ps.setDouble(4, newAccount.getAmountToBePaid());
			ps.setDouble(5, newAccount.getCurrentAmount());
			ps.setInt(6, newAccount.getDuration());
			ps.setDouble(7, newAccount.getMonthlyRate());
			ps.setDouble(8, newAccount.getInterestRate());
			status = ps.executeUpdate();
			
			ps.close();
			con.close();			
		} catch (SQLException e) {
			System.out.println(e);
		}
		return status;
	}
}
