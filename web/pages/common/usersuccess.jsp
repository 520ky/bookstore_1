<%--
  Created by IntelliJ IDEA.
  User: HC
  Date: 2023/3/28
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
<div style="float: left; margin-left: 170px">

    <c:if test="${sessionScope.user == null}">
        <span>
        <a href="pages/user/login.jsp">登录</a> |
        </span>
        <span>
        <a href="pages/user/regist.jsp">注册</a>
        </span>

    </c:if>

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