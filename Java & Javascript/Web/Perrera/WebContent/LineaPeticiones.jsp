<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,Perrera.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Peticiones realizadas</title>
</head>
<body>
<div>
	<jsp:include page="Menu.html"  />
</div>
	<table>
		<tr>
			<td colspan="7"></td>
		</tr>
		<tr>
			<td>Estado</td><td>Fecha de pedido</td><td>Nombre</td><td>Especie</td><td>Peso</td><td>Fecha de nacimiento</td><td>Eliminar</td>
		</tr>
<%
		LineaPeticion sessionPeticiones = (LineaPeticion)session.getAttribute("session.peticiones");
		int peticionesTotales=0;
		boolean boton=false;
		if(sessionPeticiones != null){
			peticionesTotales=sessionPeticiones.getNumeroPeticiones();
			List peticiones = sessionPeticiones.getPeticiones();
        	Iterator it = peticiones.iterator();
        	while (it.hasNext()) {
				Peticion pet= (Peticion) it.next();
				Animal animal = pet.getAnimal();		
				if(pet.getEstado()==Peticion.EN_CARRITO){
					boton=true;
				}
%>
				<tr>
						<td> <%=pet.getEstado() %> </td>
						<td> <%=pet.getFecha().toString()%> </td>
						<td> <%=animal.getNombre() %></td>
						<td> <%=animal.getEspecie() %></td>
						<td> <%=animal.getPeso() %></td>
						<td> <%=animal.getFechaNacimiento() %></td>
						<td> <a href="FrontController?respuesta=eliminarPeticion.jsp?numSerie=<%=animal.getNumSerie()%>">Eliminar peticion</a></td>
				</tr>
<%			
			}
        }else{%>
			<tr>
					<td colspan='6'>
					NO HAY PETICIONES REALIZADAS
					</td>
			</tr>
    <%}
   if(peticionesTotales>=LineaPeticion.MAX_PETICIONES){
    	%>
				<tr>
						<td colspan='6'>
							Maximo de peticiones alcanzadas.
						</td>						
				</tr>
<% }
	if(boton){%>
				<tr>
						<td colspan='3'>
						<a href="FrontController?respuesta=confirmarPeticiones.jsp">Confirmar peticiones</a>
						</td>	
				</tr>
<%	}%>
	</table>
</body>
</html>