package ua.itea.signin;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.itea.SessionAttributeManager;
import ua.itea.form.SignInBlockerForm;
import ua.itea.form.SignInForm;

public class SignInBlockerServlet extends HttpServlet {
	private static final long serialVersionUID = -8212769279029106492L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
										throws ServletException, IOException {
		SessionAttributeManager sam = new SessionAttributeManager(req.getSession());
		SignInBlocker signInBlocker = sam.getAttribute(SignInBlocker.class);
		long currentTime = new Date().getTime();
		
		if(!signInBlocker.isCapchaValid()) {
			signInBlocker.setCapcha(new Capcha(req.getParameter("capcha")));	
		}
		
		if(signInBlocker.isBlocked(currentTime)) {
			setSignInBlockedForm(sam, currentTime);
			resp.sendRedirect("/lesson06_task/sign-in-blocker.jsp");
		} else {
			signInBlocker.unblock();
			
			SignInForm sif = sam.getAttribute(SignInForm.class);
			sif.setAccessDenied(false);
			
			resp.sendRedirect("/sign-in");
		}
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
}
