package serviceImpl;

import java.util.Scanner;

import org.apache.log4j.Logger;

import dao.userDao;
import daoimplementation.userDaoImpl;
import exception.GlobalException;
import model.user;
import service.userDashboard;

public class userDashboardImpl implements userDashboard {
	// declaring static objects to use in entire class
	static Logger log = Logger.getLogger(userDashboardImpl.class);
	static Scanner sc = new Scanner(System.in);
	static userDashboardImpl udl = new userDashboardImpl();
	static userDao dao = new userDaoImpl();
	static int userId;

	@Override
	public void dashboard(int userId) throws GlobalException {
		// TODO Auto-generated method stub
		log.info("\t\t\t---------------------Welcome to userdashboard----------------------");
		int op = 0;
		userId = userId;
		while (op < 6) {
			// user can select operation
			log.info(
					"\nPress 1 for viewRoom\nPress 2 for view dueAmount \nPress 3 for view profile\nPress 4 for Update Phone number \nPress 5 for Change password");

			op = sc.nextInt();

			switch (op) {

				case 1 -> udl.viewRoom();

				case 2 -> udl.viewDueAmount();

				case 3 -> udl.viewProfile();

				case 4 -> udl.changePhonenumber();

				case 5 -> udl.changePassword();
			}
		}
	}

	@Override
	public void viewRoom() {
		// TODO Auto-generated method stub
		user u1 = dao.viewRoom(userId);
		log.info("Hello " + u1.getUserName() + " your room number is" + u1.getUserRoom().getRoomId() + " room name is "
				+ u1.getUserRoom().getRoomName() + " and it is " + u1.getUserRoom().getRoomType() + " room");
	}

	@Override
	public void viewDueAmount() {
		// TODO Auto-generated method stub
		int amount = dao.viewDueAmount(userId);
		log.info("your fee due upto this month is :" + amount);
	}

	@Override
	public void viewProfile() {
		// TODO Auto-generated method stub
		user u1 = dao.viewProfile(userId);
		log.info(u1);
	}

	@Override
	public void changePhonenumber() {
		// TODO Auto-generated method stub
		log.info("Enter New Phone number");
		String phone = sc.next();
		int st = dao.changePhone(userId, phone);
		if (st == 1) {
			log.info("Phone number updated");
		}
	}

	@Override
	public void changePassword() throws GlobalException {
		// TODO Auto-generated method stub
		log.info("Enter Current Password");
		String oldpwd = sc.next();
		log.info("Enter New Password");
		String newpwd = sc.next();
		int st = dao.changePassword(userId, oldpwd, newpwd);
		if (st == 1) {
			log.info("password changed");
		}
	}
}
