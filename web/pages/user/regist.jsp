<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>会员注册页面</title>
		<!--写base标签，永远固定相对路径跳转的结果-->
		<%@ include file="/pages/common/head.jsp"%>

		<script type="text/javascript">
			// 页面加载完成之后
			$(function () {
				//绑定头像
				$("#headImg").click(function(){
					$("#imgFile").click();
					$("#imgFile").change(function (){
						var form = new FormData(document.getElementById("forms"));
						// console.log(form);
						$.ajax({
							url:"uploadHeaderServlet",
							type:'POST',
							data:form,
							processData:false,
							contentType:false,
							success:function (data) {

								// console.log(data);
								if (data.length>14)
								{
									$("#imgUrl").attr('value',data);
								}else {
									$("span.errorMsg").text(data);
								}

							}
						})
						// 获取上传文件对象
						var file = $(this)[0].files[0];
						console.log(file)

						// 读取文件URL
						var reader = new FileReader();
						reader.readAsDataURL(file);
						// 阅读文件完成后触发的事件
						reader.onload = function () {
							// 读取的URL结果：this.result
							$("#headImg").attr("src", this.result);//#headImg img标签的ID
						}
						// $("#headerSub").click();

					})

				})
				// 给注册绑定单击事件
				$("#sub_btn").click(function () {
					// 验证用户名：必须由字母，数字下划线组成，并且长度为5到12位
					//1 获取用户名输入框里的内容
					var usernameText = $("#username").val();
					//2 创建正则表达式对象
					var usernamePatt = /^\w{5,12}$/;
					 var reg=/[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;
					 if (reg.test(usernameText)){
						 $("span.errorMsg").text("含有非法字符！");
						 return false;
					 }else {
						 //3 使用test方法验证
						 if (!usernamePatt.test(usernameText)) {
							 //4 提示用户结果
							 $("span.errorMsg").text("用户名不合法！");

							 return false;
						 }
					 }


					// 验证密码：必须由字母，数字下划线组成，并且长度为5到12位
					//1 获取用户名输入框里的内容
					var passwordText = $("#password").val();
					//2 创建正则表达式对象
					var passwordPatt = /^\w{5,12}$/;
					//3 使用test方法验证

					if (!passwordPatt.test(passwordText)) {
						//4 提示用户结果
						$("span.errorMsg").text("密码不合法！");

						return false;
					}

					// 验证确认密码：和密码相同
					//1 获取确认密码内容
					var repwdText = $("#repwd").val();
					//2 和密码相比较
					if (repwdText != passwordText) {
						//3 提示用户
						$("span.errorMsg").text("确认密码和密码不一致！");

						return false;
					}

					// 邮箱验证：xxxxx@xxx.com
					//1 获取邮箱里的内容
					var emailText = $("#email").val();
					//2 创建正则表达式对象
					var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
					//3 使用test方法验证是否合法
					if (!emailPatt.test(emailText)) {
						//4 提示用户
						$("span.errorMsg").text("邮箱格式不合法！");

						return false;
					}

					// 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
					var codeText = $("#code").val();

					//去掉验证码前后空格
					// alert("去空格前：["+codeText+"]")
					codeText = $.trim(codeText);
					// alert("去空格后：["+codeText+"]")
					if (codeText == null || codeText == "") {
						//4 提示用户
						$("span.errorMsg").text("验证码不能为空！");

						return false;
					}
					// 去掉错误信息
					$("span.errorMsg").text("");
				});

				$("#imgChange").click(function(){
					this.src= "${pageScope.basePath}kaptcha.jpg?d="+new Date()
				});

				$("#username").blur(function(){
					 let  name = $("#username").val();
					 $.getJSON(
					 		"login_RegistServlet",
							 {
							 	action:"testUsername",
								 username:name
							 },
							 function (date) {
								if (date.result==1){
									//用户名可注册
									$("span.errorMsg").text("");
								}else if (date.result==0){
									$("span.errorMsg").text("用户名已存在！");
								}
							 }
					 )
				});



			});

		</script>


	<style type="text/css">
		.login_form{
			height:470px;
			margin-top: 0px;
		}

	</style>
	</head>
	<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>

			<div class="login_banner">

				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>

				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册网站用户</h1>
								<span class="errorMsg">
<%--									用EL表达式表示--%>
									${requestScope.msg}
<%--									<%=request.getAttribute("msg")==null?"":request.getAttribute("msg")%>--%>
								</span>
							</div>
							<div class="form">
								<form action="login_RegistServlet" method="post">
									<input type="hidden" name="action" value="regist"/>
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   autocomplete="off" tabindex="1" name="username" id="username"
										   value="${requestScope.username}"
<%--											value="<%=request.getAttribute("user")==null?"":request.getAttribute("user")%>"--%>
									/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" id="password"
										   value="${requestScope.password}"
<%--											value="<%=request.getAttribute("password")==null?"":request.getAttribute("password")%>"--%>
									/>
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd"
										   value="${requestScope.password}"
<%--										   value="<%=request.getAttribute("password")==null?"":request.getAttribute("password")%>"--%>
									/>
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   value="${requestScope.email}"
<%--										   value="<%=request.getAttribute("email")==null?"":request.getAttribute("email")%>"--%>

										   autocomplete="off" tabindex="1" name="email" id="email" />
									<br />

									<div style="width: 360px;height: 80px;">
										<label style="padding-top: 32px;float: left ">头像：</label>
										<div style="width: 80px;height: 80px;float:left; margin-left: 34px;margin-top: 10px">
											<img id="headImg"  style="width: 100%;height: 100%" src="https://tails-image.oss-cn-hangzhou.aliyuncs.com/images/header.webp" alt="选择头像">
										</div>
										<input id="imgUrl" type="hidden" name="url" value="https://tails-image.oss-cn-hangzhou.aliyuncs.com/images/header.webp"/>
									</div>
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" name="code" style="width: 120px;" id="code" />
									<img alt="" id="imgChange" src="kaptcha.jpg" style="float: right; margin-right: 40px; width: 110px;height: 40px">
									<br />
									<br/>
									<input type="submit" value="注册" id="sub_btn" />
								</form >
								<form id="forms" action="uploadHeaderServlet" enctype="multipart/form-data" method="post">
									<input type="file" name="files" id="imgFile" style="display: none"
									/>
								</form>

							</div>

						</div>
					</div>
				</div>
			</div>
		<%@ include file="/pages/common/footer.jsp"%>

	</body>
</html>