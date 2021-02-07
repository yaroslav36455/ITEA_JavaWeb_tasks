<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<%@ include file="includes/header.jsp"%>
<div id="content">
	<c:forEach var="product" items="${sessionScope.productsRack}">
		<div class="product">
			<table>
				<tr>
					<td>${product.name}</td>
					<td>Категория: ${product.category.name}</td>
				</tr>
				<tr>
					<td><img src="img/${product.picture}" height="100px" /></td>
					<td width="200px">${product.description}</td>
				</tr>
				<tr>
					<td>Цена за ед.: ${product.price}</td>
					<td>
						<form action="products" method="post" class="controll">
							<img alt="minus" src="img/minus.png" width="25px" height="25px"
								onclick="minus('${product.id}')" />
							<input id="${product.id}" type="text" size="2" height="20px" name="count" value="1" />
							<img alt="plus" src="img/plus.png" width="25px" height="25px"
								onclick="plus('${product.id}')" />
							<input type="hidden" name="productId" value="${product.id}" />
							<input type="hidden" name="operation" value="buy" />
							<input type="submit" value="Купить" onclick="buy('${product.id}')" />
						</form>
					</td>
				</tr>
			</table>
		</div>
	</c:forEach>
</div>
<%@ include file="includes/footer.jsp"%>
