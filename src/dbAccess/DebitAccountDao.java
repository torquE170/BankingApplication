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
			PreparedStatement ps = con.prepareStatement("INSERT INTO debit_account () VALUES()");
			// continue here
			
			ps.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return status;
	}
}
