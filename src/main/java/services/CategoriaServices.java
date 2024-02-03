package services;

import java.util.List;

import business.GestionCategoria;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import modelo.Categoria;
import modelo.Persona;

@Path("categorias")
public class CategoriaServices {
	@Inject
	private GestionCategoria gestionCategoria;
	
	/*@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Categoria persona) {
		try {
			gestionPersonas.guardarPersonas(persona);
			return Response.ok(persona).build();
		}catch(Exception e){
			ErrorMessage em = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(em)
					.build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizar(Persona persona) {
		try {
			gestionPersonas.actualizarPersona(persona);
			return Response.ok(persona).build();
		}catch(Exception e){
			ErrorMessage em = new ErrorMessage(98, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(em)
					.build();
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public String borrar(@QueryParam("id")int codigo) {
		try {
			gestionPersonas.removePersona(codigo);
			return "OK";
		} catch (Exception e) {
			// TODO: handle exception
			return "ERROR";
		}
	}
	
	/*@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response leer(@QueryParam("dni")String cedula) {
		
		try {
			Persona ge = gestionClientes.getClienteporCedula(cedula);
			return Response.ok(ge).build();
		} catch (Exception e) {
			// TODO: handle exception
			ErrorMessage em = new ErrorMessage(4, "Cliente no existe ");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(em)
					.build();
		}
	}*/
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list")
	public Response getCategorias(){
		List <Categoria> lista = gestionCategoria.getCategorias();
		if(lista.size()>0) {
			return Response.ok(lista).build();
		}
		ErrorMessage em = new ErrorMessage(7, "No se registran clientes");
		return Response.status(Response.Status.NOT_FOUND)
				.entity(em)
				.build();
	}
}
