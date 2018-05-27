
package dominio;

public class LoginMessage {

		  private String user;
		  private String room;

		  public LoginMessage(String user, String room) {
					 this.user = user;
					 this.room = room;
		  }

		  public String getUser() {
					 return user;
		  }

		  public void setUser(String user) {
					 this.user = user;
		  }

		  public String getRoom() {
					 return room;
		  }

		  public void setRoom(String room) {
					 this.room = room;
		  }

}
