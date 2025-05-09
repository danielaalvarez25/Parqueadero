package model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Cliente {
	private String nombre;
	private String cedula;
	private String telefono;
	private String correo;
	private ArrayList<Vehiculo> vehiculos;
	protected Cliente(String nombre, String cedula, String telefono, String correo, ArrayList<Vehiculo> vehiculos) {
		super();
		this.nombre = nombre;
		this.cedula = cedula;
		this.telefono = telefono;
		this.correo = correo;
		this.vehiculos = vehiculos;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public ArrayList<Vehiculo> getVehiculos() {
		return vehiculos;
	}
	public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}
	
	 public void agregarVehiculo(Vehiculo vehiculo) {
		vehiculos.add(vehiculo);
		}
	 

}
