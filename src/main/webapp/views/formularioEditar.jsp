<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Empleado a editar</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
 <h1>Empleado a editar</h1>
 <form action="empresa" method="get">
  <input type="hidden" name="opcion" value="buscarEditar">
  <table border="1">
   <tr>
    <td>DNI:</td>
    <td><input type="text" name="dni" size="50" required></td>
   </tr>
  </table>
  </br>
  <input type="submit" value="Buscar">
 </form>
 <p><a href="index.jsp">Volver</a></p>
</body>
</html>