package dbAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import resources.Customer;

public class CustomerDao {

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
}
