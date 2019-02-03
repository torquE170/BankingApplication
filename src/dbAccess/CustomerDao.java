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
			PreparedStatement ps = con.prepareStatement("INSERT INTO customers (customerId, firstName, lastName, dateOfBirth, address) VALUES(?, ?, ?, ?, ?)");
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
	
	/**
	 * Gets a customer from database by his full name
	 * @param customerId
	 * @return A customer object with info from database
	 */
	public static Customer retrieveCustomer(String customerId) {
		
		Customer oneCustomer = new Customer();
		try {
			Connection con = DB.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM customers WHERE customerId = ?");
			ps.setString(1, customerId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				oneCustomer.setId(rs.getInt("id"));
				oneCustomer.setCustomerId(rs.getString("customerId"));
				oneCustomer.setFirstName(rs.getString("firstName"));
				oneCustomer.setLastName(rs.getString("lastName"));
				oneCustomer.setDateOfBirth(rs.getString("dateOfBirth"));
				oneCustomer.getAddress().setText(rs.getString("address"));
			}
		
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return oneCustomer;
	}
}
