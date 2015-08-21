package Servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Centro.*;
import Perrera.CentroStore;
import Perrera.Centro;
import Perrera.RangoException;

public class CentroServlet extends HttpServlet {
	
	public void doGet (HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {

		// selecciona el tipo de contenido en la cabecera antes de acceder a Writer
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// Luego escribe la respuesta
		out.println("<html>" + "<head><title>Listado de Centros</title></head>"+ "<body>");

		out.println("<H3> LISTADO DE CENTROS (con doGet)</H3>");

		try {
			List list1 = CentroStore.getInstance().getCentros();
			
			//Muestra en pantalla algunos datos de cada centro
			out.println("<table border=2 bgcolor='silver'>");
			out.println("<tr><th bgcolor='olive'>NOMBRE CENTRO</th><th bgcolor='olive'>CODIGO POSTAL</th><th bgcolor='olive'>DIRECCION</th><th bgcolor='olive'>POBLACION</th><th bgcolor='olive'>PROVINCIA</th><th bgcolor='olive'>TELEFONO</th><th bgcolor='olive'>DESCRIPCION</th>");
			for (Iterator iter = list1.iterator(); iter.hasNext();) {
				Centro cen = (Centro) iter.next();
				out.println( "<tr><th>"+ cen.getNombre()+ "</th><th>" +
				cen.getCodigoPostal()+ "</th><th> " + cen.getDireccion() + "</th><th>" +
				cen.getPoblacion()+ "</th><th> " + cen.getProvincia()+"</th><th>" +
				cen.getTelefono()+"</th><th> " + cen.getDescripcion()+"</th></tr>");	
			}
			out.println("</table>");
			
		} catch (RangoException e) {
			e.printStackTrace();
		}
		
		out.println("</body></html>");
		
		out.close();
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// selecciona el tipo de contenido en la cabecera antes de acceder a Writer
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// Luego escribe la respuesta
		out.println("<html>" + "<head><title>Listado de Centros </title></head>"+ "<body>");

		out.println("<H3> LISTADO DE CENTROS (con doPost)</H3>");

		try {
			List list1 = CentroStore.getInstance().getCentros();
			
			//Muestra en pantalla algunos datos de cada centro
			out.println("<table border=2 bgcolor='silver'>");
			out.println("<tr><th bgcolor='olive'>NOMBRE CENTRO</th><th bgcolor='olive'>CODIGO POSTAL</th><th bgcolor='olive'>DIRECCION</th><th bgcolor='olive'>POBLACION</th><th bgcolor='olive'>PROVINCIA</th><th bgcolor='olive'>TELEFONO</th><th bgcolor='olive'>DESCRIPCION</th>");
			for (Iterator iter = list1.iterator(); iter.hasNext();) {
				Centro cen = (Centro) iter.next();
				out.println( "<tr><th>"+ cen.getNombre()+ "</th><th>" +
				cen.getCodigoPostal()+ "</th><th> " + cen.getDireccion() + "</th><th>" +
				cen.getPoblacion()+ "</th><th> " + cen.getProvincia()+"</th><th>" +
				cen.getTelefono()+"</th><th> " + cen.getDescripcion()+"</th></tr>");	
			}
			out.println("</table>");
			
		} catch (RangoException e) {
			e.printStackTrace();
		}
		
		out.println("</body></html>");
		
		out.close();
	}
}
