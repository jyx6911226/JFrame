<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> 
<!DOCTYPE html>
<html>
<head>
<title>validTest</title>
</head>
<body>
	<h3>后台数据校验测试</h3>
	<form action="${pageContext.request.contextPath}/Valid/test" method="post">
		id:<input type="text" name="id"><br>
		name:<input type="text" name="name">
		email:<input type="text" name="email">
		<input type="submit" value="提交">
	</form> 
</body>
</html>