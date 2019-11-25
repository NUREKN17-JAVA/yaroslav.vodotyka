package db;

import java.io.IOException;
import java.util.Properties;

public abstract class DaoFactory {

	private static final String DAO_FACTORY = "dao.factory";
	protected static final String USER_DAO = "db.UserDao";
	protected static Properties prop;
	
	private static DaoFactory instance;
	
	static{
		prop = new Properties();
		try{
			prop.load(DaoFactory.class.getClass().getClassLoader().getResourceAsStream("settings.properties"));
		}
		catch(IOException e){
			throw new RuntimeException(e);
		}
	}
	
	protected DaoFactory() {
	}

	public static synchronized DaoFactory GetInstance(){
		if(instance == null){
			try {
				Class factoryClass = Class.forName(prop.getProperty(DAO_FACTORY));
				instance = (DaoFactory)factoryClass.newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return instance;
	}
	
	public static void init(Properties propParam){
		prop = propParam;
		instance = null;
	}
	
	protected ConnectionFactory GetConnectionFactory(){
		return new ConnectionFactoryImpl(prop);
	}
	
	public abstract UserDao GetUserDao();
}
