<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="Perrera.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Reserva de Animales</title>
</head>
<body>
<H1>Sistema de Reserva de Animales</H1>
  <P> <a href="RegistroUsuario.jsp">Registrarse por Primera Vez</a></P>
<FORM action="FrontController?respuesta=VistaCentros.jsp" method="post"></P>
  <P>Login: <INPUT TYPE="TEXT" NAME="user" SIZE="20"></p>

  <P>Password: <INPUT TYPE="PASSWORD" NAME="passwd" SIZE="20"></P>
  <P><INPUT type="SUBMIT" VALUE="OK" NAME="boton2">
  <INPUT TYPE="reset" VALUE="Salir" NAME="boton3"></P>
</FORM> 
</body>
</html>