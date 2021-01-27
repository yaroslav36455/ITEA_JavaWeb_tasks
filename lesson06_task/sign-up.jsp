<%@ page import="ua.itea.Address"%>
<%@ page import="ua.itea.Gender"%>
<%@ page import="ua.itea.SessionAttributeManager"%>
<%@ page import="ua.itea.form.SignUpForm"%>
<%@ include file = "includes/header.jsp" %>

<% 
	SessionAttributeManager sam = new SessionAttributeManager(session);
	SignUpForm suf = sam.getAttribute(SignUpForm.class);

	String name = suf.getName();
	String login = suf.getLogin();
	String password = "";
	String retypePassword = "";
	String email = suf.getEmail();
	String address = suf.getAddress().toString();
	String gender = suf.getGender().toString();
	String comment = suf.getComment();
	boolean agree = suf.isAgreeAmigaInstall();

	String nameTip = suf.getNameTip();
	String loginTip = suf.getLoginTip();
	String passwordTip = suf.getPasswordTip();
	String retypePasswordTip = suf.getRetypePasswordTip();
	String emailTip = suf.getEmailTip();
	String addressTip = suf.getAddressTip();
	String genderTip = suf.getGenderTip();
	String commentTip = suf.getCommentTip();
	String agreeTip = suf.getAgreeAmigaInstallTip();
%>
<a href="/sign-in">Sign In</a>
<br/><br/>
<form id="signupForm" action="/sign-up" method="post">
	<center>
		<table border="0">
		<tr>
			<td>name:</td>
			<td><input value="<%=name%>" type="text" name="name"></td>
			<td><%=(nameTip)%></td>
		</tr>
		<tr>
			<td>login:</td>
			<td><input value="<%=login%>" type="text" name="login"></td>
			<td><%=(loginTip)%></td>
		</tr>
		<tr>
			<td>password:</td>
			<td><input value="<%=password%>" type="password" name="password"></td>
			<td><%=(passwordTip)%></td>
		</tr>
		<tr>
			<td>retype password:</td>
			<td><input value="<%=retypePassword%>" type="password" name="retypePassword"></td>
			<td><%=(retypePasswordTip)%></td>
		</tr>
		<tr>
			<td>e-mail:</td>
			<td><input value="<%=email%>" type="text" name="email"></td>
			<td><%=(emailTip)%></td>
		</tr>
		<tr>
			<td>address:</td>
			<td>
				<select name="address">
					<option <%=(address.equals(Address.DNR.toString()) ? "selected": "")%>
						value="<%=(Address.DNR.toString())%>"><%=(Address.DNR.toString())%></option>
					<option <%=(address.equals(Address.LNR.toString()) ? "selected": "")%>
					 	value="<%=(Address.LNR.toString())%>"><%=(Address.LNR.toString())%></option>
					<option <%=(address.equals(Address.TSD.toString()) ? "selected": "")%>
						value="<%=(Address.TSD.toString())%>"><%=(Address.TSD.toString())%></option>
				</select>
			</td>
			<td><%=(addressTip)%></td>
		</tr>
		<tr>
			<td>gender:</td>
			<td>
				<%=(Gender.MALE.toString())%>
					<input <%= (gender.equals(Gender.MALE.toString()) ? "checked" : "")%>
							value="<%= (Gender.MALE.toString())%>" type="radio" name="gender"/>
				<%=(Gender.FEMALE.toString())%>
					<input <%= (gender.equals(Gender.FEMALE.toString()) ? "checked" : "")%> 
							value="<%= (Gender.FEMALE.toString())%>" type="radio" name="gender"/>
			</td>
			<td><%=(genderTip)%></td>
		</tr>
		<tr>
			<td>comment:</td>
			<td><textarea name="comment" cols="20" rows="10"><%= comment%></textarea></td>
			<td><%=(commentTip)%></td>
		</tr>
		<tr>
			<td>I agree to install Amiga browser:</td>
			<td><input <%= (agree ? "checked" : "")%> type="checkbox" name="agree"></td>
			<td><%=(agreeTip)%></td>
		</tr>
		<tr>
			<td></td>
			<td align="right"><input type="submit" value="Send"></td>
		</tr>
		</table>
	</center>
</form>

<%@ include file = "includes/footer.jsp" %>