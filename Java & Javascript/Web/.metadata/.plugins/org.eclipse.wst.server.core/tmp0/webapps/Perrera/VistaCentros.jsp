<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,Perrera.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Listado de centros</title>
</head>
<body>
<div>
	<jsp:include page="Menu.html"  />
</div>
	<h1>Listado de centros <h1>
	<table>
		<tr>
			<th>
				Nombre
			</th>
			<th>
				Codigo Postal
			</th>
			<th>
				Direccion
			</th>
			<th>
				Poblacion
			</th>
			<th>
				Provincia
			</th>
			<th>
				Telefono
			</th>
			<th>
				Descripcion
			</th>		
		</tr>
<%
	List lista=CentroStore.getInstance().getCentros();
	Iterator it=lista.iterator();
	Centro centroIt=null;
	
	while(it.hasNext()){
		centroIt=(Centro) it.next();
%>		
		<tr>
			<td>
			<a href="FrontController?respuesta=VistaAnimalesxCentro.jsp&centroId=<%=centroIt.getCentroID()%>">
<%=centroIt.getNombre()%>
			</a>
			</td>
			<td>
<%=centroIt.getCodigoPostal()%>
			</td>
			<td>
<%=centroIt.getDireccion()%>
			</td>
			<td>
<%=centroIt.getPoblacion()%>
			</td>
			<td>
<%=centroIt.getProvincia()%>
			</td>
			<td>
<%=centroIt.getTelefono()%>
			</td>
		</tr>			
<%}%>
	</table>
</body>
</html>