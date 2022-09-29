package dao;

import exception.GlobalException;
import model.user;

public interface hostelmsDao {

	public int registration(user u1) throws GlobalException;
	public user login(String username,String password) throws GlobalException;
}