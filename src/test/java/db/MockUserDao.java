package db;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import nure.cs.vodotyka.usermanagment.User;

public class MockUserDao implements UserDao {

	private long id = 0;
	private Map users = new HashMap();
	
	@Override
	public User CreateUser(User user) throws DatabaseException {
		Long currId = new Long(++id);
		user.setId(currId);
		users.put(currId, user);
		return user;
	}

	@Override
	public void UpdateUser(User user) throws DatabaseException {
		Long currId = user.getId();
		users.remove(currId);
		users.put(currId, user);
	}

	@Override
	public void DeleteUser(long userId) throws DatabaseException {
		Long currId = new Long(userId);
		users.remove(currId);
	}

	@Override
	public User GetUser(long userId) throws DatabaseException {
		Long currId = new Long(userId);
		return (User)users.get(currId);
	}

	@Override
	public Collection GetAll() throws DatabaseException {
		return users.values();
	}

	@Override
	public void SetConnectionFactory(ConnectionFactory connectionFactory) {
		// TODO Auto-generated method stub

	}

}