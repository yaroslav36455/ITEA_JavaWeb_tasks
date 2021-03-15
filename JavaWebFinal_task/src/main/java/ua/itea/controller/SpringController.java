package ua.itea.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ua.itea.controller.signin.Captcha;
import ua.itea.controller.signin.SignInBlocker;
import ua.itea.controller.signup.RegisteringUserBuilder;
import ua.itea.controller.signup.SignUpHandler;
import ua.itea.dao.DAOFactory;
import ua.itea.dao.ProductsDAO;
import ua.itea.dao.UserDAO;
import ua.itea.model.products.Category;
import ua.itea.model.products.Rack;
import ua.itea.model.users.Authentication;
import ua.itea.model.users.User;

@Controller
public class SpringController {
	private static final String INDEX = "index";
	private static final String CART = "cart";
	private static final String PRODUCTS = "products";
	private static final String SIGN_IN = "sign-in";
	private static final String SIGN_IN_BLOCKER = "sign-in-blocker";
	private static final String SIGN_UP = "sign-up";
	private static final String SIGN_UP_SUCCESS = "sign-up-success";
	private static final String SIGN_UP_FAILED = "sign-up-failed";
	private static final String USER_HOME = "user-home";

	@Autowired
	private DAOFactory daoFactory;

	public DAOFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	

	@GetMapping(value = INDEX)
	public String index()  {
		return INDEX;
	}
	
	@GetMapping(value = CART)
	public String cart()  {
		return CART;
	}
	
	@GetMapping(value = USER_HOME)
	public String userHome(HttpSession session)  {
		SessionAttributeManager sam = new SessionAttributeManager(session);
		
		return sam.hasAttribute(User.class) ? USER_HOME : ("redirect:" + SIGN_IN + ".html");
	}
	
	@GetMapping(value = PRODUCTS)
	public String products(HttpSession session) {
		SessionAttributeManager sam = new SessionAttributeManager(session);
		ProductsDAO productsDAO = daoFactory.getProductsDAO();
		Rack rack = new Rack();
		rack.addAll(productsDAO.getProducts());
		sam.setAttribute(rack);
		return PRODUCTS;
	}
	
	@GetMapping(value = PRODUCTS, params = {"category"})
	public String products(@RequestParam("category") String category, HttpSession session)  {
		SessionAttributeManager sam = new SessionAttributeManager(session);
		ProductsDAO productsDAO = daoFactory.getProductsDAO();
		Rack rack = new Rack();
		
		rack.addAll(productsDAO.getProducts(Enum.valueOf(Category.class, category)));
		sam.setAttribute(rack);
		return PRODUCTS;
	}

	@PostMapping(value = SIGN_IN, params = { "login", "password" })
	public String signInPost(@RequestParam("login") String login,
							 @RequestParam("password") String password,
							 HttpSession session) {
		SessionAttributeManager sam = new SessionAttributeManager(session);
		UserDAO userDAO = daoFactory.getUserDAO();
		User user = userDAO.select(new Authentication(login, password));

		if(user != null) {
			sam.setAttribute(user);	
		} else {
			sam.getAttribute(SignInBlocker.class).captureMistake();
		}
		
		return signInGet(session);
	}
	
	@GetMapping(value = SIGN_IN)
	public String signInGet(HttpSession session) {
		SessionAttributeManager sam = new SessionAttributeManager(session);
		String result = SIGN_IN;
		
		if(sam.hasAttribute(User.class)) {
			result = "redirect:" + USER_HOME + ".html";
			sam.removeAttribute(SignInBlocker.class);
		} else {
			SignInBlocker sib = sam.getAttribute(SignInBlocker.class);
			
			if(sib.isBlocked()) {
				sib.updateTime();
				
				if(sib.isBlocked()) {
					result = SIGN_IN_BLOCKER;	
				}
			}	
		}
		
		return result;
	}
	
	@PostMapping(value = SIGN_IN, params = "captcha")
	public String signInBlocker(@RequestParam("captcha") String captcha, HttpSession session) {
		SessionAttributeManager sam = new SessionAttributeManager(session);
		SignInBlocker sib = sam.getAttribute(SignInBlocker.class);
		sib.update(new Captcha(captcha));
		
		return sib.isBlocked() ? SIGN_IN_BLOCKER : SIGN_IN;
	}

	@PostMapping(value = SIGN_IN, params = {"out"})
	public String signOut(HttpSession session) {
		SessionAttributeManager sam = new SessionAttributeManager(session);
		sam.removeAttribute(User.class);

		return SIGN_IN;
	}

	@RequestMapping(value = SIGN_UP, method = RequestMethod.GET)
	public String signUpGet(HttpSession session) {
		SessionAttributeManager sam = new SessionAttributeManager(session);

		sam.ensureAttribute(RegisteringUserBuilder.class);
		sam.ensureAttribute(SignUpHandler.class);

		return SIGN_UP;
	}

	@PostMapping(value = SIGN_UP)
	public String signUpPost(HttpServletRequest req) {
		SessionAttributeManager sam = new SessionAttributeManager(req.getSession());
		RegisteringUserBuilder registeringUserBuilder = sam.getAttribute(RegisteringUserBuilder.class);
		SignUpHandler signUpHandler = sam.getAttribute(SignUpHandler.class);
		String result = SIGN_UP;
		UserDAO userDAO = daoFactory.getUserDAO();

		registeringUserBuilder.setName((String) req.getParameter("name"));
		registeringUserBuilder.setLogin((String) req.getParameter("login"));
		registeringUserBuilder.setPassword((String) req.getParameter("password"));
		registeringUserBuilder.setRetypePassword((String) req.getParameter("retypePassword"));
		registeringUserBuilder.setEmail((String) req.getParameter("email"));
		registeringUserBuilder.setAddress((String) req.getParameter("address"));
		registeringUserBuilder.setGender((String) req.getParameter("gender"));
		registeringUserBuilder.setComment((String) req.getParameter("comment"));
		registeringUserBuilder.setAmigaAgree((String) req.getParameter("amigaAgree"));

		signUpHandler.check(registeringUserBuilder);

		if (signUpHandler.isCorrect()) {
			if (userDAO.insert(registeringUserBuilder.createUser())) {
				result = SIGN_UP_SUCCESS;
				sam.removeAttribute(registeringUserBuilder);
				sam.removeAttribute(signUpHandler);
			} else {
				result = SIGN_UP_FAILED;
			}
		}

		return result;
	}
}
