package ua.itea.controller.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartStateChanged {
	@JsonProperty(value = "concernedProductCount")
	private int concernedProductCount;
	@JsonProperty(value = "totalProductCount")
	private int totalProductCount;
	
	public int getConcernedProductCount() {
		return concernedProductCount;
	}
	public void setConcernedProductCount(int concernedProductCount) {
		this.concernedProductCount = concernedProductCount;
	}
	public int getTotalProductCount() {
		return totalProductCount;
	}
	public void setTotalProductCount(int totalProductCount) {
		this.totalProductCount = totalProductCount;
	}
}
