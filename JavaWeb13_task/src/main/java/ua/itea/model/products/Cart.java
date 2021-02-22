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
	
	public void put(Product product, Integer count)  {
		Integer oldCount = cart.get(product);
		
		cart.put(product, (oldCount == null ? 0 : oldCount) + count);
		this.count += count;
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