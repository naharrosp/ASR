/*Serverlet de login

  Evaluará los campos recibidos
  - Si el usuario tiene la correspondiente cookie, se le redigira de manera automática
  - Si son correctos envía a selección de chat y proporciona la cookie
  - Si no son correctos se le devolverá a la misma página*/

package servlet;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoCloudant.CloudantUsuarioDAO;

import dominio.Usuario;

import service.RoomService;
import service.UserService;

@WebServlet("/login")
public class login extends HttpServlet {
		  private static final long serialVersionUID = 1L;

		  public login() {
					 super();
		  }

		  @Override
		  public void init(){
					 System.out.println("Usuarios:");
					 Collection<Usuario> usuarios = (new CloudantUsuarioDAO()).getAll();
					 System.out.println("Longitud: " + usuarios.size());
					 System.out.println("Inicializando login servlet");
					 RoomService.init();
					 System.out.println("Inicializado login servlet");
		  }

		  @Override
		  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					 //for(String name: request.getParameterNames())
								//System.out.println("Parameter: " + name);
					 //for(String name: request.getAttributeNames())
								//System.out.println("Attribute: " + name);
					 //System.out.println("DoGetRecibido");
					 //TODO: Añadir soporte a reconocimiento de imágenes
					 String username = request.getParameter("username");
					 String password = request.getParameter("password");
					 if(username == null) {
								response.getWriter().println("Debe proporcionar usuario");
								response.getWriter().println("Username: " + username);
								return;
					 }
					 if( !UserService.loginCheck(username,password) ){
								response.getWriter().println("Usuario o contraseña incorrectos");
								return;
					 }
					 request.setAttribute("roomList",(new CloudantUsuarioDAO()).get(username).getChats());

					 request.getSession().setAttribute("userid", username);
					 request.getRequestDispatcher("/rooms.jsp").forward(request, response);
		  }

		  @Override
		  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					 doGet(request, response);
		  }

}
