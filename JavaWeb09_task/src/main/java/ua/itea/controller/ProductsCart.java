package ua.itea.controller;

import java.util.HashMap;

import ua.itea.model.products.Product;

@AutoCreatableAttribute
public class ProductsCart extends HashMap<Product, Integer> implements SessionAttribute {
	private static final long serialVersionUID = 7221076375832782699L;

	@Override
	public String toString() {
		Integer count = 0;
		for (Integer i : this.values()) {
			count += i;
		}
		return count.toString();
	}
}