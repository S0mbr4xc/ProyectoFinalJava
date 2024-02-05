package business;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import com.fasterxml.jackson.annotation.JsonBackReference;

import dao.CabeceraDAO;
import dao.CarritoDAO;
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
import modelo.Carrito;
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
	@Inject
	private CarritoDAO carritoDAO;
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
		 persona.setContra("jiji");
		 personaDAO.insert(persona);
		 
		 Persona persona2 = new Persona();
		 //persona.setCodigo(1);
		 persona2.setCedula("0150517241");
		 persona2.setApellido("Maldonado");
		 persona2.setNombre("Zoila");
		 persona2.setDireccion("El Arenal");
		 persona2.setTelefono("0994030867");
		 persona2.setCorreo("bea76");
		 persona2.setContra("jiji");
		 personaDAO.insert(persona2);
		 
		 Carrito carrito = new Carrito();
		 carrito.setPersona(persona);
		 carritoDAO.insert(carrito);
		 
		 Carrito carrito2 = new Carrito();
		 carrito2.setPersona(persona2);
		 carritoDAO.insert(carrito2);
		 
		 Categoria categoria = new Categoria();
		 categoria.setNombre("PC Gamer");
		 categoriaDAO.insert(categoria);
		 
		 Categoria categoria2 = new Categoria();
		 categoria2.setNombre("Articulos de Belleza");
		 categoriaDAO.insert(categoria2);
		 
		 Categoria categoria3 = new Categoria();
		 categoria3.setNombre("Celulares");
		 categoriaDAO.insert(categoria3);
		 
		 Categoria categoria4 = new Categoria();
		 categoria4.setNombre("Linea Blanca");
		 categoriaDAO.insert(categoria4);
		 
		 Categoria categoria5 = new Categoria();
		 categoria5.setNombre("Ropa");
		 categoriaDAO.insert(categoria5);
		 
		 Categoria categoria6 = new Categoria();
		 categoria6.setNombre("Libros");
		 categoriaDAO.insert(categoria6);
		 
		 Producto producto = new Producto();
		 producto.setNombre("RTX 3080");
		 producto.setPrecio(100.0);
		 producto.setStock(5);
		 producto.setCodeBarras("010203");
		 producto.setUrl("https://th.bing.com/th/id/OIP.o4FDR9jMpahxfSa3-9l4FAHaDn?w=315&h=180&c=7&r=0&o=5&pid=1.7");
		 producto.setDescripcion("The GeForce RTX 3080 delivers the ultra-performance that gamers crave, powered by Ampere—NVIDIA's 2nd gen .");
		 producto.setCategoria(categoria);
		 productoDAO.insert(producto);
		 
		

		 
		 Producto producto2 = new Producto();
		 producto2.setNombre("Serum");
		 producto2.setPrecio(10.0);
		 producto2.setStock(10);
		 producto2.setCodeBarras("010101");
		 producto2.setUrl("https://th.bing.com/th/id/OIP.AysL-rn6B8EWv2BkCTDBfAHaIa?w=190&h=216&c=7&r=0&o=5&pid=1.7");
		 producto2.setDescripcion("La palabra sérum, deriva de la palabra serum en inglés, que significa suero. Y es un concentrado de ingredientes activos, el cual se ha convertido en el complemento perfecto a nuestra rutina de belleza diaria.");
		 producto2.setCategoria(categoria2);
		 
		 productoDAO.insert(producto2);
		 
		 Producto producto3 = new Producto();
		 producto3.setNombre("Iphone 14");
		 producto3.setPrecio(1000.0);
		 producto3.setStock(5);
		 producto3.setCodeBarras("010102");
		 producto3.setUrl("https://th.bing.com/th/id/OIP.BBjeLwCpYb22yGXbVwO6ugHaHa?w=185&h=184&c=7&r=0&o=5&pid=1.7");
		 producto3.setDescripcion("El iPhone 14 cuenta con sistemas GSM, CDMA, HSPA, EVDO, LTE, 5G1. La fecha de presentación es Septiembre 07 2022");
		 producto3.setCategoria(categoria3);
		
		 productoDAO.insert(producto3);
		 
		 Producto producto4 = new Producto();
		 producto4.setNombre("Cocina");
		 producto4.setPrecio(600.0);
		 producto4.setStock(5);
		 producto4.setCodeBarras("010103");
		 producto4.setUrl("https://th.bing.com/th/id/OIP.A90vvrmKvPlt_1kIE7b-VQHaHa?w=201&h=201&c=7&r=0&o=5&pid=1.7");
		 producto4.setDescripcion("la cocina es el sitio en el cual se prepara la comida. Puede ser el ambiente dedicado a esa tarea en un hogar o el espacio específico en un restaurante,");
		 producto4.setCategoria(categoria4);
		 
		 productoDAO.insert(producto4);
		 
		 Producto producto5 = new Producto();
		 producto5.setNombre("Zapatos ADODAS");
		 producto5.setPrecio(50.0);
		 producto5.setStock(50);
		 producto5.setCodeBarras("010204");
		 producto5.setUrl("https://th.bing.com/th/id/OIP.exvsscV6FWlr-TjFuvyp-QHaFu?w=242&h=186&c=7&r=0&o=5&pid=1.7");
		 producto5.setDescripcion("Zapato es un término que proviene de zabata, un vocablo de la lengua turca. Un zapato es una pieza de calzado que protege al pie, brindándole comodidad a la persona");
		 producto5.setCategoria(categoria5);
		 //producto5.setCarrito(carrito2);
		 productoDAO.insert(producto5);
		 
		 Producto producto6 = new Producto();
		 producto6.setNombre("Clean Code");
		 producto6.setPrecio(10.0);
		 producto6.setStock(10);
		 producto6.setCodeBarras("010205");
		 producto6.setDescripcion("Lo más sencillo posible: KISS KISS (del inglés keep it simple, stupid o “que sea sencillo, estúpido”) es uno de los principios del código limpio más antiguos, que ya utilizaba el ejército estadounidense en la década de 1960");
		 producto6.setUrl("https://th.bing.com/th/id/R.2aca70dfbdcef936cb99c63497be7dd3?rik=D04BpW2HTTDPCQ&riu=http%3a%2f%2fbilder.buecher.de%2fprodukte%2f23%2f23888%2f23888404z.jpg&ehk=LGJoVKaZqoLi7Tsi1CLbsBesvJaHELADizXLRyjdXlA%3d&risl=&pid=ImgRaw&r=0");
		 producto6.setCategoria(categoria6);
		 productoDAO.insert(producto6);
		 
		 
		 /*Cabecera cabecera = new Cabecera();
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
		 detalle.setCarrito(carrito);
		 List<Detalle> lDet = new ArrayList<>();
		 detalleDAO.insert(detalle);
		 */
		 
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
