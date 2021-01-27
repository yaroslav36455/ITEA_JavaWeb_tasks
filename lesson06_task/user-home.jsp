<%@ page import="ua.itea.form.UserHomeForm"%>
<%@ page import="ua.itea.SessionAttributeManager"%>

<%@ include file = "includes/header.jsp" %>
<% 
	SessionAttributeManager sam = new SessionAttributeManager(session);
	UserHomeForm uhf = sam.getAttribute(UserHomeForm.class);
%>
<form id="loginForm" action="/user-home" method="post">
	<div class="field"><label><b>name:</b> <%=(uhf.getName())%></label></div>
	<div class="field"><label><b>e-mail:</b> <%=(uhf.getEmail())%></label></div>
	<div class="field"><label><b>adderss:</b> <%=(uhf.getAddress().toString())%></label></div>
	<div class="field"><label><b>gender:</b> <%=(uhf.getGender().toString())%></label></div>
	<div class="field"><label><b>comment:</b> <%=(uhf.getComment())%></label></div>
	<div class="submit">
		<button type="submit">Logout</button>
	</div>
</form>
<%@ include file = "includes/footer.jsp" %>
