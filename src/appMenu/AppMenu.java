package appMenu;

import java.io.IOException;
import java.util.Scanner;

import administration.Login;
import administration.User;
import dbAccess.UserDao;
import resources.Customer;
import resources.DebitAccount;
import resources.LoanAccount;

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
								promptEnterKey();
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
								System.out.println("      Banking Menu\n");
								System.out.println("  1 - Add a Customer");
								System.out.println("  2 - Customer wants to...\n");
								System.out.println("  0 - Back\n");								
								System.out.print(">> ");
								option = keyboard.nextInt();
								System.out.println();
								switch(option) {
								case 1: {
									Customer.addCustomer();
									break;
								}
								case 2:{
									do {
										// Customer options
										System.out.println("      Customer Actions\n");
										System.out.println("  1 - Debit Actions");
										System.out.println("  2 - Loan Actions\n");
										System.out.println("  0 - Back\n");
										System.out.print(">> ");
										option = keyboard.nextInt();
										keyboard.nextLine();
										switch(option) {
										case 1: {
											
											do {
												// Debit Menu
												System.out.println("      Debit Menu\n");
												System.out.println("  1 - New Account");
												System.out.println("  2 - Deposit money");
												System.out.println("  3 - Withdraw money\n");
												System.out.println("  0 - Back\n");
												System.out.print(">> ");
												option = keyboard.nextInt();
												keyboard.nextLine();
												switch (option) {
												case 1: {
													DebitAccount.addAccount();
													break;
												}
												case 0: {
													option = -5;
													break;
												}
												default: {
													option = 0;
													System.out.println("  Enter a valid option!");
													break;
												}
												}
											} while (option != -5);
											break;
										}
										case 2: {
											do {
												// Debit Menu
												System.out.println("      Loan Account Menu\n");
												System.out.println("  1 - New Account");
												System.out.println("  2 - Option");
												System.out.println("  3 - Options\n");
												System.out.println("  0 - Back\n");
												System.out.print(">> ");
												option = keyboard.nextInt();
												keyboard.nextLine();
												switch (option) {
												case 1: {
													LoanAccount.addAccount();
													break;
												}
												case 0: {
													option = -6;
													break;
												}
												default: {
													option = 0;
													System.out.println("  Enter a valid option!");
													break;
												}
												}
											} while (option != -6);
											break;											
										}
										case 0: {
											option = -4;
											break;
										}
										default: {
											option = 0;
											System.out.println("  Enter a valid option!");
											break;
										}
										}
									} while (option != -4);
									break;
								}
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
	
	public static void promptEnterKey() {
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
