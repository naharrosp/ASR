package dominio;

import java.util.HashSet;

public class Usuario {

		  private String _id;
		  private String _rev;
		  private String name = null;
		  private HashSet <String> chats = new HashSet<String>();

		  //Info de sesion
		  private int edad;
		  private int genero;

		  public static final int HOMBRE=1;
		  public static final int MUJER=2;


		  public Usuario(String _id, String name, int edad, int genero) {
					 super();
					 this._id = _id;
					 this.name = name;
					 this.edad=edad;
					 this.genero=genero;
		  }
		  public String get_id() {
					 return _id;
		  }
		  public void set_id(String _id) {
					 this._id = _id;
		  }
		  public String get_rev() {
					 return _rev;
		  }
		  public void set_rev(String _rev) {
					 this._rev = _rev;
		  }
		  public String getName() {
					 return name;
		  }
		  public void setName(String name) {
					 this.name = name;
		  }
		  public HashSet<String> getChats() {
					 return chats;
		  }
		  public void addChat(String chat) {
					 this.chats.add(chat);
		  }

		  public void removeChat(String chat) {
					 this.chats.remove(chat);
		  }




		  public int getEdad() {
					 return edad;
		  }
		  public void setEdad(int edad) {
					 this.edad = edad;
		  }
		  public int getGenero() {
					 return genero;
		  }
		  public void setGenero(int genero) {
					 this.genero = genero;
		  }

		  @Override
		  public String toString() {
					 return "Usuario [_id=" + _id + ", _rev=" + _rev + ", name=" + name + ", chats=" + chats + ", edad=" + edad
								+ ", genero=" + genero + "]";
		  }
		  @Override
		  public int hashCode() {
					 final int prime = 31;
					 int result = 1;
					 result = prime * result + ((_id == null) ? 0 : _id.hashCode());
					 return result;
		  }
		  @Override
		  public boolean equals(Object obj) {
					 if (this == obj)
								return true;
					 if (obj == null)
								return false;
					 if (getClass() != obj.getClass())
								return false;
					 Usuario other = (Usuario) obj;
					 if (_id == null) {
								if (other._id != null)
										  return false;
					 } else if (!_id.equals(other._id))
								return false;
					 return true;
		  }
}
