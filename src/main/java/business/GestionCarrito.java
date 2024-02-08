package business;

import java.util.ArrayList;
import java.util.Date;
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
		        if (carrito == null) {
		            // Si no se encuentra el carrito, crea uno nuevo
		            carrito = new Carrito();

		            // Asigna la persona al carrito
		            Persona cliente = personaDAO.obtenerPorCodigo(codigo);
		            carrito.setPersona(cliente);
		        }

		        // Obtén o inicializa la lista de detalles del carrito
		        List<Detalle> detalles = carrito.getDetalle();
		        if (detalles == null) {
		            detalles = new ArrayList<>();
		        }

		        // Paso 2: Crea un nuevo detalle.
		        Detalle detalle = new Detalle();

		        // Paso 3: Asigna el producto al detalle.
		        detalle.setProducto(producto);
		        detalle.setCarrito(carrito);

		        // Paso 4: Agrega el detalle a la lista de detalles.
		        detalles.add(detalle);
		        carrito.setDetalle(detalles);

		        // Actualiza el carrito en la base de datos
		        carritoDAO.update(carrito);

		        System.out.println("Producto agregado al carrito exitosamente.");
		    } catch (Exception e) {
		        System.out.println("Error interno del servidor: " + e.getMessage());
		        e.printStackTrace();
		        // Puedes manejar la excepción según tus necesidades
		    }
		}
	 
	 public void establecerCabeceraEnDetalles(int codigoCarrito, int codigoPersona) {
		    try {
		        // Paso 1: Obtén el carrito asociado al cliente mediante su código.
		        Carrito carrito = carritoDAO.obtenerCarritoPorCodigoPersona(codigoCarrito);
		        System.out.println("CODIGOOOOOOOOOOOOOOOOOOO" + carrito.getCodigo());
		        Persona persona = personaDAO.obtenerPorCodigo(codigoPersona);
		        System.out.println("CODIGOOOOOOOOOOOOOOOOOOO" + persona.getNombre());
		        if (carrito.getPersona() != null) {
		            // Paso 2: Obtén la lista de detalles en el carrito.
		            List<Detalle> detalles = carrito.getDetalle();
		            
		            // Actualizar el stock del producto después de asignar la cabecera a todos los detalles
		            for (Detalle detalle : detalles) {
		            	if(detalle.getCabecera() == null) {
		                Cabecera cabecera = new Cabecera(); // Crea una nueva cabecera para cada detalle
		                cabeceraDAO.insert(cabecera);
		                cabecera.setFecha(new Date());
		                //cabecera.setPersona(persona);
		                cabecera.setSubtotal(0); // Implementa este método según necesites
		                cabecera.setIva(0); // Implementa este método según necesites
		                cabecera.setTotal(0);
		                // Implementa este método según necesites
		                
		                detalle.setCabecera(cabecera);
		                detalle.setCarrito(null);
		                detalle.getCabecera().setPersona(persona);
		                
		                detalle.getProducto().setStock(detalle.getProducto().getStock() - detalle.getCantidad());
		            	}
		                
		                // Luego puedes guardar los detalles actualizados en la base de datos
		                //detalleDAO.update(detalle);
		            }

		            // Paso 5: Actualiza el carrito en la base de datos.
		            carrito.setPersona(null);
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




	 public void eliminarCarrito(int codigoCarrito) {
		    try {
		        // Busca el carrito por su código
		        Carrito carrito = carritoDAO.obtenerCarritoPorCodigoPersona(codigoCarrito);
		        
		        // Si el carrito existe, elimínalo de la base de datos
		        if (carrito != null) {
		            carritoDAO.remove(codigoCarrito);
		            System.out.println("Carrito eliminado correctamente de la base de datos.");
		        } else {
		            System.out.println("No se encontró ningún carrito con el código especificado.");
		        }
		    } catch (Exception e) {
		        System.out.println("Error al intentar eliminar el carrito de la base de datos: " + e.getMessage());
		        e.printStackTrace();
		        // Puedes manejar la excepción según tus necesidades
		    }
		}

	 
	

}
