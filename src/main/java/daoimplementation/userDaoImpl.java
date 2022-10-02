package daoimplementation;

import org.hibernate.Session;

import config.hibernateUtil;
import dao.userDao;
import exception.GlobalException;
import model.user;

public class userDaoImpl implements userDao {

	@Override
	public user viewRoom(int uId) {
		// TODO Auto-generated method stub
		try (Session ses = hibernateUtil.getSession()) {

			user u2 = ses.get(user.class, uId);
			return u2;
		}
	}

	@Override
	public int viewDueAmount(int uId) {
		// TODO Auto-generated method stub
		try (Session ses = hibernateUtil.getSession()) {

			int amount = (int) ses.createQuery("select userFee from user where userId=:uId").setParameter("uId", uId)
					.uniqueResult();
			return amount;
		}
	}

	@Override
	public user viewProfile(int uId) {
		// TODO Auto-generated method stub
		try (Session ses = hibernateUtil.getSession()) {

			user u2 = ses.get(user.class, uId);
			return u2;
		}
	}

	@Override
	public int changePhone(int uId, String phone) {
		// TODO Auto-generated method stub
		try (Session ses = hibernateUtil.getSession()) {
			ses.beginTransaction();
			int status = ses.createQuery("update user set userPhone=:phone where userId=:uId")
					.setParameter("phone", phone).setParameter("uId", uId).executeUpdate();
			ses.getTransaction().commit();
			return status;
		}
	}

	@Override
	public int changePassword(int uId, String oldPwd, String newPwd) throws GlobalException {
		// TODO Auto-generated method stub
		try (Session ses = hibernateUtil.getSession()) {
			ses.beginTransaction();
			user u1 = ses.get(user.class, uId);
			if (u1.getUserPassword().equals(oldPwd)) {
				int status = ses.createQuery("update user set userPassword=:newPwd where uId=:uId")
						.setParameter("newPwd", newPwd).setParameter("uId", uId).executeUpdate();
				ses.getTransaction().commit();
				return status;
			} else {
				throw new GlobalException("To update password you have to enter current password");
			}
		}
	}
}