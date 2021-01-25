<%@ page import="ua.itea.form.SignInBlockerForm" %>
<%@ include file = "includes/header.jsp" %>

<% 
	SignInBlockerForm signInBlockerForm
						= (SignInBlockerForm) session.getAttribute("signInBlockerForm");
	String timeColor = signInBlockerForm.getTimeMessageColor();
	String timeMessage = signInBlockerForm.getTimeMessage();
%>
<center>
	<h1>Sign in blocker</h1>
	<font color="<%=signInBlockerForm.getTimeMessageColor()%>"><%=timeMessage%></font>

<form id="loginForm" action="/sign-in-blocker" method="post">

<%if(signInBlockerForm.isShowCapcha()) {%>

	<div class="field">
		<label>Enter:<%=signInBlockerForm.getCapcha()%></label>
		<div class="input">
			<input type="text" name="capcha" value="" id="login" />
		</div>
	</div>

	<div class="submit">
		<button type="submit">Enter</button>
	</div>

<%} else {%>
	  <div class="submit">
		<button type="submit">Try Again</button>
	  </div>
<%}%>

</form>
</center>

<%@ include file = "includes/footer.jsp" %>