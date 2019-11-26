package db;

import com.mockobjects.dynamic.Mock;

public class MockDaoFactory extends DaoFactory {

	private Mock mockUserDao;
	
	public MockDaoFactory(){
		mockUserDao = new Mock(UserDao.class);
	}
	
	public Mock GetMockUserDao(){
		return mockUserDao;
	}
	
	@Override
	public UserDao GetUserDao() {
		return (UserDao)mockUserDao.proxy();
	}

}
