package business;

import java.util.List;

import dao.PersonaDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import modelo.Persona;


@Stateless
public class GestionPersonas {
	@Inject
	private PersonaDAO personaDAO;
	
	public void guardarPersonas(Persona persona) {
		Persona per = personaDAO.read(persona.getCodigo());
		if(per != null) {
			personaDAO.update(persona);
		}else {
			personaDAO.insert(persona);
		}
		
	}
	
	public void actualizarPersona(Persona persona) throws Exception {
		Persona per = personaDAO.read(persona.getCodigo());
		if(per != null){
			personaDAO.update(persona);
		}else {
			throw new Exception("La persona no existe jeje");
		}
	}
	
	public void removePersona(int codigo) {
		personaDAO.remove(codigo);
	}
	
	public List<Persona> getPersonas(){
		return personaDAO.getAll();
		
	}
	
	public Persona autenticarPersona(String correo, String contra) throws Exception{
		Persona persona = personaDAO.obtenerPorCorreo(correo);
		if (persona != null && persona.getContra().equals(contra)) {
			return persona;
		}else {
			throw new Exception("Las credenciales no coinciden");
		}
	}
	
}
