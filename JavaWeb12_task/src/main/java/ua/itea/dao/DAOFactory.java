package ua.itea.dao;

public interface DAOFactory {
	public UserDAO getUserDAO();
	public ProductsDAO getProductsDAO();
}
