package ua.itea.db.sqlite;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import ua.itea.dao.DAOFactory;
import ua.itea.dao.ProductsDAO;
import ua.itea.dao.UserDAO;

@Service
public class DAOFactorySqlite implements DAOFactory {
	private UserDAOSqlite userDAOSqlite;
	private ProductsDAOSqlite productsDAOSqlite;
	
	public DAOFactorySqlite(DataSource dataSource) {
		userDAOSqlite = new UserDAOSqlite(dataSource);
		productsDAOSqlite = new ProductsDAOSqlite(dataSource);
	}

	public DAOFactorySqlite() {
		/* empty */
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
