package model;
/**
 * Clase que modela la factura
 */

public class Factura {
	/**
	 * ATRIBUTOS
	 */

	private Parqueadero parqueadero;
	private Registro registro;
/**
 * Metodo de constructor para factura
 * @param parqueadero
 * @param registro
 */
	public Factura(Parqueadero parqueadero, Registro registro) {
		super();
		this.parqueadero = parqueadero;
		this.registro = registro;
	}
/**
 * GETTERS AND SETTERS 
 * @return
 */
	public Parqueadero getParqueadero() {
		return parqueadero;
	}

	public void setParqueadero(Parqueadero parqueadero) {
		this.parqueadero = parqueadero;
	}

	public Registro getRegistro() {
		return registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

}
