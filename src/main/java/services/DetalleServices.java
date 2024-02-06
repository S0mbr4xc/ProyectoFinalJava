package services;

import java.util.List;

import business.GestionDetalles;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import modelo.Cabecera;
import modelo.Detalle;

@Path("detalle")
public class DetalleServices {
	@Inject
	private GestionDetalles gestionDetalles;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list")
	public Response getCabeceras(){
		List <Detalle> lista = gestionDetalles.getDetalles();
		if(lista.size()>0) {
			return Response.ok(lista).build();
		}
		ErrorMessage em = new ErrorMessage(57, "No se registran detalle");
		return Response.status(Response.Status.NOT_FOUND)
				.entity(em)
				.build();
	}
}
