package model;

import java.util.Date;

public class Membresia {
	private Date fechaInicio;
	private Date fechaFin;
	private String tipo;
	
	public Membresia(Date fechaInicio, Date fechaFin, String tipo) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.tipo = tipo;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	 
}
