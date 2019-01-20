package appMenu;

import java.util.Scanner;

import resources.Account;

public class TestClass {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		
		Account myAccount = new Account();
		myAccount.setAccountId(myAccount.generateAccount());
		System.out.println("Account: " + myAccount.getAccountId());
		
		keyboard.close();
	}

}
