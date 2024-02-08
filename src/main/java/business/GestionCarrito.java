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
		        
		        // Verificar si el carrito y la persona existen
		        if (carrito != null && persona != null) {
		            // Crear una nueva instancia de cabecera
		            Cabecera cabecera = new Cabecera();
		            cabecera.setFecha(new Date());
		            cabecera.setPersona(persona);
		            cabecera.setSubtotal(0); // Debes implementar este cálculo según tus necesidades
		            cabecera.setIva(0); // Debes implementar este cálculo según tus necesidades
		            cabecera.setTotal(0); // Debes implementar este cálculo según tus necesidades
		            
		            // Obtener la lista de detalles en el carrito
		            List<Detalle> detalles = carrito.getDetalle();
		            
		            // Asignar la misma cabecera a todos los detalles
		            for (Detalle detalle : detalles) {
		                detalle.setCabecera(cabecera);
		                
		                // Actualizar el stock del producto después de asignar la cabecera a todos los detalles
		                detalle.getProducto().setStock(detalle.getProducto().getStock() - detalle.getCantidad());
		                
		                // Luego puedes guardar los detalles actualizados en la base de datos
		                // detalleDAO.update(detalle);
		            }
		            
		            // Actualizar el carrito en la base de datos
		            carrito.setPersona(null);
		            carritoDAO.update(carrito);

		            System.out.println("Cabecera asignada a los detalles exitosamente.");
		        } else {
		            System.out.println("No se encuentra el carrito o la persona.");
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
	 
	 public double[] sumarDetallesCabecera(int idCabecera) {
		    // Obtener los detalles de la cabecera
		    List<Detalle> detalles = detalleDAO.obtenerDetallesPorCabeceraId(idCabecera);

		    double ivaTotal = 0;
		    double subtotalTotal = 0;
		    double totalTotal = 0;

		    // Iterar sobre los detalles y sumar los valores
		    for (Detalle detalle : detalles) {
		        // Sumar el IVA, subtotal y total del detalle actual a los totales
		        ivaTotal += detalle.getIva();
		        subtotalTotal += detalle.getSubtotal();
		        totalTotal += detalle.getTotal();
		        Cabecera cabecera = detalle.getCabecera();
		        cabecera.setSubtotal(subtotalTotal);
		        cabecera.setIva(ivaTotal);
		        cabecera.setTotal(totalTotal);
		    }
		    
		    

		    // Retornar un arreglo con los valores sumados
		    return new double[]{ivaTotal, subtotalTotal, totalTotal};
		}


}
