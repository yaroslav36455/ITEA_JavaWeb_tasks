<%@ page import="java.util.Date" %>
<%@ page import="ua.itea.StringGenerator" %>
<%! private int counter = 0;%>
<%! private long blockedTime = 0;%>
<%! private long blockedDiffTime = 10*1000;%>
<%! private boolean isShowForm = true;%>
<%! private boolean capchaBlock = false;%>
<%! private StringGenerator strGen = new StringGenerator();%>
<%@ include file = "includes/header.jsp" %>
<%
	String login = request.getParameter("login");
	String password = request.getParameter("password");
	String capcha = request.getParameter("capcha");

	if (login != null && password != null) {
	String color = "#ff0000";
	String access = "denied";
	if(login.equals("admin") && password.equals("123")) {
		color = "#00ff00";
		access = "granted";
		isShowForm = false;
	} else {
		counter++;
	}

	if(counter >= 3) {
		isShowForm = false;
		capchaBlock = true;
		strGen.generate();
		out.write("<br/>Access blocked");
		blockedTime = new Date().getTime();
	}

	out.write(String.format("<font color='%s'><br/>Access %s </font>", color, access));
	}

	long current = new Date().getTime();

	if (capchaBlock && strGen.isValid(capcha)) {
		capchaBlock = false;
		isShowForm = true;
	}

	if(counter >= 3 && !capchaBlock) {
		out.write("<br/> T:" + ((current - blockedTime) / 1000.0));
		if(current - (blockedTime + blockedDiffTime) >= 0) {
			counter = 0;
			blockedTime = 0;
			isShowForm = true;
		}
	}

	out.write("<br/>" + blockedTime);
	out.write("<br/>" + current);
	out.write("<br/>Count:" + counter);

	if (isShowForm) {
%>

<form id="loginForm" action="" method="post">

	<div class="field">
		<label>Enter your login:</label>
		<div class="input"><input type="text" name="login" value="" id="login" /></div>
	</div>

	<div class="field">
		<a href="#" id="forgot">Forgot your password?</a>
		<label>Enter your password:</label>
		<div class="input"><input type="password" name="password" value="" id="pass" /></div>
	</div>

	<div class="submit">
		<button type="submit">Enter</button>
		<label id="remember"><input name="" type="checkbox" value="" /> Remember me</label>
	</div>

</form>

<%	} else if(capchaBlock) {
%>

<form id="capchaForm" action="" method="post">
	<div class="field">
		<label>Enter text: <%= (strGen.getString()) %></label>
		<div class="input"><input type="text" name="capcha"/></div>
	</div>
	<div class="submit">
		<button type="submit">Enter</button>
	</div>
</form>

<%}%>
<%@ include file = "includes/footer.jsp" %>