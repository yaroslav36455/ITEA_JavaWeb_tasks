package ua.itea.controller.view;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import ua.itea.controller.api.handler.products.NoSuchProductCategoryException;
import ua.itea.dao.DAOFactory;
import ua.itea.dao.ProductsDAO;
import ua.itea.model.products.Category;

@Controller
@RequestMapping(value = "view/products")
public class ProductsController {
	@Autowired
	private DAOFactory daoFactory;

	@GetMapping
	public String products(HttpServletRequest request) {
		ProductsDAO productsDAO = daoFactory.getProductsDAO();
	    Object obj = productsDAO.getProducts();
	    
	    request.setAttribute("items", obj);
		return "products";
	}
	
	@GetMapping(params = {"category"})
	public String products(@RequestParam("category") String category,
						   HttpServletRequest request)
								   	   throws NoSuchProductCategoryException {
		try {
			Category c = Enum.valueOf(Category.class, category);
			ProductsDAO productsDAO = daoFactory.getProductsDAO();
		    Object obj = productsDAO.getProducts(c);
		    
		    request.setAttribute("items", obj);
			return "products";	
		} catch (IllegalArgumentException e) {
			throw new NoSuchProductCategoryException();
		}
	}
	
	
	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY, reason = "No such product category")
	@ExceptionHandler(value = {NoSuchProductCategoryException.class})
	public void exceptionHandler() {
		/* empty */
	}
}
