package dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import modelo.Detalle;


@Stateless
public class DetalleDAO {
	@PersistenceContext
	private EntityManager em;
	
	public void insert(Detalle detalle) {
		em.persist(detalle);
	}
	
	public void update(Detalle detalle) {
		em.merge(detalle);
	}
	
	public void remove(int codigo) {
		Detalle detalle = em.find(Detalle.class, codigo);
		em.remove(detalle);
	}
	
	public Detalle read(int codigo) {
		Detalle detalle = em.find(Detalle.class, codigo);
		return detalle;
	}
	
	public List<Detalle> getAll(){
		String jpql = "SELECT d FROM Detalle d";
		Query q = em.createQuery(jpql, Detalle.class);
		return q.getResultList();
	}
}
