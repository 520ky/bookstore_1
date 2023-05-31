<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>网站首页</title>
    <%@ include file="/pages/common/head.jsp"%>
    <script type="text/javascript">

        $(function(){
            // $("div .img_div").mouseenter(function(){
            //
            // });
            $("button.addCart").click(function () {
                let bookId = $(this).attr("bookId");
                if (${sessionScope.user!=null}){
                    $.getJSON(
                        "cartServlet",
                        {
                            action:"addToCart",
                            id:bookId
                        }
                        ,function (date) {
                            $("#cartCount").html("您的购物车中有"+date.allcount+"件商品")
                            $("#lastname").html("您刚刚将<span style='color: red' >"+date.lastname+"</span>加入到了购物车中");
                        }
                    )
                    <%--location.href = "${pageScope.basePath}cartServlet?action=addToCart&id="+bookId;--%>
                }else{
                    if (confirm("请先登录!")){
                        location.href = "${pageScope.basePath}pages/user/login.jsp";
                    }
                    return false;
                }

            });

            $("#ImgClick").click(function (){
                $(".dropdown-content").css("display","block")
            })
            $(".dropdown-content").mouseleave(function (){
                $(".dropdown-content").css("display","none")
            })
        });


    </script>

</head>
<body>

<div id="header">

<%--    <%@ include file="/pages/common/usersuccess.jsp"%>--%>
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
        <div class="dropdown-content" style="width: 75px;height: 130px;margin-left: 130px">
            <a href="login_RegistServlet?action=logoff">注销</a>
            <a href="#">修改</a>
            <a href="#">退出</a>
        </div>

    </div>
</div>
<div id="main" style="height: 710px">
    <div id="book">
        <div style="width: 400px;height: 40px">
            <form action="clientServlet" method="get" style="margin-top: 10px">
                <input type="hidden" name="action" value="selectGoods">
                查找商品:<input type="text" maxlength="10" name="goodsName"  style="width: 250px ;height: 25px"/>
                <input type="submit" value="搜索" style="height: 25px">
            </form>
        </div>
        <div class="book_cond" >
            <form action="clientServlet" method="get" style="margin-left: -400px">
                <input type="hidden" name="action" value="clientIndex"/>
                价格范围：<input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max}"> 元
                <input type="submit" id="searchByPrice" value="查询" />
            </form>
        </div>

        <div style="text-align: center">

                <span id="cartCount"></span>
                <div id="lastname">
                </div>

        </div>

        <c:forEach items="${requestScope.page.items}" var="book">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="${book.imgPath}" />
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">商品名:</span>
                        <span class="sp2">${book.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">商品描述:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">￥${book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${book.sales}</span>
                    </div>
                   <%-- <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.stock}</span>
                    </div>--%>
                    <div class="book_add">
                        <button bookId="${book.id}" class="addCart">加入购物车</button>
                    </div>
                </div>
            </div>
        </c:forEach>



    </div>

    <div id="page_nav">
        <c:if test="${requestScope.page.pageNo>1}">
            <a href="${requestScope.page.url}&pagNo=1">
                <button>
                    首页
                </button>
                </a>
            <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo-1}">
                <button>
                    上一页
                </button>
                </a>
        </c:if>
<%--        1.小于等于五--%>
<%--        2、大于5--%>
<%--            a、前三个为1,2,3--%>
<%--            b、后三个位n-2,n-1,n--%>
<%--            c、中间可跳转--%>
        <c:choose>
            <c:when test="${requestScope.page.pageTotal <=5}">
                <c:set var="begin" value="1"></c:set>
                <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
            </c:when>
            <c:when test="${requestScope.page.pageTotal>5}">
                <c:choose>
                    <c:when test="${requestScope.page.pageNo <=3}">
                        <c:set var="begin" value="1"></c:set>
                        <c:set var="end" value="5"></c:set>
                    </c:when>
                    <c:when test="${requestScope.page.pageNo>requestScope.page.pageTotal-3}">
                        <c:set var="begin" value="${requestScope.page.pageTotal-4}"></c:set>
                        <c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
                    </c:when>
                    <c:otherwise>
                        <c:set var="begin" value="${requestScope.page.pageNo-2}"></c:set>
                        <c:set var="end" value="${requestScope.page.pageNo+2}"></c:set>
                    </c:otherwise>
                </c:choose>
            </c:when>
        </c:choose>
        <c:forEach begin="${begin}" end="${end}" var="pageno">
            <c:if test="${pageno==requestScope.page.pageNo}">
                <button style="background: #f24e00">
                    ${pageno}
                </button>

            </c:if>
            <c:if test="${pageno!=requestScope.page.pageNo}">
                <a href="${requestScope.page.url}&pageNo=${pageno}">
                        <button>
                                ${pageno}
                        </button>
                       </a>
            </c:if>

        </c:forEach>


        <c:if test="${requestScope.page.pageNo<requestScope.page.pageTotal}">
            <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo+1}">
                <button>
                    下一页
                </button>
                </a>
            <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">
                <button>
                    末页
                </button>
               </a>
        </c:if>

        共${requestScope.page.pageTotal}页，有${requestScope.page.pageTotalCount}条记录
        到第<input value="" name="pn" id="pn_input"/>页
        <input type="button" id="button1" value="确定">
        <script type="text/javascript">
            $(function(){
                $("#button1").click(function(){
                    let val = $("#pn_input").val();
                    location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo="+val;
                })
            })
        </script>
    </div>

</div>
<br/>
<br/>
<%@ include file="/pages/common/footer.jsp"%>
</body>
</html>