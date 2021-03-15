package ua.itea.controller.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Products {
	@JsonProperty(value = "productId")
	private int productId;
	@JsonProperty(value = "count")
	private int count;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
