package ua.itea.controller.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.itea.controller.SessionAttributeManager;
import ua.itea.controller.signin.Captcha;
import ua.itea.controller.signin.SignInBlocker;
import ua.itea.model.User;
import ua.itea.service.AuthorizationService;

public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = -4015585978293209110L;
	private AuthorizationService as;
	
	{
		as = new AuthorizationService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
									     throws ServletException, IOException {
		SessionAttributeManager sam = new SessionAttributeManager(req.getSession());
		SignInBlocker signInBlocker = sam.getAttribute(SignInBlocker.class);
		String jspFile = "WEB-INF/view/" + (signInBlocker.isBlocked() ? "sign-in-blocker.jsp"
											   					      : "sign-in.jsp");
		
		RequestDispatcher rd = req.getRequestDispatcher(jspFile);
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
										 throws ServletException, IOException {
		SessionAttributeManager sam = new SessionAttributeManager(req.getSession());
		
		if(req.getParameter("sign-out") != null) {
			sam.removeAttribute(User.class);
		} else if (req.getParameter("update-sign-in-blocker") != null) {
			Captcha captcha = new Captcha(req.getParameter("captcha"));
			SignInBlocker signInBlocker = sam.getAttribute(SignInBlocker.class);
			
			signInBlocker.update(captcha);
		} else {
			String login = req.getParameter("login");
			String password = req.getParameter("password");
			
			User user = as.getUser(login, password);
			if(user != null) {
				sam.setAttribute(user);	
				sam.removeAttribute(SignInBlocker.class);
			} else {
				sam.getAttribute(SignInBlocker.class).captureMistake();
			}
		}

		doGet(req, resp);
	}
}
