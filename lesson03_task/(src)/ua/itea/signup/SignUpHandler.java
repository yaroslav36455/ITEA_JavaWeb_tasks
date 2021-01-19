package ua.itea.signup;

import java.util.ArrayList;
import java.util.List;

public class SignUpHandler {
	private NameHandler nameHandler;
	private LoginHandler loginHandler;
	private PasswordHandler passwordHandler;
	private RetypePasswordHandler retypePasswordHandler;
	private EmailHandler emailHandler;
	private AddressHandler addressHandler;
	private GenderHandler genderHandler;
	private CommentHandler commentHandler;
	private AmigaAgreeHandler amigaAgreeHandler;

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
	}

	public NameHandler getNameHandler() {
		return nameHandler;
	}

	public LoginHandler getLoginHandler() {
		return loginHandler;
	}

	public PasswordHandler getPasswordHandler() {
		return passwordHandler;
	}

	public RetypePasswordHandler getRetypePasswordHandler() {
		return retypePasswordHandler;
	}

	public EmailHandler getEmailHandler() {
		return emailHandler;
	}

	public AddressHandler getAddressHandler() {
		return addressHandler;
	}

	public GenderHandler getGenderHandler() {
		return genderHandler;
	}

	public CommentHandler getCommentHandler() {
		return commentHandler;
	}

	public AmigaAgreeHandler getAmigaAgreeHandler() {
		return amigaAgreeHandler;
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

	public void check(SignUpBuilder sub) {
		nameHandler.check(sub.getName());
		loginHandler.check(sub.getLogin());
		passwordHandler.check(sub.getPassword());
		retypePasswordHandler.check(sub.getPassword(), sub.getRetypePassword());
		emailHandler.check(sub.getEmail());
		addressHandler.check(sub.getAddress());
		genderHandler.check(sub.getGender());
		commentHandler.check(sub.getComment());
		amigaAgreeHandler.check(sub.isAmigaAgree());
	}

	public List<String> getHints() {
		List<String> hints = new ArrayList<>();

		if (!nameHandler.isCorrect()) {
			hints.add(nameHandler.getHint());
		}

		if (!loginHandler.isCorrect()) {
			hints.add(loginHandler.getHint());
		}

		if (!passwordHandler.isCorrect()) {
			hints.add(passwordHandler.getHint());
		}

		if (!passwordHandler.isCorrect()) {
			hints.add(passwordHandler.getHint());
		}

		if (!emailHandler.isCorrect()) {
			hints.add(emailHandler.getHint());
		}

		if (!addressHandler.isCorrect()) {
			hints.add(addressHandler.getHint());
		}

		if (!genderHandler.isCorrect()) {
			hints.add(genderHandler.getHint());
		}

		if (!commentHandler.isCorrect()) {
			hints.add(commentHandler.getHint());
		}

		if (!amigaAgreeHandler.isCorrect()) {
			hints.add(amigaAgreeHandler.getHint());
		}

		return hints;
	}
}
