package ua.itea.controller.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = -3007201548968146383L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
									    throws ServletException, IOException {		
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/view/cart.jsp");
		rd.forward(req, resp);	
	}
}
