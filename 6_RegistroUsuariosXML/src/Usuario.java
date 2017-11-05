import java.io.Serializable;
import java.util.Vector;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement(name = "usuario")
@XmlType(propOrder = {"dni", "nombre", "apellido"})
public class Usuario implements Serializable {

	String dni;
	String nombre;
	String apellido;
	
	public String getDni() {
		return dni;
	}
	
	@XmlElement(name = "dni_usuario")
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	
	@XmlElement(name = "nombre_usuario")
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	
	// @XmlAttribute
	@XmlElement(name = "apellido_usuario")
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
}