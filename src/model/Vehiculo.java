package model;

public class Vehiculo {
	protected String placa;
	protected String color;
	protected String Modelo;
	protected Cliente cliente;
	private TipoVehiculoEnum tipoVehiculo;
	protected Vehiculo(String placa, String color, String modelo, Cliente cliente, TipoVehiculoEnum tipoVehiculo) {
		super();
		this.placa = placa;
		this.color = color;
		Modelo = modelo;
		this.cliente = cliente;
		this.tipoVehiculo = tipoVehiculo;
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
		return Modelo;
	}
	public void setModelo(String modelo) {
		Modelo = modelo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public TipoVehiculoEnum getTipoVehiculo() {
		return tipoVehiculo;
	}
	public void setTipoVehiculo(TipoVehiculoEnum tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

}


	