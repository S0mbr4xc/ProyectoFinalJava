package business;

import java.util.List;

import dao.DetalleDAO;
import dao.ProductoDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import modelo.Cabecera;
import modelo.Detalle;
import modelo.Producto;

@Stateless
public class GestionDetalles {
	
	@Inject
	private DetalleDAO detalleDAO;
	@Inject
	private ProductoDAO productoDAO;
	
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
	
	 public void actualizarDetalles(int IdProductoID, int cantidad, int detalleId) {
		    try {
		        // Itera sobre cada detalle en la lista
		        	Detalle detalle = detalleDAO.obtenerDetallePorProductoId(IdProductoID);
		            // Obtiene el producto asociado al detalle
		            Producto producto = detalle.getProducto();
		            

		            if(detalle.getCodigo() == detalleId) {
		            	 	
				            double subtotal = producto.getPrecio();
				            double iva = subtotal * 0.12;
				            double total = cantidad   * subtotal;
				            

				            // Actualiza los valores en el objeto detalle
				            detalle.setIva(iva);
				            detalle.setSubtotal(subtotal);
				            detalle.setTotal(total);
				            detalle.setCantidad(cantidad);
				            detalle.setPrecioUnitario(detalle.getProducto().getPrecio());
				            
				            
				            // Actualiza el detalle en la base de datos
				            detalleDAO.update(detalle);
	
				           
				            
				            
		            }
		           
		        System.out.println("Detalles actualizados correctamente en la base de datos.");
		    } catch (Exception e) {
		        System.out.println("Error al actualizar los detalles en la base de datos: " + e.getMessage());
		        e.printStackTrace();
		        // Puedes manejar la excepción según tus necesidades
		    }
		}
	 
	 public List<Detalle> obtenerDetallesPorCabeceraId(int cabeceraId) {
	        return detalleDAO.obtenerDetallesPorCabeceraId(cabeceraId);
	    }


}
