package services;

import java.util.List;

import business.GestionProductos;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import modelo.Categoria;
import modelo.Producto;

@Path("productos")
public class ProductoServices {
	@Inject
	private GestionProductos gestionProductos;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list")
	public Response getProductos(){
		List <Producto> lista = gestionProductos.getProductos();
		if(lista.size()>0) {
			return Response.ok(lista).build();
		}
		ErrorMessage em = new ErrorMessage(8, "No se registran clientes");
		return Response.status(Response.Status.NOT_FOUND)
				.entity(em)
				.build();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list/{categoria}")
	public Response getProductoCat(@PathParam("categoria") int categoria){
		List <Producto> lista = gestionProductos.getProCat(categoria);
		if(lista.size()>0) {
			return Response.ok(lista).build();
		}
		ErrorMessage em = new ErrorMessage(8, "No se registran clientes");
		return Response.status(Response.Status.NOT_FOUND)
				.entity(em)
				.build();
	}
}
