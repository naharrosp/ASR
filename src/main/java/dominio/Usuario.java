package dominio;

import java.util.Set;
import java.util.TreeSet;

public class Usuario {

	private String _id;
	private String _rev;
	private String name = null;
	private Set <String> chats = new TreeSet<String>();


	public Usuario(String _id, String name) {
		super();
		this._id = _id;
		this.name = name;
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
	public Set<String> getChats() {
		return chats;
	}
	public void addChat(String chat) {
		this.chats.add(chat);
	}

	public void removeChat(String chat) {
		this.chats.remove(chat);
	}


	@Override
	public String toString() {
		return "Usuario [_id=" + _id + ", _rev=" + _rev + ", name=" + name + ", chats=" + chats + "]";
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
