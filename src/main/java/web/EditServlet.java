package web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DaoFactory;
import db.DatabaseException;
import nure.cs.vodotyka.usermanagment.User;

public class EditServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(req.getParameter("okButton") != null){
			doOk(req, resp);
		} else if(req.getParameter("cancelButton") != null){
			doCancel();
		} else{
			ShowPage();
		}
	}

	private void ShowPage() {
		// TODO Auto-generated method stub
		
	}

	private void doCancel() {
		// TODO Auto-generated method stub
		
	}

	private void doOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = GetUser(req);
		try {
			ProccesUser(user);
		} catch (DatabaseException e) {
			
		}
		req.getRequestDispatcher("/browse").forward(req, resp);
	}

	private User GetUser(HttpServletRequest req) {
		User user = new User();
		String idStr = req.getParameter("id");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String dateStr = req.getParameter("date");
		
		if(idStr != null){
			user.setId(new Long(idStr));
		}
		user.setFirstName(firstName);
		user.setLastName(lastName);
		try {
			user.setDateOfBirth(DateFormat.getDateInstance().parse(dateStr));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return user;
	}

	private void ProccesUser(User user) throws DatabaseException {
		DaoFactory.GetInstance().GetUserDao().UpdateUser(user);
	}

	
}
