
package dominio;

public class LoginMessage {

		  private String id;
		  private String room;

		  public LoginMessage(String id, String room) {
					 this.id = id;
					 this.room = room;
		  }

		  public String getId() {
					 return id;
		  }

		  public void setId(String id) {
					 this.id = id;
		  }

		  public String getRoom() {
					 return room;
		  }

		  public void setRoom(String room) {
					 this.room = room;
		  }
}
