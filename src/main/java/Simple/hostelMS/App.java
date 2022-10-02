package Simple.hostelMS;

import java.util.Scanner;

import org.apache.log4j.Logger;

import exception.GlobalException;
import service.loginRegister;
import serviceImpl.loginRegisterImpl;

public class App {
	static Logger log = Logger.getLogger(App.class);

	public static void main(String[] args) throws GlobalException {
		int choose = 0;
		Scanner sc = new Scanner(System.in);
		log.info("\t\t\t\t\t---------Hostel Management System----------");
		loginRegister loginreg = new loginRegisterImpl();

		while (choose < 3) {
			log.info("\nPress 1 for Registeration\nPress 2 for Login\nPress 3 to Exit");
			choose = sc.nextInt();

			switch (choose) {
				case 1 -> loginreg.register();   //Register Method for new users.
				
				case 2 -> loginreg.login();		//Login Method for existing users.
			}
		}
		sc.close();
	}
}