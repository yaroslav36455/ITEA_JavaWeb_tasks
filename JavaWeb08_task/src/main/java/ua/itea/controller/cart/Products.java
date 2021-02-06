package ua.itea.controller.cart;

import java.util.ArrayList;

import ua.itea.controller.SessionAttribute;
import ua.itea.model.products.Product;

public class Products extends ArrayList<Product> implements SessionAttribute {
	private static final long serialVersionUID = 7221076375832782699L;

	private String qwf;
	
	public String getQwf() {
		return Integer.valueOf(super.size()).toString();
	}
}
