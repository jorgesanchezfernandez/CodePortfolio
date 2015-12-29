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
	String numSerie=request.getParameter("numSerie");
	Animal animal=AnimalStore.getInstance().getAnimal(numSerie);
	AnimalProcesor aniPros=new AnimalProcesor();
	List lista=aniPros.obetenerCalendario(animal);
	Iterator it=lista.iterator();
	Vacuna vacuna=null;
	
	while(it.hasNext()){
		vacuna=(Vacuna)it.next();
		%>
		<p> <%=vacuna.getNombre()%> <%=vacuna.getTipo()%> <%=vacuna.getEdadVacunacion()%>
		<%
	}
	

%>
</body>
</html>