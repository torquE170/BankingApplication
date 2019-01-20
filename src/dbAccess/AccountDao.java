package dbAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao {

	/**
	 * Checks for account Id uniqueness 
	 * @param accountId
	 * @return	1 for unique account id
	 * 			0 for finding a exact match
	 */
	public static boolean uniqueAccount(String accountId) {
		
		boolean isUnique = true;
		String id = "";
		try {
			Connection con = DB.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM customers WHERE accountId = ?");
			ps.setString(1, accountId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				id = rs.getString("accountId");
				if (id.equals(accountId)) {
					isUnique = false;
					break;
				}
			}
			
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return isUnique;
	}
}
