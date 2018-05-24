package service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import daoCloudant.CloudantUsuarioDAO;

import dominio.ImageData;
import dominio.Usuario;

public class UserService {

		  private static Map<String, Usuario> usuarios;

		  public static void init(){
					 if(usuarios == null)
								usuarios = new HashMap<String, Usuario>();
					 for(Usuario user: (new CloudantUsuarioDAO()).getAll())
								usuarios.put(user.get_id(), user);
		  }

		  //Comprobar si un usuario está logeado
		  public static boolean isLogged(HttpSession session){
					 String userid = (String) session.getAttribute("userid");
					 if(userid == null)
								return false;
					 //Comprobar que sea un userid válido
					 return usuarios.containsKey(userid);
		  }

		  public static boolean loginCheck(String id, String password, ImageData data){
					 Usuario user = usuarios.get(id);
					 if(user == null)
								return false;
					 if(user.getPassword() != password)
								return false;
					 if(!user.getImageData().compare(data))
								return false;
					 //Guardamos los nuevos datos de imágen
					 user.setImageData(data); //Se actuliza en el mapa debido a que es una referencia
					 (new CloudantUsuarioDAO()).update(id, user);

					 return true;
		  }

		  public static void logon(String id, String password, String name, ImageData data) throws Exception{
					 if(usuarios.containsKey(id))
								throw new Exception();
					 Usuario user = new Usuario(id,name,password,data);
					 usuarios.put(id,user);
					 (new CloudantUsuarioDAO()).update(id,user);
		  }
}
