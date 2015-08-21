package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Perrera.*;
import java.*;

public final class ValidarUsuario_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.List _jspx_dependants;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      _jspxFactory = JspFactory.getDefaultFactory();
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>\r\n");
      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\" />\n");
      out.write("<title>Validación del Usuario</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");


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
	   
      out.write("\r\n");
      out.write("\t   <FORM Action=\"RegistroUsuario.jsp\" Method=\"post\">\r\n");
      out.write("\t   <P>Por favor,rellene todos los campos obligatorios</P>\r\n");
      out.write("\t   <INPUT TYPE=\"SUBMIT\" VALUE=\"Atrás\" NAME=\"boton1\"></INPUT>\r\n");
      out.write("\t   </FORM> ");
 }
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
		{error = 1;
      out.write("\r\n");
      out.write("\t\t<P>El campo DNI no es correcto</P>");
}
		usu.setDireccion(Direccion);
		try { usu.setTelefono(Telefono);}
		catch (Exception e)
		{error = 1;
		
      out.write("<P>El campo telefono no es correcto</P>");
}
		usu.setFichaUsuario(fich);
		
		UsuarioStore.getInstance().setUsuario(usu);
		
		if (error == 1){
	
      out.write("  <FORM Action=\"RegistroUsuario.jsp\" Method=\"post\">\r\n");
      out.write("\t\t\t<P>El registro no es correcto</P>\r\n");
      out.write("\t\t\t<INPUT TYPE=\"SUBMIT\" VALUE=\"Ok\" NAME=\"boton2\"></INPUT>\r\n");
      out.write("\t\t\t</FORM>\t");

			}		
		else{
	
      out.write("  <FORM Action=\"Main.jsp\" Method=\"post\">\r\n");
      out.write("\t\t<P>El registro es correcto</P>\r\n");
      out.write("\t\t<INPUT TYPE=\"SUBMIT\" VALUE=\"Ok\" NAME=\"boton2\"></INPUT>\r\n");
      out.write("\t\t</FORM>\t");

			}
		}

      out.write("\t\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
