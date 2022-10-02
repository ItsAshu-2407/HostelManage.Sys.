package serviceImpl;

import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import Simple.hostelMS.App;
import dao.hostelmsDao;
import daoimplementation.hostelmsDaoImpl;
import exception.GlobalException;
import model.user;
import service.adminDashboard;
import service.loginRegister;
import service.userDashboard;

public class loginRegisterImpl implements loginRegister {
	static Logger log=Logger.getLogger(App.class);
	static Scanner sc=new Scanner(System.in);
	static hostelmsDao dao = new hostelmsDaoImpl();
	
	//registration method
	public void register() throws GlobalException{
		log.info("welcome to registeration");
		log.info("Enter Username");
		String uname=sc.next();
		log.info("Create Password");
		String upwd=sc.next();
		log.info("Enter Phone number");
		String uphone=sc.next();
		log.info("Enter Address");
		String uaddress=sc.next();
		
		user u1=new user();
		u1.setUserName(uname);
		u1.setUserPassword(upwd);
		u1.setUserPhone(uphone);
		u1.setUserAddress(uaddress);
		u1.setUserRole("student");
		u1.setUserRoom(null);
		u1.setUserFee(0);
		//regular expressions to check data correctness
		if(Pattern.matches("[a-zA-Z]{4,}", uname)&&Pattern.matches("[a-zA-Z0-9@#]{6,}",upwd)&&Pattern.matches("[0-9]{10}", uphone))
		{
			//saving the user details
			int status=dao.registration(u1);
			//log.info(status);
			if(status==1) {
				log.info("Registration success");
			}
			else {
				throw new GlobalException("Something went wrong");
			}
		}
		else {
			throw new GlobalException("Invalid data");
		}
}

	public void login()throws GlobalException {
		log.info("welcome to Login");
		log.info("Enter username");
		String username=sc.next();
		log.info("Enter password");
		String password=sc.next();
		user u1=dao.login(username, password);
		log.info("Hello "+u1.getUserName()+" Login Success");
		userDashboard udl = new userDashboardImpl();
		adminDashboard adl=new adminDashboardImpl();
		//if userrole is student userdashboard will open
		//if userrole is admin admindashboard will open
		if(u1.getUserRole().equals("student")) {
			udl.dashboard(u1.getUserId());
		}
		else if(u1.getUserRole().equals("admin")) {
			adl.dashboard();
		}
	}

}