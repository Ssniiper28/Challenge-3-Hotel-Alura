package utils;

import java.sql.Connection;
import java.sql.SQLException;

import dao.HuespedDao;
import dao.ReservaDao;
import models.Huesped;
import models.Reserva;

public class RegistrarReserva {
	
	public static Reserva reservacion = null;
	public static Huesped huesped = null;
	
	public static boolean registrarReservacion() {
		ConnectionFactory factory = new ConnectionFactory();
		final Connection con = factory.getConnection();
		
		try (con) {
			con.setAutoCommit(false);
			HuespedDao huespedDao = new HuespedDao(con);
			ReservaDao reservaDao = new ReservaDao(con);
	
			if (reservacion != null && huesped != null) {
				int id = reservaDao.registrar(reservacion);
				
				if (id != 0) {
					huesped.setReservaId(id);
					huespedDao.registrar(huesped);
					
					try {
						con.commit();
						System.out.println("Reserva del " 
								+ reservacion.getFechaEntrada() 
								+ " al " + reservacion.getFechaSalida() 
								+ " con precio de " + reservacion.getCosto()
								+ " a nombre de: " + huesped.getNombre() + " creada");
						return true;
					} catch (SQLException e) {
						//con.rollback();
						//e.printStackTrace();
						return false;
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return false;
		
	}

	public static void setReserva(Reserva reserva) {
		reservacion = reserva;
	}
	
	public static void setHuesped(Huesped huesped) {
		RegistrarReserva.huesped = huesped;
	}	
}
	
