<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<%@ include file="includes/header.jsp"%>
<form action="/JavaWeb12_task/sign-in.html" method="post">
	<center>
		<div>
			<h3>Вы авторизованы как ${sessionScope.user.name}!</h3>
		</div>
		<div>
		    <input type="hidden" name="out">
			<input type='submit' value='Выход' />
		</div>
	</center>
</form>
<%@ include file="includes/footer.jsp"%>