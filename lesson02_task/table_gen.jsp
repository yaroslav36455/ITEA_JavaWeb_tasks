<% 
	String columns = request.getParameter("columns");
	String rows = request.getParameter("rows");

	try {
		int iRows = Integer.valueOf(rows);
		int iColumns = Integer.valueOf(columns);
		StringBuilder tableStr;
		String redBkg = "#ff0000";
		String greenBkg = "#00ff00";

		if(iRows <= 0 || iColumns <=0) {
			throw new IllegalArgumentException("Non positive integer values");
		}

		/* draw table */
 		tableStr = new StringBuilder();
		tableStr.append("<table border='1'>\n");
		for(int r = 0; r < iRows; r++) {
			tableStr.append("<tr>\n");
			for(int c = 0; c < iColumns; c++) {
				String cell = String.format("<td align='right' style='background-color: %s'>%d</td>",
											r % 2 == c % 2? redBkg : greenBkg,
											(int) (Math.random() * 100));
				tableStr.append(cell);
			}
			tableStr.append("</tr>\n");
		}
		tableStr.append("</table>\n");

		out.write(tableStr.toString());
	} catch(IllegalArgumentException e) {
%>
<form action='table_gen.jsp'>
	<table border='0'>
		<tr>
			<td>Columns:</td>
			<td><input type='text' name='columns'></td>
		</tr>
		<tr>
			<td>Rows:</td>
			<td><input type='text' name='rows'>
			</td>
		</tr>
		<tr>
			<td></td>
			<td align='right'><input type='submit' value='Generate'></td>
		</tr>
	</table>
</form>

<%}%>