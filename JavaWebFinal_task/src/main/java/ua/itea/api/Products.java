package ua.itea.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Products {
	@JsonProperty(value = "id")
	private int id;
	@JsonProperty(value = "count")
	private int count;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
