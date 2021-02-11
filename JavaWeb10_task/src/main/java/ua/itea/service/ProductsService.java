package ua.itea.service;

import java.util.List;

import ua.itea.dao.ProductsDAO;
import ua.itea.db.sqlite.Connector;
import ua.itea.db.sqlite.ProductsDAOSqlite;
import ua.itea.model.products.Category;
import ua.itea.model.products.Product;

public class ProductsService {
	private ProductsDAO productsDAO;
	
	public ProductsService() {
		productsDAO = new ProductsDAOSqlite(new Connector());
	}
	
	public Product getProduct(Integer id) {
		return productsDAO.getProduct(id);
	}
	
	public List<Product> getProductsAll() {
		return productsDAO.getProducts();
	}
	
	public List<Product> getProductsByCategory(Category category) {
		return productsDAO.getProducts(category);
	}
}
