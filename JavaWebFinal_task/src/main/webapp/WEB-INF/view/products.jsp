<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<%@ include file="includes/header.jsp"%>

<c:forEach var="product" items="${requestScope.items.iterator()}">
	<div id="card${product.id}" class="product">
		<table>
			<tr>
				<td>${product.name}</td>
				<td>Категория: <c:choose>
						<c:when test="${product.category eq 'MEDIA'}">Медиа</c:when>
						<c:when test="${product.category eq 'OFFICE'}">Офис</c:when>
						<c:when test="${product.category eq 'CAD'}">САПР</c:when>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td><img src="${project}img/${product.picture}" height="100px" /></td>
				<td width="200px">${product.description}</td>
			</tr>
			<tr>
				<td>Цена за ед.: ${product.price}</td>
				<td>
					<div class="controll">
						<img alt="minus" src="${project}img/minus.png" width="25px" height="25px"
							onclick="minus('${product.id}')" /> <input
							id="input${product.id}" type="text" size="2" height="20px"
							name="count" value="1" /> <img alt="plus" src="${project}img/plus.png"
							width="25px" height="25px" onclick="plus('${product.id}')" /> <input
							type="hidden" name="productId" value="${product.id}" /> <input
							type="button" value="Купить" onclick="buy('${product.id}')" />
					</div>
				</td>
			</tr>
		</table>
	</div>
</c:forEach>
<%@ include file="includes/footer.jsp"%>
