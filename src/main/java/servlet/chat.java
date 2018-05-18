/*Punto de llegada a la página de chat.
 * Se utilizará los atributos de room y la cookie de usuario
 * para inicializar el cliente de chat websocket y los productores y
 * consumidores kafka.
 *
 */

package servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Test
 */

@WebServlet("/Test2")
public class chat extends HttpServlet {
    private static final long serialVersionUID = 1L;



	/**
	 *
	 */
	public chat() {
		super();
	}

	/**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//String room = (String) request.getAttribute("room");
				//int userid;
				//for( Cookie cookie: request.getCookies()){
					 //if( cookie.getName() == "userid" ) //Nombre del token de usuario
								//userid = Integer.parseInt(cookie.getValue());
				//}



    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}

