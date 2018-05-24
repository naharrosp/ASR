package dominio;

import java.util.Set;
import java.util.TreeSet;

public class Usuario {

	private String _id;
	private String name = null;
	private String password = null;
	private ImageData imageData;
	private Set <String> chats = new TreeSet<String>();


	public Usuario(String _id) { //Debe ser utilizado solamente para comparar con nuevos usuarios
		super();
		this._id = _id;
	}

	public Usuario(String _id, String name, String password, ImageData imageData) {//Para nuevos usuarios
		this._id = _id;
		this.name = name;
		this.password = password;
		this.imageData = imageData;
	}

	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getChats() {
		return chats;
	}
	public ImageData getImageData() {
		return imageData;
	}
	public void setImageData(ImageData imageData) {
		this.imageData = imageData;
	}

	public void addChat(String chat) {
		this.chats.add(chat);
	}

	public void removeChat(String chat) {
		this.chats.remove(chat);
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
