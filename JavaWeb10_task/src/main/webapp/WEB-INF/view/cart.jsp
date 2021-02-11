<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="includes/header.jsp"%>
<div id="content">
	<c:forEach var="product" items="${sessionScope.productsCart}">
		<div id="card${product.key.id}" class="product">
			<table>
				<tr>
					<td>${product.key.name}</td>
					<td>Категория: ${product.key.category.name}</td>
				</tr>
				<tr>
					<td><img src="img/${product.key.picture}" height="100px" /></td>
					<td width="200px">${product.key.description}</td>
				</tr>
				<tr>
					<td>Цена за ед.: ${product.key.price}</td>
					<td>
					    <div>Количество: <span id="theProdCount">${product.value}</span></div>
						<div class="controll">
							<img alt="minus" src="img/minus.png" width="25px" height="25px"
								onclick="minus('${product.key.id}')" />
							<input id="input${product.key.id}" type="text" size="2" height="20px"
								name="count" value="1" />
							<img alt="plus" src="img/plus.png"
								width="25px" height="25px" onclick="plus('${product.key.id}')" />
							<input type="hidden" name="productId" value="${product.key.id}" />                          
							<input type="button" value="Выложить" onclick="layOut('${product.key.id}')"/>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</c:forEach>
</div>
<%@ include file="includes/footer.jsp"%>
