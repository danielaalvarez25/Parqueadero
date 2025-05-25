package model;

import java.util.Date;

import model.enumeration.PeriodoEnum;

public class Membresia {

	private Date fechaInicio;
	private Date fechaFin;
	private PeriodoEnum periodo;

	public Membresia(Date fechaInicio, Date fechaFin, PeriodoEnum periodo) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.periodo = periodo;
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

	public PeriodoEnum getPeriodo() {
		return periodo;
	}

	public void setPeriodo(PeriodoEnum periodo) {
		this.periodo = periodo;
	}

}
