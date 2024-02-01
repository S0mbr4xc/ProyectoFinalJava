package dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import modelo.Persona;

@Stateless
public class PersonaDAO {
	@PersistenceContext
	private EntityManager em;
	
	public void insert(Persona persona) {
		em.persist(persona);
	}
	
	public void update(Persona persona) {
		em.merge(persona);
	}
	
	public void remove(int codigo) {
		Persona persona = em.find(Persona.class, codigo);
		em.remove(persona);
	}
	
	public Persona read(int codigo) {
		Persona persona = em.find(Persona.class, codigo);
		return persona;
	}
	
	public List<Persona> getAll(){
		String jpql = "SELECT p FROM Persona p";
		Query q = em.createQuery(jpql, Persona.class);
		return q.getResultList();
	}
}
