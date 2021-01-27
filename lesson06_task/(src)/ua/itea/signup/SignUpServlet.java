package ua.itea.signup;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.itea.SessionAttributeManager;
import ua.itea.db.Connector;
import ua.itea.db.UsersDB;
import ua.itea.form.SignUpForm;

public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 8194121379642672190L;
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
		SignUpForm signUpForm = sam.getAttribute(SignUpForm.class);
		SignUpBuilder sub = new SignUpBuilder();
		SignUpHandler suh = new SignUpHandler();
		
		suh.check(sub);
		signUpForm.fill(sub, suh);
		showSignUpForm(resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SessionAttributeManager sam = new SessionAttributeManager(req.getSession());
		SignUpBuilder sub = new SignUpBuilder();
		SignUpHandler suh = new SignUpHandler();

		sub.setName(req.getParameter("name"));
		sub.setLogin(req.getParameter("login"));
		sub.setPassword(req.getParameter("password"));
		sub.setRetypePassword(req.getParameter("retypePassword"));
		sub.setEmail(req.getParameter("email"));
		sub.setAddress(req.getParameter("address"));
		sub.setGender(req.getParameter("gender"));
		sub.setComment(req.getParameter("comment"));
		sub.setAmigaAgree(req.getParameter("agree"));

		suh.check(sub);
		
		try {
			if(suh.isCorrect()) {
				if(usersDB.addUser(connector.getConnection(), sub.createUser())) {
					sam.removeAttribute(SignUpForm.class);
					showSignUpSuccessForm(resp);
				} else {
					SignUpForm suf = sam.getAttribute(SignUpForm.class);

					suf.fill(sub, suh);
					suf.setLoginTip("Login or email is already occupied by someone." + suf.getLoginTip());
					suf.setEmailTip("Login or email is already occupied by someone." + suf.getEmailTip());
					showSignUpForm(resp);
				}
			} else {
				SignUpForm suf = sam.getAttribute(SignUpForm.class);
				suf.fill(sub, suh);
				showSignUpForm(resp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void showSignUpSuccessForm(HttpServletResponse resp) throws IOException {
		resp.sendRedirect("/lesson06_task/sign-up-success.jsp");
	}

	private void showSignUpForm(HttpServletResponse resp) throws IOException {
		resp.sendRedirect("/lesson06_task/sign-up.jsp");
	}
}
