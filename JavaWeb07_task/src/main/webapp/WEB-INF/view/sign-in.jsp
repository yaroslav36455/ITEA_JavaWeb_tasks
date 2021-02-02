<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<%@ include file="includes/header.jsp"%>
<c:choose>
	<c:when test="${empty sessionScope.user}">
		<form action='sign-in' method="post">
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
	</c:when>
	<c:otherwise>
		<form action="sign-in" method="post">
			<center>
				<div><h3>Вы авторизованы как ${sessionScope.user.name}!</h3></div>
				<div>
					<input type='submit' name='sign-out' value='Выход'>
				</div>
			</center>
		</form>
	</c:otherwise>
</c:choose>
<%@ include file="includes/footer.jsp"%>