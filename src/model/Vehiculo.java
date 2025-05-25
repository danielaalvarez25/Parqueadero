package model;
import model.enumeration.TipoVehiculoEnum;

public class Vehiculo {

	private String placa;
	private String color;
	private String modelo;
	private TipoVehiculoEnum tipoVehiculo;
	private double precio;
	private Membresia membresia;

	public Vehiculo(String placa, String color, String modelo, TipoVehiculoEnum tipoVehiculo, double precio,
			Membresia membresia) {
		super();
		this.placa = placa;
		this.color = color;
		this.modelo = modelo;
		this.tipoVehiculo = tipoVehiculo;
		this.precio = precio;
		this.membresia = membresia;
	}

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

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Membresia getMembresia() {
		return membresia;
	}

	public void setMembresia(Membresia membresia) {
		this.membresia = membresia;
	}

}
