package ua.itea.db.sqlite;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;

import ua.itea.controller.signup.AddressHandler;
import ua.itea.controller.signup.CommentHandler;
import ua.itea.controller.signup.EmailHandler;
import ua.itea.controller.signup.GenderHandler;
import ua.itea.controller.signup.LoginHandler;
import ua.itea.controller.signup.NameHandler;
import ua.itea.controller.signup.RegisteringUser;
import ua.itea.dao.UserDAO;
import ua.itea.model.users.Authentication;
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
			= "SELECT `id`, `login`, `name`, `email`, `address`, `gender`, `comment` FROM `users`"
			+ " WHERE `login` = ? AND `password` = ?;";
	
	private Cryptographer cryptographer;
	private EntityManager entityManager;
	
	public UserDAOSqlite(EntityManager entityManager) {
		this.cryptographer = new Cryptographer();
		
		this.entityManager = entityManager;
		
		try {
			entityManager.getTransaction().begin();
			entityManager.createNativeQuery(CREATE_USER_TABLE).executeUpdate();
			entityManager.createNativeQuery(UNIQUE_LOGIN).executeUpdate();
			entityManager.createNativeQuery(UNIQUE_EMAIL).executeUpdate();
			entityManager.getTransaction().commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	@Override
	public boolean insert(RegisteringUser regUser) {
		int result = 0;
		try {
			int i = 0;
			entityManager.getTransaction().begin();
			result = entityManager.createNativeQuery(INSERT_USER)
									.setParameter(++i, regUser.getLogin())
									.setParameter(++i, cryptographer.encrypt(regUser.getPassword()))
									.setParameter(++i, regUser.getName())
									.setParameter(++i, regUser.getEmail())
									.setParameter(++i, regUser.getAddress().ordinal())
									.setParameter(++i, regUser.getGender().ordinal())
									.setParameter(++i, regUser.getComment())
									.executeUpdate();
			entityManager.getTransaction().commit();
		} catch (PersistenceException e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
		return result != 0;
	}

	@Override
	public User select(Authentication authentication) {
		User result = null;
		try {
			Object login = authentication.getLogin();
			Object password = cryptographer.encrypt(authentication.getPassword());
			
			result = (User) entityManager.createNativeQuery(SELECT_USER, User.class)
		               						.setParameter(1, login)
		               						.setParameter(2, password)
		               						.getSingleResult();
		} catch (NoResultException | NonUniqueResultException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
