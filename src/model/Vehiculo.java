package model;
import model.enumeration.TipoVehiculoEnum;
/**
 * Clase que modela el vehiculo
 */
public class Vehiculo {
/**
 * Atributos
 */
	private String placa;
	private String color;
	private String modelo;
	private TipoVehiculoEnum tipoVehiculo;
	private Membresia membresia;
/**
 * Metodo constructor para vehiculo
 * @param placa
 * @param color
 * @param modelo
 * @param tipoVehiculo
 * @param membresia
 */
	public Vehiculo(String placa, String color, String modelo, TipoVehiculoEnum tipoVehiculo, Membresia membresia) {
		super();
		this.placa = placa;
		this.color = color;
		this.modelo = modelo;
		this.tipoVehiculo = tipoVehiculo;
		this.membresia = membresia;
	}
/**
 * GETTERS AND SETTERS
 * @return
 */
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public TipoVehiculoEnum getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculoEnum tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public Membresia getMembresia() {
		return membresia;
	}

	public void setMembresia(Membresia membresia) {
		this.membresia = membresia;
	}

}
