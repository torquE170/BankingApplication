package administration;

import static dbAccess.UserDao.retrieveUser;
import java.io.Console;
import java.util.Currency;
import java.util.Scanner;

public class Login {

	/**
	 * Provides a login form
	 */
	public static User loginUser() {

		Scanner keyboard = new Scanner(System.in);

		boolean loginSuccess = false;
		int tries = 0;
		User user = new User();
		while (!loginSuccess) {
						
			System.out.println("\n  Login form:");
			System.out.print(">> Username: ");
			String username1 = keyboard.nextLine();

			System.out.print(">> Password: ");
//			Console console = System.console();
//			char[] text = console.readPassword();
//			String password1 = new String(text);
			String password1 = keyboard.nextLine();

			user = retrieveUser(username1, password1);

			System.out.println();
			if (user.getIsAdmin() == 'Y') {

				System.out.println("  Hello Admin!");
				loginSuccess = true;
			} else if (user.getIsAdmin() == 'N') {

				System.out.println("  Hello User!");
				loginSuccess = true;
			} else {

				System.out.println("  Login Failed!");
				tries++;
			}
			
			if (tries >= 3) {
				if (tries == 3) {
					int option = -1;			
					do {
						System.out.println("  1 - Reset Password with Secret Question!");
						System.out.println("  2 - Try again!\n");
						System.out.println("  0 - Back\n");
						System.out.print(">> ");
						option = keyboard.nextInt();
						
						switch(option) {
						case 1: {
							User.resetPassword();
							break;
						}
						case 2: {
							tries = 0;
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
					} while(option != -1);
				}
				keyboard.nextLine();
				
				if (tries == 4) {
					System.out.println();
					return null;
				}
			}
		}
		System.out.println();
		return user;
	}

}
