package appMenu;

import java.io.IOException;
import java.util.Scanner;

import administration.Login;
import administration.User;
import dbAccess.UserDao;

public class AppMenu {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		User currentUser;
		int option = -1;
//		clearScreen();
		System.out.println("  *** Banking Application, please do no proced without proper access credentials ***\n");
		do {
			System.out.println(" --- Login Menu ---\n");
			System.out.println("  1 - Login\n");
			System.out.println("  0 - Exit\n");
			System.out.print(">> ");
			option = keyboard.nextInt();
			System.out.println();
			
			switch(option) {
			// Login Menu
			case 1: {
				currentUser = Login.loginUser();
				// User or Admin Menu
				if (currentUser != null) {
					if (currentUser.getIsAdmin() == 'Y') {
						// Admin Menu
						do {
							
							System.out.println("  1 - Add User");
							System.out.println("  2 - Reset User");
							System.out.println("  3 - Remove User");						
							System.out.println("  4 - Give / Take admin");
							System.out.println("  5 - View Users\n");
							System.out.println("  0 - Back\n");
							System.out.print(">> ");
							option = keyboard.nextInt();
							System.out.println();
							
							switch (option) {
							case 1: {
								User.addUser();
								break;								
							}
							case 2: {
								User.resetUser();
								break;
							}
							case 3: {
								User.deleteUser();
								break;
							}
							case 4: {
								User.setAdmin();
								break;
							}
							case 5: {
								User.viewUsers();
								promtEnterKey();
								break;
							}
							case 0: {
								option = -2;
								break;
							}
							default: {
								option = 0;
								System.out.println("  Enter a valid option!");
								break;
							}
							}
						} while (option != -2);
					} else if (currentUser.getIsAdmin() == 'N') {
						if (currentUser.getIsActive() == 'N') {
							// User Activation
							currentUser = currentUser.activateUser();
						} 
						if (currentUser.getIsActive() == 'Y') {
							// User Menu
							do {
								System.out.println("      User Menu\n");
								System.out.println("  0 - Back\n");								
								System.out.print(">> ");
								option = keyboard.nextInt();
								System.out.println();
								switch(option) {
								case 0: {
									option = -3;
									break;
								}
								default: {
									option = 0;
									System.out.println("  Enter a valid option!");
									break;
								}
								}
							} while(option != -3);
						}
					}
				}
				break;
			}
			case 0: {
				option = -1;
				break;
			}
			default: {
				System.out.println("  Enter a valid option!");
				option = 0;
				break;
			}
			}			
		} while (option != -1);
		
	}

	private static void clearScreen() {
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch(IOException e) {
			System.out.println(e);
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}
	
	public static void promtEnterKey() {
		Scanner keyboard = new Scanner(System.in);
		try {
			System.out.print("  --- Press Enter to continue ---");
			keyboard.nextLine();
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println();
	}

}
