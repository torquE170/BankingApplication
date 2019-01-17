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
			PreparedStatement ps = con.prepareStatement("INSERT INTO customers () VALUES()");
		} catch(SQLException e) {
			System.out.println(e);
		}
	}
}
