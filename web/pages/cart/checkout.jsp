<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>结算页面</title>

	<%@ include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function (){
			$("#ImgClick").click(function (){
				$(".dropdown-content").css("display","block")
			})
			$(".dropdown-content").mouseleave(function (){
				$(".dropdown-content").css("display","none")
			})
		})
	</script>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
<%--			<img class="logo_img" alt="" src="static/script/logo.gif" >--%>
<%--			<span class="wel_word">结算</span>--%>
<%--			<div>--%>
<%--				<span>欢迎<span class="um_span">${sessionScope.username}</span>光临网上商城</span>--%>
<%--				<a href="orderServlet?action=queryOrder">我的订单</a>--%>
<%--				<a href="index.jsp">注销</a>&nbsp;&nbsp;--%>
<%--				<a href="index.jsp">返回</a>--%>
<%--			</div>--%>

<%--	<%@ include file="/pages/common/usersuccess.jsp"%>--%>
	<div style="float: left; margin-left: 170px">

		<c:if test="${sessionScope.user!= null}">
             <span>欢迎！用户<span class="um_span">${sessionScope.user.username}
			 </span>光临商城</span>
		</c:if>

	</div>

	<div>
    <span style="display: flex;
                align-items: flex-end;">

        <c:if test="${sessionScope.user != null}">
			<a href="pages/cart/cart.jsp">购物车</a>&nbsp;
			<a href="orderServlet?action=queryOrder">我的订单</a>&nbsp;
			<a href="index.jsp">返回</a>&nbsp;

			<c:if test="${sessionScope.user.userAdmin == admin}">
				<a href="pages/manager/manager.jsp">后台管理</a>&nbsp;&nbsp;
			</c:if>

			<img id="ImgClick" class="dropdown"
				 src="${sessionScope.user.url}"
				 style="width: 30px;height: 30px;float: right;">
		</c:if>

    </span>
		<div class="dropdown-content" style="width: 75px;height: 130px;margin-left: 200px">
			<a href="login_RegistServlet?action=logoff">注销</a>
			<a href="#">修改</a>
			<a href="#">退出</a>
		</div>
	</div>
	
	<div id="main" style="height: 600px">
		
		<h1>你的订单已结算，订单号为${sessionScope.orderId}</h1>
		
	
	</div>

	<%@ include file="/pages/common/footer.jsp"%>

</body>
</html>