<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%@ include file="/pages/common/head.jsp"%>
</head>
<body>
	
	<div id="header">
<%--			<img class="logo_img" alt="" src="../../static/script/logo.gif" >--%>

		<%@ include file="/pages/common/manager_mue.jsp"%>

	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>用户名</td>
				<td>下单时间</td>
				<td>金额</td>
				<td>详情</td>
				<td>发货</td>
				
			</tr>
			<c:forEach items="${requestScope.order_manager}" var="detail">
				<tr>
					<td>${detail.username}</td>
					<td>${detail.createTime}</td>
					<td>${detail.price}</td>
					<td><a href="orderServlet?action=queryOrderItemByOrderId&orderId=${detail.orderId}">查看详情</a></td>
					<c:if test="${detail.status ==0}">
						<td><a href="#">点击发货</a></td>
					</c:if>
					<c:if test="${detail.status == 1}">
						<td>已发货，等待签收</td>
					</c:if>
					<c:if test="${detail.status == 3}">
						<td>已签收</td>
					</c:if>
				</tr>
			</c:forEach>


		</table>
	</div>

	<%@ include file="/pages/common/footer.jsp"%>

</body>
</html>