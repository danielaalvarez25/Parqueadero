package model;

import java.util.Date;

import model.enumeration.PeriodoEnum;
/**
 * Clase que modela la membresia 
 */

public class Membresia {
/**
 * ATRIBUTOS
 */
	private Date fechaInicio;
	private Date fechaFin;
	private PeriodoEnum periodo;
	/**
	 * Metodo constructor para cliente
	 * @param fechaInicio
	 * @param fechaFin
	 * @param periodo
	 */

	public Membresia(Date fechaInicio, Date fechaFin, PeriodoEnum periodo) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.periodo = periodo;
	}
	
	public boolean estaActiva() {
	    Date ahora = new Date();
	    return ahora.compareTo(fechaInicio) >= 0 && ahora.compareTo(fechaFin) <= 0;
	}

/**
 * GETTERS AND SETTERS
 * @return
 */
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

	public PeriodoEnum getPeriodo() {
		return periodo;
	}

	public void setPeriodo(PeriodoEnum periodo) {
		this.periodo = periodo;
	}

}
