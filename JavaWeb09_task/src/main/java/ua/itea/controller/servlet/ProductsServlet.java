package ua.itea.controller.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.itea.controller.ProductsCart;
import ua.itea.controller.ProductsRack;
import ua.itea.controller.SessionAttributeManager;
import ua.itea.model.products.Category;
import ua.itea.model.products.Product;
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
			ProductsRack productsRack = new ProductsRack();

			productsRack = new ProductsRack();

			if (category == null) {
				productsRack.addAll(productsService.getProductsAll());
			} else {
				Category c = Enum.valueOf(Category.class, category);
				productsRack.addAll(productsService.getProductsByCategory(c));
			}
			sam.setAttribute(productsRack);

			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/view/products.jsp");
			rd.forward(req, resp);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			resp.sendError(404);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
												throws ServletException, IOException {
		SessionAttributeManager sam = new SessionAttributeManager(req.getSession());
		ProductsCart productsCart = sam.getAttribute(ProductsCart.class);

		try {
			Integer productId = Integer.valueOf(req.getParameter("productId"));
			Integer add = Integer.valueOf(req.getParameter("count"));
			
			Product product = productsService.getProduct(productId);
			
			if (add <= 0) {
				throw new IllegalArgumentException("Non positive products count");
			}

			if (product == null) {
				throw new IllegalArgumentException("Incorrect product id");
			}

			Integer count = productsCart.get(product);
			Integer oldCount = count == null ? 0 : count;

			productsCart.put(product, oldCount + add);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		
		doGet(req, resp);
	}
}
