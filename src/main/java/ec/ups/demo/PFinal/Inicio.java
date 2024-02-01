package ec.ups.demo.PFinal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@Singleton
@Startup
public class Inicio {
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
	        
	        try {
	            obtenerPersonas();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    private void obtenerPersonas() throws SQLException {
	        String query = "SELECT id, nombre, apellido, edad, direccion, email FROM Persona";
	        try (Connection connection = dataSource.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(query);
	             ResultSet resultSet = preparedStatement.executeQuery()) {

	            while (resultSet.next()) {
	                int id = resultSet.getInt("id");
	                String nombre = resultSet.getString("nombre");
	                String apellido = resultSet.getString("apellido");
	                int edad = resultSet.getInt("edad");
	                String direccion = resultSet.getString("direccion");
	                String email = resultSet.getString("email");

	                // Aquí puedes procesar o imprimir los datos de cada persona
	                System.out.printf("ID: %d, Nombre: %s, Apellido: %s, Edad: %d, Dirección: %s, Email: %s%n",
	                                  id, nombre, apellido, edad, direccion, email);
	            }
	        }
	    }
	    
	    
}

