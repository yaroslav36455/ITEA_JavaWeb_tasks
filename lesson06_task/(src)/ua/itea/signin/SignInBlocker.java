package ua.itea.signin;

import java.util.Date;

import ua.itea.AutoCreatableAttribute;
import ua.itea.SessionAttribute;

@AutoCreatableAttribute
public class SignInBlocker implements SessionAttribute {
	private static final long BLOCK_TIME_MILLISECONDS = 10000;
	private static final int MAX_ATTEMPTS = 3;
	private Capcha generatedCapcha;
	private Capcha capcha;
	private long lastBlockTime;
	private int loginAttemptCounter;
	
	public SignInBlocker() {
		generatedCapcha = new Capcha("");
		capcha = new Capcha("");
	}
	
	public void captureMistake() {
		++loginAttemptCounter;
		
		if (loginAttemptCounter == MAX_ATTEMPTS) {
			block();
		}
	}
	
	public void block() {
		CapchaGenerator generator = new CapchaGenerator();
		
		generatedCapcha = generator.generate();
		capcha = new Capcha("");
		lastBlockTime = new Date().getTime();
	}
	
	public void unblock() {
		loginAttemptCounter = 0;
		lastBlockTime = 0;
		capcha = generatedCapcha;
	}
	
	public Capcha getCapcha() {
		return this.capcha;
	}
	
	public void setCapcha(Capcha capcha) {
		this.capcha = capcha;
	}
	
	public Capcha getGeneratedCapcha() {
		return generatedCapcha;
	}

	public boolean isBlocked(long currentTime) {
		return isTimeBlocked(currentTime) || !isCapchaValid();
	}
	
	private boolean isTimeBlocked(long currentTime) {
		return (currentTime - lastBlockTime) < BLOCK_TIME_MILLISECONDS;
	}
	
	public boolean isCapchaValid() {
		return capcha.isValid(generatedCapcha);
	}

	public long getLastBlockTime() {
		return lastBlockTime;
	}

	public static long getBlockTimeMilliseconds() {
		return BLOCK_TIME_MILLISECONDS;
	}
}
