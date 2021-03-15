package ua.itea.model.products;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Cart implements Iterable<Entry<Product, Integer>> {
	private Map<Product, Integer> cart;
	private Integer count;
	
	public Cart() {
		cart = new HashMap<Product, Integer>();
		count = 0;
	}
	
	public Integer put(Product product, Integer count) {
		Integer infoCount = cart.get(product);
		Integer oldCount = infoCount == null ? 0 : infoCount;
		Integer newCount = oldCount + count;
		
		cart.put(product, newCount);
		this.count += newCount - oldCount;
		
		return newCount;
	}
	
	public Integer layOut(Product product, Integer count) {
		Integer oldCount = cart.get(product);
		Integer newCount = 0;
		
		if(oldCount != null) {
			newCount = Math.max(oldCount - count, 0);
			
			if(newCount > 0) {
				cart.put(product, newCount);
			} else {
				cart.remove(product);
			}
			
			this.count -= oldCount - newCount;
		}
		
		return newCount;
	}

	public Integer getCount() {
		return count;
	}

	@Override
	public Iterator<Entry<Product, Integer>> iterator() {
		return cart.entrySet().iterator();
	}
}