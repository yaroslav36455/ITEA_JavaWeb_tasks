package ua.itea.controller.view;

import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.itea.controller.api.util.SessionAttributeManager;
import ua.itea.dao.DAOFactory;
import ua.itea.dao.ProductsDAO;
import ua.itea.model.products.Cart;
import ua.itea.model.products.Product;

@Controller
@RequestMapping(path = "view/cart")
public class CartController {
	@Autowired
	private SessionAttributeManager sam;
	@Autowired
	private DAOFactory daoFactory;
	
	@GetMapping
	public String cart(HttpServletRequest request) {
		ProductsDAO productsDAO = daoFactory.getProductsDAO();
		Cart cart = sam.getAttribute(Cart.class);
		Set<Product> items = new TreeSet<>((p1, p2)->Integer.compare(p1.getId(), p2.getId()));
		
		items.addAll(productsDAO.getProducts(cart.getIds()));
		
	    request.setAttribute("items", items);
		return "cart";
	}
}
