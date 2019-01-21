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
			PreparedStatement ps = con.prepareStatement("INSERT INTO debit_account (accountId, customerId, depositAmount, duration, interest) VALUES(?, ?, ?, ?, ?)");
			ps.setString(1, newAccount.getAccountId());
			ps.setString(2, newAccount.getCustomerId());
			ps.setDouble(3, newAccount.getDepositAmount());
			ps.setString(4, newAccount.getDuration());
			ps.setDouble(5, newAccount.getInterest());
			status = ps.executeUpdate();
									
			ps.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return status;
	}
}
