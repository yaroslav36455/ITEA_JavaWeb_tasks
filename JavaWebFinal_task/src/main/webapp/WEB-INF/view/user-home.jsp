<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<%@ include file="includes/header.jsp"%>
<center>
	<table>
		<tr>
			<td>Имя:</td>
			<td>${sessionScope.user.name}</td>
		</tr>
		<tr>
			<td>Пол:</td>
			<td><c:choose>
					<c:when test="${sessionScope.user.gender eq 'MALE'}">Мужской</c:when>
					<c:when test="${sessionScope.user.gender eq 'FEMALE'}">Женский</c:when>
				</c:choose></td>
		</tr>
		<tr>
			<td>Логин:</td>
			<td>${sessionScope.user.login}</td>
		</tr>
		<tr>
			<td>Почта:</td>
			<td>${sessionScope.user.email}</td>
		</tr>
		<tr>
			<td>Адрес:</td>
			<td><c:choose>
                    <c:when test="${sessionScope.user.address eq 'DNR'}">Донецкая Народная Республика</c:when>
                    <c:when test="${sessionScope.user.address eq 'LNR'}">Луганская Народная Республика</c:when>
                    <c:when test="${sessionScope.user.address eq 'KNR'}">Крымская Народная Республика</c:when>
                </c:choose></td>
		</tr>
		<tr>
			<td>Комментарии:</td>
			<td>${sessionScope.user.comment}</td>
		</tr>
	</table>
	<button onclick="signOut()">Выход</button>
</center>
<%@ include file="includes/footer.jsp"%>