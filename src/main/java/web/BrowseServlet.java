package web;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DaoFactory;
import db.DatabaseException;

public class BrowseServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(req.getParameter("addButton") != null){
			Add(req, resp);
		}
		else if(req.getParameter("editButton") != null){
			Edit(req, resp);
		}
		else if(req.getParameter("deleteButton") != null){
			Delete(req, resp);
		}
		else if(req.getParameter("detailsButton") != null){
			Details(req, resp);
		}
		Browse(req, resp);
	}

	private void Details(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}

	private void Delete(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}

	private void Edit(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}

	private void Add(HttpServletRequest req, HttpServletResponse resp) {
		
	}

	private void Browse(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
		Collection users;
		try {
			users = DaoFactory.GetInstance().GetUserDao().GetAll();
			req.getSession().setAttribute("users", users);
			req.getRequestDispatcher("/browse.jsp").forward(req, resp);
		} catch (DatabaseException e) {
			throw new ServletException(e);
		} catch (IOException e) {
			throw new ServletException(e);
		}
	}
}
