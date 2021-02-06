<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="includes/header.jsp"%>
<div id="content">
	<c:forEach var="product" items="${sessionScope.productsCart}">
		<table>
			<tr>
				<td>${product.name}</td>
				<td>${product.category.name}</td>
			</tr>
			<tr>
				<td><img src="img/${product.picture}" height="100px" /></td>
				<td width="200px">${product.description}</td>
			</tr>
			<tr>
				<td>${product.price}</td>
				<td></td>
			</tr>
		</table>
	</c:forEach>
</div>
<%@ include file="includes/footer.jsp"%>
