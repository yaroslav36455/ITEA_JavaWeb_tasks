package ua.itea.db.sqlite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.sqlite.SQLiteException;

import ua.itea.controller.signup.AddressHandler;
import ua.itea.controller.signup.CommentHandler;
import ua.itea.controller.signup.EmailHandler;
import ua.itea.controller.signup.GenderHandler;
import ua.itea.controller.signup.LoginHandler;
import ua.itea.controller.signup.NameHandler;
import ua.itea.controller.signup.RegisteringUser;
import ua.itea.dao.UserDAO;
import ua.itea.model.users.Address;
import ua.itea.model.users.Authentication;
import ua.itea.model.users.Gender;
import ua.itea.model.users.User;

public class UserDAOSqlite implements UserDAO {
	private static final String CREATE_USER_TABLE
	= "CREATE TABLE IF NOT EXISTS `users` (`id` INTEGER PRIMARY KEY AUTOINCREMENT"
			+ ", `login` VARCHAR(" + LoginHandler.getMaxLength() + ") NOT NULL"
			+ ", `password` VARBINARY(" + Cryptographer.getBytes() + ") NOT NULL"
			+ ", `name` VARCHAR(" + NameHandler.getMaxLength() + ") NOT NULL"
			+ ", `email` VARCHAR(" + EmailHandler.getMaxLength() + ") NOT NULL"
			+ ", `address` VARCHAR(" + AddressHandler.getMaxLength() + ") NOT NULL"
			+ ", `gender` VARCHAR(" + GenderHandler.getMaxLength() + ") NOT NULL"
			+ ", `comment` VARCHAR(" + CommentHandler.getMaxLength() + ") NOT NULL);";
	private static final String UNIQUE_LOGIN
			= "CREATE UNIQUE INDEX IF NOT EXISTS `login_unique` on `users` (`login`);";
	private static final String UNIQUE_EMAIL
			= "CREATE UNIQUE INDEX IF NOT EXISTS `email_unique` on `users` (`email`);";
	private static final String INSERT_USER 
			= "INSERT OR FAIL INTO `users`"
			+ " (`login`, `password`, `name`, `email`, `address`, `gender`, `comment`)"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?);";
	private static final String SELECT_USER
			= "SELECT `id`, `name`, `email`, `address`, `gender`, `comment` FROM `users`"
			+ " WHERE `login` = ? AND `password` = ?;";
	

	private DataSource dataSource;
	private Cryptographer cryptographer;
	
	public UserDAOSqlite(DataSource dataSource) {
		Statement statement = null;
		Connection conn = null;
		
		this.cryptographer = new Cryptographer();
		this.dataSource = dataSource;
		try {
			conn = dataSource.getConnection();
			statement = conn.createStatement();
			
			statement.execute(CREATE_USER_TABLE);
			statement.execute(UNIQUE_LOGIN);
			statement.execute(UNIQUE_EMAIL);
			
		} catch (SQLException e) {
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
	public boolean insert(RegisteringUser regUser) {
		boolean result = true;
		PreparedStatement ps = null;
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(INSERT_USER);
			
			int i = 1;
			ps.setString(i++, regUser.getLogin());
			ps.setBytes(i++, cryptographer.encrypt(regUser.getPassword()));
			ps.setString(i++, regUser.getName());
			ps.setString(i++, regUser.getEmail());
			ps.setString(i++, regUser.getAddress().name());
			ps.setString(i++, regUser.getGender().name());
			ps.setString(i++, regUser.getComment());
			
			ps.execute();
			
		} catch (SQLiteException e) {
			result = false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();	
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
		
		return result;
	}

	@Override
	public User select(Authentication authentication) {
		User user = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(SELECT_USER);
			
			ps.setString(1, authentication.getLogin());
			ps.setBytes(2, cryptographer.encrypt(authentication.getPassword()));
			
			rs = ps.executeQuery();
			if(rs.next()) {
				long id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Address address = Enum.valueOf(Address.class, rs.getString("address"));
				Gender gender = Enum.valueOf(Gender.class, rs.getString("gender"));
				String comment = rs.getString("comment");
				
				user = new User(id);
				user.setLogin(authentication.getLogin());
				user.setName(name);
				user.setEmail(email);
				user.setAddress(address);
				user.setGender(gender);
				user.setComment(comment);
			}
		} catch (SQLException | IllegalArgumentException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();	
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(ps != null) {
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
		
		return user;
	}

}
