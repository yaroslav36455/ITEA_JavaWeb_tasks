package ua.itea.dao;

import java.util.List;

import ua.itea.model.products.Category;
import ua.itea.model.products.Product;

public interface ProductsDAO {
	public boolean isValidId(Integer id);
	public Product getProduct(Integer id);
	public List<Product> getProducts();
	public List<Product> getProducts(Category category);
	public List<Product> getProducts(Iterable<Integer> ids);
}
