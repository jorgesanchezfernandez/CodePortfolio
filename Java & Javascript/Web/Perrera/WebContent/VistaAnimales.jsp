<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,Perrera.*" %>
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
	<h1>Listado de Animales </h1>
	<table>
		<tr>
			<th>
				Nombre
			</th>
			<th>
				Especie
			</th>
			<th>
				Fecha Nacimiento
			</th>
			<th>
				Peso
			</th>
			<th>
				Peticion
			</th>
			<th>
				Cola de espera
			</th>
		</tr>
<%
	List lista=AnimalStore.getInstance().getAnimales();
	Iterator it=lista.iterator();
	Animal animalIt=null;
	
	while(it.hasNext()){
		animalIt=(Animal) it.next();
%>
		<tr>
			<td>
				<%=animalIt.getNombre() %>
			</td>
			<td>
				<%=animalIt.getEspecie() %>
			</td>
			<td>
				<%=animalIt.getFechaNacimiento()%>
			</td>
			<td>
				<%=animalIt.getPeso()%>
			</td>
			<td>
				<a href="FrontController?respuesta=realizarPeticion.jsp?numSerie=<%=animalIt.getNumSerie()%>">Peticion</a>
			</td>
			<td>
				<a href="FrontController?respuesta=VisualizarCola.jsp?numSerie=<%=animalIt.getNumSerie()%>"><%=animalIt.getPeticiones().size()%></a>
			</td>
		</tr>
<%} %>
	</table>
</body>
</html>