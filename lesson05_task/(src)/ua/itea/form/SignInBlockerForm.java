package ua.itea.form;

import ua.itea.AutoCreatableAttribute;
import ua.itea.SessionAttribute;
import ua.itea.signin.Capcha;

@AutoCreatableAttribute
public class SignInBlockerForm implements SessionAttribute {
	private Capcha capcha;
	private boolean showCapcha;
	private long remainingTime;
	
	public SignInBlockerForm() {
		/* empty */
	}

	public long getRemainingTime() {
		return remainingTime;
	}

	public void setRemainingTime(long remainingTime) {
		this.remainingTime = remainingTime;
	}

	public void setCapcha(Capcha capcha) {
		this.capcha = capcha;
	}

	public void setShowCapcha(boolean showCapcha) {
		this.showCapcha = showCapcha;
	}

	public Capcha getCapcha() {
		return capcha;
	}

	public boolean isShowCapcha() {
		return showCapcha;
	}
	
	public String getTimeMessageColor() {
		return isTimeBlockerWorks() ? "#aa0000" : "#00aa00"; 
	}
	
	public String getTimeMessage() {
		return isTimeBlockerWorks()
				? "Remaining blocking time: " + String.valueOf(remainingTime / 1000.0) + " seconds"
				: "Time blocking removed";
	}
	
	private boolean isTimeBlockerWorks() {
		return remainingTime > 0;
	}
}
