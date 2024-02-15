package business;

import java.util.List;

import dao.ProductoDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import modelo.Producto;

@Stateless
public class GestionProductos {
	
	@Inject
	private ProductoDAO productoDAO;
	
	public void guardarProducto(Producto producto) {
		Producto pro = productoDAO.read(producto.getCodigo());
		if(pro != null) {
			productoDAO.update(producto);
		}else {
			productoDAO.insert(producto);
		}
		
	}
	
	public void actualizarProducto(Producto producto) throws Exception {
		Producto pro = productoDAO.read(producto.getCodigo());
		if(pro != null){
			productoDAO.update(producto);
		}else {
			throw new Exception("La categoria no existe jeje");
		}
	}
	
	public void removeProducto(int codigo) {
		productoDAO.remove(codigo);
	}
	
	public List<Producto> getProductos(){
		return productoDAO.getAll();
	}
	
	public List<Producto> getProCat(int num){
		return productoDAO.getProdCat(num);
	}
}
