<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="Perrera.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>
<div>
	<jsp:include page="Menu.html"  />
</div>
	<H1>Vacunas</H1>
<table>
	<tr>
	<th>Nombre</th><th>Descripción</th> <th>Edad</th><th>Tipo</th>
	</tr>
<%
	List vacunas = VacunaStore.getInstance().getVacunas();
	for (Iterator iter = vacunas.iterator(); iter.hasNext();) {
		Vacuna v = (Vacuna) iter.next();
%>
<tr>

<td> <%=v.getNombre()%> </td>
<td> <%=v.getDescripcion()%></td>
<td> <%=v.getEdadVacunacion()%></td>
<td> <%=v.getTipo()%></td>
</td>
</tr>
<%
} %></table>
</body>
</html>