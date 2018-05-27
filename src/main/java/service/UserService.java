package service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import daoCloudant.CloudantUsuarioDAO;

import dominio.ImageData;
import dominio.Usuario;

public class UserService {

		  //private static Map<String, Usuario> usuarios;

		  //public static void init(){
					 //if(usuarios == null)
								//usuarios = new HashMap<String, Usuario>();
					 //for(Usuario user: (new CloudantUsuarioDAO()).getAll())
								//usuarios.put(user.get_id(), user);
		  //}

		  //Comprobar si un usuario está logeado
		  public static boolean isLogged(HttpSession session){
					 String userid = (String) session.getAttribute("userid");
					 if(userid == null)
								return false;
					 //Comprobar que sea un userid válido
					 return (new CloudantUsuarioDAO()).get(userid) != null;
		  }

		  public static boolean loginCheck(String id, String password){
					 Usuario user = (new CloudantUsuarioDAO()).get(id);
					 if(user == null)
								return false;
					 if(user.getPassword() != null)
								if(user.getPassword().equals(password))
										  return false;
					 return true;
		  }

		  public static boolean loginCheck(String id, String password, ImageData data){
					 Usuario user = (new CloudantUsuarioDAO()).get(id);
					 if(!loginCheck(id, password))
								return false;
					 if(!user.getImageData().compare(data))
								return false;
					 //Guardamos los nuevos datos de imágen
					 user.setImageData(data); //Se actuliza en el mapa debido a que es una referencia
					 (new CloudantUsuarioDAO()).update(id, user);

					 return true;
		  }

		  public static void logon(String id, String password, String name, ImageData data) throws Exception{
					 if((new CloudantUsuarioDAO()).get(id) != null)
								throw new Exception();
					 Usuario user = new Usuario(id,name,password,data);
					 (new CloudantUsuarioDAO()).persist(user);
		  }
}
