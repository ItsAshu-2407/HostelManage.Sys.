package Simple.hostelMS;

import config.hibernateUtil;
import dao.adminDao;
import dao.hostelmsDao;
import dao.userDao;
import daoimplementation.adminDaoImpl;
import daoimplementation.hostelmsDaoImpl;
import daoimplementation.userDaoImpl;
import exception.GlobalException;
import model.user;

import org.hibernate.Session;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit test for simple App.
 */
public class AppTest {
	//Test 1.
	@Test
	@DisplayName("   TESTING REGISTRATION   ")
	void registrationTest() {
		hostelmsDao dao = new hostelmsDaoImpl() ;
		
		user u2 = new user();			//new user inserted.
		u2.setUserName("Nishant");
		u2.setUserPassword("nis123");
		u2.setUserFee(20000);
		u2.setUserAddress("Delhi");
		u2.setUserPhone("8212365475");
		
		user u1 = new user();			//Checked for Existed User.
		u1.setUserName("adminashu");
		u1.setUserPassword("ashu123");
		u1.setUserPhone("7033528099");
		u1.setUserRole("admin");
		 

		
		
		assertAll(
				
				//Positive test case as new user is inserted here.
				()->assertEquals(1,dao.registration(u2)),
				
				//Negative Test case as we have checked the existing user so we are getting Global Exception.
				()->assertThrows ( GlobalException.class,()->dao.registration(u1))
				);
	}
	
	//Test 2.
	@Test
	@DisplayName("	Login Testing	")
	void testLogin() throws GlobalException  {
		
		hostelmsDao dao = new hostelmsDaoImpl();
		
		Session ses = hibernateUtil.getSession();
		
		user u1 = ses.get(user.class, 1);
		
		user u2 = ses.get(user.class, 1);
		
		assertAll(
				()->assertEquals(u1.toString(),u2.toString()),
				
				()->assertThrows ( GlobalException.class,()->dao.login("adminashu", "ashu123"))
				
				);
	}		
		@Test
		@DisplayName(" Delete user Test ")
		void deletUserTest() {
			
			adminDao dao = new adminDaoImpl();
			
			assertAll(
					()->assertEquals(1,dao.deleteUser(1)),
					
					()->assertThrows(GlobalException.class,()->dao.deleteUser(20))
					);
		}
		
		@Test
		@DisplayName("	Change Password	")
		void testChangePassword() {
			
			userDao dao = new userDaoImpl();
			
			assertAll(
					()->assertEquals(1,dao.changePassword(3,"nis123","nishant123")),
					
					()->assertThrows(GlobalException.class,()->dao.changePassword(8,"golu214","sandy245"))
					);
			
		}
}