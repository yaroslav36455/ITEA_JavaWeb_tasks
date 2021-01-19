package ua.itea;

public class StringGenerator {
	private String str;
	
	public void generate() {
		char[] arr = new char[5];
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (char)(Math.random() * 25);
			arr[i] += (Math.random() < 0.5 ? 'A' : 'a');
		}
		
		str = new String(arr);
	}
	
	public String getString()  {
		return str;
	}
	
	public boolean isValid(String str) {
		return this.str.equals(str);
	}
}
