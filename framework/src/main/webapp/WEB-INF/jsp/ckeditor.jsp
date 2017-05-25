<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>ckeditor</title>
</head>
<body>
<h3>ckeditor测试</h3>
<textarea id="TextArea1" cols="20" rows="2" class="ckeditor"></textarea>
</body>
<!-- ckeditor-->
<script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/config.js"></script>
</html>