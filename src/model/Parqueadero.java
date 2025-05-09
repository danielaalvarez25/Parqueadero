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
	private ArrayList<Pago> pagos;
	private ArrayList<Vehiculo> vehiculos;
	private ArrayList<Membresia> membresias;

	protected Parqueadero(String nombre, String direccion, String representante, ArrayList<String> contactos,
			ArrayList<Registro> registros, ArrayList<Cliente> clientes, ArrayList<Pago> pagos,
			ArrayList<Vehiculo> vehiculos, ArrayList<Membresia> membresias) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.representante = representante;
		this.contactos = contactos;
		this.registros = registros;
		this.clientes = clientes;
		this.pagos = pagos;
		this.vehiculos = vehiculos;
		this.membresias = membresias;
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

	public ArrayList<Pago> getPagos() {
		return pagos;
	}

	public void setPagos(ArrayList<Pago> pagos) {
		this.pagos = pagos;
	}

	public ArrayList<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public ArrayList<Membresia> getMembresias() {
		return membresias;
	}

	public void setMembresias(ArrayList<Membresia> membresias) {
		this.membresias = membresias;
	}

	public void registarCliente(Cliente cliente) {
		for (Cliente c : clientes) {
			if (c.getCedula().equalsIgnoreCase(cliente.getCedula())) {
				JOptionPane.showMessageDialog(null, "ya existe");
			} else {
				clientes.add(cliente);
			}

		}
	}

	public boolean eliminarCliente(Cliente cliente) {
		for (Cliente c : clientes) {
			if (c.getCedula().equalsIgnoreCase(cliente.getCedula())) {
				clientes.remove(c);
				return true;
			}
		}
		return false;
	}

	public Cliente buscarCliente(Cliente cliente) {
		for (Cliente c : clientes) {
			if (c.getCedula().equalsIgnoreCase(cliente.getCedula())) {
				return c;
			}
		}

		return null;
	}

	public void actualizarCliente(Cliente cliente) {
		for (Cliente c : clientes) {
			if (c.getCedula().equals(cliente.getCedula())) {

				cliente.setNombre(cliente.getNombre());
				cliente.setTelefono(cliente.getTelefono());
				cliente.setCorreo(cliente.getCorreo());
				return;
			}
		}
	}

	public void registarVehiculo(Vehiculo vehiculo, Cliente cliente) {
		for (Vehiculo v : vehiculos) {
			if (v.getPlaca().equalsIgnoreCase(vehiculo.getPlaca())) {
				JOptionPane.showMessageDialog(null, "ya existe el vehiculo");
			} else {
				cliente.agregarVehiculo(vehiculo);
				vehiculos.add(vehiculo);
			}

		}
	}

	public ArrayList<Vehiculo> buscarVehiculo(String placa, TipoVehiculoEnum tipoVehiculo, Cliente cliente) {
		ArrayList<Vehiculo> vehiculosEncontrados = new ArrayList<>();
		for (Vehiculo v : vehiculos) {
			boolean match = true;
			if (placa != null && !placa.isEmpty() && !v.getPlaca().equalsIgnoreCase(placa)) {
				match = false;
			}
			if (tipoVehiculo != null && v.getTipoVehiculo() != tipoVehiculo) {
				match = false;
			}
			if (cliente != null && !cliente.getVehiculos().contains(v)) {
				match = false;
			}
			if (match) {
				vehiculosEncontrados.add(v);
			}
		}
		return vehiculosEncontrados;
	}

	public void actualizarVehiculo(Vehiculo vehiculo) {
		for (Vehiculo v : vehiculos) {
			if (v.getPlaca().equals(vehiculo.getPlaca())) {

				vehiculo.setColor(vehiculo.getColor());
				vehiculo.setModelo(vehiculo.getModelo());

				return;
			}
		}
	}

	public void actualizarDatos(String nombre, String direccion, String representante, ArrayList<String> contactos) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.representante = representante;
		this.contactos = contactos;
	}

}
