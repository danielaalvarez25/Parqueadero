package model;

public class Tarifa {

	private double precioHora;
	private double precioMes;
	private double precioAnio;
	protected Tarifa(double precioHora, double precioMes, double precioAnio) {
		super();
		this.precioHora = precioHora;
		this.precioMes = precioMes;
		this.precioAnio = precioAnio;
	}
	public double getPrecioHora() {
		return precioHora;
	}
	public void setPrecioHora(double precioHora) {
		this.precioHora = precioHora;
	}
	public double getPrecioMes() {
		return precioMes;
	}
	public void setPrecioMes(double precioMes) {
		this.precioMes = precioMes;
	}
	public double getPrecioAnio() {
		return precioAnio;
	}
	public void setPrecioAnio(double precioAnio) {
		this.precioAnio = precioAnio;
	}
	
	

}
