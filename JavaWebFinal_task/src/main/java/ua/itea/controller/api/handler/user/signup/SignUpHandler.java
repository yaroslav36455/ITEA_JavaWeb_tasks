package ua.itea.controller.api.handler.user.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.itea.api.SignUpRequest;
import ua.itea.api.SignUpResponse;
import ua.itea.dao.UserDAO;
import ua.itea.model.users.Address;
import ua.itea.model.users.Gender;

@Component
public class SignUpHandler {
	@Autowired
	private UserDAO userDAO;
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
	
	public SignUpResponse handle(SignUpRequest signUpRequest) throws SignUpException {
		SignUpResponse signUpResponse = check(signUpRequest);
		
		if(!signUpResponse.isError()) {
			RegisteringUser registeringUser = new RegisteringUser();
			
			registeringUser.setName(signUpRequest.getName());
			registeringUser.setLogin(signUpRequest.getLogin());
			registeringUser.setPassword(signUpRequest.getPassword());
			registeringUser.setEmail(signUpRequest.getEmail());
			registeringUser.setAddress(Enum.valueOf(Address.class, signUpRequest.getAddress()));
			registeringUser.setGender(Enum.valueOf(Gender.class, signUpRequest.getGender()));
			registeringUser.setComment(signUpRequest.getComment());
			
			signUpResponse.setLoginAndOrEmailUsed(!userDAO.insert(registeringUser));
		}
		
		if(signUpResponse.isError()) {
			throw new SignUpException(signUpResponse);
		}
		
		return signUpResponse;
	}
	
	private SignUpResponse check(SignUpRequest sur) {
		SignUpResponse signUpResponse = new SignUpResponse();
		
		nameHandler.check(sur.getName());
		loginHandler.check(sur.getLogin());
		passwordHandler.check(sur.getPassword());
		retypePasswordHandler.check(sur.getPassword(), sur.getRetypePassword());
		emailHandler.check(sur.getEmail());
		addressHandler.check(sur.getAddress());
		genderHandler.check(sur.getGender());
		commentHandler.check(sur.getComment());
		amigaAgreeHandler.check(sur.isAmigaAgree());
		
		signUpResponse.setNameNotPresent(!nameHandler.isPresent());
		signUpResponse.setNameLengthOutOfRange(!nameHandler.isLengthInRange());
		signUpResponse.setNameNotAllowedCharactersPresent(!nameHandler.isAllowedCharactersPresent());
		signUpResponse.setNameNotAllowedCharacterCasePresent(!nameHandler.isValidCharacterCase());
		
		signUpResponse.setLoginNotPreseent(!loginHandler.isPresent());
		signUpResponse.setLoginLengthOutOfRange(!loginHandler.isLengthInRange());
		signUpResponse.setLoginNotAllowedCharactersPresent(!loginHandler.isAllowedCharactersPresent());
		
		signUpResponse.setPasswordNotPresent(!passwordHandler.isPresent());
		signUpResponse.setPasswordLengthOutOfRange(!passwordHandler.isLengthInRange());
		signUpResponse.setPasswordUppercaseLettersNotPresent(!passwordHandler.isUppercaseLettersPresent());
		signUpResponse.setPasswordLowercaseLettersNotPresent(!passwordHandler.isLowercaseLettersPresent());
		signUpResponse.setPasswordDigitsNotPresent(!passwordHandler.isDigitsPresent());
		signUpResponse.setPasswordPunctuationsNotPresent(!passwordHandler.isPunctuationsPresent());
		
		signUpResponse.setRetypePasswordNotPresent(!retypePasswordHandler.isPresent());
		signUpResponse.setRetypePasswordNotMatch(!retypePasswordHandler.isMatch());
		
		//fix email handler, fix trim for some fields (not only in email handler)
		signUpResponse.setEmailNotPresent(!emailHandler.isPresent());
		signUpResponse.setEmailIncorrect(!emailHandler.isCorrect());
		
		signUpResponse.setAddressNotPresent(!addressHandler.isPresent());
		signUpResponse.setAddressIncorrect(!addressHandler.isCorrect());
		
		signUpResponse.setGenderNotPresent(!genderHandler.isPresent());
		signUpResponse.setGenderIncorrect(!genderHandler.isCorrect());
		
		signUpResponse.setCommentNotPresent(!commentHandler.isPresent());
		signUpResponse.setCommentLengthOutOfRange(!commentHandler.isLengthInRange());
		
		signUpResponse.setAmigaNotAgree(!amigaAgreeHandler.isAgree());

		return signUpResponse;
	}
}
