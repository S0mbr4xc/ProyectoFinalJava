package services;

import java.util.List;

import business.GestionDetalles;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
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
	
  	@POST
    @Path("/actualizar/{productoId}/{cantidad}/{detalleId}")
    @Produces(MediaType.APPLICATION_JSON)
  	@Consumes
    public Response actualizarDetalles(@PathParam("productoId") int productoId, @PathParam("cantidad") int cantidad, @PathParam("detalleId") int detalleId) {
        try {
            gestionDetalles.actualizarDetalles(productoId, cantidad, detalleId);
            return Response.ok("Detalles actualizados correctamente en la base de datos.").build();
        } catch (Exception e) {
            ErrorMessage em = new ErrorMessage(99, "Error al actualizar los detalles en la base de datos: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(em)
                .build();
        }
    }
  	
  	@GET
  	@Produces(MediaType.APPLICATION_JSON)
  	@Path("cabecera/{cabeceraId}")
  	public Response getDetallesPorCabeceraId(@PathParam("cabeceraId") int cabeceraId) {
  	    List<Detalle> detalles = gestionDetalles.obtenerDetallesPorCabeceraId(cabeceraId);
  	    if (!detalles.isEmpty()) {
  	        return Response.ok(detalles).build();
  	    } else {
  	        ErrorMessage em = new ErrorMessage(404, "No se encontraron detalles para la cabecera con ID " + cabeceraId);
  	        return Response.status(Response.Status.NOT_FOUND)
  	            .entity(em)
  	            .build();
  	    }
  	}
}
