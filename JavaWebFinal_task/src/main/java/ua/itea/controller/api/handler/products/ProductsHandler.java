package ua.itea.controller.api.handler.products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.itea.dao.ProductsDAO;
import ua.itea.model.products.Category;
import ua.itea.model.products.Product;

@Component
public class ProductsHandler {
	@Autowired
	private ProductsDAO productsDAO;
	
	public Product getProduct(Integer id) throws NoSuchProductIdException {
		if(!productsDAO.isValidId(id)) {
			throw new NoSuchProductIdException();
		}
		
		return productsDAO.getProduct(id);
	}
	
	public List<Product> getProducts(List<Integer> ids) {
		return productsDAO.getProducts(ids);
	}
	
	public List<Product> getProducts() {
		return productsDAO.getProducts();
	}
	
	public List<Product> getProducts(String category)
										throws NoSuchProductCategoryException {
		try {
			return productsDAO.getProducts(Enum.valueOf(Category.class, category));
		} catch(IllegalArgumentException e) {
			throw new NoSuchProductCategoryException();
		}
	}
}
