package dbAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import resources.DebitAccount;

public class DebitAccountDao {

	public static int saveAccount(DebitAccount newAccount) {
		
		int status = 0;
		try {
			Connection con = DB.getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO debit_accounts (accountId, customerId, depositAmount, duration, interest, amountAfterInterest, created) "
					+ " VALUES(?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, newAccount.getAccountId());
			ps.setString(2, newAccount.getCustomerId());
			ps.setDouble(3, newAccount.getDepositAmount());
			ps.setInt(4, newAccount.getDuration());
			ps.setDouble(5, newAccount.getInterest());
			ps.setDouble(6, newAccount.getAmountAfterInterest());
			ps.setDate(7, new java.sql.Date(newAccount.getCreated().getTime()) );
			status = ps.executeUpdate();
			
			ps.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return status;
	}
	
	
}
