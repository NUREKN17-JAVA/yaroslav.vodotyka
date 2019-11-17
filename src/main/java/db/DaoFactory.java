package db;

import java.io.IOException;
import java.util.Properties;

public class DaoFactory {

	private static final String USER_DAO = "db.UserDao";
	private final Properties prop;
	
	private final static DaoFactory INSTANCE = new DaoFactory();
	
	private DaoFactory() {
		prop = new Properties();
		try{
			prop.load(getClass().getClassLoader().getResourceAsStream("settings.properties"));
		}
		catch(IOException e){
			throw new RuntimeException(e);
		}
	}

	public static DaoFactory GetInstance(){
		return INSTANCE;
	}
	
	private ConnectionFactory GetConnectionFactory(){
		String user = prop.getProperty("connection.user");
		String url = prop.getProperty("connection.url");
		String driver = prop.getProperty("connection.driver");
		String password = prop.getProperty("connection.password");
		
		return new ConnectionFactoryImpl(driver, url, user, password);
	}
	
	public UserDao GetUserDao(){
		UserDao userDao = null;
		try {
			Class type = Class.forName(prop.getProperty(USER_DAO));
			userDao = (UserDao)type.newInstance();
			userDao.SetConnectionFactory(GetConnectionFactory());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		return userDao;
		
	}
}
