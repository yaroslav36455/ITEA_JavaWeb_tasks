<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="includes/header.jsp"%>
<div id="content">
	<c:forEach var="product" items="${requestScope.items.iterator()}">
		<div id="card${product.getId()}" class="product">
			<table>
				<tr>
					<td>${product.getName()}</td>
					<td>Категория: <c:choose>
							<c:when test="${product.getCategory() eq 'MEDIA'}">Медиа</c:when>
							<c:when test="${product.getCategory() eq 'OFFICE'}">Офис</c:when>
							<c:when test="${product.getCategory() eq 'CAD'}">САПР</c:when>
						</c:choose></td>
				</tr>
				<tr>
					<td><img src="${project}img/${product.getPicture()}"
						height="100px" /></td>
					<td width="200px">${product.getDescription()}</td>
				</tr>
				<tr>
					<td>Цена за ед.: ${product.getPrice()}</td>
					<td>
						<div>
							Количество: <span id="theProdCount${product.getId()}">${sessionScope.cart.getCount(product.getId())}</span>
						</div>
						<div class="controll">
							<img alt="minus" src="${project}img/minus.png" width="25px" height="25px"
								onclick="minus('${product.getId()}')" /> <input
								id="input${product.getId()}" type="text" size="2"
								height="20px" name="count" value="1" /> <img alt="plus"
								src="${project}img/plus.png" width="25px" height="25px"
								onclick="plus('${product.getId()}')" /> <input
								type="hidden" name="productId"
								value="${product.getId()}" /> <input type="button"
								value="Выложить" onclick="layOut('${product.getId()}')" />
						</div>
					</td>
				</tr>
			</table>
		</div>
	</c:forEach>
</div>
<%@ include file="includes/footer.jsp"%>
