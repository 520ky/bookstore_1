<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>

	<%@ include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			$("a.deleteGoods").click(function () {
				return confirm("确定要删除该商品吗？");
			})
			$("a.clearCart").click(function () {
				return confirm("确定要清空购物车吗?");
			})

			$(".changeCount").change(function () {
				//获取修改商品的名称和id
				let name = $(this).parent().parent().find("td:first").html();
				let id = $(this).attr("bookId");
				//获取修改后的值
				let count = this.value;
				if (confirm("确定要修改【"+name+"】商品为"+count+"件吗?")){
					//发送请求给服务器
					$.getJSON(
							"cartServlet",
							{
								action:"update",
								id:id,
								count:count
							},
							function (date) {
								$("#priceAll_"+id).html(date.priceAll);
								$("span.b_count").html(date.allCount);
								$("span.b_price").html(date.cartAllPrice);

							}
					)
					<%--location.href = "${pageScope.basePath}cartServlet?action=update&id="+id+"&count="+count;--%>
				}else {
					//取消不修改，数量值返回到原样,defaultValue为表单原来默认的值
					this.value = this.defaultValue
				}
			})
		})
	</script>
</head>
<body>
	
	<div id="header">
<%--			<img class="logo_img" alt="" src="static/img/logo.gif" >--%>
<%--			<span class="wel_word">购物车</span>--%>
		<%@ include file="/pages/common/usersuccess.jsp"%>

	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>图片</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5">亲您购物车中没有任何商品！
						<a href="index.jsp">点击返回主页</a></td>
				</tr>

			</c:if>
			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items}" var="item">
					<tr>
						<td>${item.value.name}</td>
						<td>
							<img src="${item.value.imgeUrl}" style="height: 60px;width: 60px">
						</td>
						<td>
							<input type="text" maxlength="3" bookId="${item.value.id}" value="${item.value.count}" style="width: 50px;"
							class="changeCount"
							/>
						</td>
						<td>${item.value.price}</td>
						<td id="priceAll_${item.value.id}">${item.value.priceAll}</td>
						<td>
							<button >
								<a style="text-decoration-line: none" href="cartServlet?action=delete&id=${item.value.id}"
								   class="deleteGoods">删除</a>
							</button>

						</td>
					</tr>
				</c:forEach>
			</c:if>



		</table>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.allCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.allPrice}</span>元</span>
				<span class="cart_span">
					<button>
					<a style="text-decoration-line: none" class="clearCart" href="cartServlet?action=clear">清空购物车</a>

					</button>
				</span>
				<span class="cart_span">
					<button>
					<a style="text-decoration-line: none" href="orderServlet?action=createOrder">去结账</a>
					</button>
				</span>
			</div>
		</c:if>

	
	</div>

	<%@ include file="/pages/common/footer.jsp"%>

</body>
</html>