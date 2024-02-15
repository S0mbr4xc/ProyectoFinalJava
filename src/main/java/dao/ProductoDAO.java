package dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import modelo.Categoria;
import modelo.Persona;
import modelo.Producto;

@Stateless
public class ProductoDAO {
	@PersistenceContext
	private EntityManager em;
	
	public void insert(Producto producto) {
		em.persist(producto);
	}
	
	public void update(Producto producto) {
		em.merge(producto);
	}
	
	public void remove(int codigo) {
		Producto producto = em.find(Producto.class, codigo);
		em.remove(producto);
	}
	
	public Producto read(int codigo) {
		Producto producto = em.find(Producto.class, codigo);
		return producto;
	}
	
	public List<Producto> getAll(){
		String jpql = "SELECT p FROM Producto p";
		Query q = em.createQuery(jpql, Producto.class);
		return q.getResultList();
	}
	
	public List<Producto> getProdCat(int num){
		String jpql = "SELECT p FROM Producto p WHERE categoria_id = :num";
		Query q = em.createQuery(jpql, Producto.class);
		q.setParameter("num", num);
		return q.getResultList();
	}

}
