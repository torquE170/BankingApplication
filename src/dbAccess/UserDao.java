package dbAccess;

import static dbAccess.DB.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import administration.User;

public class UserDao {

	/**
	 * Returns a User. Useful for new users.
	 */
	public static User retrieveUser(String username, String password) {
		
		User user = new User();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
				user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("isAdmin").charAt(0), rs.getString("isActive").charAt(0));
			}
			rs.close();
			ps.close();
			con.close();
		} catch(SQLException e) {
			System.out.println(e);
		}
		return user;
	}

	/**
	 * Saves a New User to the database (for admin use).
	 */
	public static int adminSave(String username, String password, char isAdmin, char isActive) {
		
		int status = 0;
		try {
			
			Connection con = DB.getConnection();
			PreparedStatement ps = con.prepareStatement("INSERT INTO users (username, password, isAdmin, isActive) VALUES(?, ?, ?, ?)");		
		
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, String.valueOf(isAdmin));
			ps.setString(4, String.valueOf(isActive));
			
			status = ps.executeUpdate();
			ps.close();
			con.close();
		} catch(SQLException e) {
			System.out.println(e);
		}
		return status;
	}
	
	public static int updateUserStatus(String username, String password, char isActive, String secretQuestion, String secretAnswer) {
		
		int status = 0;
		try {
			Connection con = DB.getConnection();
			PreparedStatement ps = con.prepareStatement("UPDATE users SET password = ?, isActive = ?, secretQuestion = ?, secretAnswer = ? WHERE username = ?");
			ps.setString(1, password);
			ps.setString(2, String.valueOf(isActive));
			ps.setString(3, secretQuestion);
			ps.setString(4, secretAnswer);
			ps.setString(5, username);
			status = ps.executeUpdate();
			
			ps.close();
			con.close();
		} catch(SQLException e) {
			System.out.println(e);
		}
		return status;
	}
	
	// separator
	
	/**
	 * Returns a List of Users
	 */
	public static List<User> getUsers() {
		
		List<User> totalUsers = new ArrayList<User>();
		try {
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM users");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String username = rs.getString("username");
				char isAdmin = rs.getString("is_admin").charAt(0);
				char isActive = rs.getString("isActive").charAt(0);
				User newUser = new User(id, username, isAdmin, isActive);
				totalUsers.add(newUser);
			}
			ps.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return totalUsers;
	}
	
	/**
	 * Is a update for is_admin column in database.
	 */
	public static int update(int id, String username, char isAdmin) {
		int status = 0;
		try {
			Connection con = DB.getConnection();
			PreparedStatement ps = con.prepareStatement("UPDATE users SET is_admin = ? WHERE ID = ? AND username = ?");
			ps.setString(1, String.valueOf(isAdmin));
			ps.setInt(2, id);
			ps.setString(3, username);
			status = ps.executeUpdate();
			ps.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return status;
	}
	
	/**
	 * Removes specific entry from database.
	 */
	public static int remove(int id, String username) {
		
		int status = 0;
		try {
			Connection con = DB.getConnection();
			PreparedStatement ps = con.prepareStatement("DELETE FROM users WHERE ID = ? AND username = ?");
			ps.setInt(1, id);
			ps.setString(2, username);
			status = ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return status;
	}
	
}
