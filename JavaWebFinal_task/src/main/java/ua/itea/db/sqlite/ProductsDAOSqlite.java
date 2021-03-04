package ua.itea.db.sqlite;

import java.util.List;

import javax.persistence.EntityManager;

import ua.itea.dao.ProductsDAO;
import ua.itea.model.products.Category;
import ua.itea.model.products.Product;

public class ProductsDAOSqlite implements ProductsDAO {
	private EntityManager entityManager;
	
	public ProductsDAOSqlite(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Product> getProducts() {
		return entityManager.createNamedQuery("GetAll", Product.class).getResultList();
	}

	@Override
	public List<Product> getProducts(Category category) {
		return entityManager.createNamedQuery("GetAll" + category, Product.class).getResultList();
	}

	@Override
	public Product getProduct(Integer id) {
		return entityManager.find(Product.class, id);
	}
}
