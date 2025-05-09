package model;

import java.util.Date;

public class Registro {
	private Date ingreso;
	private Date salida;
	protected Registro(Date ingreso, Date salida) {
		super();
		this.ingreso = ingreso;
		this.salida = salida;
	}
	public Date getIngreso() {
		return ingreso;
	}
	public void setIngreso(Date ingreso) {
		this.ingreso = ingreso;
	}
	public Date getSalida() {
		return salida;
	}
	public void setSalida(Date salida) {
		this.salida = salida;
	}
	
	

}
