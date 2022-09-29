package Simple.hostelMS;
import java.util.Scanner;

import org.apache.log4j.Logger;

import exception.GlobalException;
import service.loginRegister;
import serviceImpl.loginRegisterImpl;


public class App 
{
	static Logger log=Logger.getLogger(App.class);
    public static void main( String[] args )throws GlobalException
    {
    	Scanner bs=new Scanner(System.in);
    	log.info("\t\t\t\t\t---------Hostel Management System----------");
    	loginRegister loginreg = new loginRegisterImpl();
    	log.info("\nPress 1 for Registeration\nPress 2 for Login");
    	int op=bs.nextInt();
    	
    	switch(op) {
    	case 1->loginreg.register();
    	case 2->loginreg.login();
    	}
    	
    	
    	
    }
}