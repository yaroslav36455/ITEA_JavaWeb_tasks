<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ include file="includes/header.jsp"%>
<form action="/JavaWeb12_task/sign-up.html" method="post">
	<center>
		<table border="0">
			<tr>
				<td>Имя:</td>
				<td><input value="${sessionScope.registeringUserBuilder.name}"
					type="text" name="name"></td>
				<td>${sessionScope.signUpHandler.nameTip}</td>
			</tr>
			<tr>
				<td>Логин:</td>
				<td><input value="${sessionScope.registeringUserBuilder.login}"
					type="text" name="login"></td>
				<td>${sessionScope.signUpHandler.loginTip}</td>
			</tr>
			<tr>
				<td>Пароль:</td>
				<td><input
					value=""
					type="password" name="password"></td>
				<td>${sessionScope.signUpHandler.passwordTip}</td>
			</tr>
			<tr>
				<td>Повторите пароль:</td>
				<td><input
					value=""
					type="password" name="retypePassword"></td>
				<td>${sessionScope.signUpHandler.retypePasswordTip}</td>
			</tr>
			<tr>
				<td>e-mail:</td>
				<td><input value="${sessionScope.registeringUserBuilder.email}"
					type="text" name="email"></td>
				<td>${sessionScope.signUpHandler.emailTip}</td>
			</tr>
			<tr>
				<td>Адрес:</td>
				<td><select name="address">
						<option
							${sessionScope.registeringUserBuilder.address eq 'DNR' ? 'selected' : ''}
							value="DNR">DNR</option>
						<option
							${sessionScope.registeringUserBuilder.address eq 'LNR' ? 'selected' : ''}
							value="LNR">LNR</option>
						<option
							${sessionScope.registeringUserBuilder.address eq 'TSD' ? 'selected' : ''}
							value="TSD">TSD</option>
				</select></td>
				<td>${sessionScope.signUpHandler.addressTip}</td>
			</tr>
			<tr>
				<td>Пол:</td>
				<td>'M' <input
					${sessionScope.registeringUserBuilder.gender eq 'MALE' ? 'checked' : ''}
					value="MALE" type="radio" name="gender" /> 'Ж' <input
					${sessionScope.registeringUserBuilder.gender eq 'FEMALE' ? 'checked' : ''}
					value="FEMALE" type="radio" name="gender" />
				</td>
				<td>${sessionScope.signUpHandler.genderTip}</td>
			</tr>
			<tr>
				<td>Комментарий:</td>
				<td><textarea name="comment" cols="20" rows="10">${sessionScope.registeringUserBuilder.comment}</textarea></td>
				<td>${sessionScope.signUpHandler.commentTip}</td>
			</tr>
			<tr>
				<td>Я согласен/согласна установить Амига браузер:</td>
				<td><input
					${sessionScope.registeringUserBuilder.amigaAgree ? 'checked' : ''}
					type="checkbox" name="amigaAgree"></td>
				<td>${sessionScope.signUpHandler.amigaAgreeTip}</td>
			</tr>
			<tr>
				<td></td>
				<td align="right"><input type="submit" value="Отправить"></td>
			</tr>
		</table>
	</center>
</form>
<%@ include file="includes/footer.jsp"%>
