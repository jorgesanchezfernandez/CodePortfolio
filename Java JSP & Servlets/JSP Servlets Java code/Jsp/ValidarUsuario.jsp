<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Validaci�n del Usuario</title>
</head>
<body>
<%

	String Nombre = request.getParameter ("Nombre");;
	String Login = request.getParameter ("Login");
	String Password = request.getParameter ("Password");
	String Apellidos = request.getParameter ("Apellidos");
	String Dni = request.getParameter ("DNI");
	String Direccion = request.getParameter ("Direccion");
	String Telefono = request.getParameter ("Telefono");
	
   int NHijos = 0, EMenor = 0, MCasa = 0, MJardin = 0, NAnimales = 0, SAnual = 0;
   
   if (	request.getParameter ("NHijos") != "") {
	   NHijos = Integer.parseInt(request.getParameter("NHijos"));    
   }
   if ( request.getParameter ("EMenor") != "") { 
	   EMenor = Integer.parseInt(request.getParameter("EMenor"));
   }
   if ( request.getParameter ("MCasa") != "") { 
	   MCasa = Integer.parseInt(request.getParameter("MCasa"));
   }
   if ( request.getParameter ("MJardin") != "") {
	   MJardin = Integer.parseInt(request.getParameter("MJardin"));
   }
   if ( request.getParameter ("NAnimales") != "") {
	   NAnimales = Integer.parseInt(request.getParameter("NAnimales"));
   }
   if ( request.getParameter ("SAnual") != "") {
	   SAnual = Integer.parseInt(request.getParameter("SAnual"));
   }
	
	if (Nombre=="" || Login=="" || Password=="" ||
       Apellidos=="" || Dni=="" || Direccion=="" ||
	   Telefono==""){
	   %>
	   <FORM Action="RegistroUsuario.jsp" Method="post">
	   <P>Por favor,rellene todos los campos obligatorios</P>
	   <INPUT TYPE="SUBMIT" VALUE="Atr�s" NAME="boton1"></INPUT>
	   </FORM> <% }
	else
	{ 
		int error = 0;
		Usuario usu = new Usuario();
		Ficha   fich= new Ficha();
		fich.setEdadMenor(EMenor);
		fich.setM2Casa(MCasa);
		fich.setM2Jardin(MJardin);
		fich.setNAnimales(NAnimales);
		fich.setNumHijos(NHijos);
		fich.setSalarioAnual(SAnual);
		
		usu.setNombre(Nombre);
		usu.setLogin(Login);
		usu.setPassword(Password);
		usu.setApellidos(Apellidos);
		try {   usu.setDni(Dni);}
		catch ( Exception e )
		{error = 1;%>
		<P>El campo DNI no es correcto</P><%}
		usu.setDireccion(Direccion);
		try { usu.setTelefono(Telefono);}
		catch (Exception e)
		{error = 1;
		%><P>El campo telefono no es correcto</P><%}
		usu.setFichaUsuario(fich);
		
		UsuarioStore.getInstance().setUsuario(usu);
		
		if (error == 1){
	%>  <FORM Action="RegistroUsuario.jsp" Method="post">
			<P>El registro no es correcto</P>
			<INPUT TYPE="SUBMIT" VALUE="Ok" NAME="boton2"></INPUT>
			</FORM>	<%
			}		
		else{
	%>  <FORM Action="Main.jsp" Method="post">
		<P>El registro es correcto</P>
		<INPUT TYPE="SUBMIT" VALUE="Ok" NAME="boton2"></INPUT>
		</FORM>	<%
			}
		}
%>	
</body>
</html>