<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="includes/header.jsp"%>
<div id="content">
	<c:forEach var="product" items="${sessionScope.cart.iterator()}">
		<div id="card${product.getKey().getId()}" class="product">
			<table>
				<tr>
					<td>${product.getKey().getName()}</td>
					<td>Категория: <c:choose>
							<c:when test="${product.getKey().getCategory() eq 'MEDIA'}">Медиа</c:when>
							<c:when test="${product.getKey().getCategory() eq 'OFFICE'}">Офис</c:when>
							<c:when test="${product.getKey().getCategory() eq 'CAD'}">САПР</c:when>
						</c:choose></td>
				</tr>
				<tr>
					<td><img src="img/${product.getKey().getPicture()}"
						height="100px" /></td>
					<td width="200px">${product.getKey().getDescription()}</td>
				</tr>
				<tr>
					<td>Цена за ед.: ${product.getKey().getPrice()}</td>
					<td>
						<div>
							Количество: <span id="theProdCount${product.getKey().getId()}">${product.getValue()}</span>
						</div>
						<div class="controll">
							<img alt="minus" src="img/minus.png" width="25px" height="25px"
								onclick="minus('${product.getKey().getId()}')" /> <input
								id="input${product.getKey().getId()}" type="text" size="2"
								height="20px" name="count" value="1" /> <img alt="plus"
								src="img/plus.png" width="25px" height="25px"
								onclick="plus('${product.getKey().getId()}')" /> <input
								type="hidden" name="productId"
								value="${product.getKey().getId()}" /> <input type="button"
								value="Выложить" onclick="layOut('${product.getKey().getId()}')" />
						</div>
					</td>
				</tr>
			</table>
		</div>
	</c:forEach>
</div>
<%@ include file="includes/footer.jsp"%>
