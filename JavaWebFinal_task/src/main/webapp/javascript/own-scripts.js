var projectPath = "/JavaWebFinal_task/";

// products
function plus(id) {
	var input = $("#input" + id).get(0);
	var result = +input.value + 1;
	var prodCountTagId = "#theProdCount" + id;
	
	if($(prodCountTagId).get(0) != undefined) {
		var max = parseInt($(prodCountTagId).text());
		result = Math.min(result, max);
	}

	input.value = result;
}

function minus(id) {
	var input = $("#input" + id).get(0);
	input.value = Math.max(+input.value - 1, 1);
}

// cart
function buy(id) {
	var input = $("#input" + id).get(0);
    var count = parseInt(input.value);

    input.value = 1;
    if(isNaN(count) || count <= 0) {
        alert("Ошибка ввода");
    } else {
        $.ajax({
            type: "PATCH",
            url: projectPath + "api/cart/products/add",
            data: JSON.stringify({"id":id, "count":count}),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function() {
            	setTotalProductsCount();
            }
        });
    }
}

function layOut(id) {
	var input = $("#input" + id).get(0);
	var count = parseInt(input.value);

	input.value = 1;
    if(isNaN(count) || count <= 0) {
        alert("Ошибка ввода");
    } else {
        $.ajax({
            type: "PATCH",
            url: projectPath + "api/cart/products/remove",
            data: JSON.stringify({"id":id, "count":count}),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(products) {
                var count = products.count; 
                
                if(count > 0) {
                    $("#theProdCount" + id).html(count);
                } else {
                    $("#card" + id).remove();
                }
                
                setTotalProductsCount();
            }
        });
    }
}

function setTotalProductsCount() {
    $.ajax({
	    type: "GET",
	    url: projectPath + "api/cart/products",
	    contentType: "application/json; charset=utf-8",
	    dataType: "json",
	    success: function(products) {
	    	var totalCount = 0;
	    	for(i in products) {
	    		totalCount += products[i].count;
	    	}
	        $("#prodCount").html(totalCount);
	    }
	});
}

function signIn() {
	refreshSignInTagsView();

	var login = $("#login").val();
	var password = $("#password").val();

	try {
		if(!checkAuthentication(login, password)) {
			throw new AuthenticationFilledFieldsException();
		}

		$.ajax({
			type: "POST",
			url: projectPath + "api/user/session",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify({
				"login":login,
				"password":password
			}),
			statusCode: {
				201: function() { // user found
					window.location.replace(projectPath + "view/user/home");
				},
				403: function() { // blocked
					signInBlockerUpdateTimeAndShow();
				},
				404: function() { // not found such user
					suchUserNotFoundView();
				},
				409: function() { // already signed in as another user
					// should not occur if the request is made from a browser
					alert("You already signed in as another user");
				},
				422: function() { // incorrect filled fields
					incorrectSignInFilledFieldsView();
				}
			}
		});
	} catch(ex) {
		if(ex instanceof AuthenticationFilledFieldsException) {
			incorrectSignInFilledFieldsView();
		} else {
			throw ex;
		}
	}
}

class AuthenticationFilledFieldsException {}

function checkAuthentication(login, password) {
	return login.length > 0 && password.length > 0
			&& login.trim() == login && password.trim() == password;
}

function incorrectSignInFilledFieldsView() {
	$("#invalid-fields").prop("hidden", false);
}

function suchUserNotFoundView() {
	$.ajax({
	    type: "GET",
	    url: projectPath + "api/user/sign-in/attempts",
	    dataType: "json",
	    success: function(attempts) {
	    	if(attempts.left > 0) {
	    		$("#user-not-found").prop("hidden", false);
				$("#blocker-counter").prop("hidden", false);
				$("#attempts-left").html(attempts.left);
	    	} else {
	    		signInBlockerUpdateTimeAndShow();
	    	}
	    }
	});
}

function refreshSignInTagsView() {
	$("#invalid-fields").prop("hidden", true);
	$("#user-not-found").prop("hidden", true);
	$("#blocker-counter").prop("hidden", true);
}

function signOut() {
		$.ajax({
		type: "DELETE",
		url: projectPath + "api/user/session",
		statusCode: {
			204: function() {
				window.location.replace(projectPath + "view/user/sign-in");
			},
		}
	});
}

function signInBlockerUpdateTimeAndShow() {
	$.ajax({
		type: "GET",
		url: projectPath + "api/user/sign-in/blocker",
		dataType: "json",
		success: updateBlockerView
	});
}

function signInBlockerUpdateTimeCaptchaAndShow() {
	var input = $("#captchaInput").val();

	$.ajax({
		type: "PATCH",
		url: projectPath + "api/user/sign-in/blocker",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		data: JSON.stringify({
			"captcha":input
		}),
		success: updateBlockerView
	});
}

function updateBlockerView(blockerInfo) {
	var signIn = new SignIn(new SignInBlocker(blockerInfo));

	signIn.updateView();
}

class SignIn {
	constructor(signInBlocker) {
		this.signInBlocker = signInBlocker;
	}

	updateView() {
		if(this.signInBlocker.isBlocked()) {
			$("#showSignIn").prop("hidden", true);
			$("#showBlocker").prop("hidden", false);
			this.signInBlocker.updateView();
		} else {
			$("#showSignIn").prop("hidden", false);
			$("#showBlocker").prop("hidden", true);
		}
	}
}

class SignInBlocker {
	constructor(info) {
		this.millisecondsLeft = info.millisecondsLeft;
		this.generatedCaptcha = info.generatedCaptcha;
		this.captchaRequired = info.captchaRequired;
	}

	isBlocked() {
		return this.isCaptchaRequire() || this.isTimeLeft();
	}

	isCaptchaRequire() {
		return this.captchaRequired;
	}

	isTimeLeft() {
		return this.millisecondsLeft > 0;
	}

	updateView() {
		this.updateTimeView();
		this.updateCaptcha();
	}

	updateTimeView() {
		$("#timeUnblocked").prop("hidden", this.isTimeLeft());
		$("#timeBlocked").prop("hidden", !this.isTimeLeft());
		$("#timeUpdateButton").prop("hidden", this.isCaptchaRequire());
		if(this.isTimeLeft()) {
			$("#secondsLeft").text(this.millisecondsLeft / 1000);
		}
	}

	updateCaptcha() {
		$("#captcha").prop("hidden", !this.isCaptchaRequire());
		if(this.isCaptchaRequire()) {
			$("#captchaView").text(this.generatedCaptcha);
		}
	}
}


function signUp() {
	showInvalidFieldMessage(false);
	showLoginAndOrEmailUsedMessage(false);

	var nameHandler = getNameHandler();
	var loginHandler = getLoginHandler();
	var passwordHandler = getPasswordHandler();
	var retypePasswordHandler = getRetypePasswordHandler();
	var emailHandler = getEmailHandler();
	var address = $("#address option:selected").val();
	var gender = $("#gender input[type='radio']:checked").val();
	var commentHandler = getCommentHandler();
	var amigaAgreeHandler = getAmigaAgreeHandler();

	try {
		if(!nameHandler.isValid() ||
		   !loginHandler.isValid() ||
		   !passwordHandler.isValid() ||
		   !retypePasswordHandler.isValid() ||
		   !emailHandler.isValid() ||
		   !commentHandler.isValid() ||
		   !amigaAgreeHandler.isValid()) {
			throw new SignUpException();
		}

		$.ajax({
			type: "POST",
			url: projectPath + "api/user/sign-up",
			contentType: "application/json; charset=utf-8",
			data: JSON.stringify({
	    		"name":nameHandler.name,
	    		"login":loginHandler.login,
	    		"password":passwordHandler.password,
	    		"retypePassword":retypePasswordHandler.retypePassword,
	    		"email":emailHandler.email,
	    		"address":address,
	    		"gender":gender,
	    		"comment":commentHandler.comment,
	    		"amigaAgree":amigaAgreeHandler.checked
			}),
			statusCode: {
				201: function() {
					signUpSuccess();
				},
				400: function(response) {
					var errorCode = response.responseJSON.error;

					if(errorCode & 0x40000000) {
						showLoginAndOrEmailUsedMessage(true);
					} else {
						// not processed as it was processed before sending
						console.log("error code: " + errorCode);
					}
				}
			}
		})
	} catch(ex) {
		if(ex instanceof SignUpException) {
			showInvalidFieldMessage(true);
		} else {
			throw ex;
		}
	}
}

function signUpSuccess(){
	$("#sign-up-success").prop("hidden", false);
	$("#sign-up-table").prop("hidden", true);
}

function showInvalidFieldMessage(state) {
	$("#invalidField").prop("hidden", !state);
}

function showLoginAndOrEmailUsedMessage(state) {
	$("#loginAndOrEmailUsed").prop("hidden", !state);
}

class SignUpException {}


/* name handler for sign up */
class NameHandler {
	static MIN_LENGTH = 2;
	static MAX_LENGTH = 15;
	name = "";
	present = null;
	lengthInRange = null;
	allowedCharacters = null;
	validCharCase = null;

	constructor(name) {
		this.name = name.trim();
	}

	isValid() {
		return this.isPresent() && this.isLengthInRange()
				&& this.isAllowedCharacters() && this.isValidCharCase();
	}

	isPresent() {
		if(this.present == null) {
			this.present = this.name != null && this.name.length > 0;
		}
		return this.present;
	}

	isLengthInRange() {
		if(this.lengthInRange == null) {
			this.lengthInRange = this.name != null 
							   && this.name.length >= NameHandler.MIN_LENGTH
							   && this.name.length <= NameHandler.MAX_LENGTH;
		}
		return this.lengthInRange;
	}

	isAllowedCharacters() {
		if(this.allowedCharacters == null) {
			this.allowedCharacters = false;
			if(this.name != null) {
				var reg = new RegExp('[A-Za-z]+');
				var array = this.name.match(reg);
				this.allowedCharacters = array != null
											&& array.length === 1
											&& array[0] === this.name;
			}
		}
		return this.allowedCharacters;
	}

	isValidCharCase() {
		if(this.validCharCase == null) {
			this.validCharCase = false;
			if(this.name != null && this.name.length >= 2) {
				var firstChar = this.name.substring(0, 1);
				var last = this.name.substring(1);
				this.validCharCase = firstChar === firstChar.toUpperCase()
									&& last === last.toLowerCase();
			}
		}
		return this.validCharCase;
	}
}

function handleNameInput() {
	updateNameTips(getNameHandler());
}

function getNameHandler() {
	var name = $("#name").val();
	return new NameHandler(name); 
}

function updateNameTips(nameHandler) {
	$("#namePresent").prop("class", nameHandler.isPresent() ? "good" : "bad");
	$("#nameLength").prop("class", nameHandler.isLengthInRange() ? "good" : "bad");
	$("#nameCharCategory").prop("class", nameHandler.isAllowedCharacters() ? "good" : "bad");
	$("#nameCase").prop("class", nameHandler.isValidCharCase() ? "good" : "bad");
}

/* login handler for sign up */
class LoginHandler {
	static MIN_LENGTH = 6;
	static MAX_LENGTH = 14;
	login = "";
	present = null;
	lengthInRange = null;
	allowedCharacters = null;

	constructor(login) {
		this.login = login;
	}

	isValid() {
		return this.isPresent() && this.isLengthInRange()
				&& this.isAllowedCharacters();
	}

	isPresent() {
		if(this.present == null) {
			this.present = this.login != null && this.login.length > 0;
		}
		return this.present;
	}

	isLengthInRange() {
		if(this.lengthInRange == null) {
			this.lengthInRange = this.login != null 
							   && this.login.length >= LoginHandler.MIN_LENGTH
							   && this.login.length <= LoginHandler.MAX_LENGTH;
		}
		return this.lengthInRange;
	}

	isAllowedCharacters() {
		if(this.allowedCharacters == null) {
			this.allowedCharacters = false;
			if(this.login != null) {
				var reg = new RegExp(/[A-Za-z0-9\p{General_Category=Punctuation}]+/gu);
				var array = this.login.match(reg);
				this.allowedCharacters = array != null
											&& array.length === 1
											&& array[0] === this.login;
			}
		}
		return this.allowedCharacters;
	}
}

function handleLoginInput() {
	updateLoginTips(getLoginHandler());
}

function getLoginHandler() {
	var login = $("#login").val();
	return new LoginHandler(login);
}

function updateLoginTips(loginHandler) {
	$("#loginPresent").prop("class", loginHandler.isPresent() ? "good" : "bad");
	$("#loginLength").prop("class", loginHandler.isLengthInRange() ? "good" : "bad");
	$("#loginCharCategory").prop("class", loginHandler.isAllowedCharacters() ? "good" : "bad");
}


/* password handler for sign up */
class PasswordHandler {
	static MIN_LENGTH = 8;
	static MAX_LENGTH = 15;
	password = "";
	present = null;
	lengthInRange = null;
	uppercaseLetters = null;
	lowercaseLetters = null;
	digits = null;
	punctuations = null;

	constructor(password) {
		this.password = password;
	}

	isValid() {
		return this.isPresent() && this.isLengthInRange()
				&& this.hasUppercaseLetters() && this.hasLowercaseLetters()
				&& this.hasDigits() && this.hasPunctuations();
	}

	isPresent() {
		if(this.present == null) {
			this.present = this.password != null && this.password.length > 0;
		}
		return this.present;
	}

	isLengthInRange() {
		if(this.lengthInRange == null) {
			this.lengthInRange = this.password != null 
							   && this.password.length >= PasswordHandler.MIN_LENGTH
							   && this.password.length <= PasswordHandler.MAX_LENGTH;
		}
		return this.lengthInRange;
	}

	hasUppercaseLetters() {
		if(this.uppercaseLetters == null) {
			var reg = new RegExp(/\p{General_Category=Uppercase_Letter}/gu);
			this.uppercaseLetters = reg.test(this.password);
		}
		return this.uppercaseLetters;
	}

	hasLowercaseLetters() {
		if(this.lowercaseLetters == null) {
			var reg = new RegExp(/\p{General_Category=Lowercase_Letter}/gu);
			this.lowercaseLetters = reg.test(this.password);
		}
		return this.lowercaseLetters;
	}

	hasDigits() {
		if(this.digits == null) {
			var reg = new RegExp(/[0-9]/gu);
			this.digits = reg.test(this.password);
		}
		return this.digits;
	}

	hasPunctuations() {
		if(this.punctuations == null) {
			var reg = new RegExp(/\p{General_Category=Punctuation}/gu);
			this.punctuations = reg.test(this.password);
		}
		return this.punctuations;
	}
}

function handlePasswordInput() {
	passwordHandle();
	retypePasswordHandle();
}

function passwordHandle() {
	updatePasswordTips(getPasswordHandler());
}

function getPasswordHandler() {
	var password = $("#password").val();
	return new PasswordHandler(password);
}

function updatePasswordTips(passwordHandler) {
	$("#passwordPresent").prop("class", passwordHandler.isPresent() ? "good" : "bad");
	$("#passwordLength").prop("class", passwordHandler.isLengthInRange() ? "good" : "bad");
	$("#passwordUppercaseLetters").prop("class", passwordHandler.hasUppercaseLetters() ? "good" : "bad");
	$("#passwordLowercaseLetters").prop("class", passwordHandler.hasLowercaseLetters() ? "good" : "bad");
	$("#passwordDigits").prop("class", passwordHandler.hasDigits() ? "good" : "bad");
	$("#passwordPunctuations").prop("class", passwordHandler.hasPunctuations() ? "good" : "bad");	
}

/* retype password handler for sign up */
class RetypePasswordHandler {
	retypePassword = "";
	constructor(password, retypePassword) {
		this.retypePassword = retypePassword;
		this.match = password === retypePassword;
	}

	isValid() {
		 return this.isMatch();
	}

	isMatch() {
		return this.match;
	}
}

function handleRetypePasswordInput() {
	$("#retypePasswordTip").removeAttr("hidden");
	passwordHandle();
	retypePasswordHandle();
}

function retypePasswordHandle() {
	updateRetypePasswordTips(getRetypePasswordHandler());
}

function getRetypePasswordHandler() {
	var password = $("#password").val();
	var retypePassword = $("#retypePassword").prop("value");
	return new RetypePasswordHandler(password, retypePassword);
}

function updateRetypePasswordTips(retypePasswordHandler) {
	$("#retypePasswordMatch").prop("class", retypePasswordHandler.isMatch() ? "good" : "bad");
}


/* email handler for sign up */
class EmailHandler {
	static MAX_LENGTH = 100;
	email = "";
	present = null;
	lengthInRange = null;
	correct = null;

	constructor(email) {
		this.email = email.trim();
	}

	isValid() {
		return this.isPresent() && this.isLengthInRange()
				&& this.isCorrect();
	}

	isPresent() {
		if(this.present == null) {
			this.present = this.email != null && this.email.length > 0;
		}
		return this.present;
	}

	isLengthInRange() {
		if(this.lengthInRange == null) {
			this.lengthInRange = this.email != null 
							   && this.email.length <= EmailHandler.MAX_LENGTH;
		}
		return this.lengthInRange;
	}

	isCorrect() {
		if(this.correct == null) {
			var reg = new RegExp(/(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/);
			this.correct = reg.test(this.email);
		}
		return this.correct;
	}
}

function handleEmailInput() {
	updateEmailTips(getEmailHandler());
}

function getEmailHandler() {
	var email = $("#email").val();
	return new EmailHandler(email);	
}

function updateEmailTips(emailHandler) {
	$("#emailPresent").prop("class", emailHandler.isPresent() ? "good" : "bad");
	$("#emailLength").prop("class", emailHandler.isLengthInRange() ? "good" : "bad");
	$("#emailCorrect").prop("class", emailHandler.isCorrect() ? "good" : "bad");
}

/* comment handler for sign up */
class CommentHandler {
	static MAX_LENGTH = 256;
	comment = "";
	present = null;
	length = null;
	constructor(comment) {
		this.comment = comment.trim();
	}

	isValid() {
		return this.isPresent() && this.isLengthInRange() ;
	}

	isPresent() {
		if(this.present == null) {
			this.present = this.comment != null && this.comment.length > 0;
		}
		return this.present;
	}

	isLengthInRange() {
		return this.getLength() <= CommentHandler.MAX_LENGTH;
	}

	getLength() {
		if(this.length == null) {
			this.lungth = this.comment == null ? 0 : this.comment.length;
		}
		return this.comment.length;
	}
}

function handleCommentInput() {
	updateCommentTips(getCommentHandler());
}

function getCommentHandler() {
	var comment = $("#comment").val();
	return new CommentHandler(comment);
}

function updateCommentTips(commentHandler) {
	$("#commentPresent").prop("class", commentHandler.isPresent() ? "good" : "bad");
	$("#commentLength").prop("class", (commentHandler.isPresent()
									&& commentHandler.isLengthInRange()) ? "good" : "bad");
	$("#commentChars").text(commentHandler.getLength());
}


/* amiga agree handler for sign up*/
class AmigaAgreeHandler {
	constructor(checked) {
		this.checked = checked;
	}

	isValid() {
		return this.isChecked();
	}

	isChecked() {
		return this.checked;
	}
}

function handleAmigaAgreeInput() {
	updateAmigaAgreeTips(getAmigaAgreeHandler());
}

function getAmigaAgreeHandler() {
	var amigaAgree = $("#amigaAgree").prop("checked");
	return new AmigaAgreeHandler(amigaAgree);
}

function updateAmigaAgreeTips(amigaAgreeHandler) {
	$("#amigaAgreePresent").prop("class", amigaAgreeHandler.isChecked() ? "good" : "bad");
}
