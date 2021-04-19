package ua.itea.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ua.itea.api.Products;
import ua.itea.controller.api.handler.cart.CartHandler;
import ua.itea.controller.api.handler.cart.ProductCountException;
import ua.itea.controller.api.handler.products.NoSuchProductIdException;

@RestController
@RequestMapping(value = "api/cart")
public class CartRestController {
	@Autowired
	private CartHandler cartHandler;
	
	@GetMapping(path = "products", params = "id")
	public Products getProducts(@RequestParam(name = "id") Integer id)
							    		 throws NoSuchProductIdException {
		return cartHandler.getProducts(id);
	}
	
	@GetMapping(path = "products")
	public List<Products> getProducts() {		
		return cartHandler.getProducts();
	}

	@PatchMapping(path = "products/add")
	public Products addProducts(@RequestBody Products products) 
						        		  throws NoSuchProductIdException,
						        			     ProductCountException {
		return cartHandler.addProducts(products);
	}
	
	@PatchMapping(path = "products/remove")
	public Products removeProducts(@RequestBody Products products)
            		            		  throws NoSuchProductIdException,
            		            		   	     ProductCountException {		
		return cartHandler.removeProducts(products);
	}
	
	@PatchMapping(path = "products/set")
	public Products setProducts(@RequestBody Products products)
            		            		  throws NoSuchProductIdException,
            		            			     ProductCountException {
		return cartHandler.setProducts(products);
	}
	
	
	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(value = {NoSuchProductIdException.class,
							   ProductCountException.class})
	public void exceptionHandler() {
		/* empty */
	}
}
