package ua.itea.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SignInAttempts {
	@JsonProperty(value = "max")
	private int max;
	@JsonProperty(value = "left")
	private int left;
	
	public SignInAttempts() {
		/* empty */
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}
}
