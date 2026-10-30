<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editar Empleado</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
 <h1>Editar Empleado</h1>
 <form action="empresa" method="post">
 <input type="hidden" name="opcion" value="editar">
  <table border="1">
   <tr>
    <td>Dni:</td>
    <td><input type="text" name="dni" size="50" value="${empleado.dni}" readonly></td>
   </tr>
   <tr>
    <td>Nombre:</td>
    <td><input type="text" name="nombre" size="50" value="${empleado.nombre}"></td>
   </tr>
   <tr>
    <td>Sexo:</td>
    <td><input type="text" name="sexo" size="50" value="${empleado.sexo}"></td>
   </tr>
   <tr>
    <td>Categoria:</td>
    <td><input type="text" name="categoria" size="50" value="${empleado.categoria}"></td>
   </tr>
   <tr>
    <td>Anios Trabajados:</td>
    <td><input type="text" name="anyos" size="50" value="${empleado.anyos}"></td>
   </tr>
  </table>
   </br>
  <input type="submit" value="Guardar">
 </form>
 <p><a href="empresa?opcion=editar">Editar otro</a></p>
  <p><a href="index.jsp">Volver al inicio</a></p>
</body>
</html>