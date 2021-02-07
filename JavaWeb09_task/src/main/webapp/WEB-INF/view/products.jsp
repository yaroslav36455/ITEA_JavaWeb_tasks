<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<%@ include file="includes/header.jsp"%>
<div id="content">
    <c:forEach var="product" items="${sessionScope.productsRack}">
        <table>
            <tr>
                <td>${product.name}</td>
                <td>${product.category.name}</td>
            </tr>
            <tr>
                <td><img src="img/${product.picture}"
                    height="100px" /></td>
                <td width="200px">${product.description}</td>
            </tr>
            <tr>
                <td>${product.price}</td>
                <td>
                    <form action="cart" method="post">
                        <button type="submit" name="productId" value="${product.id}">Купить</button>
                    </form>
                </td>
            </tr>
        </table>
    </c:forEach>
</div>
<%@ include file="includes/footer.jsp"%>
