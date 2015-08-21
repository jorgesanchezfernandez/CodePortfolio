package Servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class holaMundo extends HttpServlet {
	
	public void doGet (HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException {

		// selecciona el tipo de contenido en la cabecera antes de acceder a Writer
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// Luego escribe la respuesta
		out.println("Hola");
		out.close();
	}
	
}
