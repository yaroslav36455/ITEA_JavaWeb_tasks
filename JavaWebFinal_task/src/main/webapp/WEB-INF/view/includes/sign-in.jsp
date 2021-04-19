<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>


<div class="sendingExceptionMessage" hidden id="invalid-fields">Ошибка: вы не заполнили или заполнили некорректно одно или боллее полей</div>
<div class="sendingExceptionMessage" hidden id="user-not-found">Ошибка: пользователь с таким логином и/или паролем не найден</div>
<div class="sendingExceptionMessage" hidden id="blocker-counter">У вас осталось <span id="attempts-left"></span> попытки до блокирования</div>
<table id="sign-in-table" border='0'>
	<tr>
		<td>Логин:</td>
		<td><input id="login" type='text'></td>
	</tr>
	<tr>
		<td>Пароль:</td>
		<td><input id="password" type='password'></td>
	</tr>
	<tr>
		<td></td>
		<td align="right"><button onclick="signIn()">Вход</button></td>
	</tr>
</table>
