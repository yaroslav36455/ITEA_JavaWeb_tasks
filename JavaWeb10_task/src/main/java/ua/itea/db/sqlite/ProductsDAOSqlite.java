package ua.itea.db.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.itea.dao.ProductsDAO;
import ua.itea.model.products.Category;
import ua.itea.model.products.Product;

public class ProductsDAOSqlite implements ProductsDAO {
	private static final String CREATE_PRODUCTS_TABLE
	        = "CREATE TABLE IF NOT EXISTS `products` (`id` INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ " `name` VARCHAR(20) NOT NULL,"
			+ " description TEXT NOT NULL,"
			+ " price INTEGER NOT NULL,"
			+ " picture VARCHAR(64) NOT NULL,"
			+ " category_id INTEGER,"
			+ " FOREIGN KEY (category_id) REFERENCES category(id));";
	
	private static final String CREATE_PRODUCTS_CATEGORY_TABLE
			= "CREATE TABLE IF NOT EXISTS `category` (`id` INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ " `name` VARCHAR(20) NOT NULL);";

	private static final String SELECT_ALL
			= "SELECT `products`.`id`, `products`.`name`, `products`.`price`,"
			+ " `products`.`description`, `products`.`picture`, `category`.`id` AS category FROM"
			+ " `products` JOIN `category` ON `products`.`category_id` = `category`.`id`";
	
	private static final String SELECT_ALL_BY_CATEGORY
			= SELECT_ALL + " WHERE `category`.`id` = ?";
	
	private static final String SELECT
			= "SELECT `products`.`id`, `products`.`name`, `products`.`price`,"
			+ " `products`.`description`, `products`.`picture`, `category`.`id` AS category FROM"
			+ " `products` JOIN `category` ON `products`.`category_id`"
			+ " = `category`.`id` WHERE `products`.`id` = ?;";
	
	private Connector connector;

	public ProductsDAOSqlite(Connector connector) {		
		Statement statement = null;
		Connection conn = null;
		
		this.connector = connector;
		
		try {
			conn = connector.getConnection();
			statement = conn.createStatement();
			
			statement.execute(CREATE_PRODUCTS_CATEGORY_TABLE);
			statement.execute(CREATE_PRODUCTS_TABLE);
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(statement != null) {
					statement.close();	
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) {
					conn.close();	
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Product> getProducts() {
		return getProductsImpl(null);
	}

	@Override
	public List<Product> getProducts(Category category) {
		return getProductsImpl(category);
	}
	
	private List<Product> getProductsImpl(Category category) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Product> products = new ArrayList<>();

		try {
			conn = connector.getConnection();

			if(category == null) {
				ps = conn.prepareStatement(SELECT_ALL);
			} else {
				ps = conn.prepareStatement(SELECT_ALL_BY_CATEGORY);
				ps.setInt(1, category.ordinal() + 1);
			}
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Product product = getProduct(rs);

				products.add(product);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if(conn != null) {
					conn.close();	
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return products;
	}

	@Override
	public Product getProduct(Integer id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Product product = null;

		try {
			conn = connector.getConnection();
			ps = conn.prepareStatement(SELECT);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				product = getProduct(rs);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if(conn != null) {
					conn.close();	
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return product;
	}
	
	private Product getProduct(ResultSet rs) throws SQLException {		
		Product product = new Product(rs.getInt("id"));

		product.setName(rs.getString("name"));
		product.setPrice(rs.getInt("price"));
		product.setDescription(rs.getString("description"));
		product.setCategory(Category.values()[rs.getInt("category") - 1]);
		product.setPicture(rs.getString("picture"));
		
		return product;
	}
}
