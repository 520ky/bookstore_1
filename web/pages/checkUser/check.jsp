<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户信息修改</title>
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
                    $("span.errorMsg").text("两次输入的密码不一致！");
                    return false;
                }
                // 去掉错误信息
                $("span.errorMsg").text("");
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
<%--<div id="login_header">--%>
<%--    <img class="logo_img" alt="" src="static/img/logo.gif" >--%>
<%--</div>--%>

<div class="login_banner">
    <div id="content">
        <div class="login_form" style="margin-right: 400px">
            <div class="login_box">
                <div class="tit">
                    <h1>修改用户信息</h1>
                    <span class="errorMsg">
<%--									用EL表达式表示--%>
									${requestScope.msg}
								</span>
                </div>
                <div class="form">
                    <form action="#" method="post">
                        <input type="hidden" name="action" value="regist"/>
                        <br />
                        <label>用户新密码：</label>
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
                        <button style="width: 135px;height: 33px;margin-right: 80px;margin-top: 30px;background: aqua">
                            <a href="#" style="font-size: 20px;text-decoration-line: none">确定修改</a>
                        </button>
                        <button style="width: 135px;height: 33px;background: aqua">
                            <a href="#" style="font-size: 20px;text-decoration-line: none">取消修改</a>
                        </button>
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