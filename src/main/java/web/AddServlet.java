package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nure.cs.vodotyka.usermanagment.User;
import db.DaoFactory;
import db.DatabaseException;

public class AddServlet extends EditServlet {

	@Override
	protected void ShowPage(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/add.jsp").forward(req, resp);
	}

	@Override
	protected void ProccesUser(User user) throws DatabaseException {
		DaoFactory.GetInstance().GetUserDao().CreateUser(user);
	}

	
}
