package business;

import java.util.List;

import dao.CabeceraDAO;
import dao.CarritoDAO;
import dao.DetalleDAO;
import dao.PersonaDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import modelo.Cabecera;
import modelo.Carrito;
import modelo.Detalle;
import modelo.Persona;
import modelo.Producto;
import services.ErrorMessage;

@Stateless
public class GestionCarrito {
	
	@Inject
	private CarritoDAO carritoDAO;
	@Inject
	private PersonaDAO personaDAO;
	@Inject
	private CabeceraDAO cabeceraDAO;
	@Inject
	private DetalleDAO detalleDAO;
	
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
	 
	 public void agregarProductoACarrito(Producto producto, int codigo) {
		    try {
		        // Paso 1: Obtén el carrito asociado al cliente mediante su correo.
		        Carrito carrito = carritoDAO.obtenerCarritoPorCodigoPersona(codigo);
		        System.out.println(carrito.getPersona().getNombre() + "---------------OJO EN ESTA VERGA--------------");
		        if (carrito != null) {
		            // Paso 2: Crea un nuevo detalle.
		            Detalle detalle = new Detalle();

		            // Paso 3: Asigna el producto al detalle.
		            detalle.setProducto(producto);
		            detalle.setCarrito(carrito);

		            // Paso 4: Asigna el cliente al carrito (si aún no está asignado).
		            if (carrito.getPersona() == null) {
		                Persona cliente = personaDAO.obtenerPorCodigo(codigo);
		                carrito.setPersona(cliente);
		            }

		            // Paso 5: Agrega el detalle al carrito.
		            List<Detalle> detalles = carrito.getDetalle();
		            detalles.add(detalle);
		            carrito.setDetalle(detalles);

		            // Actualiza el carrito en la base de datos
		            carritoDAO.update(carrito);

		            System.out.println("Producto agregado al carrito exitosamente.");
		        } else {
		            System.out.println("No se encuentra el carrito");
		            // Puedes manejar la lógica de negocio aquí, lanzar excepciones, etc.
		        }
		    } catch (Exception e) {
		        System.out.println("Error interno del servidor: " + e.getMessage());
		        e.printStackTrace();
		        // Puedes manejar la excepción según tus necesidades
		    }
		}
	 
	 public void establecerCabeceraEnDetalles(Cabecera cabecera, int codigoCarrito) {
		    try {
		        // Paso 1: Obtén el carrito asociado al cliente mediante su código.
		        Carrito carrito = carritoDAO.obtenerCarritoPorCodigoPersona(codigoCarrito);

		        if (carrito != null) {
		            // Paso 2: Obtén la lista de detalles en el carrito.
		            List<Detalle> detalles = carrito.getDetalle();

		            // Paso 3: Asigna la cabecera a cada detalle en la lista.
		            for (Detalle detalle : detalles) {
		                detalle.setCabecera(cabecera);
		            }

		            // Paso 4: Actualiza el carrito en la base de datos.
		            carritoDAO.update(carrito);

		            System.out.println("Cabecera asignada a los detalles exitosamente.");
		        } else {
		            System.out.println("No se encuentra el carrito");
		            // Puedes manejar la lógica de negocio aquí, lanzar excepciones, etc.
		        }
		    } catch (Exception e) {
		        System.out.println("Error interno del servidor: " + e.getMessage());
		        e.printStackTrace();
		        // Puedes manejar la excepción según tus necesidades
		    }
		}
}
