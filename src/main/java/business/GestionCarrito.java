package business;

import java.util.List;

import dao.CarritoDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import modelo.Carrito;
import modelo.Persona;
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
	
	public List<Producto> obtenerProductos(int codigoCarrito){
		List <Producto> lista = carritoDAO.obtenerProductosDeCarrito(codigoCarrito);
		return lista;
		
	}
	
	/*public Producto agregarProductos(Producto producto, int codigoCarrito) {
	    Carrito carrito = carritoDAO.read(codigoCarrito);

	    if (carrito != null) {
	        producto.setCarrito(carrito);
	        carritoDAO.agregarProductos(producto, codigoCarrito); // Asegúrate de ajustar este método en CarritoDAO
	        return producto;
	    }
	    return null; // Manejar el caso en que no se encuentra el carrito
	}

	public void eliminarProductoDelCarrito(int codigoCarrito, Producto producto) {
	        Carrito carrito = carritoDAO.read(codigoCarrito);

	        if (carrito != null) {
	            List<Producto> productos = carrito.getProducto();
	            productos.remove(producto);
	            carrito.setProducto(productos);
	            carritoDAO.update(carrito);
	        }
	    }
	
	 /*public void vaciarCarrito(int codigoCarrito) {
	        Carrito carrito = carritoDAO.read(codigoCarrito);

	        if (carrito != null) {
	            carrito.setProducto(null);
	            carritoDAO.update(carrito);
	        }
	    }
	 */
	 public Carrito obtenerCarritoPersona(String correo) {
		 return carritoDAO.obtenerCarritoPersona(correo);
	 }
	 
	 public Carrito obtenerCarritoPorPersona(int codigo) {
		 return carritoDAO.obtenerCarritoPorCodigoPersona(codigo);
	 }
}
