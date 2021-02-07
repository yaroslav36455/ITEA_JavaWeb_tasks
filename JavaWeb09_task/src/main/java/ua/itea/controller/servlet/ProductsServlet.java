package ua.itea.controller.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.itea.controller.SessionAttributeManager;
import ua.itea.controller.cart.Products;
import ua.itea.model.products.Category;
import ua.itea.service.ProductsService;

public class ProductsServlet extends HttpServlet {
	private static final long serialVersionUID = -8338609146119320425L;
	private ProductsService productsService;

	{
		productsService = new ProductsService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SessionAttributeManager sam = new SessionAttributeManager(req.getSession());
		try {
			String category = req.getParameter("category");
			Products products = new Products();

			products = new Products();

			if (category == null) {
				products.addAll(productsService.getProductsAll());
			} else {
				Category c = Enum.valueOf(Category.class, category);
				products.addAll(productsService.getProductsByCategory(c));
			}
			sam.setAttribute(products, "Rack");

			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/view/products.jsp");
			rd.forward(req, resp);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			resp.sendError(404);
		}
	}
}
