package ua.itea.controller.api.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ua.itea.dao.DAOFactory;
import ua.itea.dao.ProductsDAO;
import ua.itea.dao.UserDAO;

@Configuration
public class ConfigurationDAO {
	@Autowired
	private DAOFactory daoFactory;
	
	@Bean
	public ProductsDAO getProductsDAO() {
		return daoFactory.getProductsDAO();
	}
	
	@Bean
	public UserDAO getUserDAO() {
		return daoFactory.getUserDAO();
	}
}
