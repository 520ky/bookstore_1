<%--
  Created by IntelliJ IDEA.
  User: HC
  Date: 2023/4/7
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
    <%@ include file="/pages/common/head.jsp"%>
</head>
<body>

<div id="header">
    <%--			<img class="logo_img" alt="" src="static/img/logo.gif" >--%>
<%--    <span class="wel_word">订单详情</span>--%>
        <div>
            <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临网上商城</span>
<%--            <a href="#">我的订单</a>--%>
            <a href="pages/order/order.jsp">返回订单页</a>
        </div>

</div>

<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>商品图片</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
        </tr>
            <c:forEach items="${requestScope.order_item}" var="order_item">
                <tr>
                    <td>${order_item.name}</td>
                    <td>
                        <img src="${order_item.url}" style="height: 60px;width: 60px">

                    </td>
                    <td>
                        ${order_item.count}
                    </td>
                    <td>${order_item.price}</td>
                    <td>${order_item.totalPrice}</td>
                </tr>

            </c:forEach>
    </table>
    <div class="cart_info">
        <span class="cart_span">
            <button>
            <a class="clearCart" href="orderServlet?action=deleteOrder&orderId=${requestScope.order_item.get(1).orderId}">删除该订单</a>
            </button>
        </span>

    </div>



</div>

<%@ include file="/pages/common/footer.jsp"%>

</body>
</html>