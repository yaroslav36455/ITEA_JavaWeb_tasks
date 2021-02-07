package ua.itea.db.sqlite;

import ua.itea.dao.DAOFactory;
import ua.itea.dao.ProductsDAO;
import ua.itea.dao.UserDAO;

public class DAOFactorySqlite implements DAOFactory {
	private Connector connector;
	
	public DAOFactorySqlite() {	
		connector = new Connector();
	}
	
	@Override
	public UserDAO getUserDAO() {
		return new UserDAOSqlite(connector);
	}

	@Override
	public ProductsDAO getProductsDAO() {
		return new ProductsDAOSqlite(connector);
	}

}
