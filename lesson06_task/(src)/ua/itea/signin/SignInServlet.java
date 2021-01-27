package ua.itea.signin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.itea.Authentication;
import ua.itea.SessionAttributeManager;
import ua.itea.User;
import ua.itea.db.Connector;
import ua.itea.db.UsersDB;
import ua.itea.form.SignInBlockerForm;
import ua.itea.form.SignInForm;
import ua.itea.form.UserHomeForm;

public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = -4015585978293209110L;
	private Connector connector;
	private UsersDB usersDB;

	{
		connector = new Connector();
		try {
			usersDB = new UsersDB(connector.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SessionAttributeManager sam = new SessionAttributeManager(req.getSession());
		
		if(sam.getAttribute(User.class) != null) {
			resp.sendRedirect("/user-home");
		} else {
			long currentTime = new Date().getTime();
			if (sam.getAttribute(SignInBlocker.class).isBlocked(currentTime)) {
				resp.sendRedirect("/lesson06_task/sign-in-blocker.jsp");	
			} else {
				resp.sendRedirect("/lesson06_task/sign-in.jsp");	
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SessionAttributeManager sam = new SessionAttributeManager(req.getSession());
		SignInBlocker signInBlocker = sam.getAttribute(SignInBlocker.class);
		
		String login = (String) req.getParameter("login");
		String password = (String) req.getParameter("password");
		
		try {
			User user = usersDB.getUser(connector.getConnection(),
										new Authentication(login, password));
			
			if(user != null) {
				sam.setAttribute(user);
				sam.removeAttribute(SignInBlocker.class);
				sam.removeAttribute(SignInForm.class);
				setUserHomeForm(sam, user);
			} else {
				signInBlocker.captureMistake();
				
				long currentTime = new Date().getTime();
				if(signInBlocker.isBlocked(currentTime)) {
					setSignInBlockedForm(sam, currentTime);
				} else {
					SignInForm sif = sam.getAttribute(SignInForm.class);
					sif.setLogin(login);
					sif.setAccessDenied(true);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		doGet(req, resp);
	}
	
	private void setSignInBlockedForm(SessionAttributeManager sam, long currentTime) {
		SignInBlocker signInBlocker = sam.getAttribute(SignInBlocker.class);
		SignInBlockerForm signInBlockerForm = sam.getAttribute(SignInBlockerForm.class);;
		
		long blockingTime = currentTime - signInBlocker.getLastBlockTime();
		long remainingTime = SignInBlocker.getBlockTimeMilliseconds() - blockingTime;
		
		signInBlockerForm.setShowCapcha(!signInBlocker.isCapchaValid());
		signInBlockerForm.setCapcha(signInBlocker.getGeneratedCapcha());
		signInBlockerForm.setRemainingTime(remainingTime);
	}

	private void setUserHomeForm(SessionAttributeManager sam, User user) {
		UserHomeForm userHomeForm = sam.getAttribute(UserHomeForm.class);
		
		userHomeForm.setName(user.getName());
		userHomeForm.setLogin(user.getLogin());
		userHomeForm.setEmail(user.getEmail());
		userHomeForm.setAddress(user.getAddress());
		userHomeForm.setGender(user.getGender());
		userHomeForm.setComment(user.getComment());
	}
}
