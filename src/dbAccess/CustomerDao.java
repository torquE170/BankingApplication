package dbAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import resources.Customer;

public class CustomerDao {

	/**
	 * Commits a Customer entry to data base
	 * @param newCustomer
	 * @return 	1 for success
	 * 			0 for failure
	 */
	public static int createCustomer(Customer newCustomer) {
		
		int status = 0;
		try {
			
			Connection con = DB.getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO customers (customerId, firstName, lastName, DOB, adress) VALUES(?, ?, ?, ?, ?)");
			ps.setString(1, newCustomer.getCustomerId());
			ps.setString(2, newCustomer.getFirstName());
			ps.setString(3, newCustomer.getLastName());
			ps.setString(4, newCustomer.getDateOfBirth());
			ps.setString(5, newCustomer.getAddress().getText());
			status = ps.executeUpdate();
			
			ps.close();
			con.close();
		} catch(SQLException e) {
			System.out.println(e);
		}
		return status;
	}
	
	/**
	 * Checks for customer id uniqueness
	 * @param customerId
	 * @return	1 for unique ID
	 * 			0 for finding a identical ID
	 */
	public static boolean uniqueUsername(String customerId) {
		
		boolean isUnique = true;
		String id = "";
		try {
			Connection con = DB.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM customers WHERE customerId = ?");
			ps.setString(1, customerId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				id = rs.getString("customerId");
				if (id.equals(customerId)) {
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
