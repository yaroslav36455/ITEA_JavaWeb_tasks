package ua.itea.controller.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.itea.controller.SessionAttributeManager;
import ua.itea.controller.signup.RegisteringUserBuilder;
import ua.itea.controller.signup.SignUpHandler;
import ua.itea.service.UserService;

public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = -9222458693700400731L;
	private UserService us;

	{
		us = new UserService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
											throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/view/sign-up.jsp");
		SessionAttributeManager sam = new SessionAttributeManager(req.getSession());

		sam.ensureAttribute(RegisteringUserBuilder.class);
		sam.ensureAttribute(SignUpHandler.class);
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
											throws ServletException, IOException {
		SessionAttributeManager sam = new SessionAttributeManager(req.getSession());
		RegisteringUserBuilder registeringUserBuilder = sam.getAttribute(RegisteringUserBuilder.class);
		SignUpHandler signUpHandler = sam.getAttribute(SignUpHandler.class);
		
		registeringUserBuilder.setName((String) req.getParameter("name"));
		registeringUserBuilder.setLogin((String) req.getParameter("login"));
		registeringUserBuilder.setPassword((String) req.getParameter("password"));
		registeringUserBuilder.setRetypePassword((String) req.getParameter("retypePassword"));
		registeringUserBuilder.setEmail((String) req.getParameter("email"));
		registeringUserBuilder.setAddress((String) req.getParameter("address"));
		registeringUserBuilder.setGender((String) req.getParameter("gender"));
		registeringUserBuilder.setComment((String) req.getParameter("comment"));
		registeringUserBuilder.setAmigaAgree((String) req.getParameter("amigaAgree"));

		signUpHandler.check(registeringUserBuilder);
		
		if(signUpHandler.isCorrect()) {
			if(us.setUser(registeringUserBuilder.createUser())) {
				RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/view/sign-up-success.jsp");
				rd.forward(req, resp);
				sam.removeAttribute(registeringUserBuilder);
				sam.removeAttribute(SignUpHandler.class);
			} else {
				RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/view/sign-up-failed.jsp");
				rd.forward(req, resp);
			}
		} else {
			doGet(req, resp);
		}
	}
}
