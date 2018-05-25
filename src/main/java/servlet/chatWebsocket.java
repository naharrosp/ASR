/*El chat envía y recibe toda la información a través de este punto.
  Los mensajes recibidos tendrán la siguiente estructura:
  -Userid: int (Identificador de usuario)
  -RoomId: int (Identificador de chat, posiblemente una string sea válida)
  -Msg: string (contenido del mensaje)

  Los mensajes enviados tendrán la siguiente estructura:

*/

package servlet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import dominio.LoginMessage;

import service.RoomUser;


@ServerEndpoint("/wsEndpoint")
public class chatWebsocket {

		  RoomUser user;

		  @OnOpen
		  public void open(Session session) {
					 // Identificar al usuario del websocket
					 // Comunicar con kafka el color del usuario


		  }

		  @OnClose
		  public void close(Session session) {
					 // DO NOTHING
					 // Si es posible, logearlo
					 //RoomService.eliminateClient( id );
		  }

		  @OnError
		  public void onError(Session session, Throwable error) {
					 //log
					 //RoomService.eliminateClient( id );
		  }

		  @OnMessage //Una vez el usuario esté registrado dar el handler al RoomUser
		  public void handleMessage(String message, Session session) {
					 // No es necesario identificar al usuario, solamente hace falta enviar mensaje a kafka
					 //if( !RoomService.isClientReg( id ) ){
					 if( user == null){
								try{
										  Gson gson = new Gson();
										  LoginMessage msg = gson.fromJson( message, LoginMessage.class );
										  user = RoomUser.userFactory( msg.getId(), msg.getRoom());
										  //TODO: return ok message
								}
								catch(JsonSyntaxException e){ //No envía un mensaje de login
										  //TODO: return failed message
								}
					 }else{
								user.fwMessage( message );
					 }
		  }
}    

