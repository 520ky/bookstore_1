<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>
	<%@ include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>

		<div id="header">
			<img class="logo_img" alt="" src="/static/script/logo.gif" >
			<span class="wel_word">编辑图书</span>

			<%@ include file="/pages/common/manager_mue.jsp"%>

		</div>
		
		<div id="main">
			<form action="manager/bookServlet" >
				<input type="hidden" name="action" value="${ empty param.id ?"addBook":"update"}"/>
				<input type="hidden" name="id" value="${requestScope.onebook.id}"/>
				<input type="hidden" name="pageNo" value="${param.pageNo}"/>
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>
						<td><input name="name" type="text" value="${requestScope.onebook.name}"/></td>
						<td><input name="price" type="text" value="${requestScope.onebook.price}"/></td>
						<td><input name="author" type="text" value="${requestScope.onebook.author}"/></td>
						<td><input name="sales" type="text" value="${requestScope.onebook.sales}"/></td>
						<td><input name="stock" type="text" value="${requestScope.onebook.stock}"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>

		<%@ include file="/pages/common/footer.jsp"%>

</body>
</html>