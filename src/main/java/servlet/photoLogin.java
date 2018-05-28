package servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoCloudant.CloudantUsuarioDAO;

import dominio.ImageData;
import dominio.Usuario;

import service.FaceRecognitionService;
import service.UserService;

/**
 * Servlet implementation class photoLogin
 */
@WebServlet("/photoLogin")
public class photoLogin extends HttpServlet {
		  private static final long serialVersionUID = 1L;


		  public void init() throws ServletException{
					 System.out.println("Iniciando el servicio de reconocimiento facial");
					 FaceRecognitionService.init();
					 System.out.println("Servicio de rooms reconocimiento facial");
		  }

		  public photoLogin() {
					 super();
		  }

		  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

					 System.out.print("Petición de reconocimiento facial recibida");
					 String username = (String) request.getSession().getAttribute("userid");
					 String imgSrc = request.getParameter("imgSrc");
					 Enumeration<String> l = request.getParameterNames();
					 while(l.hasMoreElements())
								System.out.println("Parámetro: " + l.nextElement());

					 if(username == null) {
								response.getWriter().println("Debe estar previamente logeado");
								response.getWriter().println("Username: " + username);
								return;
					 }
					 if(imgSrc == null) {
								response.getWriter().println("Debe proporcionar una imagen");
								response.getWriter().println("imgSrc: " + imgSrc);
								return;
					 }

					 try{
								System.out.println("Arrancando análisis");
								ImageData data = FaceRecognitionService.processImage(imgSrc);
								System.out.println();
								System.out.println("Resultados:");
								System.out.println("EdadMax: "+data.getMaxAge());
								System.out.println("EdadMin: "+data.getMinAge());
								System.out.println("Sexo: "+data.getGender());
								Usuario user =  (new CloudantUsuarioDAO()).get(username);
								ImageData userData = user.getImageData();
								if(userData == null){
										  //Si el usuario no tiene datos de imagen se le toma la imagen presente
										  user.setImageData(data);		  
										  return;
								}
								if(!data.compare(userData)){
										  response.getWriter().println("Las imágnes no cuadran");
										  response.getWriter().println();

										  response.getWriter().println("Datos en BBDD");
										  System.out.println("EdadMax: "+userData.getMaxAge());
										  System.out.println("EdadMin: "+userData.getMinAge());
										  System.out.println("Sexo: "+userData.getGender());

										  response.getWriter().println("Datos enviados");
										  System.out.println("EdadMax: "+data.getMaxAge());
										  System.out.println("EdadMin: "+data.getMinAge());
										  System.out.println("Sexo: "+data.getGender());
										  return;
								}
								request.getRequestDispatcher("/rooms.jsp").forward(request, response);

					 }
					 catch(Exception e){
								response.getWriter().println("Error terrible" + e.getMessage());
					 }
		  }

		  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					 doGet(request, response);
		  }

}
