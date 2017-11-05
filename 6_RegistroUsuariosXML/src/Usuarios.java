import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Usuarios {
	
	private int id;
	private String nombreCreador;
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	
	public Usuarios() {
		
	}
	
	public Usuarios(int id, String creador, ArrayList<Usuario> alUsers) {  
	    super();
	    this.id = id;
	    this.setNombreCreador(creador);
	    this.usuarios = alUsers;
	}
	
	 
	public int getId() {  
	    return id;  
	}
	@XmlAttribute
	public void setId(int id) {  
	    this.id = id;  
	}
	
	// El nombre del set/get es el nombre que se le asigna
	public ArrayList<Usuario> getUsers() {  
	    return usuarios;  
	}

	public void setUsers(ArrayList<Usuario> alUsers) {  
	    this.usuarios = alUsers;  
	}

	public String getNombreCreador() {
		return nombreCreador;
	}

	@XmlElement
	public void setNombreCreador(String nombreCreador) {
		this.nombreCreador = nombreCreador;
	}  
}