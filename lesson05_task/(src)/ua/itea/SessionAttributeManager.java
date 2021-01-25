package ua.itea;

import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpSession;

public class SessionAttributeManager {
	private HttpSession session;
	
	public SessionAttributeManager(HttpSession session) {
		this.session = session;
	}
	
	public <T extends SessionAttribute> void setAttribute(T attribute, String ...nameEnding) {
		final String attrName = getAttributeName(attribute.getClass(), nameEnding);
		
		session.setAttribute(attrName, attribute);
	}

	public <T extends SessionAttribute> T getAttribute(Class<T> clazz, String ...nameEnding) {
		final String attrName = getAttributeName(clazz, nameEnding);
		T attr = retrieveAttribute(attrName);
		
		if(attr == null && clazz.isAnnotationPresent(AutoCreatableAttribute.class)) {
			attr = createAttribute(clazz, attrName);
			session.setAttribute(attrName, attr);
		}
		
		return attr;
	}
	
	public <T extends SessionAttribute> void removeAttribute(T attribute, String ...nameEnding) {		
		removeAttribute(attribute.getClass(), nameEnding);
	}
	
	public <T extends SessionAttribute> void removeAttribute(Class<T> clazz, String ...nameEnding) {		
		session.removeAttribute(getAttributeName(clazz, nameEnding));
	}
	
	@SuppressWarnings("unchecked")
	private <T extends SessionAttribute> T retrieveAttribute(String attrName) {
		return (T) session.getAttribute(attrName);
	}
	
	private <T extends SessionAttribute> T createAttribute(Class<T> clazz, String attrName) {
		T attr = null;
		
		try {
			clazz.getDeclaredConstructor().setAccessible(true);
			attr = clazz.getDeclaredConstructor().newInstance();
			clazz.getDeclaredConstructor().setAccessible(false);
		} catch (NoSuchMethodException | SecurityException
				| InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return attr;
	}
	
	private <T extends SessionAttribute> String getAttributeName(Class<T> clazz, String ...nameEnding) {
		final String className = clazz.getSimpleName();
		final char firstChar = Character.toLowerCase(className.charAt(0));
		String resultName = firstChar + className.substring(1);
		
		for (String ne : nameEnding) {
			resultName += ne;
		}
		
		return resultName;
	}
}
