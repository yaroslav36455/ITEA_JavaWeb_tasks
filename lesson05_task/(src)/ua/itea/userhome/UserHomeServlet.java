package ua.itea.userhome;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.itea.SessionAttributeManager;
import ua.itea.User;

public class UserHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1609646886072837570L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SessionAttributeManager sam = new SessionAttributeManager(req.getSession());
		
		if(sam.getAttribute(User.class) != null) {
			resp.sendRedirect("/lesson05_task/user-home.jsp");
		} else {
			resp.sendRedirect("/sign-in");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SessionAttributeManager sam = new SessionAttributeManager(req.getSession());
		
		sam.removeAttribute(User.class);
		doGet(req, resp);
	}
}
