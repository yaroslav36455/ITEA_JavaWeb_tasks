<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<%@ include file="includes/header.jsp"%>
<form action='/JavaWeb12_task/sign-in.html' method="post">
	<center>
		<table border='0'>
			<tr>
				<td>Логин:</td>
				<td><input type='text' name='login'></td>
			</tr>
			<tr>
				<td>Пароль:</td>
				<td><input type='password' name='password'></td>
			</tr>
			<tr>
				<td></td>
				<td align='right'><input type='submit' value='Вход'></td>
			</tr>
		</table>
	</center>
</form>
<%@ include file="includes/footer.jsp"%>