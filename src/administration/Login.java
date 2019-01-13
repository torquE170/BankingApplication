package administration;

import static dbAccess.UserDao.retrieveUser;
import java.io.Console;
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
										
					System.out.println("   **Plese note, you can exit at anytime by pressing CTRL + Break!!**");
				}
				
				if (tries == 4) {
					return null;
				}
			}
		}
		return user;
	}

}
