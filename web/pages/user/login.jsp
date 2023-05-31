<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录页面</title>
	<!--写base标签，永远固定相对路径跳转的结果-->
	<%@ include file="/pages/common/head.jsp"%>

	<script type="text/javascript">
		$(function () {
			$("#imgChange").click(function(){
				this.src= "${pageScope.basePath}kaptcha.jpg?d="+new Date()
			});
		});

	</script>
</head>
<body>
		<div id="login_header">
<%--			<img class="logo_img" alt="" src="static/img/logo.gif" >--%>
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎登录</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1 >网上商城登录</h1>
								<a href="pages/user/regist.jsp">立即注册</a>
							</div>
							<div class="msg_cont">
								<b></b>
								<span class="errorMsg">
									${ (empty requestScope.msg)?"请输入用户名和密码":requestScope.msg}
<%--									<%=request.getAttribute("msg")==null?"请输入用户名和密码":request.getAttribute("msg")%>--%>
								</span>
							</div>
							<div class="form">
								<form action="login_RegistServlet" method="post">
									<input type="hidden" name="action" value="login"/>
									<label style="color: black">用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   autocomplete="off" tabindex="1" name="username"
										   value="${requestScope.username}"
<%--											value="<%=request.getAttribute("name")!=null?request.getAttribute("name"):""%>"--%>
									/>
									<br />
									<br />
									<label style="color: black">用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<label style="color: black">验证码：</label>
									<input class="itxt" type="text" name="code" style="width: 120px;" id="code" />
									<img alt="" id="imgChange" src="kaptcha.jpg" style="float: right; margin-right: 40px; width: 110px;height: 40px">
									<br/>
									<br/>
										<label style="color: black;margin-left: 80px" for="user">普通用户:</label>
									    <input  type="radio" checked="checked" value="user" name="user" id="user">
									&nbsp;
										<label style="color: black">管理员:<input type="radio" value="admin" name="user"></label>
									<br/>

									<input type="submit" value="登录" id="sub_btn" />

								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%@ include file="/pages/common/footer.jsp"%>

</body>
</html>