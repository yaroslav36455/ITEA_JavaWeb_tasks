<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<%@ include file="includes/header.jsp"%>
<center>
	<h3>
		<c:choose>
			<c:when test="${sessionScope.signInBlocker.remainingTimeMilliseconds eq 0}">
				<font color="green">Блокировка по времени снята</font>
			</c:when>
			<c:otherwise>
				<font color="red">Вход заблокирован на
					${sessionScope.signInBlocker.remainingTimeSeconds} секунд</font>
			</c:otherwise>
		</c:choose>
	</h3>

	<form action="sign-in.html" method="post">
		<c:choose>
			<c:when test="${not sessionScope.signInBlocker.captchaValid}">
				<div>
					<h3>Введите:${sessionScope.signInBlocker.generatedCaptcha}</h3>
					<div>
						<input type="text" name="captcha" value="" />
					</div>
				</div>
				<div>
					<input type="submit" value="Отправить"/>
				</div>
			</c:when>
			<c:otherwise>
				<div class="submit">
					<input type="submit" value="Обновить"/>
				</div>
			</c:otherwise>
		</c:choose>
	</form>
</center>
<%@ include file="includes/footer.jsp"%>