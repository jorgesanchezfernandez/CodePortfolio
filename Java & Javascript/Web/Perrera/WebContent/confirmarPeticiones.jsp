<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,Perrera.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>
<%
IPetProcesor petProces = new PetProcesor();
String mensaje=null;
LineaPeticion peticiones = (LineaPeticion)session.getAttribute("session.peticiones");
Usuario usuario=(Usuario)session.getAttribute("session.usu");

if(peticiones != null && usuario!=null)
{			
	petProces.registraPeticion(usuario,peticiones);
	mensaje = new String ("Se realizaron las peticiones correctamente");
}
else
{
		mensaje = new String ("No se han realizado peticiones");
}


%>
<div>
	<jsp:include page="LineaPeticiones.jsp?botones=0"  />
</div>
<div>
<%=mensaje%>
</div>
</body>
</html>