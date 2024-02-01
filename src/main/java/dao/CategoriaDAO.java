package dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import modelo.Categoria;
import modelo.Persona;

@Stateless
public class CategoriaDAO {
	@PersistenceContext
	private EntityManager em;
	
	
	
	public void insert(Categoria categoria) {
		em.persist(categoria);
	}
	
	public void update(Categoria categoria) {
		em.merge(categoria);
	}
	
	public void remove(int codigo) {
		Categoria categoria = em.find(Categoria.class, codigo);
		em.remove(categoria);
	}
	
	public Categoria read(int codigo) {
		Categoria categoria = em.find(Categoria.class, codigo);
		return categoria;
	}
	
	public List<Categoria> getAll(){
		String jpql = "SELECT c FROM Categoria c";
		Query q = em.createQuery(jpql, Categoria.class);
		return q.getResultList();
	}
	
}
