package model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Parqueadero {
	
	private String nombre;
	private String direccion;
	private String representante;
	private ArrayList<String> contactos;
	
	private ArrayList<Registro> registros;
	private ArrayList<Cliente> clientes;
	
	public Parqueadero() {
		super();
	}

	public Parqueadero(String nombre, String direccion, String representante) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.representante = representante;
		this.contactos = new ArrayList<String>();
		this.registros = new ArrayList<Registro>();
		this.clientes = new ArrayList<Cliente>();
	}

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

	
	
}
