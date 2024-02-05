package dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import modelo.Carrito;
import modelo.Persona;
import modelo.Producto;

@Stateless
public class CarritoDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public void insert(Carrito carrito) {
		em.persist(carrito);
	}
	
	public void update(Carrito carrito) {
		em.merge(carrito);
	}
	
	public void remove(int codigo) {
		Carrito carrito = em.find(Carrito.class, codigo);
		em.remove(carrito);
	}
	
	public Carrito read(int codigo) {
		Carrito carrito = em.find(Carrito.class, codigo);
		return carrito;
	}

	
	public List<Carrito> getAll(){
		String jpql = "SELECT c FROM Carrito c";
		Query q = em.createQuery(jpql, Carrito.class);
		return q.getResultList();
	}
	
	
	public Carrito obtenerCarritoPersona(String correo) {
	    String jpql = "SELECT c FROM Carrito c WHERE c.persona.correo = :correo";
	    TypedQuery<Carrito> query = em.createQuery(jpql, Carrito.class);
	    query.setParameter("correo", correo);

	    try {
	        return query.getSingleResult();
	    } catch (NoResultException e) {
	        return null; // Manejar el caso en que no se encuentre ningún carrito
	    } catch (NonUniqueResultException e) {
	        // Manejar el caso en que hay múltiples carritos asociados a la persona
	        // Dependiendo de la lógica de tu aplicación, podrías devolver el primero, lanzar una excepción, etc.
	        return query.getResultList().get(0);
	    }
	}
	
	public List<Producto> obtenerProductosDeCarrito(int codigoCarrito) {
	    String jpql = "SELECT p FROM Producto p WHERE p.carrito.codigo = :codigoCarrito";
	    TypedQuery<Producto> query = em.createQuery(jpql, Producto.class);
	    query.setParameter("codigoCarrito", codigoCarrito);

	    return query.getResultList();
	}
	
	public Carrito obtenerCarritoPorCodigoPersona(int codigoPersona) {
	    String jpql = "SELECT c FROM Carrito c WHERE c.persona.codigo = :codigoPersona";
	    TypedQuery<Carrito> query = em.createQuery(jpql, Carrito.class);
	    query.setParameter("codigoPersona", codigoPersona);

	    try {
	        return query.getSingleResult();
	    } catch (NoResultException e) {
	        return null; // Manejar el caso en que no se encuentre ningún carrito
	    } catch (NonUniqueResultException e) {
	        // Manejar el caso en que hay múltiples carritos asociados a la persona
	        // Dependiendo de la lógica de tu aplicación, podrías devolver el primero, lanzar una excepción, etc.
	        return query.getResultList().get(0);
	    }
	}
	
	/*public Producto agregarProductos(Producto producto, int codigoCarrito) {
	    Carrito carrito = em.find(Carrito.class, codigoCarrito);

	    if (carrito != null) {
	        List<Producto> productos = carrito.getProducto();
	        
	        carrito.setProducto(productos);
	        em.merge(carrito);
	        em.flush();
	        return producto;
	    }
	    return null; // Manejar el caso en que no se encuentra el carrito
	}*/




}
