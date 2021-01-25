<%@ page import="ua.itea.SessionAttributeManager" %>
<%@ page import="ua.itea.form.SignInForm" %>
<%@ include file = "includes/header.jsp" %>
<%
	SessionAttributeManager sam = new SessionAttributeManager(session);
	SignInForm signInForm = sam.getAttribute(SignInForm.class);
%>

<a href="/sign-up">Sign Up</a>
<form id="loginForm" action="/sign-in" method="post">

<%if(signInForm.isAccessDenied()) {%>
	<div class="field">
		<center><label style="color: red">Access denied, try again</label></center>
	</div>
<%}%>

	<div class="field">
		<label>Enter your login:</label>
		<div class="input">
			<input type="text" name="login" value="<%=(signInForm.getLogin())%>" id="login" />
		</div>
	</div>

	<div class="field">
		<a href="#" id="forgot">Forgot your password?</a>
		<label>Enter your password:</label>
		<div class="input">
			<input type="password" name="password" value="" id="pass" />
		</div>
	</div>

	<div class="submit">
		<button type="submit">Enter</button>
		<label id="remember"><input name="" type="checkbox" value="" /> Remember me</label>
	</div>

</form>

<%@ include file = "includes/footer.jsp" %>