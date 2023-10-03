package models;

import java.sql.Date;
import java.sql.RowId;
import java.util.ArrayList;
import java.util.Vector;

public class Huesped {
	private int id;
	private String nombre;
	private String apellido;
	private Date fechaDeNacimiento;
	private String nacionalidad;
	private String telefono;
	private int reservaId;
	
	public Huesped(String nombre, String apellido, long fechaDeNacimiento, String nacionalidad, String telefono) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaDeNacimiento = new Date(fechaDeNacimiento);
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.reservaId = reservaId;
	}
	
	

	public Huesped(int id, String nombre, String apellido, Date fechaDeNacimiento, String nacionalidad, String telefono, int reservaId) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.reservaId = reservaId;
	}



	public Huesped(ArrayList<Object> datos) {
		this.id = (Integer) datos.get(0);
		this.nombre = (String) datos.get(1);
		this.apellido = (String) datos.get(2);
		this.fechaDeNacimiento = (Date) datos.get(3);
		this.nacionalidad = (String) datos.get(4);
		this.telefono = (String) datos.get(5);
		this.reservaId = (Integer) datos.get(6);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getReservaId() {
		return reservaId;
	}

	public void setReservaId(int reservaId) {
		this.reservaId = reservaId;
	}



	public Object[] getDatos() {
		Object[] datos =  {
			this.id,
			this.nombre,
			this.apellido,
			this.fechaDeNacimiento,
			this.nacionalidad,
			this.telefono,
			this.reservaId
		};
		
		return datos;
	}



	public int getId() {
		return this.id;
	}
	
	
	
}
