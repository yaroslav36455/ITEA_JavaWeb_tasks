package ua.itea.controller.signup;

import ua.itea.controller.SessionAttribute;

public class SignUpHandler implements SessionAttribute {
	private NameHandler nameHandler;
	private LoginHandler loginHandler;
	private PasswordHandler passwordHandler;
	private RetypePasswordHandler retypePasswordHandler;
	private EmailHandler emailHandler;
	private AddressHandler addressHandler;
	private GenderHandler genderHandler;
	private CommentHandler commentHandler;
	private AmigaAgreeHandler amigaAgreeHandler;
	
	private String nameTip;
	private String loginTip;
	private String passwordTip;
	private String retypePasswordTip;
	private String emailTip;
	private String addressTip;
	private String genderTip;
	private String commentTip;
	private String amigaAgreeTip;

	public SignUpHandler() {
		nameHandler = new NameHandler();
		loginHandler = new LoginHandler();
		passwordHandler = new PasswordHandler();
		retypePasswordHandler = new RetypePasswordHandler();
		emailHandler = new EmailHandler();
		addressHandler = new AddressHandler();
		genderHandler = new GenderHandler();
		commentHandler = new CommentHandler();
		amigaAgreeHandler = new AmigaAgreeHandler();
		
		updateTips();
	}
	
	private void updateTips() {
		nameTip = nameHandler.getTip();
		loginTip = loginHandler.getTip();
		passwordTip = passwordHandler.getTip();
		retypePasswordTip = retypePasswordHandler.getTip();
		emailTip = emailHandler.getTip();
		addressTip = addressHandler.getTip();
		genderTip = genderHandler.getTip();
		commentTip = commentHandler.getTip();
		amigaAgreeTip = amigaAgreeHandler.getTip();
	}
	
	public boolean isCorrect() {
		return nameHandler.isCorrect()
				&& loginHandler.isCorrect()
				&& passwordHandler.isCorrect()
				&& retypePasswordHandler.isCorrect()
				&& emailHandler.isCorrect()
				&& addressHandler.isCorrect()
				&& genderHandler.isCorrect()
				&& commentHandler.isCorrect()
				&& amigaAgreeHandler.isCorrect();
	}

	public void check(RegisteringUserBuilder rub) {
		nameHandler.check(rub.getName());
		loginHandler.check(rub.getLogin());
		passwordHandler.check(rub.getPassword());
		retypePasswordHandler.check(rub.getPassword(), rub.getRetypePassword());
		emailHandler.check(rub.getEmail());
		addressHandler.check(rub.getAddress());
		genderHandler.check(rub.getGender());
		commentHandler.check(rub.getComment());
		amigaAgreeHandler.check(rub.isAmigaAgree());
		
		updateTips();
	}

	public String getNameTip() {
		return nameTip;
	}

	public String getLoginTip() {
		return loginTip;
	}

	public String getPasswordTip() {
		return passwordTip;
	}

	public String getRetypePasswordTip() {
		return retypePasswordTip;
	}

	public String getEmailTip() {
		return emailTip;
	}

	public String getAddressTip() {
		return addressTip;
	}

	public String getGenderTip() {
		return genderTip;
	}

	public String getCommentTip() {
		return commentTip;
	}

	public String getAmigaAgreeTip() {
		return amigaAgreeTip;
	}
}
