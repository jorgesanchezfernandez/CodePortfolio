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
	<h1>Cola de peticiones </h1>
	<table>
		<tr>
			<th>
				Nombre
			</th>
			<th>
				Dni
			</th>
			<th>
				Puntos
			</th>
		</tr>
<%
	String animalId=request.getParameter("numSerie");
	
	Animal animal=AnimalStore.getInstance().getAnimal(animalId);
	if(animal!=null){
		System.out.println(animal.getNombre());
		List cola=animal.getPeticiones();
		Iterator it=cola.iterator();
		Usuario usu=null;
		
		while(it.hasNext()){
			usu=(Usuario) it.next();
%>
		<tr>
			<th>
				<%=usu.getNombre() %>
			</th>
			<th>
				<%=usu.getDni() %>
			</th>
			<th>
				<%=usu.getFichaUsuario().getPuntos() %>
			</th>
		</tr>
<%
		}
		if(usu==null){
%>
				<tr>
						<td colspan='3'>
							Ninguna peticion realizada para este animal.
						</td>						
				</tr>
<%
		}
	}
%>

</body>
</html>