package ua.itea.controller.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.itea.controller.SessionAttributeManager;
import ua.itea.controller.cart.Products;
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
//		SessionAttributeManager sam = new SessionAttributeManager(req.getSession());
//		sam.ensureAttribute(Products.class, "Cart");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
										throws ServletException, IOException {
		SessionAttributeManager sam = new SessionAttributeManager(req.getSession());
		Products products = sam.getAttribute(Products.class, "Cart");
		
		if(products == null) {
			products = new Products();
			sam.setAttribute(products, "Cart");
		}
		
		try {
			Integer productId = Integer.valueOf(req.getParameter("productId"));
			Product product = productsService.getProduct(productId);
			
			if(product != null) {
				products.add(product);
			} else {
				throw new IllegalArgumentException();
			}
			
			resp.sendRedirect("products");	
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			resp.sendError(404);
		}
	}
}
