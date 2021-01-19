<%! private int counter = 0;%>
<a href="1.jsp">Reload</a>

<% 
	out.write("" + ++counter);
%>