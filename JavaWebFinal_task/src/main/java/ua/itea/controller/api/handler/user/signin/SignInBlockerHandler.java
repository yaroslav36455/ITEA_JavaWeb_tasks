package ua.itea.controller.api.handler.user.signin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.itea.api.SignInAttempts;
import ua.itea.api.SignInBlockerInfo;
import ua.itea.api.SignInBlockerUpdate;
import ua.itea.controller.api.util.SessionAttributeManager;

@Component
public class SignInBlockerHandler {
	@Autowired
	private SessionAttributeManager sam;
	
	public SignInAttempts getAttempts() {
		SignInBlocker blocker = sam.getAttributeOrNull(SignInBlocker.class);
		SignInAttempts result = new SignInAttempts();
		
		result.setMax(SignInBlocker.getAttemptsMax());
		result.setLeft(SignInBlocker.getAttemptsMax());
		if(blocker != null) {
			result.setLeft(blocker.getAttmptsLeft());
		}
		
		return result;
	}
	
	public SignInBlockerInfo updateAndGetInfo() {
		SignInBlocker sib = sam.getAttributeOrNull(SignInBlocker.class);
		update(sib);
		
		return getInfoImpl(sib);
	}
	
	public void update() {
		update(sam.getAttributeOrNull(SignInBlocker.class));
	}
	
	private void update(SignInBlocker sib) {
		if(sib != null) {
			sib.updateTime();
			if(!sib.isBlocked()) {
				sam.removeAttribute(SignInBlocker.class);
			}
		}
	}
	
	public SignInBlockerInfo updateAndGetInfo(SignInBlockerUpdate sibu) {
		SignInBlocker sib = sam.getAttributeOrNull(SignInBlocker.class);
		if(sib != null) {
			sib.update(new Captcha(sibu.getCaptcha()));
			if(!sib.isBlocked()) {
				sam.removeAttribute(SignInBlocker.class);
			}
		}
		
		return getInfoImpl(sib);
	}
	
	private SignInBlockerInfo getInfoImpl(SignInBlocker sib) {
		SignInBlockerInfo sibi = new SignInBlockerInfo();
		
		if(sib != null) {		
			sibi.setMillisecondsLeft(sib.getRemainingTimeMilliseconds());
			sibi.setCaptchaRequired(!sib.isCaptchaValid());
			if(sib.getGeneratedCaptcha() != null) {
				sibi.setGeneratedCaptcha(sib.getGeneratedCaptcha().get());	
			}
		}
		
		return sibi;
	}
}
