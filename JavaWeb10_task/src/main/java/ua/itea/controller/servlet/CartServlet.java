package ua.itea.controller.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.itea.controller.ProductsCart;
import ua.itea.controller.SessionAttributeManager;
import ua.itea.model.products.Product;
import ua.itea.service.ProductsService;

public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = -3007201548968146383L;
	private ProductsService productsService;

	{
		productsService = new ProductsService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
									    throws ServletException, IOException {		
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/view/cart.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
												throws ServletException, IOException {
		SessionAttributeManager sam = new SessionAttributeManager(req.getSession());
		ProductsCart productsCart = sam.getAttribute(ProductsCart.class);

		try {
			Integer productId = Integer.valueOf(req.getParameter("productId"));
			Integer layOut = Integer.valueOf(req.getParameter("count"));
			
			Product product = productsService.getProduct(productId);
			
			if (layOut <= 0) {
				throw new IllegalArgumentException("Non positive products count");
			}

			if (product == null) {
				throw new IllegalArgumentException("Incorrect product id");
			}

			Integer oldCount = 0;
			Integer newCount = 0;
			if(productsCart.containsKey(product)) {
				oldCount = productsCart.get(product);
				newCount = oldCount - layOut;
				
				if(newCount > 0) {
					productsCart.put(product, newCount);
				} else {
					productsCart.remove(product);
				}
			}
			
			resp.addHeader("Content-Type", "text/plain");
			resp.getWriter().write(productsCart.toString() + "|" + newCount);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}
}
