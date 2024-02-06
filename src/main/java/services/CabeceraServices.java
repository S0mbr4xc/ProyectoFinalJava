package services;

import java.util.List;

import business.GestionCabeceras;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import modelo.Cabecera;
import modelo.Carrito;
import modelo.Persona;

@Path("cabecera")
public class CabeceraServices {

	@Inject
	private GestionCabeceras gestionCabecera;
	
	@Path("crear")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Cabecera cabecera) {
		try {
			gestionCabecera.guardarCabecera(cabecera);
			return Response.ok(cabecera).build();
		}catch(Exception e){
			ErrorMessage em = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(em)
					.build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list")
	public Response getCabeceras(){
		List <Cabecera> lista = gestionCabecera.getCabeceras();
		if(lista.size()>0) {
			return Response.ok(lista).build();
		}
		ErrorMessage em = new ErrorMessage(56, "No se registran cabeceras");
		return Response.status(Response.Status.NOT_FOUND)
				.entity(em)
				.build();
	}
	
}
