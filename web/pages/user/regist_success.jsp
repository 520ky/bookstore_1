<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册页面</title>
	<%@ include file="/pages/common/head.jsp"%>

<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 250px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
		<div id="header">
<%--				<img class="logo_img" alt="" src="static/img/logo.gif" >--%>
<%--				<span class="wel_word"></span>--%>
<%--			<%@ include file="/pages/common/usersuccess.jsp"%>--%>
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

		</div>
		
		<div id="main">
		
			<h1>注册成功! <a href="index.jsp">点击！转到主页</a></h1>
	
		</div>

		<%@ include file="/pages/common/footer.jsp"%>

</body>
</html>