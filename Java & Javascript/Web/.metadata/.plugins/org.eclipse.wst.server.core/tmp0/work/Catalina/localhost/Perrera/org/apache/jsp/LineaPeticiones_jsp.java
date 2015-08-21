package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import Perrera.*;

public final class LineaPeticiones_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?>\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
      out.write("<head>\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\" />\n");
      out.write("<title>Peticiones realizadas</title>\n");
      out.write("</head>\n");
      out.write("<body>\r\n");
      out.write("<div>\r\n");
      out.write("\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "Menu.html", out, false);
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("\t<table>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td colspan=\"7\"></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td>Estado</td><td>Fecha de pedido</td><td>Nombre</td><td>Especie</td><td>Peso</td><td>Fecha de nacimiento</td><td>Eliminar</td>\r\n");
      out.write("\t\t</tr>\n");

		LineaPeticion sessionPeticiones = (LineaPeticion)session.getAttribute("session.peticiones");
		int peticionesTotales=0;
		boolean boton=false;
		if(sessionPeticiones != null){
			peticionesTotales=sessionPeticiones.getNumeroPeticiones();
			List peticiones = sessionPeticiones.getPeticiones();
        	Iterator it = peticiones.iterator();
        	while (it.hasNext()) {
				Peticion pet= (Peticion) it.next();
				Animal animal = pet.getAnimal();		
				if(pet.getEstado()==Peticion.EN_CARRITO){
					boton=true;
				}

      out.write("\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td> ");
      out.print(pet.getEstado() );
      out.write(" </td>\r\n");
      out.write("\t\t\t\t\t\t<td> ");
      out.print(pet.getFecha().toString());
      out.write(" </td>\r\n");
      out.write("\t\t\t\t\t\t<td> ");
      out.print(animal.getNombre() );
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t<td> ");
      out.print(animal.getEspecie() );
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t<td> ");
      out.print(animal.getPeso() );
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t<td> ");
      out.print(animal.getFechaNacimiento() );
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t\t<td> <a href=\"FrontController?respuesta=eliminarPeticion.jsp?numSerie=");
      out.print(animal.getNumSerie());
      out.write("\">Eliminar peticion</a></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
			
			}
        }else{
      out.write("\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td colspan='6'>\r\n");
      out.write("\t\t\t\t\tNO HAY PETICIONES REALIZADAS\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("    ");
}
   if(peticionesTotales>=LineaPeticion.MAX_PETICIONES){
    	
      out.write("\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td colspan='6'>\r\n");
      out.write("\t\t\t\t\t\t\tMaximo de peticiones alcanzadas.\r\n");
      out.write("\t\t\t\t\t\t</td>\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t</tr>\r\n");
 }
	if(boton){
      out.write("\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td colspan='3'>\r\n");
      out.write("\t\t\t\t\t\t<a href=\"FrontController?respuesta=confirmarPeticiones.jsp\">Confirmar peticiones</a>\r\n");
      out.write("\t\t\t\t\t\t</td>\t\r\n");
      out.write("\t\t\t\t</tr>\r\n");
	}
      out.write("\r\n");
      out.write("\t</table>\r\n");
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
