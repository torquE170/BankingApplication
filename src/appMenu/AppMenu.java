package appMenu;

import administration.Login;
import administration.User;
import dbAccess.UserDao;

public class AppMenu {

	public static void main(String[] args) {
		
		User currentUser = Login.loginUser();
//		User.activateUser(currentUser);
		User.resetUser(currentUser);
	}

}
