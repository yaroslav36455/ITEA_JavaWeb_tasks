package ua.itea.model.products;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Rack implements Iterable<Product>{
	private List<Product> rack;
	
	public Rack() {
		rack = new ArrayList<>();
	}
	
	public void addAll(List<Product> products) {
		rack.addAll(products);
	}
	
	public Integer getCount() {
		return rack.size();
	}

	@Override
	public Iterator<Product> iterator() {
		return rack.iterator();
	}
}
