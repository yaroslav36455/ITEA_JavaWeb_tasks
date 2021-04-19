package ua.itea.controller.api.handler.user.signin;

class Captcha {
	private String str = "";
	
	public Captcha() {
		/* empty */
	}
	
	public Captcha(String str) {
		if(str != null) {
			this.str = str;	
		}
	}
	
	public void set(String str) {
		if(str != null) {
			this.str = str;	
		} 
	}
	
	public boolean isValid(Captcha captcha) {
		return str.equals(captcha.str);
	}
	
	@Override
	public String toString() {
		return get();
	}
	
	public String get() {
		return str;
	}
}
