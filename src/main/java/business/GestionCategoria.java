package business;

import java.util.List;

import dao.CategoriaDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import modelo.Categoria;

@Stateless
public class GestionCategoria {
	
	@Inject
	private CategoriaDAO categoriaDAO;
	
	public void guardarCategoria(Categoria categoria) {
		Categoria cat = categoriaDAO.read(categoria.getCodigo());
		if(cat != null) {
			categoriaDAO.update(categoria);
		}else {
			categoriaDAO.insert(categoria);
		}
		
	}
	
	public void actualizarCategoria(Categoria categoria) throws Exception {
		Categoria cat = categoriaDAO.read(categoria.getCodigo());
		if(cat != null){
			categoriaDAO.update(categoria);
		}else {
			throw new Exception("La categoria no existe jeje");
		}
	}
	
	public void removeCategoria(int codigo) {
		categoriaDAO.remove(codigo);
	}
	
	public List<Categoria> getCategorias(){
		return categoriaDAO.getAll();
		
	}
}
