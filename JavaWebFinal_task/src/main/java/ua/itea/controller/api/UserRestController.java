package ua.itea.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ua.itea.api.SignInAttempts;
import ua.itea.api.SignInBlockerInfo;
import ua.itea.api.SignInBlockerUpdate;
import ua.itea.api.SignInRequest;
import ua.itea.api.SignUpRequest;
import ua.itea.api.SignUpResponse;
import ua.itea.api.UserInfo;
import ua.itea.controller.api.handler.user.UnauthenticatedUserException;
import ua.itea.controller.api.handler.user.UserHandler;
import ua.itea.controller.api.handler.user.signin.AlreadyAuthenticatedAsAnotherUserException;
import ua.itea.controller.api.handler.user.signin.BlockerException;
import ua.itea.controller.api.handler.user.signin.IncorrectFilledFieldsException;
import ua.itea.controller.api.handler.user.signin.SignInBlockerHandler;
import ua.itea.controller.api.handler.user.signin.SignInException;
import ua.itea.controller.api.handler.user.signin.SignInHandler;
import ua.itea.controller.api.handler.user.signin.UserNotFoundException;
import ua.itea.controller.api.handler.user.signup.SignUpException;
import ua.itea.controller.api.handler.user.signup.SignUpHandler;

@RestController
@RequestMapping(path = "api/user")
public class UserRestController {
	@Autowired
	private UserHandler userHandler;
	@Autowired
	private SignUpHandler signUpHandler;
	@Autowired
	private SignInHandler signInHandler;
	@Autowired
	private SignInBlockerHandler sibh;

	@GetMapping
	public UserInfo getInfo() throws UnauthenticatedUserException {		
		return userHandler.getInfo();
	}
	
	@PostMapping(path = "sign-up")
	@ResponseStatus(code = HttpStatus.CREATED)
	public SignUpResponse signUp(@RequestBody SignUpRequest signUpRequest)
														throws SignUpException {		
		return signUpHandler.handle(signUpRequest);
	}
	
	@PostMapping(path = "session")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void signIn(@RequestBody SignInRequest signInRequest) throws SignInException {		
		signInHandler.signIn(signInRequest);
	}
	
	@DeleteMapping(path = "session")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void signOut() {		
		userHandler.remove();
	}
	
	@PatchMapping(path = "sign-in/blocker")
	@ResponseStatus(code = HttpStatus.OK)
	public SignInBlockerInfo blockerUpdate(@RequestBody SignInBlockerUpdate sibu) {		
		return sibh.updateAndGetInfo(sibu);
	}
	
	@GetMapping(path = "sign-in/blocker")
	@ResponseStatus(code = HttpStatus.OK)
	public SignInBlockerInfo blockerInfo() {		
		return sibh.updateAndGetInfo();
	}
	
	@GetMapping(path = "sign-in/attempts")
	@ResponseStatus(code = HttpStatus.OK)
	public SignInAttempts getAttempts() {		
		return sibh.getAttempts();
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = SignUpException.class)
	public @ResponseBody SignUpResponse badSignUp(SignUpException ex) {
		return ex.getSignUpResponse();
	}
	
	@ResponseStatus(value = HttpStatus.CONFLICT)
	@ExceptionHandler(value = AlreadyAuthenticatedAsAnotherUserException.class)
	public void conflictException() {
		/* empty */
	}
	
	@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(value = IncorrectFilledFieldsException.class)
	public void unprocessableEntityException() {
		/* empty */
	}
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = UserNotFoundException.class)
	public void notFoundException() {
		/* empty */
	}
	
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	@ExceptionHandler(value = {UnauthenticatedUserException.class,
							   BlockerException.class})
	public void forbiddenException() {
		/* empty */
	}
}
