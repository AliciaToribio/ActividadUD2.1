<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar Empleados</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
 <h1>Listar Empleados</h1>
 <table border="1">
  <tr>
        <td>DNI</td>
        <td>Nombre</td>
        <td>Sexo</td>
        <td>Categoria</td>
        <td>Anios Trabajados</td>
  </tr>
  <c:forEach var="e" items="${empleados}">
  <tr>
        <td><c:out value="${e.dni}"/></td>
        <td><c:out value="${e.nombre}"/></td>
        <td><c:out value="${e.sexo}"/></td>
        <td><c:out value="${e.categoria}"/></td>
        <td><c:out value="${e.anyos}"/></td>
  </tr>
  </c:forEach>
 </table>
 <p><a href="index.jsp">Volver</a></p>
</body>
</html>