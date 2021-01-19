<%@ page import="ua.itea.signup.*"%>
<%@ page import="ua.itea.signup.SignUpHandler"%>
<%@ include file = "includes/header.jsp" %>
<br/><br/>

<% 
	SignUpBuilder sub = new SignUpBuilder();

	sub.setName(request.getParameter("name"));
	sub.setLogin(request.getParameter("login"));
	sub.setPassword(request.getParameter("password"));
	sub.setRetypePassword(request.getParameter("retypepassword"));
	sub.setEmail(request.getParameter("email"));
	sub.setAddress(request.getParameter("address"));
	sub.setGender(request.getParameter("gender"));
	sub.setComment(request.getParameter("comment"));
	sub.setAmigaAgree(request.getParameter("agree"));

	String name = sub.getName();
	String login = sub.getLogin();
	String password = "";
	String retypepassword = "";
	String email = sub.getEmail();
	String address = sub.getAddress();
	String gender = sub.getGender();
	String comment = sub.getComment();
	boolean agree = sub.isAmigaAgree();

	SignUpHandler suh = new SignUpHandler();
	suh.check(sub);

	if(!suh.isCorrect()) {
%>

<form id="signupForm" action="" method="post">
	<center>
		<table border="0">
		<tr>
			<td>name:</td>
			<td><input value="<%=name%>" type="text" name="name"></td>
			<td><%= (suh.getNameHandler().getHint()) %></td>
		</tr>
		<tr>
			<td>login:</td>
			<td><input value="<%=login%>" type="text" name="login"></td>
			<td><%= (suh.getLoginHandler().getHint()) %></td>
		</tr>
		<tr>
			<td>password:</td>
			<td><input value="<%=password%>" type="password" name="password"></td>
			<td><%= (suh.getPasswordHandler().getHint()) %></td>
		</tr>
		<tr>
			<td>retype password:</td>
			<td><input value="<%=retypepassword%>" type="password" name="retypepassword"></td>
			<td><%= (suh.getRetypePasswordHandler().getHint()) %></td>
		</tr>
		<tr>
			<td>e-mail:</td>
			<td><input value="<%=email%>" type="text" name="email"></td>
			<td><%= (suh.getEmailHandler().getHint()) %></td>
		</tr>
		<tr>
			<td>address:</td>
			<td>
				<select name="address">
					<option <%= (address.equals("DNR") ? "selected": "") %> value="DNR">DNR</option><option <%= (address.equals("LNR") ? "selected": "") %> value="LNR">LNR</option>
					<option <%= (address.equals("TSD") ? "selected": "") %> value="TSD">TSD</option>
				</select>
			</td>
			<td><%= (suh.getAddressHandler().getHint()) %></td>
		</tr>
		<tr>
			<td>gender:</td>
			<td>
				M<input <%= (gender.equals("M") ? "checked" : "")%> value="M" type="radio" name="gender">
				F<input <%= (gender.equals("F") ? "checked" : "")%> value="F" type="radio" name="gender">
			</td>
			<td><%= (suh.getGenderHandler().getHint()) %></td>
		</tr>
		<tr>
			<td>comment:</td>
			<td><textarea name="comment" cols="20" rows="10"><%= comment%></textarea></td>
			<td><%= (suh.getCommentHandler().getHint()) %></td>
		</tr>
		<tr>
			<td>I agree to install Amiga browser:</td>
			<td><input <%= (agree ? "checked" : "")%> type="checkbox" name="agree"></td>
			<td><%= (suh.getAmigaAgreeHandler().getHint()) %></td>
		</tr>
		<tr>
			<td></td>
			<td align="right"><input type="submit" value="Send"></td>
		</tr>
		</table>
	</center>
</form>

<%} else{
	out.write("Sign up success");
}%>

<%@ include file = "includes/footer.jsp" %>