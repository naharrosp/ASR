package service;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import IBMKafkaConnector.MessageHubConsoleSample;
import daoCloudant.CloudantUsuarioDAO;
import dominio.Usuario;
import javassist.NotFoundException;

public class RoomService {

		  private static Set<String> rooms;  //Nombre de la room y la room
		  //Estas roomStrings solamente se utilizan para comprobaciones

		  private static Map<String, UsuarioConnection> roomUsers; //Id de usuario y roomUser

		  private static Set<RoomUserInfo> waitingRoom;
		  //Datos de usuario que estan listos para pasar al chat;

		  private static void initRooms(){
					 //Inicializar la existencia de las rooms

					 System.out.println("Inicializando rooms de RoomService");
					 MessageHubConsoleSample.initMessageHubProperties("kafka02-prod02.messagehub.services.eu-gb.bluemix.net:9093,   kafka04-prod02.messagehub.services.eu-gb.bluemix.net:9093, kafka01-prod02.messagehub.services.eu-gb.bluemix.net:9093, kafka03-prod02.messagehub.services.eu-gb.bluemix.net:9093, kafka05-prod02.messagehub.services.eu-gb.bluemix.net:9093", "https://kafka-admin-prod02.messagehub.services.eu-gb.bluemix.net:443","ToMM873rB0nmgldXovJ9B2cwUgbRKTxvsQjVf6pwTIkGCbZF");
					 System.out.println("Properties de RoomService Inicializadas");

					 //Las rooms deberían estar instanciadas en otra clase.
					 Collection<Usuario> usuarios = (new CloudantUsuarioDAO()).getAll();
					 rooms = new TreeSet<String>();

					 for(Usuario usuario: usuarios)
								rooms.addAll(usuario.getChats());

					 for(String room: rooms)
								MessageHubConsoleSample.createTopic(room);
		  }

		  public static void init(){
					 System.out.println("Inicializando RoomService");
					 if( roomUsers == null )
								roomUsers = new TreeMap<String, UsuarioConnection>();
					 if( rooms == null )
								initRooms();
					 if( waitingRoom == null )
								waitingRoom = new TreeSet<RoomUserInfo>();
		  }

		  public static RoomUserInfo logonUser( String userid, String roomStr) throws RoomException, NotFoundException{
					 //user debe ser golbalmente identificable
					 //room debe también ser golbalbment identificable

					 //Comprobaciones sobre los datos 
					 if( !rooms.contains(roomStr) )
								throw new RoomException("La room no existe");
					 if( !roomUsers.containsKey(userid) ) {//No veo por qué no permitir re-engancharse a la ocnexión
								Usuario user = (new CloudantUsuarioDAO()).get(userid);

								if( !user.getChats().contains(roomStr) )
										  throw new RoomException("El usuario no tiene acceso a esa room");

								//Arranque de dependencias
								//UsuarioConnection roomUser = new UsuarioConnection(userid, roomStr);
								//roomUsers.put(userid,roomUser);
					 }
								//throw new RoomException("El usuario ya existe");


					 RoomUserInfo info = new RoomUserInfo(userid,roomStr);
					 waitingRoom.add(info);
					 return info;
		  }

		  public static void logoutUser( String userid ){
					 roomUsers.remove(userid);
		  }

		  //TODO: incluir el input stream y output stream en el método
		  public static boolean loginWebSocket( String userid, String roomStr ) throws RoomException{
					 RoomUserInfo info = new RoomUserInfo(userid,roomStr);
					 return waitingRoom.remove(info); //No solamente devuelve si lo ha encontrado sino que también lo elimina.
		  }


		  public static class RoomUserInfo implements Comparable<RoomUserInfo>{ //Esta clase existe solamente para devolver la información a roomManager
					 private String userId;
					 private String roomPath;

					 public RoomUserInfo(String userId, String roomPath) {
								this.userId = userId;
								this.roomPath = roomPath;
					 }

					 public String getUserId() {
								return userId;
					 }

					 public String getRoomPath() {
								return roomPath;
					 }

					 //Super custom tuning
					 public boolean equals(RoomUserInfo o){
								return userId.equals(o.getUserId()) && roomPath.equals(o.getRoomPath());
					 }
					 public int compareTo(RoomUserInfo o){
								return o.toString().compareTo(o.toString());
					 }
		  }
		  public static Collection<String> getAllRooms(){
					 return rooms;
		  }

}

