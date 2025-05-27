package model;

import java.util.ArrayList;
/**
 * Clase que modela el cliente
 */

public class Parqueadero {
	/**
	 * ATRIBUTOS
	 */

	private String nombre;
	private String direccion;
	private String representante;
	private ArrayList<String> contactos;

	private ArrayList<Registro> registros;
	private ArrayList<Cliente> clientes;

	private double tarifaMoto;
	private double tarifaCarro;
	private double tarifaCamion;


	public Parqueadero() {
		super();
		this.contactos = new ArrayList<>();
		this.registros = new ArrayList<>();
		this.clientes = new ArrayList<>();
	}

	/**
	 * Metodo constructor para parquadero
	 * @param nombre
	 * @param direccion
	 * @param representante
	 */

	public Parqueadero(String nombre, String direccion, String representante) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.representante = representante;
		this.contactos = new ArrayList<>();
		this.registros = new ArrayList<>();
		this.clientes = new ArrayList<>();
	}
/**
 * GETTERS AND SETTERS
 * @return
 */
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getRepresentante() {
		return representante;
	}

	public void setRepresentante(String representante) {
		this.representante = representante;
	}

	public ArrayList<String> getContactos() {
		return contactos;
	}

	public void setContactos(ArrayList<String> contactos) {
		this.contactos = contactos;
	}

	public ArrayList<Registro> getRegistros() {
		return registros;
	}

	public void setRegistros(ArrayList<Registro> registros) {
		this.registros = registros;
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}

	public double getTarifaMoto() {
		return tarifaMoto;
	}

	public void setTarifaMoto(double tarifaMoto) {
		this.tarifaMoto = tarifaMoto;
	}

	public double getTarifaCarro() {
		return tarifaCarro;
	}

	public void setTarifaCarro(double tarifaCarro) {
		this.tarifaCarro = tarifaCarro;
	}

	public double getTarifaCamion() {
		return tarifaCamion;
	}

	public void setTarifaCamion(double tarifaCamion) {
		this.tarifaCamion = tarifaCamion;
	}

}
