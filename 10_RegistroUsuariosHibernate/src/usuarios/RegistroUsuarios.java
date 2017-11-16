package usuarios;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

public class RegistroUsuarios {
	
	// Constructor privado
	private void RegistroUsuario() {
		
	}
	
	/*public static Session getSession() {
		// obtener la sesion actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		//crear la sesion
		Session session = sesion.openSession();
		
		Transaction tx = session.beginTransaction();
		
		return session;
	}*/
		
	public static void anyadirUsuario(String vdni, String vnombre, String vapellido) throws UsuarioExistente {

		// obtener la sesion actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		//crear la sesion
		Session session = sesion.openSession();
		
		// crear una transaccion de la sesion
		Transaction tx = null;
		
		boolean existe = false;
         
		try {
 			// crear una transaccion de la sesion
			tx = session.beginTransaction();
			
			session = sesion.openSession();
			tx = session.beginTransaction();
			
			Usuarios usuario = new Usuarios();
			usuario.setDni(vdni);
			usuario.setNombre(vnombre);
			usuario.setApellido(vapellido);
			
			session.save(usuario);
			try {
				tx.commit();
				System.out.println("Usuario anyadido correctamente");
			} catch(Exception e) {
				ConstraintViolationException ex = (ConstraintViolationException)  e.getCause();
				System.out.println("USUARIO DUPLICADO");
				System.out.printf("MENSAJE: %s%n", ex.getMessage());
				//System.out.printf("COD ERROR: %d%n", ex.getErrorCode());
				System.out.printf("ERROR SQL: %s%n", ex.getSQLException().getMessage());
			}
			
			session.close();
 		} catch (HibernateException e) {
 			if (tx!=null) tx.rollback();
 				e.printStackTrace();
 		} finally {
 			session.close();
 			//System.exit(0);
 		}
	}
	
	public static void buscarUsuario(String vdni) throws UsuarioInexistente {
		// obtener la sesion actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		//crear la sesion
		Session session = sesion.openSession();
		
		// crear una transaccion de la sesion
		Transaction tx = null;
         
		try {
 			// crear una transaccion de la sesion
			tx = session.beginTransaction();
 			
 			//List usuarios = session.createQuery("FROM Usuarios WHERE Dni=" + vdni).list();
 			
 			Query query = session.createQuery("FROM Usuarios WHERE Dni = :dni");
 			query.setParameter("dni", vdni);
 			List usuarios = query.list();
 			
 			for (Iterator iterator = usuarios.iterator(); iterator.hasNext();) {
 	            Usuarios usuario = (Usuarios) iterator.next();
 	            if (usuario.getDni().equalsIgnoreCase(vdni)) {
 		            System.out.print("DNI: " + usuario.getDni());
 		            System.out.print("\nNombre: " + usuario.getNombre());
 		            System.out.print("\nApellido: " + usuario.getApellido() + "\n");
 					break;
 	            }
 			}
 			
 			if (usuarios.size() <= 0) {
 				throw new UsuarioInexistente("No existe el DNI introducido");
 				//System.out.println("No existe el DNI introducido");
 			}
 		} catch (HibernateException e) {
 			if (tx!=null) tx.rollback();
 				e.printStackTrace();
 		} finally {
 			session.close();
 			//System.exit(0);
 		}
	}
	
	public static void mostrarUsuarios() throws UsuarioInexistente {
		// obtener la sesion actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		//crear la sesion
		Session session = sesion.openSession();
		
		Transaction tx = null;
		try {
			// crear una transaccion de la sesion
			tx = session.beginTransaction();
						
			//List empleados = session.createQuery("FROM Empleados").list();
			List usuarios = session.createQuery("FROM Usuarios").list();
			
			for (Iterator iterator = usuarios.iterator(); iterator.hasNext();) {
	            Usuarios usuario = (Usuarios) iterator.next();
	            System.out.print("DNI: " + usuario.getDni());
	            System.out.print(" Nombre: " + usuario.getNombre());
	            System.out.print(" Apellido: " + usuario.getApellido());
	            System.out.print("\n-------");
				System.out.print("\n");
	         }
	         tx.commit();
	         
	         if (usuarios.size() <= 0) {
 				throw new UsuarioInexistente("No hay usuarios");
 				//System.out.println("No hay usuarios");
	         }
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
				e.printStackTrace();
		} finally {
			session.close();
			//System.exit(0);
		}
	}
	
	public static void borrarUsuario(String vdni) throws UsuarioInexistente {		
		
		// obtener la sesion actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		//crear la sesion
		Session session = sesion.openSession();
		
		// crear una transaccion de la sesion
		Transaction tx = null;
		
		boolean existe = false;
         
		try {
 			// crear una transaccion de la sesion
			tx = session.beginTransaction();
 			
 			//List usuarios = session.createQuery("FROM Usuarios WHERE Dni=" + vdni).list();
 			
 			Query query = session.createQuery("FROM Usuarios WHERE Dni = :dni");
 			query.setParameter("dni", vdni);
 			List usuarios = query.list();
 			
 			for (Iterator iterator = usuarios.iterator(); iterator.hasNext();) {
 	            Usuarios usuario = (Usuarios) iterator.next();
 	            if (usuario.getDni().equalsIgnoreCase(vdni)) {
 					break;
 	            }
 			}
 			session.close();
 			
 			if (usuarios.size() <= 0) {
 				throw new UsuarioInexistente("No existe el DNI introducido");
 				//System.out.println("No existe el DNI introducido");
	         } else {
 				session = sesion.openSession();
 				tx = session.beginTransaction(); 				
 				
 				Usuarios usuario = (Usuarios)session.get(Usuarios.class, vdni);
 				session.delete(usuario); 
 				tx.commit();
 				
 				System.out.println("Usuario borrado correctamente");
 				System.out.println("");
 				
 				session.close();
 				
 				//System.exit(0);
 			}
 		} catch (HibernateException e) {
 			if (tx!=null) tx.rollback();
 				e.printStackTrace();
 		} finally {
 			session.close();
 			//System.exit(0);
 		}
	}

	// https://www.mkyong.com/hibernate/hibernate-query-examples-hql/
	public static void actualizarUsuarioMySQL(String vdni) throws UsuarioInexistente {
		//Query slqUpdate;
		
		// obtener la sesion actual
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		
		//crear la sesion
		Session session = sesion.openSession();
		
		Transaction tx = null;
		
		try {
			// crear una transaccion de la sesion
			tx = session.beginTransaction();
 			
 			//List usuarios = session.createQuery("FROM Usuarios WHERE Dni=" + vdni).list();
 			
 			Query query = session.createQuery("FROM Usuarios WHERE Dni = :dni");
 			query.setParameter("dni", vdni);
 			List usuarios = query.list();
 			
 			for (Iterator iterator = usuarios.iterator(); iterator.hasNext();) {
 	            Usuarios usuario = (Usuarios) iterator.next();
 	            if (usuario.getDni().equalsIgnoreCase(vdni)) {
 		            System.out.print("DNI: " + usuario.getDni());
 		            System.out.print(", Nombre: " + usuario.getNombre());
 		            System.out.print(", Apellido: " + usuario.getApellido() + "\n");
 					break;
 	            }
 			}
 			
 			if (usuarios.size() <= 0) {
 				throw new UsuarioInexistente("No existe el DNI introducido");
 				//System.out.println("No existe el DNI introducido");
	         } else {

				Scanner sc = new Scanner(System.in);
				
				System.out.println("¿Que dato desea actualizar? (nombre || apellido || ambos)");
				String dato = sc.next();
				
				switch(dato) {
					// Actualizar nombre
					case "nombre": {
						System.out.print("Nuevo nombre: ");
						String nombre = sc.next();
			            
			            /*slqUpdate = session.createQuery("UPDATE Usuarios SET Nombre = :nombre WHERE Dni = :dni");
			            slqUpdate.setParameter("nombre", nombre);
			            slqUpdate.setParameter("dni", vdni);
						int result = slqUpdate.executeUpdate();*/
						
						Usuarios usuario = (Usuarios)session.get(Usuarios.class, vdni);
						usuario.setNombre(nombre);
						session.update(usuario);
						tx.commit();		
		 				
						System.out.println("Usuario actualizado correctamente");
						
						session.close();
						break;
					}
					
					// Actualizar apellido
					case "apellido": {
						System.out.print("Nuevo apellido: ");
						String apellido = sc.next();
			            
			            /*slqUpdate = session.createQuery("UPDATE Usuarios SET Apellido = :apellido WHERE Dni = :dni");
			            slqUpdate.setParameter("apellido", apellido);
			            slqUpdate.setParameter("dni", vdni);
						int result = slqUpdate.executeUpdate();*/
						
						Usuarios usuario = (Usuarios)session.get(Usuarios.class, vdni);
						usuario.setApellido(apellido);
						session.update(usuario);
						tx.commit();
						
						System.out.println("Usuario actualizado correctamente");
						break;
					}
					
					case "ambos": {
						System.out.print("Nuevo nombre: ");
						String nombre = sc.next();
						System.out.print("Nuevo apellido: ");
						String apellido = sc.next();
			            
			            /*slqUpdate = session.createQuery("UPDATE Usuarios SET Nombre = :nombre, Apellido = :apellido WHERE Dni = :dni");
			            slqUpdate.setParameter("nombre", nombre);
			            slqUpdate.setParameter("apellido", apellido);
			            slqUpdate.setParameter("dni", vdni);
						int result = slqUpdate.executeUpdate();*/     
						
						Usuarios usuario = (Usuarios)session.get(Usuarios.class, vdni);
						usuario.setNombre(nombre);
						usuario.setApellido(apellido);
						session.update(usuario);
						tx.commit();
			            
						System.out.println("Usuario actualizado correctamente");
						break;
					}
					
					default: {
						System.out.println();
						System.out.println("No has seleccionado una opcion valida.");
					}
				}
 			}
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
				e.printStackTrace();
		} finally {
			session.close();
			//System.exit(0);
		}
	}
}
