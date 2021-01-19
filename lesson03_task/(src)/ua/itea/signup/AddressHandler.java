package ua.itea.signup;

public class AddressHandler {
	private boolean isCorrect;
	
	public boolean isCorrect() {
		return isCorrect;
	}
	
	public void check(String address) {
		isCorrect = address.equals("DNR") || address.equals("LNR") || address.equals("TSD");
	}
	
	public String getHint() {
		return isCorrect ? "" : "Address selection error";
	}
}
