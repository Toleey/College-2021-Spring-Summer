<%--
  Created by IntelliJ IDEA.
  User: toby
  Date: 2021/6/8
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>hello</h1>

人数 ${count}
<hr/>

<c:forEach items="${userList}" var="user">
    <c:out value="${user}" />
    <br />
    <br />
</c:forEach>


</body>
</html>
