<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored = "false" %>

              </div>
            
                <div id="sidebar">
                    <table border=1>
                    <tr>
                    <td width="252" align="left">
                    <font color=white>
                    <c:choose>
                        <c:when test="${empty sessionScope.user}">
                            Вы не авторизованы
                        </c:when>
                        <c:otherwise>
                            Вы авторизировались как ${sessionScope.user.name}
                        </c:otherwise>
                    </c:choose>
                    <br/>
                    В вашей корзине
                    <span id="prodCount">
                        <c:choose>
                            <c:when test="${empty sessionScope.productsCart}">
                                0
                            </c:when>
                            <c:otherwise>
                                ${sessionScope.productsCart}
                            </c:otherwise>
                        </c:choose>
                    </span> товаров
                    </font>
                    </td>
                    </tr>
                    </table>
                    <h2>Боковое меню</h2>
                    <ul>
                        <li><a href="products?category=MEDIA">Медиа</a></li>
                        <li><a href="products?category=OFFICE">Офис</a></li>
                        <li><a href="products?category=CAD">САПР</a></li>
                        <li><a href="sign-up">Регистрация</a></li>
                        <li><a href="sign-in">Вход</a></li>
                        <li><a href="cart">Корзина</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="footer">
    <p>Copyright (c) by Бондаренко Антон</p>
</div>
<!-- end #footer -->
</body>
</html>