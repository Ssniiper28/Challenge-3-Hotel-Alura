package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Reserva;

public class ReservaDao {
	
	final private Connection con;
	
	public ReservaDao(Connection con) {
		this.con = con;
	}

	
	public int registrar(Reserva reserva) {
		try{
			
			con.setAutoCommit(false);
			
			final PreparedStatement statement = con.prepareStatement("INSERT INTO reservaciones("
				+ "fechaEntrada, "
				+ "fechaSalida, "
				+ "valor, "
				+ "formaPago) "
				+ "VALUES (?, ?, ?, ?)", 
				Statement.RETURN_GENERATED_KEYS);
		
			try (statement){
				
				statement.setDate(1, reserva.getFechaEntrada());
				statement.setDate(2, reserva.getFechaSalida());
				statement.setInt(3, reserva.getCosto());
				statement.setString(4, reserva.getFormaPago());
				
				statement.execute();
				
				ResultSet result = statement.getGeneratedKeys();
				
				result.next();
				
				return result.getInt(1);

				
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public List<Reserva> getListaReservas(String termino) {
		int idReserva = 0;
		String ids = "(";
		try {
			
			idReserva = Integer.parseInt(termino);
			ids += "0)";
			
		} catch (NumberFormatException e) {
			
			try {
				PreparedStatement statement = con.prepareStatement("SELECT idReserva FROM huespedes WHERE apellido = ?");
				statement.setString(1, termino);
			
				ResultSet resultSet = statement.executeQuery();
				
				while (resultSet.next()) {
					ids += resultSet.getInt("idReserva") + ", ";
				}
				
				ids += "0)";
			} catch (Exception ex){
				throw new RuntimeException(ex);
			}
		}
		
		try {
			
			System.out.println(ids);
			
			String query = "SELECT * FROM reservaciones WHERE id IN " + ids + " OR id = ?";
			
			PreparedStatement statement = con.prepareStatement(query);
			
			statement.setInt(1, idReserva);

			ResultSet resultSet = statement.executeQuery();
			
			List<Reserva> resultado = new ArrayList<>();
			
			while(resultSet.next()){
				resultado.add(new Reserva(
						resultSet.getInt("id"),
						resultSet.getDate("fechaEntrada"), 
						resultSet.getDate("fechaSalida"), 
						resultSet.getInt("valor"), 
						resultSet.getString("formaPago")	
						));
			}
			
			return resultado;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		//return null;
	}
	
	public List<Reserva> getListaReservas() {
		
		try {
			PreparedStatement statement = con.prepareStatement("SELECT * FROM reservaciones");
			
			ResultSet resultSet = statement.executeQuery();
			
			List<Reserva> resultado = new ArrayList<>();
			
			while(resultSet.next()){
				resultado.add(new Reserva(
						resultSet.getInt("id"),
						resultSet.getDate("fechaEntrada"), 
						resultSet.getDate("fechaSalida"), 
						resultSet.getInt("valor"), 
						resultSet.getString("formaPago")	
						));
			}
			
			return resultado;
			
		} catch (Exception e) {
			
		}
		return null;
	}


	public boolean editar(Reserva reserva) {
		try{
			PreparedStatement statement = con.prepareStatement("UPDATE reservaciones SET "
					+ "fechaEntrada = ?, "
					+ "fechaSalida = ?, "
					+ "valor = ?, "
					+ "formaPago = ? "
					+ "WHERE id = ?");
			
			statement.setDate(1, reserva.getFechaEntrada());
			statement.setDate(2, reserva.getFechaSalida());
			statement.setInt(3, reserva.getCosto());
			statement.setString(4, reserva.getFormaPago());
			statement.setInt(5, reserva.getId());
			
			statement.execute();
			
			return true;
			
		} catch (SQLException e) {
			
			return false;
			//throw new RuntimeException(e);
		}
	}


	public void eliminar(int id) {
		try{
			
			PreparedStatement statement1 = con.prepareStatement(
					"SELECT id FROM huespedes WHERE idReserva = ?", 
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			
			statement1.setInt(1, id);
			
			ResultSet result = statement1.executeQuery();
			
			result.next();
			
			int huespedId = result.getInt(1);
			
			PreparedStatement statement  = con.prepareStatement("DELETE FROM huespedes WHERE id = ?");
			statement.setInt(1, huespedId);
			statement.execute();
			
			PreparedStatement statement2  = con.prepareStatement("DELETE FROM reservaciones WHERE id = ?");
			statement2.setInt(1, id);
			statement2.execute();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
