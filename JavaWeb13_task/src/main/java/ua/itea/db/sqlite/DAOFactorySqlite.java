package ua.itea.db.sqlite;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import ua.itea.dao.DAOFactory;
import ua.itea.dao.ProductsDAO;
import ua.itea.dao.UserDAO;

public class DAOFactorySqlite implements DAOFactory {
	private UserDAOSqlite userDAOSqlite;
	private ProductsDAOSqlite productsDAOSqlite;
	
	public DAOFactorySqlite() {
		EntityManager entityManager = Persistence.createEntityManagerFactory("product").createEntityManager();
		userDAOSqlite = new UserDAOSqlite(entityManager);
		productsDAOSqlite = new ProductsDAOSqlite(entityManager);
	}

	@Override
	public UserDAO getUserDAO() {
		return userDAOSqlite;
	}

	@Override
	public ProductsDAO getProductsDAO() {
		return productsDAOSqlite;
	}
}
