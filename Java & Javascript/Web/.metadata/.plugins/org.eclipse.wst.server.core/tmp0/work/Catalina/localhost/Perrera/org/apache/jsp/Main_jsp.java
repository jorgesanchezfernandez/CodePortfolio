package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Perrera.*;
import java.util.*;

public final class Main_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<title>Reserva de Animales</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<H1>Sistema de Reserva de Animales</H1>\r\n");
      out.write("  <P> <a href=\"RegistroUsuario.jsp\">Registrarse por Primera Vez</a></P>\r\n");
      out.write("<FORM action=\"FrontController?respuesta=VistaCentros.jsp\" method=\"post\"></P>\r\n");
      out.write("  <P>Login: <INPUT TYPE=\"TEXT\" NAME=\"user\" SIZE=\"20\"></p>\r\n");
      out.write("\r\n");
      out.write("  <P>Password: <INPUT TYPE=\"PASSWORD\" NAME=\"passwd\" SIZE=\"20\"></P>\r\n");
      out.write("  <P><INPUT type=\"SUBMIT\" VALUE=\"OK\" NAME=\"boton2\">\r\n");
      out.write("  <INPUT TYPE=\"reset\" VALUE=\"Salir\" NAME=\"boton3\"></P>\r\n");
      out.write("</FORM> \r\n");
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
