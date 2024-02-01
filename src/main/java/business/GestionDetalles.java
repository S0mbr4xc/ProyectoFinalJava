package business;

import java.util.List;

import dao.DetalleDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import modelo.Cabecera;
import modelo.Detalle;

@Stateless
public class GestionDetalles {
	
	@Inject
	private DetalleDAO detalleDAO;
	
	public void guardarDetalle(Detalle detalle) {
		Detalle det = detalleDAO.read(detalle.getCodigo());
		if(det != null) {
			detalleDAO.update(detalle);
		}else {
			detalleDAO.insert(detalle);
		}
		
	}
	
	public void actualizarDetalle(Detalle detalle) throws Exception {
		Detalle det = detalleDAO.read(detalle.getCodigo());
		if(det != null){
			detalleDAO.update(detalle);
		}else {
			throw new Exception("El detalle no existe jeje");
		}
	}
	
	public void removeDetalle(int codigo) {
		detalleDAO.remove(codigo);
	}
	
	public List<Detalle> getDetalles(){
		return detalleDAO.getAll();
		
	}


}
