package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import nure.cs.vodotyka.usermanagment.User;

import java.sql.PreparedStatement;
import java.sql.CallableStatement;

public class HsqlDbUserDao implements UserDao {

	private static final String SELECT_ALL_QUERY = "SELECT * FROM users";
	private static final String INSERT_QUERY = "INSERT INTO users (firstname, lastname, dateofbirth) VALUES(?,?,?)";
	private ConnectionFactory connectionFactory;
	
	public HsqlDbUserDao(){
		
	}
	
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

	public void UpdateUser(User user) throws DatabaseException {
		// TODO Auto-generated method stub

	}

	public void DeleteUser(long userId) throws DatabaseException {
		// TODO Auto-generated method stub

	}

	public User GetUser(long userId) throws DatabaseException {
		try {
			Connection dbConnection = connectionFactory.CreateConnection();
			PreparedStatement statement = dbConnection.prepareStatement("SELECT * FROM users AS u WHERE u.id = ?");
			statement.setLong(1, userId);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()){
				User user = new User();
				user.setId(new Long(resultSet.getLong(1)));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setDateOfBirth(resultSet.getDate(4));
				
				return user;
			}
			else{
				return null;
			}
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	public Collection GetAll() throws DatabaseException {
		try {
			Collection result = new LinkedList();
			Connection dbConnection = connectionFactory.CreateConnection();
			Statement statement = dbConnection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
			while(resultSet.next()){
				User user = new User();
				user.setId(new Long(resultSet.getLong(1)));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setDateOfBirth(resultSet.getDate(4));
				result.add(user);
			}
			
			return result;
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	public void SetConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
		
	}

}
