package ua.itea.controller.signin;

public class CaptchaGenerator {
	
	public Captcha generate() {
		char[] arr = new char[5];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (char)(Math.random() * 25);
			arr[i] += (Math.random() < 0.5 ? 'A' : 'a');
		}
		
		return new Captcha(new String(arr));
	}
}
