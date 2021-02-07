package ua.itea.controller;

import java.util.ArrayList;

import ua.itea.model.products.Product;

public class ProductsRack extends ArrayList<Product> implements SessionAttribute {
	private static final long serialVersionUID = 7221076375832782699L;

	@Override
	public String toString() {
		return Integer.valueOf(super.size()).toString();
	}
}
