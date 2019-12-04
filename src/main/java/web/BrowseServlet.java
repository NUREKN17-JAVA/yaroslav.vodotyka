package web;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nure.cs.vodotyka.usermanagment.User;
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
		}else{
			Browse(req, resp);
		}
		
	}

	private void Details(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idStr = req.getParameter("id");
		if(idStr == null || idStr.trim().length() == 0){
			req.setAttribute("error", "You must select a user");
		} else{
			try {
				User user = DaoFactory.GetInstance().GetUserDao().GetUser(new Long(idStr));
				req.getSession().setAttribute("user", user);
			} catch (DatabaseException e) {
				req.setAttribute("error", "Error" + e.toString());
				req.getRequestDispatcher("/browse.jsp").forward(req,  resp);
				return;
			}
			req.getRequestDispatcher("/details.jsp").forward(req,  resp);
		}
	}

	private void Delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idStr = req.getParameter("id");
		if(idStr == null || idStr.trim().length() == 0){
			req.setAttribute("error", "You must select a user");
		} else{
			try {
				DaoFactory.GetInstance().GetUserDao().DeleteUser(new Long(idStr));
			}  catch (DatabaseException e) {
				req.setAttribute("error", "Error" + e.toString());
				req.getRequestDispatcher("/browse.jsp").forward(req,  resp);
				return;
			}
			req.getRequestDispatcher("/browse.jsp").forward(req, resp);
		}
	}

	private void Edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idStr = req.getParameter("id");
		if(idStr == null || idStr.trim().length() == 0){
			req.setAttribute("error", "You must select a user");
		} else{
			try {
				req.getRequestDispatcher("/browse.jsp").forward(req, resp);
			} catch (ServletException | IOException e) {
				req.setAttribute("error", "Error" + e.toString());
			}
			try{
				User user = DaoFactory.GetInstance().GetUserDao().GetUser(new Long(idStr));
				req.getSession().setAttribute("user", user);
			} catch(DatabaseException ex){
				req.setAttribute("error", "Error" + ex.toString());
				req.getRequestDispatcher("/browse.jsp").forward(req,  resp);
				return;
			}
			req.getRequestDispatcher("/edit.jsp").forward(req,  resp);
		}
	}

	private void Add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/add").forward(req, resp);
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
