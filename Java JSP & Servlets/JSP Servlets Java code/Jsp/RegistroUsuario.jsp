<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Registro de usuario</title>
</head>
<body>
<H1>Registro de Usuario</H1>
<FORM Action="ValidarUsuario.jsp" Method="post"></P>
  <P>*Nombre: <INPUT TYPE="TEXT" NAME="Nombre" SIZE="20"></p>
  <P>*Login: <INPUT TYPE="TEXT" NAME="Login" SIZE="20"></p>
  <P>*Password: <INPUT TYPE="PASSWORD" NAME="Password" SIZE="20"></p>
  <P>*Apellidos: <INPUT TYPE="TEXT" NAME="Apellidos" SIZE="20"></p>
  <P>*DNI: <INPUT TYPE="TEXT" NAME="DNI" SIZE="20"></p>
  <P>*Dirección: <INPUT TYPE="TEXT" NAME="Direccion" SIZE="20"></p>
  <P>*Telefono: <INPUT TYPE="TEXT" NAME="Telefono" SIZE="20"></P>
  <P>Numero de Hijos: <INPUT TYPE="TEXT" NAME="NHijos" SIZE="20"></P>
  <P>Edad del Menor de los Hijos: <INPUT TYPE="TEXT" NAME="EMenor" SIZE="20"></P>
  <P>Metros cuadrados de Casa: <INPUT TYPE="TEXT" NAME="MCasa" SIZE="20"></P>
  <P>Metros cuadrados de Jardín: <INPUT TYPE="TEXT" NAME="MJardin" SIZE="20"></P>
  <P>Número de Animales: <INPUT TYPE="TEXT" NAME="NAnimales" SIZE="20"></P>
  <P>Salário Anual: <INPUT TYPE="TEXT" NAME="SAnual" SIZE="20"></P>
  <P><INPUT TYPE="SUBMIT" VALUE="OK" NAME="boton2">
  <INPUT TYPE=BUTTON OnClick="Main.jsp" VALUE="Salir" NAME="boton3"></P>
 </FORM>
</body>
</html>