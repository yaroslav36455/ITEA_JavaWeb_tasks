package ua.itea.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteException;

import ua.itea.controller.signup.AddressHandler;
import ua.itea.controller.signup.CommentHandler;
import ua.itea.controller.signup.EmailHandler;
import ua.itea.controller.signup.GenderHandler;
import ua.itea.controller.signup.LoginHandler;
import ua.itea.controller.signup.NameHandler;
import ua.itea.controller.signup.RegisteringUser;
import ua.itea.model.Address;
import ua.itea.model.Authentication;
import ua.itea.model.Gender;
import ua.itea.model.User;

public class UsersDB {
	private static final String CREATE_TABLE
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
	
	private static final String READ_USER
			= "SELECT `id`, `name`, `email`, `address`, `gender`, `comment` FROM `users`"
			+ " WHERE `login` = ? AND `password` = ?;";
	
	private Cryptographer cryptographer;
	
	public UsersDB(Connection conn) {
		Statement statement = null;
		
		try {
			statement = conn.createStatement();
			
			statement.execute(CREATE_TABLE);
			statement.execute(UNIQUE_LOGIN);
			statement.execute(UNIQUE_EMAIL);
			
			cryptographer = new Cryptographer();
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
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public User getUser(Connection conn, Authentication authentication) {
		User user = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(READ_USER);
			
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
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return user;
	}
	
	
	public boolean addUser(Connection conn, RegisteringUser regUser) {
		boolean result = true;
		PreparedStatement ps = null;
		
		try {
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
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
