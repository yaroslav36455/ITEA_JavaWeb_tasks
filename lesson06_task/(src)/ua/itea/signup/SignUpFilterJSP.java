package ua.itea.signup;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.itea.SessionAttributeManager;
import ua.itea.form.SignUpForm;

public class SignUpFilterJSP implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filter)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();
		SessionAttributeManager sam = new SessionAttributeManager(session);
		
		if (sam.hasAttribute(SignUpForm.class)) {
			filter.doFilter(req, resp);
		} else {
			response.sendRedirect("/sign-up");
		}
	}

}
