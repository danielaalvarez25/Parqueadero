package service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import model.Cliente;
import model.Parqueadero;
import model.Vehiculo;
import model.enumeration.TipoVehiculoEnum;

public class ParqueaderoService {

	private Parqueadero parqueadero;

	public ParqueaderoService() {
		super();
		this.parqueadero = new Parqueadero();
	}

	public void registarCliente(Cliente cliente) {
		for (Cliente c : parqueadero.getClientes()) {
			if (c.getCedula().equalsIgnoreCase(cliente.getCedula())) {
				JOptionPane.showMessageDialog(null, "ya existe");
			} else {
				parqueadero.getClientes().add(cliente);
			}

		}
	}

	public boolean eliminarCliente(Cliente cliente) {
		for (Cliente c : parqueadero.getClientes()) {
			if (c.getCedula().equalsIgnoreCase(cliente.getCedula())) {
				parqueadero.getClientes().remove(c);
				return true;
			}
		}
		return false;
	}

	public Cliente buscarCliente(String busqueda) {
		for (Cliente c : parqueadero.getClientes()) {
			if (c.getNombre().contains(busqueda) || c.getCedula().contains(busqueda)
					|| c.getTelefono().contains(busqueda)) {
				return c;
			}
		}

		return null;
	}

	public void actualizarCliente(Cliente cliente) {
		for (Cliente c : parqueadero.getClientes()) {
			if (c.getCedula().equals(cliente.getCedula())) {
				cliente.setNombre(cliente.getNombre());
				cliente.setTelefono(cliente.getTelefono());
				cliente.setCorreo(cliente.getCorreo());
				return;
			}
		}
	}

	public Date obtenerFechaActual() {
		LocalDateTime ahora = LocalDateTime.now();
		return Date.from(ahora.atZone(ZoneId.systemDefault()).toInstant());
	}

	public ArrayList<String> obtenerVehiculosXCliente(Cliente cliente) {
		ArrayList<String> vehiculos = new ArrayList<String>();
		for (Vehiculo v : cliente.getVehiculos()) {
			String vehiculo = "PLACA: " + v.getPlaca();
			if (v.getMembresia() != null) {
				Date fechaActual = obtenerFechaActual();
				if (fechaActual.before(v.getMembresia().getFechaFin())) {
					vehiculo += " Membresia " + v.getMembresia().getPeriodo() + " vigente hasta: "
							+ v.getMembresia().getFechaFin().toString();
				} else {
					vehiculo += " Membresia vencida: " + v.getMembresia().getFechaFin().toString();
				}
			} else {
				vehiculo += " Sin membresia vigente.";
			}
			vehiculos.add(vehiculo);
		}
		return vehiculos;
	}

	public boolean registrarVehiculo(Vehiculo vehiculo, Cliente cliente) {
		for (Vehiculo v : cliente.getVehiculos()) {
			if (v.getPlaca().equalsIgnoreCase(vehiculo.getPlaca())) {
				JOptionPane.showMessageDialog(null, "ya existe el vehiculo");
			} else {
				cliente.agregarVehiculo(vehiculo);
				cliente.getVehiculos().add(vehiculo);
				return true;
			}
		}
		return false;
	}

	public ArrayList<Vehiculo> buscarVehiculo(String placa, TipoVehiculoEnum tipoVehiculo, Cliente cliente) {
		ArrayList<Vehiculo> vehiculosEncontrados = new ArrayList<>();
		for (Vehiculo v : cliente.getVehiculos()) {
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

	public void actualizarVehiculo(Cliente cliente, Vehiculo vehiculo) {
		for (Vehiculo v : cliente.getVehiculos()) {
			if (v.getPlaca().equals(vehiculo.getPlaca())) {
				vehiculo.setColor(vehiculo.getColor());
				vehiculo.setModelo(vehiculo.getModelo());
				vehiculo.setPlaca(vehiculo.getPlaca());
			}
		}
	}

	public void actualizarNombreParqueadero(String nombre) {

		this.parqueadero.setNombre(nombre);
	}

	public void actualizarRepresentanteParqueadero(String representante) {

		this.parqueadero.setRepresentante(representante);
	}

	public void actualizarDireccionParqueadero(String direccion) {

		this.parqueadero.setDireccion(direccion);

	}

	public void agregarNuevoContacto(String contacto) {
		for (String p : parqueadero.getContactos()) {
			if (p.contains(contacto)) {
				JOptionPane.showMessageDialog(null, "ya existe el contacto");
			} else {
				parqueadero.getContactos().add(contacto);
			}
		}

	}

	public boolean eliminarContacto(String contacto) {
		for(String p: parqueadero.getContactos()) {
			if(p.equalsIgnoreCase(contacto)){
				parqueadero.getContactos().remove(p);
			}
			return true;
		}
		return false;
	}

	public void actualizarContactoParqueadero(String representante) {

		this.parqueadero.setRepresentante(representante);;
	}
}
