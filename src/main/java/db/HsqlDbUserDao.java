package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import nure.cs.vodotyka.usermanagment.User;

import java.sql.PreparedStatement;
import java.sql.CallableStatement;

public class HsqlDbUserDao implements UserDao {

	private static final String INSERT_QUERY = "INSERT INTO users (firstname, lastname, dateofbirth) VALUES(?,?,?)";
	private ConnectionFactory connectionFactory;
	
	public HsqlDbUserDao(ConnectionFactory connectionFactoryParam){
		connectionFactory = connectionFactoryParam;
	}

	public User CreateUser(User user) throws DatabaseException {
		try {
			Connection dbConnection = connectionFactory.CreateConnection();
			PreparedStatement statement = 
					dbConnection.prepareStatement(INSERT_QUERY);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setDate(3, new Date(user.getDateOfBirth().getTime()));
			
			int rowsAffected = statement.executeUpdate();
			if(rowsAffected != 1){
				throw new DatabaseException("Rows affected on query - " + rowsAffected);
			}
			CallableStatement callable = dbConnection.prepareCall("call IDENTITY()");
			ResultSet keys = callable.executeQuery();
			if(keys.next()){
				user.setId(new Long(keys.getLong(1)));
			}
			
			keys.close();
			callable.close();
			statement.close();
			dbConnection.close();
			
			return user;
		} catch (DatabaseException e){
			throw e;
		}
		catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	public void UpdateUser(long userId) throws DatabaseException {
		// TODO Auto-generated method stub

	}

	public void DeleteUser(long userId) throws DatabaseException {
		// TODO Auto-generated method stub

	}

	public User GetUser(long userId) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection GetAll() throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

}
