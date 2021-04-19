package ua.itea.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ua.itea.controller.api.handler.products.NoSuchProductCategoryException;
import ua.itea.controller.api.handler.products.NoSuchProductIdException;
import ua.itea.controller.api.handler.products.ProductsHandler;
import ua.itea.model.products.Product;

@RestController
@RequestMapping(value = "api/products")
public class ProductsRestController {
	@Autowired
	public ProductsHandler productsHandler;
	
	@GetMapping(params = "id")
	public Product getProduct(@RequestParam(name = "id") Integer id)
										throws NoSuchProductIdException {						
		return productsHandler.getProduct(id);
	}
	
	@GetMapping(path = "byid")
	public List<Product> getProducts(@RequestBody List<Integer> ids) {		
		return productsHandler.getProducts(ids);
	}
	
	@GetMapping(path = "all")
	public List<Product> getProducts() {		
		return productsHandler.getProducts();
	}
	
	@GetMapping(params = {"category"})
	public List<Product> getProducts(@RequestParam("category") String category)
										throws NoSuchProductCategoryException {
		return productsHandler.getProducts(category);
	}
	
	
	@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(value = {NoSuchProductIdException.class,
							   NoSuchProductCategoryException.class})
	public void exceptionHandler() {
		/* empty */
	}
}
