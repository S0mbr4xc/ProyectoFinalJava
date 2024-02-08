package dao;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
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
	
	public void updateAll(List<Detalle> det) {
		em.merge(det);
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
	
	public Detalle obtenerDetallePorProductoId(int productoId) {
	    String jpql = "SELECT d FROM Detalle d WHERE d.producto.id = :productoId";
	    TypedQuery<Detalle> query = em.createQuery(jpql, Detalle.class);
	    query.setParameter("productoId", productoId);

	    try {
	        return query.getSingleResult();
	    } catch (NoResultException e) {
	        return null; // Manejar el caso en que no se encuentre ningún detalle asociado al producto
	    }
	}
	
	public List<Detalle> obtenerDetallesPorCabeceraId(int cabeceraId) {
	    String jpql = "SELECT d FROM Detalle d WHERE d.cabecera.id = :cabeceraId";
	    TypedQuery<Detalle> query = em.createQuery(jpql, Detalle.class);
	    query.setParameter("cabeceraId", cabeceraId);

	    return query.getResultList();
	}


}
