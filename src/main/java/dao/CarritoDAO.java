package dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import modelo.Carrito;
import modelo.Persona;

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
	
	public Carrito obtenerCarritoPersona(Persona persona) {
		String jpql = "SELECT c FROM Carrito c WHERE c.persona. = :persona";
		Query q = em.createQuery(jpql, Carrito.class);
		q.setParameter("persona", persona);
		List<Carrito> resultados = q.getResultList();
		return resultados.isEmpty() ? null : resultados.get(0);
	}

}
