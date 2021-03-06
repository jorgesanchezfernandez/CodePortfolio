<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Peticion de mascota</title>
</head>
<body>
<%
	String numSerie = request.getParameter("numSerie");
	LineaPeticion sessionPeticiones = null;
	if(numSerie!=null && numSerie.length()>0)
	{
		SearchTree lista=AnimalStore.getInstance().getAnimales();
		Iterator it=lista.iterator();
		Animal ani=null;
		boolean encontrado=false;
		while(it.hasNext() && !encontrado){
			ani=(Animal)it.next();
			if(ani.getNumSerie().equals(numSerie))
				encontrado=true;
		}
		if(encontrado){
			sessionPeticiones=(LineaPeticion)session.getAttribute("session.peticiones");
			if(sessionPeticiones == null)
			   sessionPeticiones = new LineaPeticion();
			sessionPeticiones.addPeticion(ani);
			session.setAttribute("session.peticiones", sessionPeticiones);
		}
	}else{
	System.out.println("Animal no encontrado");
	}
%>
<div>
	<jsp:include page="LineaPeticiones.jsp?botones=1"  />
</div>
</body>
</html>