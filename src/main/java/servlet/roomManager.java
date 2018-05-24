package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.RoomException;
import service.RoomService;

/*
 Recibe las peticiones de unirse a una room y lleva al usuario a la ventana de chat.

En esta ventana existe un div con la información sobre el id de usuario y el id de la sala para poder conectarse al servidor ws
*/

/**
 * Servlet implementation class roomManager
 */
@WebServlet("/chat")
public class roomManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public roomManager() {
        super();
    }

	 public void init() throws ServletException{
				System.out.println("Iniciando el servicio de rooms");
				RoomService.init();
				System.out.println("Servicio de rooms iniciado");
	 }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			  //TODO Comprobar que el cliente esta conectado y tiene usuario

			  //Crear roomid y userid (Se comunica con roomService)
			  String userid = (String) request.getSession().getAttribute("userid");
			  String room = (String) request.getParameter("room");

			  try{
						 RoomService.RoomUserInfo info = RoomService.logonUser(userid, room);

						 //Llevar esta información a la request
						 request.setAttribute("userid",info.getUserId());
						 request.setAttribute("roompath",request.getContextPath() + info.getUserId()); //Se concatena con el path para que utilizarlo desde js sea más fácil

						 //Forward hacia el jsp de chat
						 request.getRequestDispatcher("/chat.jsp").forward(request,response);
			  }
			  catch(RoomException ex){
						 response.getWriter().println("Error terrible: " + ex.getMessage());
			  }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
