package Servlet;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FrontController extends HttpServlet {
	String user = "practica";
	String passwd = "practica";
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	processRequest(request, response);
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	processRequest(request, response);
	}
	public void processRequest(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		if (logado(request)) {
			String resource = request.getParameter("respuesta");		//Se almacena la direccion donde se ira en casa de pasar el front controller
			RequestDispatcher d = request.getRequestDispatcher(resource);
			if (d != null) {
				d.forward(request, response);
			}
		} else {
			RequestDispatcher d = request.getRequestDispatcher("error.html");
			if (d != null) {
				d.forward(request, response);
			}
		}
	}
	private boolean logado(HttpServletRequest request) {
		boolean logado = false;
		HttpSession session = request.getSession(false);
		String userForm = request.getParameter("user");
		String passwdForm = request.getParameter("passwd");
		if (session == null) {
			session = request.getSession();
			if (userForm == null || passwdForm == null || userForm.length() == 0 || passwdForm.length() == 0) {
				logado = false;
			} else {
				if (valido(userForm, passwdForm)) {
					logado = true;
					crearSession(session,userForm);
				} else {
					logado = false;
				}
			}
		} else {
			if (userForm == null || passwdForm == null) {
				logado = true;
			} else {
				if (valido(userForm, passwdForm)) {
					logado = true;
					crearSession(session,userForm);
				} else {
					logado = false;
				}
			}
		}
		return logado;
	}
	public boolean valido(String userForm, String passwdForm) {
		boolean res = false;
		res = (userForm.equals(this.user) && passwdForm.equals(this.passwd));
		return res;
	}
	
	public void crearSession(HttpSession session,String userForm){
		session.setAttribute("session.user", userForm);		
	}
}
