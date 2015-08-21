package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Perrera.*;
import java.util.*;

public final class RegistroUsuario_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<title>Insert title here</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<H1>Registro de Usuario</H1>\r\n");
      out.write("<FORM Action=\"ValidarUsuario.jsp\" Method=\"post\"></P>\r\n");
      out.write("  <P>*Nombre: <INPUT TYPE=\"TEXT\" NAME=\"Nombre\" SIZE=\"20\"></p>\r\n");
      out.write("  <P>*Login: <INPUT TYPE=\"TEXT\" NAME=\"Login\" SIZE=\"20\"></p>\r\n");
      out.write("  <P>*Password: <INPUT TYPE=\"PASSWORD\" NAME=\"Password\" SIZE=\"20\"></p>\r\n");
      out.write("  <P>*Apellidos: <INPUT TYPE=\"TEXT\" NAME=\"Apellidos\" SIZE=\"20\"></p>\r\n");
      out.write("  <P>*DNI: <INPUT TYPE=\"TEXT\" NAME=\"DNI\" SIZE=\"20\"></p>\r\n");
      out.write("  <P>*Dirección: <INPUT TYPE=\"TEXT\" NAME=\"Direccion\" SIZE=\"20\"></p>\r\n");
      out.write("  <P>*Telefono: <INPUT TYPE=\"TEXT\" NAME=\"Telefono\" SIZE=\"20\"></P>\r\n");
      out.write("  <P>Numero de Hijos: <INPUT TYPE=\"TEXT\" NAME=\"NHijos\" SIZE=\"20\"></P>\r\n");
      out.write("  <P>Edad del Menor de los Hijos: <INPUT TYPE=\"TEXT\" NAME=\"EMenor\" SIZE=\"20\"></P>\r\n");
      out.write("  <P>Metros cuadrados de Casa: <INPUT TYPE=\"TEXT\" NAME=\"MCasa\" SIZE=\"20\"></P>\r\n");
      out.write("  <P>Metros cuadrados de Jardín: <INPUT TYPE=\"TEXT\" NAME=\"MJardin\" SIZE=\"20\"></P>\r\n");
      out.write("  <P>Número de Animales: <INPUT TYPE=\"TEXT\" NAME=\"NAnimales\" SIZE=\"20\"></P>\r\n");
      out.write("  <P>Salário Anual: <INPUT TYPE=\"TEXT\" NAME=\"SAnual\" SIZE=\"20\"></P>\r\n");
      out.write("  <P><INPUT TYPE=\"SUBMIT\" VALUE=\"OK\" NAME=\"boton2\">\r\n");
      out.write("  <INPUT TYPE=BUTTON OnClick=\"Main.jsp\" VALUE=\"Salir\" NAME=\"boton3\"></P>\r\n");
      out.write(" </FORM>\n");
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
