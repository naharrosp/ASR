/*Serverlet de login

Evaluará los campos recibidos
- Si el usuario tiene la correspondiente cookie, se le redigira de manera automática
- Si son correctos envía a selección de chat y proporciona la cookie
- Si no son correctos se le devolverá a la misma página*/

package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;

@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			  //TODO: Añadir soporte a reconocimiento de imágenes
			  String username = request.getParameter("username");
			  String password = request.getParameter("password");
			  //if(!UserService.loginCheck(username, password)){ //TODO: revisión real
			  if(!username.equals("david")){
						 response.getWriter().println("Usuario incorrecto");
						 return;
			  }
			  request.getSession().setAttribute("userid", username);
			  request.getRequestDispatcher("/rooms.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
