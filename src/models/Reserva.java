package models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Vector;

public class Reserva {
	private int id;
	private Date fechaEntrada;
	private Date fechaSalida;
	private int costo;
	private String formaPago;
	
	public Reserva(Date fechaEntrada, Date fechaSalida, int costo, String formaPago) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.costo = costo;
		this.formaPago = formaPago;
	}

	public Reserva(long entrada, long salida, int costoReservacion, String formaPago) {
		this.fechaEntrada = new Date(entrada);
		this.fechaSalida = new Date(salida);
		costo = costoReservacion;
		this.formaPago = formaPago;
	}

	public Reserva(int id, Date fechaEntrada, Date fechaSalida, int costo, String formaPago) {
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.costo = costo;
		this.formaPago = formaPago;
	}

	public Reserva(ArrayList<Object> datos) {
		id = (int) datos.get(0);
		fechaEntrada = (Date) datos.get(1);
		fechaSalida = (Date) datos.get(2);
		costo = Integer.parseInt(datos.get(3) + "");
		formaPago = (String) datos.get(4);
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public Object[] getDatos() {
		Object[] datos = {
			this.id,
			this.fechaEntrada,
			this.fechaSalida,
			this.costo,
			this.formaPago
		};
		return datos;
	}

	public int getId() {
		return this.id;
	}
}
