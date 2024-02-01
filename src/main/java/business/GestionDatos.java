package business;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import dao.CabeceraDAO;
import dao.CategoriaDAO;
import dao.DetalleDAO;
import dao.PersonaDAO;
import dao.ProductoDAO;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import modelo.Cabecera;
import modelo.Categoria;
import modelo.Detalle;
import modelo.Persona;
import modelo.Producto;

@Singleton
@Startup
public class GestionDatos {
	@Inject
	private PersonaDAO personaDAO;
	@Inject
	private ProductoDAO productoDAO;
	@Inject
	private DetalleDAO detalleDAO;
	@Inject
	private CategoriaDAO categoriaDAO;
	@Inject
	private CabeceraDAO cabeceraDAO;
	@Resource(lookup = "java:/MySqlDS")
    private DataSource dataSource;
	
	@PostConstruct
	public void init() {
		 try (Connection connection = dataSource.getConnection()) {
	            // Imprimir un mensaje si la conexión es exitosa
	            System.out.println("Conexión exitosa a la base de datos.");
	        } catch (SQLException e) {
	            // Capturar y mostrar cualquier excepción que ocurra
	            System.err.println("Error al conectar a la base de datos:");
	            e.printStackTrace();
	        }
		 
		 System.out.println("INICIANDO PROCESO ");
		 Persona persona = new Persona();
		 //persona.setCodigo(1);
		 persona.setCedula("0150517241");
		 persona.setApellido("Arce");
		 persona.setNombre("Eduardo");
		 persona.setDireccion("El Arenal");
		 persona.setTelefono("0994030867");
		 persona.setCorreo("eduardomaldonado2003ortiz");
		 personaDAO.insert(persona);
		 
		 Categoria categoria = new Categoria();
		 categoria.setNombre("Tarjetas Graficas");
		 categoriaDAO.insert(categoria);
		 
		 Producto producto = new Producto();
		 producto.setNombre("RTX 3080");
		 producto.setPrecio(100.0);
		 producto.setStock(5);
		 producto.setCodeBarras("010203");
		 producto.setCategoria(categoria);
		 productoDAO.insert(producto);
		 
		 
		 
		 Cabecera cabecera = new Cabecera();
		 cabecera.setFecha(new Date());
		 cabecera.setIva(2.12);
		 cabecera.setPersona(persona);
		 cabecera.setSubtotal(112.12);
		 cabecera.setTotal(100.0);
		 cabeceraDAO.insert(cabecera);
		 
		 Detalle detalle = new Detalle();
		 detalle.setCantidad(1);
		 detalle.setIva(2.12);
		 detalle.setPrecioUnitario(100.0);
		 detalle.setSubtotal(102.12);
		 detalle.setTotal(212.12);
		 detalle.setProducto(producto);
		 detalle.setCabecera(cabecera);
		 List<Detalle> lDet = new ArrayList<>();
		 detalleDAO.insert(detalle);
		 
		 System.out.println("-------CLIENTE---------");
		 List<Persona> l = personaDAO.getAll();
		 for (Persona per : l){
			 System.out.println(per.getCodigo() + per.getNombre());
		 }
		 
		System.out.println("--------FACTURAS-------");
		List<Cabecera> s = cabeceraDAO.getAll();
		for(Cabecera c : s) {
			System.out.println(c.getPersona().getApellido() + c.getTotal());
		}
	    }
	
}
