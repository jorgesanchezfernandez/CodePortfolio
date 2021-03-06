package Servlet;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Perrera.*;
import java.util.List;
import java.util.Iterator;
//El front controller se encarga tb de ver si el usuario es valido y en casa positivo le crea la session
public class FrontController extends HttpServlet {
	String masterLogin="admin";
	String masterPass="12345";
	
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
		Usuario usuarioConectado=null;
				
		if (session == null) {
			session = request.getSession();
			if (userForm == null || passwdForm == null || userForm.length() == 0 || passwdForm.length() == 0) {
				logado = false;
			} else {
				usuarioConectado=valido(userForm, passwdForm);
				if (usuarioConectado!=null) {
					logado = true;
					crearSession(session,usuarioConectado);
				} else {
					logado = false;
				}
			}
		} else {

			//comprobamos el tiempo que lleva conectado si es mayor se estable nula
			if(session.getCreationTime() + session.getMaxInactiveInterval()<session.getCreationTime())
				session.invalidate();

			if (userForm == null || passwdForm == null) {
				logado = true;
			} else {
				usuarioConectado=valido(userForm, passwdForm);
				if (usuarioConectado!=null && session!=null) {
					logado = true;
					crearSession(session,usuarioConectado);
				} else {
					logado = false;
				}
			}
		}
		return logado;
	}
	private Usuario valido(String userForm, String passwdForm) {
		//Devuelve el usuario
		Usuario res = null;
		Iterator it=UsuarioStore.getInstance().getUsuarios().iterator();
		Usuario usu=null;
		
		while(it.hasNext() && res==null){
			usu=(Usuario) it.next();
			if(userForm.compareTo(usu.getLogin())==0 && passwdForm.compareTo(usu.getPassword())==0){
				res=usu;
			}
		}
		return res;
	}
	
	private void crearSession(HttpSession session,Usuario usu){
		session.setAttribute("session.usu", usu);
		session.setMaxInactiveInterval(60);	//Tiempo minimo de inactividad en la sesion
		if(usu.getLPeticiones()!=null)
			session.setAttribute("session.peticiones",usu.getLPeticiones());
		else
			session.setAttribute("session.peticiones",null);
	}
}

