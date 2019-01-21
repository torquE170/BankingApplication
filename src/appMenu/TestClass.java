package appMenu;

import java.util.Date;
import java.util.Scanner;
import dbAccess.DebitAccountDao;
import resources.Account;
import resources.DebitAccount;

public class TestClass {

	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		
		DebitAccount myAccount = new DebitAccount("AID", "ID", 16, 15, 14, 14, new Date());
		DebitAccountDao.saveAccount(myAccount);
	
		keyboard.close();
	}

}
