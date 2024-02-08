package services;

import java.util.List;

import business.GestionCarrito;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import modelo.Cabecera;
import modelo.Carrito;
import modelo.Persona;
import modelo.Producto;

@Path("carrito")
public class CarritoServices {
	@Inject
	private GestionCarrito gestionCarrito;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("cliente")
	public Response obtenerCarritoPersona(Persona persona) {
		Carrito carrito = gestionCarrito.obtenerCarritoPersona(persona.getCorreo());
		System.out.println(persona.getCorreo());
		if (carrito != null) {
			
			
			return Response.ok(carrito).build();
		}else {
			ErrorMessage em = new ErrorMessage(88, "No se encuentra el cliente en el carrito");
			return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(em)
                    .build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list")
	public Response getClientes(){
		List <Carrito> lista = gestionCarrito.getAll();
		if(lista.size()>0) {
			return Response.ok(lista).build();
		}
		ErrorMessage em = new ErrorMessage(6, "No se registran carritos");
		return Response.status(Response.Status.NOT_FOUND)
				.entity(em)
				.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("agregar-producto/{codigo}")
	public Response agregarProductoACarrito(Producto producto, @PathParam("codigo") int codigo) {
	    try {
	        gestionCarrito.agregarProductoACarrito(producto, codigo);
	        return Response.ok("Producto agregado al carrito exitosamente.").build();
	    } catch (Exception e) {
	        ErrorMessage eS = new ErrorMessage(63, "Error al agregar el producto al carrito: " + e.getMessage());
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	                .entity(eS)
	                .build();
	    }
	}

	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("xd/{codigo}")
	public Response obtenerCarritoPersonaCorreo(@PathParam("codigo") int codigo) {
		Carrito carrito = gestionCarrito.obtenerCarritoPorPersona(codigo);
		if (carrito != null) {
			return Response.ok(carrito).build();
		}else {
			ErrorMessage em = new ErrorMessage(88, "No se encuentra el cliente en el carrito ESTOY EN EL OTRO");
			return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(em)
                    .build();
		}
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("cabeceraAdd/{codigo}/{codigoPersona}")
	public Response agregarCabecera(@PathParam("codigo") int codigo, @PathParam("codigoPersona") int codigoPersona) {
	    Carrito carrito = gestionCarrito.obtenerCarritoPorPersona(codigo);

	    if (carrito != null) {
	        // Establecer la cabecera en los detalles del carrito
	        gestionCarrito.establecerCabeceraEnDetalles( codigo, codigoPersona);

	        return Response.ok(carrito).build();
	    } else {
	        ErrorMessage em = new ErrorMessage(55, "ERROR AL SETEAR");
	        return Response.status(Response.Status.UNAUTHORIZED)
	                .entity(em)
	                .build();
	    }
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("eliminar-carrito/{codigo}")
	public Response eliminarCarrito(@PathParam("codigo") int codigoCarrito) {
	    try {
	        gestionCarrito.eliminarCarrito(codigoCarrito);
	        return Response.ok("Carrito eliminado correctamente.").build();
	    } catch (Exception e) {
	        ErrorMessage em = new ErrorMessage(100, "Error al eliminar el carrito: " + e.getMessage());
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	                .entity(em)
	                .build();
	    }
	}
	
	
}
