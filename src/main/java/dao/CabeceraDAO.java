package dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import modelo.Cabecera;
import modelo.Persona;

@Stateless
public class CabeceraDAO {
	@PersistenceContext
	private EntityManager em;
	
	 public void insert(Cabecera cabecera) {
	        
	        em.persist(cabecera);
	    }
	public void update(Cabecera cabecera) {
		em.merge(cabecera);
	}
	
	public void remove(int codigo) {
		Cabecera cabecera = em.find(Cabecera.class, codigo);
		em.remove(cabecera);
	}
	
	public Cabecera read(int codigo) {
		Cabecera cabecera = em.find(Cabecera.class, codigo);
		return cabecera;
	}
	
	public List<Cabecera> getAll(){
		String jpql = "SELECT c FROM Cabecera c";
		Query q = em.createQuery(jpql, Cabecera.class);
		return q.getResultList();
	}
}
