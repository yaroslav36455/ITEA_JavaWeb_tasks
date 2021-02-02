package ua.itea.controller.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductsServlet extends HttpServlet {
	private static final long serialVersionUID = -8338609146119320425L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
											throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/view/products.jsp");
		rd.forward(req, resp);
	}
}
