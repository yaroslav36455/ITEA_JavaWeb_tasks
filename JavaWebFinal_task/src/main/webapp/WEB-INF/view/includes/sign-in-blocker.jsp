<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<c:set var="isCaptchaRequire" value='${not sessionScope.signInBlocker.isCaptchaValid()}' scope="page"/>
<c:set var="generatedCaptcha" value='${sessionScope.signInBlocker.getGeneratedCaptcha().get()}' scope="page"/>
<c:set var="isTimeLeft" value='${sessionScope.signInBlocker.getRemainingTimeMilliseconds() > 0}' scope="page"/>
<c:set var="secondsLeft" value='${sessionScope.signInBlocker.getRemainingTimeSeconds()}' scope="page"/>

<h3>
	<font ${isTimeLeft ? "hidden" : ""} id="timeUnblocked" color="green">
		Блокировка по времени снята
	</font>
	<font ${isTimeLeft ? "" : "hidden"} id="timeBlocked" color="red">
		Вход заблокирован на <span id="secondsLeft">${secondsLeft}</span> секунд
	</font>
</h3>

<div ${isCaptchaRequire ? "" : "hidden"} id="captcha">
	<h3>Введите:<span id="captchaView">${generatedCaptcha}</span></h3>
	<div>
		<input id="captchaInput" type="text"/>
	</div>
	<div>
		<button onclick="signInBlockerUpdateTimeCaptchaAndShow()">
			Отправить
		</button>
	</div>
</div>
<div ${isCaptchaRequire ? "hidden" : ""} id="timeUpdateButton">
	<button onclick="signInBlockerUpdateTimeAndShow()">
		Обновить
	</button>
</div>
