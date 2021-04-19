package ua.itea.controller.api.handler.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.itea.api.Products;
import ua.itea.controller.api.handler.products.NoSuchProductIdException;
import ua.itea.controller.api.util.SessionAttributeManager;
import ua.itea.dao.ProductsDAO;
import ua.itea.model.products.Cart;

@Component
public class CartHandler {
	@Autowired
	private SessionAttributeManager sam;
	@Autowired
	private ProductsDAO productsDAO;
	
	public List<Products> getProducts() {
		Cart cart = sam.getAttribute(Cart.class);
		
		List<Products> products = new ArrayList<Products>(cart.getCountKinds());		
		for(Entry<Integer, Integer> entry : cart) {
			Products prod = new Products();
			prod.setId(entry.getKey());
			prod.setCount(entry.getValue());
			
			products.add(prod);
		}
		
		return products;
	}
	
	public Products getProducts(Integer id) throws NoSuchProductIdException {
		if(!productsDAO.isValidId(id)) {
			throw new NoSuchProductIdException();
		}
		
		Cart cart = sam.getAttribute(Cart.class);
		Products products = new Products();
		
		products.setId(id);
		products.setCount(cart.getCount(id));
		return products;
	}
	
	public Products addProducts(Products products) throws NoSuchProductIdException,
														  ProductCountException {
		if(!productsDAO.isValidId(products.getId())) {
			throw new NoSuchProductIdException();
		}
		
		Cart cart = sam.getAttribute(Cart.class);
		Integer newCount = cart.put(products.getId(), products.getCount());
		
		products.setCount(newCount);
		return products;
	}
	
	public Products removeProducts(Products products) throws NoSuchProductIdException,
															 ProductCountException {
		if(!productsDAO.isValidId(products.getId())) {
			throw new NoSuchProductIdException();
		}
		
		Cart cart = sam.getAttribute(Cart.class);
		Integer newCount = cart.layOut(products.getId(), products.getCount());
		
		products.setCount(newCount);
		return products;
	}
	
	public Products setProducts(Products products) throws NoSuchProductIdException,
														  ProductCountException {
		if(!productsDAO.isValidId(products.getId())) {
			throw new NoSuchProductIdException();
		}
		
		Cart cart = sam.getAttribute(Cart.class);
		Integer newCount = cart.set(products.getId(), products.getCount());
		
		products.setCount(newCount);
		return products;
	}
}
