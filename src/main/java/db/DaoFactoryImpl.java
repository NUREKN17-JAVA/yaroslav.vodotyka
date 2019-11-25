package db;

public class DaoFactoryImpl extends DaoFactory {
	
	public UserDao GetUserDao() {
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
