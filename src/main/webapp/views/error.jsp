<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
 <h1>Se ha producido un error</h1>
 <p><c:out value="${error}"/></p>
 <p>
  <c:choose>
   <c:when test="${not empty volver}">
    <a href="${pageContext.request.contextPath}/${volver}">Volver</a>
   </c:when>
   <c:otherwise>
    <a href="${pageContext.request.contextPath}/index.jsp">Volver al inicio</a>
   </c:otherwise>
  </c:choose>
 </p>
</body>
</html>