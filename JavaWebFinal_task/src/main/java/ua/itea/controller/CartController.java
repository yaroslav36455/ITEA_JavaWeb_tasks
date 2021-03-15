package ua.itea.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.itea.controller.api.CartStateChanged;
import ua.itea.controller.api.Products;
import ua.itea.dao.DAOFactory;
import ua.itea.dao.ProductsDAO;
import ua.itea.model.products.Cart;
import ua.itea.model.products.Product;

@RestController
@RequestMapping(value = "cart")
public class CartController {
	
	@Autowired
	private DAOFactory daoFactory;
	
	public DAOFactory getDAOFactory() {
		return daoFactory;
	}

	public void setDAOFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@PutMapping(value = "add")
	public CartStateChanged addProducts(@RequestBody Products products,
						                HttpSession session) {
		SessionAttributeManager sam = new SessionAttributeManager(session);
		ProductsDAO productsDAO = daoFactory.getProductsDAO();
		Cart cart = sam.getAttribute(Cart.class);
		Product product = productsDAO.getProduct(products.getProductId());
		
		Integer newCount = cart.put(product, products.getCount());
		
		CartStateChanged response = new CartStateChanged();
		response.setConcernedProductCount(newCount);
		response.setTotalProductCount(cart.getCount());
		return response;
	}
	
	@DeleteMapping(value = "remove")
	public CartStateChanged removeProducts(@RequestBody Products products,
            		                       HttpSession session) {
		SessionAttributeManager sam = new SessionAttributeManager(session);
		ProductsDAO productsDAO = daoFactory.getProductsDAO();
		Cart cart = sam.getAttribute(Cart.class);
		Product product = productsDAO.getProduct(products.getProductId());
		
		Integer newCount = cart.layOut(product, products.getCount());
		
		CartStateChanged response = new CartStateChanged();
		response.setConcernedProductCount(newCount);
		response.setTotalProductCount(cart.getCount());
		return response;
	}
}
