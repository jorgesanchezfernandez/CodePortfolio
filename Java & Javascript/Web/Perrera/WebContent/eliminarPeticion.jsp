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
<%
	String numSerie = request.getParameter("numSerie");
	LineaPeticion sessionPeticiones = null;
	System.out.println("Inicio de la eliminacion");
	if(numSerie!=null && numSerie.length()>0)
	{
		sessionPeticiones = (LineaPeticion)session.getAttribute("session.peticiones");
		Usuario usuario = (Usuario)session.getAttribute("session.usu");
		sessionPeticiones.setUsu(usuario);
		System.out.println("Entro en numSerie");
		if(sessionPeticiones != null) 
		{
			Animal animal=AnimalStore.getInstance().getAnimal(numSerie);
			System.out.println("encuentra la sesion");
			IPetProcesor petPros=new PetProcesor();
			
			petPros.eliminarPeticion(animal,sessionPeticiones);
		}
	}
%>
<div>
	<jsp:include page="LineaPeticiones.jsp?botones=0"  />
</div>

</body>
</html>