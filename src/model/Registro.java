package model;

import java.util.Date;

/**
 * Clase que modela el registro
 */

public class Registro {
	/**
	 * Atributos
	 */

	private Date ingreso;
	private Date salida;
	private double totalAPagar;
	private Vehiculo vehiculo;

	/**
	 * Metodo constructor para registro
	 * 
	 * @param ingreso
	 * @param salida
	 * @param totalAPagar
	 * @param vehiculo
	 */

	public Registro(Date ingreso, Date salida, double totalAPagar, Vehiculo vehiculo) {
		super();
		this.ingreso = ingreso;
		this.salida = salida;
		this.totalAPagar = totalAPagar;
		this.vehiculo = vehiculo;
	}

	/**
	 * GETTERS AND SETTERS
	 * 
	 * @return
	 */

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

	public double getTotalAPagar() {
		return totalAPagar;
	}

	public void setTotalAPagar(double totalAPagar) {
		this.totalAPagar = totalAPagar;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

}
