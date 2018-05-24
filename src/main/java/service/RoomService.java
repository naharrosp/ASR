package service;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import daoCloudant.CloudantUsuarioDAO;

import dominio.Usuario;

public class RoomService {

		  private static Set<String> rooms;  //Nombre de la room y la room
		  //Estas roomStrings solamente se utilizan para comprobaciones

		  private static Map<String, RoomUser> roomUsers; //Id de usuario y roomUser

		  private static Set<RoomUserInfo> waitingRoom;
		  //Datos de usuario que estan listos para pasar al chat;

		  private static void initRooms(){
					 //Inicializar la existencia de las rooms

					 //Las rooms deberían estar instanciadas en otra clase.
					 Collection<Usuario> usuarios = (new CloudantUsuarioDAO()). getAll();

					 for(Usuario usuario: usuarios)
								rooms.addAll( usuario.getChats() );
		  }

		  public static void init(){
					 if( roomUsers == null )
								roomUsers = new TreeMap<String, RoomUser>();
					 if( rooms == null )
								initRooms();
					 if( waitingRoom == null )
								waitingRoom = new TreeSet<RoomUserInfo>();
		  }

		  //TODO deberá devolver un POJO con el userid la roomid
		  public static RoomUserInfo logonUser( String userid, String roomStr) throws RoomException{
					 //user debe ser golbalmente identificable
					 //room debe también ser golbalbment identificable

					 //Comprobaciones sobre los datos 
					 if( !rooms.contains(roomStr) )
								throw new RoomException("La room no existe");
					 if( roomUsers.containsKey(userid) )
								throw new RoomException("El usuario no existe");

					 Usuario user = (new CloudantUsuarioDAO()).get(userid);

					 if( !user.getChats().contains(roomStr) )
								throw new RoomException("El usuario no tiene acceso a esa room");

					 //Arranque de dependencias
					 RoomUser roomUser = new RoomUser(user.getName(), userid, roomStr);
					 roomUsers.put(userid,roomUser);

					 RoomUserInfo info = new RoomUserInfo(userid,roomStr);
					 waitingRoom.add(info);
					 return info;
		  }

		  public static void logoutUser( String userid ){
					 roomUsers.remove(userid);
		  }

		  //TODO: incluir el input stream y output stream en el método
		  public static void loginWebSocket( String userid, String roomStr ) throws RoomException{
					 RoomUserInfo info = new RoomUserInfo(userid,roomStr);
					 if( !waitingRoom.remove(info))
								throw new RoomException("El usuario no ha sido registrado y no esta en la sala de espeara");

					 //TODO: casi todo
		  }


		  public static class RoomUserInfo{ //Esta clase existe solamente para devolver la información a roomManager
					 private String userId;
					 private String roomPath;

					 /**
					  * @param userId
					  * @param roomPath
					  */
					 public RoomUserInfo(String userId, String roomPath) {
								this.userId = userId;
								this.roomPath = roomPath;
					 }

					 /**
					  * @return the userId
					  */
					 public String getUserId() {
								return userId;
					 }

					 /**
					  * @return the roomPath
					  */
					 public String getRoomPath() {
								return roomPath;
					 }

					 //Super custom tuning
					 public boolean equals(RoomUserInfo o){
								return userId.equals(o.getUserId()) && roomPath.equals(o.getRoomPath());
					 }
		  }
}

