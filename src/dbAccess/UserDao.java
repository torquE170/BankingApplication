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
	 * Useful for log in process.
	 * @param username
	 * @param password
	 * @return Returns a User.
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
				
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setIsAdmin(rs.getString("isAdmin").charAt(0));
				user.setIsActive(rs.getString("isActive").charAt(0));
				user.setSecretQuestion(rs.getString("secretQuestion"));
				user.setSecretAnswer(rs.getString("secretAnswer"));
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
	 * Fetches a user from database for processing
	 * @param username
	 * @return Full details for a user
	 */
	public static User getUser(String username) {
		User returnedUser = new User();
		try {
			Connection con = DB.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				returnedUser.setId(rs.getInt("id"));
				returnedUser.setUsername(rs.getString("username"));
				returnedUser.setPassword(rs.getString("password"));
				returnedUser.setIsAdmin(rs.getString("isAdmin").charAt(0));
				returnedUser.setIsActive(rs.getString("isActive").charAt(0));
				returnedUser.setSecretQuestion(rs.getString("secretQuestion"));
				returnedUser.setSecretAnswer(rs.getString("secretAnswer"));
			}
			
			if (returnedUser.getId() == 0) {
				System.out.println("Connection to database didn't succeed");
			}
			rs.close();
			ps.close();
			con.close();
		} catch(SQLException e) {
			System.out.println(e);
		}
		return returnedUser;
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
	
	/**
	 * Updates isAdmin column in database.
	 */
	public static int update(String username, char isAdmin) {
		int status = 0;
		try {
			Connection con = DB.getConnection();
			PreparedStatement ps = con.prepareStatement("UPDATE users SET isAdmin = ? WHERE username = ?");
			ps.setString(1, String.valueOf(isAdmin));
			ps.setString(2, username);
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
	public static int remove(String username) {
		
		int status = 0;
		try {
			Connection con = DB.getConnection();
			PreparedStatement ps = con.prepareStatement("DELETE FROM users WHERE username = ?");
			ps.setString(1, username);
			status = ps.executeUpdate();
			
			ps.close();
		} catch (SQLException e) {
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
				char isAdmin = rs.getString("isAdmin").charAt(0);
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
	 * Serves for activating a user
	 * @param currentUser
	 * @return 	1 for success
	 *			0 for failure
	 */
	public static int updatePassword(User currentUser) {
		
		int status = 0;
		try {
			Connection con = DB.getConnection();
			PreparedStatement ps = con.prepareStatement("UPDATE users SET password = ? WHERE username = ? AND secretQuestion = ?");
			ps.setString(1, currentUser.getPassword());
			ps.setString(2, currentUser.getUsername());
			ps.setString(3, currentUser.getSecretQuestion());
			status = ps.executeUpdate();
			if (status == 0) {
				System.out.println("   Update didn't went through to database.");
			} else {
				System.out.println("   Database updated!");
			}
						
			ps.close();
			con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return status;
	}
	
	/**
	 * Checks for a usernames uniqueness
	 * @param username
	 * @return true for unique username, false for finding another alike
	 */
	public static boolean uniqueUsername(String username) {
		
		boolean isUnique = true;
		String name = "";
		try {
			Connection con = DB.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				
				name = rs.getString("username");
			}
			if (name.equals(username)) {
				isUnique = false;
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
	 * Counts current admins in the data base
	 * @return A int of current admins
	 */
	public static int adminCount() {
		
		int counter = 0;
		try {
			  Connection con = DB.getConnection();
			  PreparedStatement ps = con.prepareStatement("SELECT COUNT(isAdmin) FROM users WHERE isAdmin = 'Y'");
			  ResultSet rs = ps.executeQuery();
			  while (rs.next()) {
				  
				  counter = rs.getInt("COUNT(isAdmin)");
			  }
			  
			  rs.close();
			  ps.close();
			  con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return counter;
	}
	
}
