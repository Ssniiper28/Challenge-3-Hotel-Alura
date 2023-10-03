package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Huesped;
import models.Reserva;
import utils.ConnectionFactory;

public class HuespedDao {
	
	final private Connection con;
	
	public HuespedDao(Connection con) {
		this.con = con;
	}
	
	public void registrar(Huesped huesped) {
		try {
			final PreparedStatement statement = con.prepareStatement("INSERT INTO huespedes("
				+ "nombre, "
				+ "apellido, "
				+ "fechaNacimiento, "
				+ "nacionalidad, "
				+ "telefono, "
				+ "idReserva) "
				+ "VALUES (?, ?, ?, ?, ?, ?)");
		
			try (statement){
				
				statement.setString(1, huesped.getNombre());
				statement.setString(2, huesped.getApellido());
				statement.setDate(3, huesped.getFechaDeNacimiento());
				statement.setString(4, huesped.getNacionalidad());
				statement.setString(5, huesped.getTelefono());
				statement.setInt(6, huesped.getReservaId());
				
				statement.execute();
				
			} catch (Exception e) {
				
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}

	public List<Huesped> getListaReservas(String termino) {
		int idReserva;
		try {
			idReserva = Integer.parseInt(termino);
		} catch (NumberFormatException e) {
			idReserva = 0;
		}
		
		
		try {
			PreparedStatement statement = con.prepareStatement("SELECT * FROM huespedes WHERE apellido = ? OR idReserva = ?");
			
			statement.setString(1, termino);
			
			statement.setInt(2, idReserva);
			
			ResultSet resultSet = statement.executeQuery();
			
			List<Huesped> resultado = new ArrayList<>();
			
			while(resultSet.next()){
				resultado.add(new Huesped(
						resultSet.getInt("id"),
						resultSet.getString("nombre"),
						resultSet.getString("apellido"),
						resultSet.getDate("fechaNacimiento"),
						resultSet.getString("nacionalidad"),
						resultSet.getString("telefono"),
						resultSet.getInt("idReserva")
						));
			}
			
			
			return resultado;
			
		} catch (Exception e) {}
		
		return null;
	}
	
	public List<Huesped> getListaReservas() {
				
		try {
			PreparedStatement statement = con.prepareStatement("SELECT * FROM huespedes");
			
			ResultSet resultSet = statement.executeQuery();
			
			List<Huesped> resultado = new ArrayList<>();
			
			while(resultSet.next()){
				resultado.add(new Huesped(
						resultSet.getInt("id"),
						resultSet.getString("nombre"),
						resultSet.getString("apellido"),
						resultSet.getDate("fechaNacimiento"),
						resultSet.getString("nacionalidad"),
						resultSet.getString("telefono"),
						resultSet.getInt("idReserva")
						));
			}
			
			
			return resultado;
			
		} catch (Exception e) {}
		
		return null;
	}


	public boolean editar(Huesped huesped) {
		try{
			PreparedStatement statement = con.prepareStatement("UPDATE huespedes SET "
					+ "nombre = ?, "
					+ "apellido = ?, "
					+ "fechaNacimiento = ?, "
					+ "nacionalidad = ?, "
					+ "telefono = ?, "
					+ "idReserva = ? "
					+ "WHERE id = ?");
			
			statement.setString(1, huesped.getNombre());
			statement.setString(2, huesped.getApellido());
			statement.setDate(3, huesped.getFechaDeNacimiento());
			statement.setString(4, huesped.getNacionalidad());
			statement.setString(5, huesped.getTelefono());
			statement.setInt(6, huesped.getReservaId());
			statement.setInt(7, huesped.getId());
			
			statement.execute();
			
			return true;
			
		} catch (SQLException e) {
			//return false;
			throw new RuntimeException(e);
		}
	}

	public void eliminar(int id) {
		try{
			
			PreparedStatement statement1 = con.prepareStatement(
					"SELECT idReserva FROM huespedes WHERE id = ?", 
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			
			statement1.setInt(1, id);
			
			ResultSet result = statement1.executeQuery();
			
			result.next();
			
			int reservaId = result.getInt(1);
			
			PreparedStatement statement  = con.prepareStatement("DELETE FROM huespedes WHERE id = ?");
			statement.setInt(1, id);
			statement.execute();
			
			PreparedStatement statement2  = con.prepareStatement("DELETE FROM reservaciones WHERE id = ?");
			statement2.setInt(1, reservaId);
			statement2.execute();
			
		} catch (SQLException e) {
			//throw new RuntimeException(e);
		}
		
	}
}
