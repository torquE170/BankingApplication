package administration;

import static dbAccess.UserDao.getUsers;
import static dbAccess.UserDao.remove;
import static dbAccess.UserDao.update;
import java.io.Console;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sound.midi.Synthesizer;

import dbAccess.UserDao;

public class User {

	private int id;
	private String username;
	private String password;
	private char isAdmin;
	private char isActive;
	private String secretQuestion;
	private String secretAnswer;

	public User() {

		this.id = 0;
		this.username = "";
		this.password = "";
		this.isAdmin = ' ';
		this.isActive = 'N';
		this.secretQuestion = "";
		this.secretAnswer = "";
	}
	public User(String username, String password, char isAdmin, char isActive) {

		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
		this.isActive = isActive;
	}
	public User(int id, String username, char isAdmin, char isActive) {

		this.id = id;
		this.username = username;
		this.isAdmin = isAdmin;
		this.isActive = isActive;
	}
	public User(int id, String username, String password, char isAdmin, char isActive) {
		
		this.id = id;
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
		this.isActive = isActive;
	}
	public User(String username, String password, char isAdmin, String secretQuestion, String secretAnswer, char isActive) {

		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
		this.secretQuestion = secretQuestion;
		this.secretAnswer = secretAnswer;
		this.isActive = isActive;
	}
	public User(int id, String username, String password, char isAdmin, char active, String secretQuestion, String secretAnswer) {

		this.id = id;
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
		this.isActive = active;
		this.secretQuestion = secretQuestion;
		this.secretAnswer = secretAnswer;
	}

	/**
	 * Receives and sends variables to the method with database connection
	 * @return int 1 if user got saved to database.
	 * 			int 0 if user didn't got saved to database.
	 */
	private void registerUser() {

		int saved = 0;
		saved = UserDao.adminSave(this.getUsername(), this.getPassword(), this.getIsAdmin(), this.getIsActive());
		if (saved == 0) {
			System.out.println("Save operation to database didn't complete successfully!");
		} else {
			System.out.println("We have a new user!");
		}
	}

	/**
	 * Calls all required methods in order for a user registration
	 */
	public static void addUser() {

		User newUser = enterUserData();
		if (newUser.validateUser()) {

			newUser.registerUser();
		}	

	}

	/**
	 * Used to get new user data
	 */
	private static User enterUserData() {

		String user, pass;
		char isAdmin;
		Scanner keyboard = new Scanner(System.in);
		//		Console console = System.console();

		System.out.println(" New user from:");
		System.out.print(">> Username: ");
		user = keyboard.nextLine();
		System.out.print(">> Password: ");
		//		char[] text = console.readPassword();
		//		pass = new String(text);
		pass = keyboard.nextLine();
		System.out.print(">> Admin: ");
		isAdmin = keyboard.nextLine().charAt(0);
		isAdmin = Character.toUpperCase(isAdmin);

		User newUser = new User(user, pass, isAdmin, 'N');
		return newUser;
	}

	/**
	 * Checks for pending login credentials integrity
	 */
	private boolean validateUser() {
		boolean ok = false;

		// Username check
		if ( validateUsername(this.getUsername()) ) {
			// Password check
			if ( validatePassword(this.getPassword()) ) {

				ok = true;
			}
		}

		if (!ok) {
			System.out.println("\n  Your login credentials don't meet the minimum requirements!\n");
			System.out.println("  Your username must be at least 5 characters long and have at least one");
			System.out.println("      lower case and one upper case!");
			System.out.println("  And your password must contain at least one lower case, one upper case,");
			System.out.println("      one number and one special character!");
		}
		return ok;
	}

	/**
	 * Validates a password if it meets certain security standards
	 * @param pass
	 * @return True if the password is at least 5 characters, one lower case, one upper case, and a special character.
	 * 			False if it doesn't meet these requirements.
	 */
	private static boolean validatePassword(String pass) {

		if(pass.length()>=5)
		{
			Pattern letter = Pattern.compile("[A-Z]");
			Pattern digit = Pattern.compile("[0-9]");
			Pattern special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

			Matcher hasLetter = letter.matcher(pass);
			Matcher hasDigit = digit.matcher(pass);
			Matcher hasSpecial = special.matcher(pass);

			return hasLetter.find() && hasDigit.find() && hasSpecial.find();

		} else {
			return false;
		}
	}

	/**
	 * Validates a username to meet certain standards
	 * @param user
	 * @return
	 */
	private static boolean validateUsername(String user) {

		if(user.length()>=5)
		{
			Pattern UpperCase = Pattern.compile("[A-Z]");
			Pattern LowerCase = Pattern.compile("[a-z]");
			Pattern digit = Pattern.compile("[0-9]*");

			Matcher hasUpperCase = UpperCase.matcher(user);
			Matcher hasLowerCase = LowerCase.matcher(user);
			Matcher hasDigit = digit.matcher(user);

			return hasUpperCase.find() && hasLowerCase.find() && hasDigit.find();

		} else {
			return false;
		}
	}

	public User activateUser() {

		Scanner keyboard = new Scanner(System.in); 
		String newPassword = "";
		boolean changed = false;
		if (this.getIsActive() == 'N') {
			System.out.println("  Activation form:");
			
			do {
				
				System.out.print("New Password: ");
				newPassword = keyboard.nextLine();
				
				if ( validatePassword(newPassword) && !newPassword.equals(this.getPassword()) ) {
					this.setPassword(newPassword);
					changed = true;
				} else {
					System.out.println("   Your password must contain at least one lower case, one upper case,");
					System.out.println("      one number and one special character!");
					System.out.println("      Also must be different than the last one!");
				}
			} while(!changed); 
			System.out.print("Enter a secret question: ");
			this.setSecretQuestion(keyboard.nextLine());

			System.out.print("Enter your answer: ");
			this.setSecretAnswer(keyboard.nextLine());

			this.setIsActive('Y');
			System.out.println();
			if (UserDao.updateUserStatus(this.getUsername(), this.getPassword(), this.getIsActive(), this.getSecretQuestion(), this.getSecretAnswer()) == 1) {
				System.out.println("  User " + this.getUsername() + " has been activated!");
			} else {
				System.out.println("  Activation failed!");
			}
			System.out.println();
		}
		return this;
	}
	
	public static void resetUser() {
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("   Reset User Form:");
		System.out.print(">> Username: ");
		String oneUsername = keyboard.nextLine();
		User currentUser = UserDao.getUser(oneUsername);
		
		if (currentUser.getIsActive() == 'Y') {
			
			currentUser.setPassword("Monday14");
			currentUser.setIsActive('N');
			currentUser.setSecretQuestion("");
			currentUser.setSecretAnswer("");
			if (UserDao.updateUserStatus(currentUser.getUsername(), currentUser.getPassword(), currentUser.getIsActive(), currentUser.getSecretQuestion(), currentUser.getSecretAnswer()) == 1) {
				System.out.println("  User " + currentUser.getUsername() + " has been deactivated and reset!");
			} else {
				System.out.println("  Deactivation failed!");
			}
		}
	}

	/**
	 * Gives or removes admin rights
	 */
	public static void setAdmin() {

		Scanner keyboard = new Scanner(System.in);
		
		System.out.print(">> Username: ");
		String user = keyboard.nextLine();
		User currentUser = UserDao.getUser(user);
		System.out.println("  Admin" + ((currentUser.getIsAdmin() == 'N')?" removal ":" ") + "form: ");
		
		System.out.print(">> Give admin: ");
		currentUser.setIsAdmin(keyboard.nextLine().charAt(0));

		int updated = update(user, currentUser.getIsAdmin());
		if (updated == 0) {
			System.out.println("Operatation failed!");
		} else {
			System.out.println( ((currentUser.getIsAdmin() == 'Y')?"User ":((currentUser.getIsAdmin() == 'N')?"Admin ":"") ) + user + " now has " + 
					((currentUser.getIsAdmin() == 'Y')?"admin":((currentUser.getIsAdmin() == 'N')?"user":"") ) + " rights!");
		}
	}
	
	/**
	 * Deletes a user
	 */
	public static void deleteUser() {

		Scanner keyboard = new Scanner(System.in);
		System.out.println("  Remove form:");
		System.out.print(">> Username: ");
		String user = keyboard.nextLine();

		int deleted = remove(user);
		if (deleted == 0) {
			System.out.println("Operatation failed!");
		} else {
			System.out.println("User " + user + " has been deleted!");
		}		
	}
	
	/**
	 * It shows a table of current users and admins
	 */
	public static void viewUsers() {

		System.out.printf("%7s %15s %10s %10s", "ID", "Username", "isAdmin", "isActive");
		System.out.println();
		List<User> users = getUsers();
		for (int i = 0; i < users.size(); i++) {
			System.out.printf("%7s %15s %10s %10s", users.get(i).getId(), users.get(i).getUsername(), users.get(i).getIsAdmin(), users.get(i).getIsActive());
			System.out.println();
		}	
	}
	
	public void resetPassword() {
		
		Scanner keyboard = new Scanner(System.in);
		String newPassword = "", username = "", answer;
		int option = -1;
		do {
			System.out.print(">> Username: ");
			username = keyboard.nextLine();
						
			System.out.println("Answer your Secret Question in order for you to reset your password!");
			System.out.println(this.getSecretQuestion());
			answer = keyboard.nextLine();
			if (answer.equals(this.getSecretAnswer())) {
				
				System.out.print("  New password: ");
				if (validatePassword(newPassword) && !newPassword.equals(this.getPassword())) {
					this.setPassword(newPassword);
				} else {
					System.out.println("   Your password must contain at least one lower case, one upper case,");
					System.out.println("      one number and one special character!");
					System.out.println("      Also must be different than the last one!");
				}
			} else {
				System.out.println("   Secret Question answered wrong!");
				System.out.println("   1 - Try again\n");
				System.out.println("   0 - Exit\n");
				System.out.print(">> ");
				option = keyboard.nextInt();
				switch (option) {
					case 1: {
						break;
					}
					case 0: {
						option = -1;
						break;
					}
					default: {
						option = 0;
						System.out.println("  Enter a valid option!");
						break;
					}
				}
			}
		} while(option != -1);

	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public char getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(char isAdmin) {
		this.isAdmin = isAdmin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSecretQuestion() {
		return secretQuestion;
	}
	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}
	public String getSecretAnswer() {
		return secretAnswer;
	}
	public void setSecretAnswer(String answer) {
		this.secretAnswer = answer;
	}
	public char getIsActive() {
		return isActive;
	}
	public void setIsActive(char active) {
		this.isActive = active;
	}

}
