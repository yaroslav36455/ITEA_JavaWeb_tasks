<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<%@ include file="includes/header.jsp"%>

<c:set var="isBlocked" value="${sessionScope.signInBlocker ne null and sessionScope.signInBlocker.isBlocked()}" scope="page"/>

<center>
	<div ${isBlocked ? "hidden" : ""} id="showSignIn">
		<%@ include file="includes/sign-in.jsp"%>
	</div>
	<div ${isBlocked ? "" : "hidden"} id="showBlocker">
		<%@ include file="includes/sign-in-blocker.jsp"%>
	</div>
</center>
<%@ include file="includes/footer.jsp"%>