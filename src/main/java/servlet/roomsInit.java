package servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoCloudant.CloudantUsuarioDAO;

import dominio.Usuario;

import service.Room;

/**
 * Servlet implementation class roomsInit
 *
 */
//@WebServlet("/roomsInit") //Este servlet no sirve ninguna petición, solamente está para ser inicializado
public class roomsInit extends HttpServlet {
		  private static final long serialVersionUID = 1L;

//TODO: Eliminar

		  /**
			* @see HttpServlet#HttpServlet()
			*/
		  public roomsInit() {
					 super();
		  }

		  private Set<Room> rooms; //Se mantiene en :w


		  public void init() throws ServletException{
					 //TODO: inicializar los message hubs!!!!!
					 System.out.println("Iniciando rooms");

					 Collection<Usuario> usuarios = (new CloudantUsuarioDAO()). getAll();
					 Set<String> roomsStr = new TreeSet<String>();

					 for(Usuario usuario: usuarios)
								roomsStr.addAll( usuario.getChats() );

					 for(String room: roomsStr)
								rooms.add( new Room( room ) );

					 System.out.println("Rooms inicializadas");
		  }

		  /**
			* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
			*/
		  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  }

		  /**
			* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
			*/
		  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					 doGet(request, response);
		  }

}
