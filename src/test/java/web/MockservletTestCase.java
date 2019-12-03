package web;

import java.util.Properties;

import com.mockobjects.dynamic.Mock;
import com.mockrunner.servlet.BasicServletTestCaseAdapter;

import db.DaoFactory;
import db.MockDaoFactory;
import db.MockUserDao;

public abstract class MockServletTestCase extends BasicServletTestCaseAdapter {

	private Mock mockUserDao;
	
	protected void setUp() throws Exception {
		Properties prop = new Properties();
		prop.setProperty("dao.factory", MockDaoFactory.class.getName());
		DaoFactory.init(prop);
		setMockUserDao(((MockDaoFactory)DaoFactory.GetInstance()).GetMockUserDao());
		super.setUp();
	}

	public Mock getMockUserDao() {
		return mockUserDao;
	}

	public void setMockUserDao(Mock mockUserDao) {
		this.mockUserDao = mockUserDao;
	}

	protected void tearDown() throws Exception {
		getMockUserDao().verify();
		super.tearDown();
	}

}
