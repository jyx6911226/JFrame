<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<title>jpa级联测试</title>
</head>
<body>
  <table>
  	<tr>
  		<td>o_id:</td>
  		<td>${oneObj1.id }</td>
  		<td>o_name:</td>
  		<td>${oneObj1.name }</td>
  	</tr>
  	<tr>
  		<td>o2_id:</td>
  		<td>${oneObj1.oneObj2.id }</td>
  		<td>o2_name:</td>
  		<td>${oneObj1.oneObj2.name }</td>
  	</tr>
  	<c:forEach var="manyObj1" items="${oneObj1.manyObj1s }">
  	<tr>
  		<td>m_id:</td>
  		<td>${manyObj1.id }</td>
  		<td>m_name:</td>
  		<td>${manyObj1.name }</td>
  	</tr>	
  	</c:forEach>
  </table>
</body>
</html>