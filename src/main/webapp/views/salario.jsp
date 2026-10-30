<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mostrar salario empleado</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
 <h1>Salario empleado</h1>
 <table border="1">
  <tr>
        <th>DNI</th>
        <th>Nombre</th>
        <th>Sexo</th>
        <th>Categoría</th>
        <th>Anios Trabajados</th>
        <th>Sueldo</th>
       </tr>
       <tr>
        <td><c:out value="${empleado.dni}"/></td>
        <td><c:out value="${empleado.nombre}"/></td>
        <td><c:out value="${empleado.sexo}"/></td>
        <td><c:out value="${empleado.categoria}"/></td>
        <td><c:out value="${empleado.anyos}"/></td>
        <td><c:out value="${sueldo}"/></td>
       </tr>
 </table>
 <p><a href="empresa?opcion=salario">Buscar otro</a></p>
 <p><a href="index.jsp">Volver al inicio</a></p>
</body>
</html>