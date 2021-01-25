package ua.itea.signin;

public class Capcha {
	private String str = "";
	
	public Capcha() {
		/* empty */
	}
	
	public Capcha(String str) {
		if(str != null) {
			this.str = str;	
		}
	}
	
	public void set(String str) {
		if(str != null) {
			this.str = str;	
		} 
	}
	
	public String get() {
		return str;
	}

	public boolean isValid(Capcha capcha) {
		return str.equals(capcha.str);
	}
	
	@Override
	public String toString() {
		return str;
	}
}
