package service;
import java.io.Writer;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import daoCloudant.CloudantUsuarioDAO;

import dominio.Usuario;

import kafka.KafkaMessagesConsumer;
import kafka.KafkaMessagesProducer;

public class RoomService {

		  private static Map<String, RoomUser> wsUser;
		  private static Map<String, Room> rooms; 


		  private static void initRooms(){
					 //Inicializar la existencia de las rooms
					 Collection<Usuario> usuarios = (new CloudantUsuarioDAO()). getAll();
					 Set<String> roomsStr = new TreeSet<String>();

					 for(Usuario usuario: usuarios)
								roomsStr.addAll( usuario.getChats() );

					 for(String room: roomsStr)
								rooms.put( room, new Room( room ) );
		  }
		  
		  private static void initCheck(){
					 if( wsUser == null )
								wsUser= new HashMap<String, RoomUser>();
					 if( rooms == null )
								initRooms();
					 
		  }

		  public static void clientToRoom( ) throws RoomException{
					 // Envía

		  }

		  public static void roomToClient() throws RoomException{

		  }

		  public static void newClient() {
					 initCheck();

		  }

		  public static boolean isClientReg( String id ){
					 return wsUser.containsKey( id );
		  }

		  public static void registerClient( String id, String name, String room ){

		  }

		  public static void eliminateClient( String id ){
					 //Eliminar de la room
					 wsUser.remove( id );
		  }



}
