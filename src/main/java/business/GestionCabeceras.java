package business;

import java.util.Date;
import java.util.List;

import dao.CabeceraDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import modelo.Cabecera;
import modelo.Carrito;
import modelo.Producto;
import modelo.Detalle;

@Stateless
public class GestionCabeceras {
	
	@Inject
	private CabeceraDAO cabeceraDAO;
	
	public void guardarCabecera(Cabecera cabecera) {
		Cabecera cab = cabeceraDAO.read(cabecera.getCodigo());
		if(cab != null) {
			cabeceraDAO.update(cabecera);
		}else {
			cabeceraDAO.insert(cabecera);
		}
		
	}
	
	public void actualizarCabecera(Cabecera cabecera) throws Exception {
		Cabecera cab = cabeceraDAO.read(cabecera.getCodigo());
		if(cab != null){
			cabeceraDAO.update(cabecera);
		}else {
			throw new Exception("La cabecera no existe jeje");
		}
	}
	
	public void removeCabecera(int codigo) {
		cabeceraDAO.remove(codigo);
	}
	
	public List<Cabecera> getCabeceras(){
		return cabeceraDAO.getAll();
		
	}
	
	public void getCabeceraPor() {
		
	}
	 
}
