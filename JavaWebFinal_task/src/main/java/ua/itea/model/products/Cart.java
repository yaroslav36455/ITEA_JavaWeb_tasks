package ua.itea.model.products;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import ua.itea.controller.api.handler.cart.ProductCountException;

import java.util.Set;
import java.util.TreeMap;

public class Cart implements Iterable<Entry<Integer, Integer>> {
	private Map<Integer, Integer> map;
	
	public Cart() {
		map = new TreeMap<Integer, Integer>();
	}
	
	public Integer put(Integer id, Integer count) throws ProductCountException {
		if(count <= 0) {
			throw new ProductCountException();
		}
		
		Integer oldCount = getCount(id);
		Integer newCount = oldCount + count;
		
		map.put(id, newCount);
		
		return newCount;
	}
	
	public Integer layOut(Integer id, Integer count) throws ProductCountException {
		if(count <= 0) {
			throw new ProductCountException();
		}
		
		Integer oldCount = getCount(id);
		Integer newCount = oldCount - count;
		
		if(newCount > 0) {
			map.put(id, newCount);
		} else {
			map.remove(id);
			newCount = 0;
		}
		
		return newCount;
	}
	
	public Integer set(Integer id, Integer count) throws ProductCountException {
		if(count < 0) {
			throw new ProductCountException();
		} else if (count == 0) {
			map.remove(id);
		} else {
			map.put(id, count);	
		}
		
		return count;
	}
	
	public Integer getCountKinds() {
		return map.size();
	}

	public Integer getCount() {
		return map.entrySet().stream().mapToInt(entry -> entry.getValue()).sum();
	}
	
	public Integer getCount(Integer id) {
		Integer infoCount = map.get(id);
		return infoCount == null ? 0 : infoCount;
	}
	
	public Set<Integer> getIds() {
		return map.keySet();
	}

	@Override
	public Iterator<Entry<Integer, Integer>> iterator() {
		return map.entrySet().iterator();
	}
}