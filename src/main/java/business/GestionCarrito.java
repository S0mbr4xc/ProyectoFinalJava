package business;

import java.util.List;

import dao.CarritoDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import modelo.Carrito;
import modelo.Producto;

@Stateless
public class GestionCarrito {
	
	@Inject
	private CarritoDAO carritoDAO;
	
	public void guardarCarrito(Carrito carrito) {
		Carrito car = carritoDAO.read(carrito.getCodigo());
		if(car != null) {
			carritoDAO.update(carrito);
		}else {
			carritoDAO.insert(carrito);
		}
		
	}
	
	public void removeCarrito(int codigo) {
		carritoDAO.remove(codigo);
	}
	
	public List<Carrito> getAll(){
		return carritoDAO.getAll();
	}
	
	public void agregarProductos(Producto producto, int codigoCarrito) {
		Carrito carrito = carritoDAO.read(codigoCarrito);
		
		if(carrito != null) {
			List<Producto> productos = carrito.getProductos();
			productos.add(producto);
			carrito.setProductos(productos);
			carritoDAO.update(carrito);
		}
	}
	
	 public void eliminarProductoDelCarrito(int codigoCarrito, Producto producto) {
	        Carrito carrito = carritoDAO.read(codigoCarrito);

	        if (carrito != null) {
	            List<Producto> productos = carrito.getProductos();
	            productos.remove(producto);
	            carrito.setProductos(productos);
	            carritoDAO.update(carrito);
	        }
	    }
	
	 public void vaciarCarrito(int codigoCarrito) {
	        Carrito carrito = carritoDAO.read(codigoCarrito);

	        if (carrito != null) {
	            carrito.setProductos(null);
	            carritoDAO.update(carrito);
	        }
	    }
}
