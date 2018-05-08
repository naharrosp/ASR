
package dominio;

public class InputMessage {

		  private String usuario;
		  private String room;
		  private String mensaje;

		  /**
			*
			*/
		  public InputMessage() {
		  }

		  /**
			* @return the usuario
			*/
		  public String getUsuario() {
					 return usuario;
		  }

		  /**
			* @param usuario the usuario to set
			*/
		  public void setUsuario(String usuario) {
					 this.usuario = usuario;
		  }

		  /**
			* @return the room
			*/
		  public String getRoom() {
					 return room;
		  }

		  /**
			* @param room the room to set
			*/
		  public void setRoom(String room) {
					 this.room = room;
		  }

		  /**
			* @return the mensaje
			*/
		  public String getMensaje() {
					 return mensaje;
		  }

		  /**
			* @param mensaje the mensaje to set
			*/
		  public void setMensaje(String mensaje) {
					 this.mensaje = mensaje;
		  }

}
