package ua.itea.signin;

public class CapchaGenerator {
	
	public Capcha generate() {
		char[] arr = new char[5];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (char)(Math.random() * 25);
			arr[i] += (Math.random() < 0.5 ? 'A' : 'a');
		}
		
		return new Capcha(new String(arr));
	}
}
