<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
<%@ include file="/pages/common/head.jsp"%>

<script type="text/javascript">
	$(function () {
		$("a.deleteClass").click(function(){
			let name = $(this).parent().parent().find("td:first").text();
			return  confirm("确定删除《"+name+"》吗？");

		})
	})
</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/script/logo.gif" >
			<span class="wel_word">图书管理系统</span>

		<%@ include file="/pages/common/manager_mue.jsp"%>
		<div class="addDi " style="margin-right: 10px">
			<a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加图书</a>
		</div>

	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
			<c:forEach items="${requestScope.page.items}" var="book">
				<tr >
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a class="updateClass" href="manager/bookServlet?action=getBook&id=${book.id}&pageNo=${requestScope.page.pageNo}">修改</a></td>
					<td><a class="deleteClass" href="manager/bookServlet?action=delete&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除</a></td>
				</tr>

			</c:forEach>


		</table>

	</div>


	<div id="page_nav">
<%--		//首页和上一页的处理--%>
		<c:if test="${requestScope.page.pageNo > 1}">
			<a href="manager/bookServlet?action=apage&pageNo=1">首页</a>
			<a href="manager/bookServlet?action=apage&pageNo=${requestScope.page.pageNo-1}">上一页</a>
		</c:if>

<%--页码输出的开始--%>
<%--	1、如果总页码小于等于5--%>
<%--	2、页码大于5--%>
<%--		（1）、1，2,3,4,5，前三个为1，2,3--%>
<%--		（2）、后三个为【7】，8,9,10--%>
<%--		（3）、大于3，小于7是--%>
	<c:choose>
		<c:when test="${requestScope.page.pageTotal<=5}">
			<c:set var="begin" value="1"></c:set>
			<c:set var="end" value="${requestScope.page.pageTotal}"></c:set>
		</c:when>
		<c:when test="${requestScope.page.pageTotal>5}">
			<c:choose>
				<c:when test="${requestScope.page.pageNo<=3}">
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

	<c:forEach begin="${begin}" end="${end}" var="i">
		<c:if test="${i==requestScope.page.pageNo}">
			【${i}】
		</c:if>
		<c:if test="${i!=requestScope.page.pageNo}">
			<a href="manager/bookServlet?action=apage&pageNo=${i}">${i}</a>
		</c:if>
	</c:forEach>


<%--	<c:choose>
&lt;%&ndash;		1、如果总页码小于等于5&ndash;%&gt;
		<c:when test="${requestScope.page.pageTotal<=5}">
&lt;%&ndash;			<c:set var="begin"&ndash;%&gt;
			<c:forEach begin="1" end="${requestScope.page.pageTotal}" var="i">
				<c:if test="${i==requestScope.page.pageNo}">
					【${i}】
				</c:if>
				<c:if test="${i!=requestScope.page.pageNo}">
					<a href="manager/bookServlet?action=apage&pageNo=${i}">${i}</a>
				</c:if>

			</c:forEach>
		</c:when>
&lt;%&ndash;		2、页码大于5&ndash;%&gt;
		<c:when test="${requestScope.page.pageTotal>5}">
&lt;%&ndash;			（1）、1，2,3,4,5，前三个为1，2,3&ndash;%&gt;
			<c:if test="${requestScope.page.pageNo<=3}">
				<c:forEach begin="1" end="5" var="i">
					<c:if test="${i==requestScope.page.pageNo}">
						【${i}】
					</c:if>
					<c:if test="${i!=requestScope.page.pageNo}">
						<a href="manager/bookServlet?action=apage&pageNo=${i}">${i}</a>
					</c:if>
				</c:forEach>
			</c:if>

			&lt;%&ndash;		（3）、大于3，小于7是&ndash;%&gt;&ndash;%&gt;
			<c:if test="${3<requestScope.page.pageNo && requestScope.page.pageNo<requestScope.page.pageTotal-2}">
				<c:forEach begin="${requestScope.page.pageNo-2}" end="${requestScope.page.pageNo+2}" var="i">
					<c:if test="${i==requestScope.page.pageNo}">
						【${i}】
					</c:if>
					<c:if test="${i!=requestScope.page.pageNo}">
						<a href="manager/bookServlet?action=apage&pageNo=${i}">${i}</a>
					</c:if>
				</c:forEach>
			</c:if>


&lt;%&ndash;			（2）、后三个为【7】，8,9,10&ndash;%&gt;
			<c:if test="${requestScope.page.pageNo>=requestScope.page.pageTotal-2}">
				<c:forEach begin="${requestScope.page.pageTotal-4}" end="${requestScope.page.pageTotal}" var="i">
					<c:if test="${i==requestScope.page.pageNo}">
						【${i}】
					</c:if>
					<c:if test="${i!=requestScope.page.pageNo}">
						<a href="manager/bookServlet?action=apage&pageNo=${i}">${i}</a>
					</c:if>
				</c:forEach>
			</c:if>



		</c:when>
	</c:choose>--%>


<%--		尾页和下一页的处理--%>
		<c:if test="${requestScope.page.pageNo < requestScope.page.pageTotal}">
			<a href="manager/bookServlet?action=apage&pageNo=${requestScope.page.pageNo+1}">下一页</a>
			<a href="manager/bookServlet?action=apage&pageNo=${requestScope.page.pageTotal}">末页</a>
		</c:if>


		共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录

		到第<input value="" name="pn" id="pn_input"/>页
		<input type="button" value="确定" id="but">
		<script type="text/javascript">
			$(function(){
				$("#but").click(function(){
					// 获取输入的页码
					let val = $("#pn_input").val();
					//location.href可以读写浏览器地址栏地址
					 location.href="${pageScope.basePath}manager/bookServlet?action=apage&pageNo="+val;

				})
			})
		</script>
	</div>

	<br/>
	<br/>
	<hr/>

	<%@ include file="/pages/common/footer.jsp"%>

</body>
</html>