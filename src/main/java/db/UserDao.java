package db;

import java.util.Collection;

import nure.cs.vodotyka.usermanagment.User;

public interface UserDao {
	User CreateUser(User user) throws DatabaseException;
	
	void UpdateUser(User user) throws DatabaseException;
	
	void DeleteUser(long userId) throws DatabaseException;
	
	User GetUser(long userId) throws DatabaseException;
	
	Collection GetAll() throws DatabaseException;
	
	void SetConnectionFactory(ConnectionFactory connectionFactory);
}
