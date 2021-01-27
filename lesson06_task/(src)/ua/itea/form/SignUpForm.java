package ua.itea.form;

import ua.itea.Address;
import ua.itea.AutoCreatableAttribute;
import ua.itea.Gender;
import ua.itea.SessionAttribute;
import ua.itea.signup.SignUpBuilder;
import ua.itea.signup.SignUpHandler;

@AutoCreatableAttribute
public class SignUpForm implements SessionAttribute {
	private String name;
	private String login;
	private String email;
	private Address address;
	private Gender gender;
	private String comment;
	private boolean agreeAmigaInstall;
	
	private String nameTip;
	private String loginTip;
	private String passwordTip;
	private String retypePasswordTip;
	private String emailTip;
	private String addressTip;
	private String genderTip;
	private String commentTip;
	private String agreeAmigaInstallTip;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public boolean isAgreeAmigaInstall() {
		return agreeAmigaInstall;
	}
	public void setAgreeAmigaInstall(boolean agreeAmigaInstall) {
		this.agreeAmigaInstall = agreeAmigaInstall;
	}
	public String getNameTip() {
		return nameTip;
	}
	public void setNameTip(String nameTip) {
		this.nameTip = nameTip;
	}
	public String getLoginTip() {
		return loginTip;
	}
	public void setLoginTip(String loginTip) {
		this.loginTip = loginTip;
	}
	public String getPasswordTip() {
		return passwordTip;
	}
	public void setPasswordTip(String passwordTip) {
		this.passwordTip = passwordTip;
	}
	public String getRetypePasswordTip() {
		return retypePasswordTip;
	}
	public void setRetypePasswordTip(String retypePasswordTip) {
		this.retypePasswordTip = retypePasswordTip;
	}
	public String getEmailTip() {
		return emailTip;
	}
	public void setEmailTip(String emailTip) {
		this.emailTip = emailTip;
	}
	public String getAddressTip() {
		return addressTip;
	}
	public void setAddressTip(String addressTip) {
		this.addressTip = addressTip;
	}
	public String getGenderTip() {
		return genderTip;
	}
	public void setGenderTip(String genderTip) {
		this.genderTip = genderTip;
	}
	public String getCommentTip() {
		return commentTip;
	}
	public void setCommentTip(String commentTip) {
		this.commentTip = commentTip;
	}
	public String getAgreeAmigaInstallTip() {
		return agreeAmigaInstallTip;
	}
	public void setAgreeAmigaInstallTip(String agreeAmigaInstallTip) {
		this.agreeAmigaInstallTip = agreeAmigaInstallTip;
	}
	
	public void fill(SignUpBuilder sub, SignUpHandler suh) {
		setName(sub.getName());
		setNameTip(suh.getNameHandler().getTip());
		
		setLogin(sub.getLogin());
		setLoginTip(suh.getLoginHandler().getTip());
		
		setPasswordTip(suh.getPasswordHandler().getTip());
		setRetypePasswordTip(suh.getRetypePasswordHandler().getTip());
		
		setEmail(sub.getEmail());
		setEmailTip(suh.getEmailHandler().getTip());
		
		setAddress(Enum.valueOf(Address.class, sub.getAddress()));
		setAddressTip(suh.getAddressHandler().getTip());
		
		setGender(Enum.valueOf(Gender.class, sub.getGender()));
		setGenderTip(suh.getAddressHandler().getTip());
		
		setComment(sub.getComment());
		setCommentTip(suh.getCommentHandler().getTip());
		
		setAgreeAmigaInstall(sub.isAmigaAgree());
		setAgreeAmigaInstallTip(suh.getAmigaAgreeHandler().getTip());
	}
}
