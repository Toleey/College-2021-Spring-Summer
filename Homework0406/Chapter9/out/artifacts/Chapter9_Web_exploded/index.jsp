
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>userCode</title>
</head>
<body>

ModelAndView
<form action="http://localhost:8080/Chapter9_Web_exploded/User/userCode1" method="get">
    <div>请输入usreCode:</div>
    <input type="text" name="userCode">
    <input type="submit">
</form>

<hr />
Model
<form action="http://localhost:8080/Chapter9_Web_exploded/User/userCode2" method="get">
    <div>请输入usreCode:</div>
    <input type="text" name="userCode">
    <input type="submit">
</form>

<hr />
Map
<form action="http://localhost:8080/Chapter9_Web_exploded/User/userCode3" method="get">
    <div>请输入usreCode:</div>
    <input type="text" name="userCode">
    <input type="submit">
</form>

</body>
</html>
