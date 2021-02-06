package ua.itea.dao;

import java.util.List;

import ua.itea.model.products.Category;
import ua.itea.model.products.Product;

public interface ProductsDAO {
	public Product getProduct(Integer id);
	public List<Product> getProducts();
	public List<Product> getProducts(Category category);
}
