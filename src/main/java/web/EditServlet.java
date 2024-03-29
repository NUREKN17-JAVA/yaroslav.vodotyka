package web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;

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
			doCancel(req, resp);
		} else{
			ShowPage(req, resp);
		}
	}

	protected void ShowPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/edit.jsp").forward(req, resp);
	}

	private void doCancel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/browse.jsp").forward(req, resp);
	}

	private void doOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = null;
		try {
			user = GetUser(req);
		} catch (ValidationException e1) {
			req.setAttribute("error", e1.getMessage());
			ShowPage(req, resp);
			return;
		}
		try {
			ProccesUser(user);
		} catch (DatabaseException e) {
			
		}
		req.getRequestDispatcher("/browse").forward(req, resp);
	}

	private User GetUser(HttpServletRequest req) throws ValidationException {
		User user = new User();
		String idStr = req.getParameter("id");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String dateStr = req.getParameter("date");
		
		if(firstName == null){
			throw new ValidationException("Last name is empty");
		}
		
		if(lastName == null){
			throw new ValidationException("First name is empty");
		}
		if(dateStr == null){
			throw new ValidationException("Date is empty");
		}
		if(idStr != null){
			user.setId(new Long(idStr));
		}
		user.setFirstName(firstName);
		user.setLastName(lastName);
		try {
			user.setDateOfBirth(DateFormat.getDateInstance().parse(dateStr));
		} catch (ParseException e) {
			throw new ValidationException("Date format error");
		}
		return user;
	}

	protected void ProccesUser(User user) throws DatabaseException {
		DaoFactory.GetInstance().GetUserDao().UpdateUser(user);
	}

	
	
}
