<%--
  Created by IntelliJ IDEA.
  User: HC
  Date: 2023/3/28
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--写base标签，永远固定相对路径跳转的结果-->

<%--动态获取base地址--%>
<%
    String src = request.getScheme()
                + "://"
                + request.getServerName()
                + ":"
                + request.getServerPort()
                + request.getContextPath()    //工程路径自带 /
                + "/";
//    System.out.println(src);
    pageContext.setAttribute("basePath",src);
%>
<base href="<%=src%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
