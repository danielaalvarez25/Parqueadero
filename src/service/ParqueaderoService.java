package service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.time.LocalDate;
import javax.swing.JOptionPane;

import model.Cliente;
import model.Membresia;
import model.Parqueadero;
import model.Registro;
import model.Vehiculo;
import model.enumeration.PeriodoEnum;
import model.enumeration.TipoVehiculoEnum;

/**
 * @author Daniela Alvarez Clase que define las operaciones del parqueadero
 */
public class ParqueaderoService {
	/**
	 * Atributos
	 */

	private Parqueadero parqueadero;
	private int cupoMotos;
	private int cupoCarros;
	private int cupoCamiones;

	public ParqueaderoService() {
		super();
		this.parqueadero = new Parqueadero();
	}

	/**
	 * Metodo para configurar los datos del parqueadero
	 * 
	 * @param nombre        el nombre del parqueadero
	 * @param direccion     del parqueadero
	 * @param representante del parqueadero
	 */
	public void configurarDatosParqueadero(String nombre, String direccion, String representante) {
		parqueadero.setNombre(nombre);
		parqueadero.setDireccion(direccion);
		parqueadero.setRepresentante(representante);
		JOptionPane.showMessageDialog(null, "Datos del parqueadero actualizados.");
	}

	/**
	 * Metodo para configurar los cupos del parqueadero
	 * 
	 * @param motos
	 * @param carros
	 * @param camiones
	 */

	public void configurarCupos(int motos, int carros, int camiones) {
		this.cupoMotos = motos;
		this.cupoCarros = carros;
		this.cupoCamiones = camiones;
	}

	/**
	 * Metodo para configurar tarifas del parqueadero
	 * 
	 * @param tarifaMoto
	 * @param tarifaCarro
	 * @param tarifaCamion
	 */

	public void configurarTarifas(double tarifaMoto, double tarifaCarro, double tarifaCamion) {
		parqueadero.setTarifaMoto(tarifaMoto);
		parqueadero.setTarifaCarro(tarifaCarro);
		parqueadero.setTarifaCamion(tarifaCamion);
	}

	/**
	 * metodo para comprobar si hay espacios disponibles
	 * 
	 * @param tipoVehiculo
	 * @return  Retorna true si hay al menos un espacio disponible; false si no hay espacio libre (ya sea porque está ocupado o reservado).
	 */

	public boolean hayEspacioDisponible(TipoVehiculoEnum tipo) {
	    int cupoTotal;
	    int espaciosOcupados = 0;
	    int espaciosPermanentesOcupados = 0;
	    Date ahora = new Date();

	    switch (tipo) {
	        case MOTO:
	            cupoTotal = cupoMotos;
	            break;
	        case CARRO:
	            cupoTotal = cupoCarros;
	            break;
	        case CAMION:
	            cupoTotal = cupoCamiones;
	            break;
	        default:
	            return false;
	    }

	    for (Registro r : parqueadero.getRegistros()) {
	        if (r.getVehiculo().getTipoVehiculo() == tipo && r.getSalida() == null) {
	            espaciosOcupados++;
	        }
	    }

	   
	    for (Cliente c : parqueadero.getClientes()) {
	        for (Vehiculo v : c.getVehiculos()) {
	            if (v.getTipoVehiculo() == tipo) {
	                Membresia m = v.getMembresia();
	                if (m != null && ahora.after(m.getFechaInicio()) && ahora.before(m.getFechaFin())) {
	                    espaciosPermanentesOcupados++;
	                }
	            }
	        }
	    }

	    
	    int espaciosDisponibles = cupoTotal - espaciosOcupados - espaciosPermanentesOcupados;

	    return espaciosDisponibles > 0;
	}

	/**
	 * Metodo para contar vehiculos por tipo
	 * 
	 * @param tipo
	 * @return Devuelve el número total de vehículos que ocupan espacio para ese tipo, ya sea por estar adentro o por membresía activa.


	 */

	private int contarVehiculosPorTipo(TipoVehiculoEnum tipo) {
		int contador = 0;
		Date hoy = new Date();

		for (Registro r : parqueadero.getRegistros()) {
			Vehiculo v = r.getVehiculo();
			Membresia m = v.getMembresia();

			boolean estaAdentro = r.getSalida() == null;
			boolean tieneMembresiaActiva = m != null && hoy.before(m.getFechaFin());

			if (v.getTipoVehiculo() == tipo && (estaAdentro || tieneMembresiaActiva)) {
				contador++;
			}
		}

		return contador;
	}

	/**
	 * Metodo para registar un cliente en el parqueadero
	 * 
	 * @param cliente
	 */

	public void registrarCliente(Cliente cliente) {
		for (Cliente c : parqueadero.getClientes()) {
			if (c.getCedula().equalsIgnoreCase(cliente.getCedula())) {
				JOptionPane.showMessageDialog(null, "Ya existe el cliente.");
				return;
			}
		}
		parqueadero.getClientes().add(cliente);
		JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente.");
	}

	/**
	 * Metodo para eliminar un cliente del parqueadero
	 * 
	 * @param cliente
	 * @return true si el cliente fue eliminado exitosamente
	 */
	public boolean eliminarCliente(String cedula) {
	    ArrayList<Cliente> clientes = parqueadero.getClientes();
	    for (int i = 0; i < clientes.size(); i++) {
	        if (clientes.get(i).getCedula().equalsIgnoreCase(cedula)) {
	            clientes.remove(i);
	            return true;
	        }
	    }
	    return false;
	}



	/**
	 * Metodo para buscar un cliente del parqueadero
	 * 
	 * @param busqueda
	 * @return el cliente encontrado
	 */
	public Cliente buscarCliente(String busqueda) {
		for (Cliente c : parqueadero.getClientes()) {
			if (c.getNombre().contains(busqueda) || c.getCedula().contains(busqueda)
					|| c.getTelefono().contains(busqueda)) {
				return c;
			}
		}

		return null;
	}

	/**
	 * metodo para actualizar los datos de un cliente
	 * 
	 * @param clienteActualizado
	 */

	public void actualizarCliente(Cliente clienteActualizado) {
		for (Cliente c : parqueadero.getClientes()) {
			if (c.getCedula().equals(clienteActualizado.getCedula())) {
				c.setNombre(clienteActualizado.getNombre());
				c.setTelefono(clienteActualizado.getTelefono());
				c.setCorreo(clienteActualizado.getCorreo());
				break;
			}
		}
	}

	/**
	 * Metodo para obtener la fecha actual
	 * 
	 * @return
	 */

	public Date obtenerFechaActual() {
		LocalDateTime ahora = LocalDateTime.now();
		return Date.from(ahora.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * Metodo para obtener vehiculos por cliente
	 * 
	 * @param cliente
	 * @return lista de vehiculos que pertenecen al cliente
	 */

	public ArrayList<String> obtenerVehiculosXCliente(Cliente cliente) {
		ArrayList<String> vehiculos = new ArrayList<>();
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

	/**
	 * Metodo para registrar vehiculo Se valida que no exista el vehiculo antes de
	 * agregarlo Valida si desea adquirir memebresia y se calcula el tiempo
	 * 
	 * @param vehiculo
	 * @param cliente
	 * @return true si se guardo false si no
	 */
	public boolean registrarVehiculo(Vehiculo vehiculo, Cliente cliente) {
		for (Vehiculo v : cliente.getVehiculos()) {
			if (v.getPlaca().equalsIgnoreCase(vehiculo.getPlaca())) {
				JOptionPane.showMessageDialog(null, "Ya existe el vehículo.");
				return false;
			}
		}

		int opcion = JOptionPane.showConfirmDialog(null, "¿Desea agregar membresía al vehículo?", "Membresía",
				JOptionPane.YES_NO_OPTION);

		if (opcion == JOptionPane.YES_OPTION) {
			Membresia membresia = crearMembresiaConPeriodoSeleccionado();
			if (membresia != null) {
				vehiculo.setMembresia(membresia);
				JOptionPane.showMessageDialog(null, "Membresía asignada correctamente.");
			} else {
				JOptionPane.showMessageDialog(null, "No se asignó membresía.");
			}
		}

		cliente.getVehiculos().add(vehiculo);
		return true;
	}
	/**
	 * metodo para para permitir al usuario elegir un periodo de membresía (mensual, trimestral o anual) 
	 * @return  una instancia de Membresia con fechas calculadas.
	 */

	private Membresia crearMembresiaConPeriodoSeleccionado() {
		String[] opciones = { "MENSUAL", "TRIMESTRAL", "ANUAL" };
		String periodoStr = (String) JOptionPane.showInputDialog(null, "Seleccione periodo de membresía:",
				"Periodo Membresía", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

		if (periodoStr == null) {
			
			return null;
		}

		PeriodoEnum periodo;
		try {
			periodo = PeriodoEnum.valueOf(periodoStr);
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "Periodo inválido.");
			return null;
		}

		LocalDate fechaInicioLD = LocalDate.now();
		LocalDate fechaFinLD;

		switch (periodo) {
		case MENSUAL:
			fechaFinLD = fechaInicioLD.plusMonths(1);
			break;
		case TRIMESTRAL:
			fechaFinLD = fechaInicioLD.plusMonths(3);
			break;
		case ANUAL:
			fechaFinLD = fechaInicioLD.plusYears(1);
			break;
		default:
			JOptionPane.showMessageDialog(null, "Periodo no soportado.");
			return null;
		}

		Date fechaInicio = Date.from(fechaInicioLD.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date fechaFin = Date.from(fechaFinLD.atStartOfDay(ZoneId.systemDefault()).toInstant());

		return new Membresia(fechaInicio, fechaFin, periodo);
	}

	/**
	 * metodo para buscar vehiculo
	 * 
	 * @param placa
	 * @param tipoVehiculo
	 * @param cliente
	 * @return
	 */
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

	/**
	 * Metodo para actualizar vehiculo
	 * 
	 * @param cliente
	 * @param vehiculo
	 */

	public void actualizarVehiculo(Cliente cliente, Vehiculo vehiculo) {
		for (Vehiculo v : cliente.getVehiculos()) {
			if (v.getPlaca().equals(vehiculo.getPlaca())) {
				v.setColor(vehiculo.getColor());
				v.setModelo(vehiculo.getModelo());
				v.setPlaca(vehiculo.getPlaca());
			}
		}
	}

	/**
	 * Metodo para actualizar el nombre del parqueadero
	 * 
	 * @param nombre
	 */

	public void actualizarNombreParqueadero(String nombre) {

		this.parqueadero.setNombre(nombre);
	}

	/**
	 * Metodo para actualizar el representante del parqueadero
	 * 
	 * @param representante
	 */

	public void actualizarRepresentanteParqueadero(String representante) {

		this.parqueadero.setRepresentante(representante);
	}

	/**
	 * Metodo para actualizar la direccion del parqueadero
	 * 
	 * @param direccion
	 */

	public void actualizarDireccionParqueadero(String direccion) {

		this.parqueadero.setDireccion(direccion);

	}

	/**
	 * Metodo para agregar nuevo contacto
	 * 
	 * @param contacto
	 */

	public void agregarNuevoContacto(String contacto) {
		boolean existe = false;
		for (String p : parqueadero.getContactos()) {
			if (p.contains(contacto)) {
				JOptionPane.showMessageDialog(null, "ya existe el contacto");
				existe = true;
			}
		}
		if (!existe) {
			parqueadero.getContactos().add(contacto);
		}
	}

	/**
	 * Metodo para eliminar contacto
	 * 
	 * @param contacto
	 * @return
	 */
	public boolean eliminarContacto(String contacto) {
		for (String p : parqueadero.getContactos()) {
			if (p.equalsIgnoreCase(contacto)) {
				parqueadero.getContactos().remove(p);
			}
			return true;
		}
		return false;
	}

	/**
	 * Metodo para actualizar contacto del parqueadero
	 * 
	 * @param representante
	 */

	public void actualizarContactoParqueadero(String representante) {

		this.parqueadero.setRepresentante(representante);
	}

	/**
	 * Metodo para registar ingreso de un vehiculo
	 * 
	 * @param vehiculo
	 * @return
	 */

	public boolean registrarIngresoVehiculo(Vehiculo vehiculo) {
		
		Membresia m = vehiculo.getMembresia();
		if (m != null && m.estaActiva()) {
			JOptionPane.showMessageDialog(null, "Vehículo con memebresia activa. Puede ingresar.(NO NECESITA FACTURAAAA)");
	        return false;
		}
	    
	    if (!hayEspacioDisponible(vehiculo.getTipoVehiculo())) {
	        JOptionPane.showMessageDialog(null, "No hay espacio disponible para este tipo de vehículo.");
	        return false;
	    }

	    for (Registro r : parqueadero.getRegistros()) {
	        if (r.getVehiculo().getPlaca().equalsIgnoreCase(vehiculo.getPlaca()) && r.getSalida() == null) {
	            JOptionPane.showMessageDialog(null, "Este vehículo ya está ingresado en el parqueadero.");
	            return false;
	        }
	    }
	    
	    Registro nuevoRegistro = new Registro(new Date(), null, 0.0, vehiculo);
	    parqueadero.getRegistros().add(nuevoRegistro);
	    JOptionPane.showMessageDialog(null, "Ingreso registrado correctamente.");
	    return true;
	}


	/**
	 * Metodo para calcular el total a pagar
	 * 
	 * @param ingreso
	 * @param salida
	 * @param tipo
	 * @return
	 */

	public double calcularTotalAPagar(Date ingreso, Date salida, TipoVehiculoEnum tipo) {
		long diferenciaMillis = salida.getTime() - ingreso.getTime();
		double horas = diferenciaMillis / (1000.0 * 60 * 60);

		
		switch (tipo) {
		case MOTO:
			return horas * parqueadero.getTarifaMoto();
		case CARRO:
			return horas * parqueadero.getTarifaCarro();
		case CAMION:
			return horas * parqueadero.getTarifaCamion();
		default:
			return 1000;
		}
	}

	/**
	 * Buscar vehiculo ingresado por placa
	 * 
	 * @param placa
	 * @return un Registro si existe a la placa indicada Null si no hay registro
	 */
	public Registro buscarRegistroActivoPorPlaca(String placa) {
		for (Registro registro : parqueadero.getRegistros()) {
			if (registro.getVehiculo().getPlaca().equalsIgnoreCase(placa) && registro.getSalida() == null) {
				return registro;
			}
		}
		return null; // no se encontró registro activo para esa placa
	}

	/**
	 * Metodo para registar la salida de un vehiculo
	 * 
	 * @param registro
	 */
	public void registrarSalidaVehiculo(String placa) {
		Registro registro = buscarRegistroActivoPorPlaca(placa);
		if (registro != null) {
			Date salida = new Date();
			registro.setSalida(salida);

			Vehiculo v = registro.getVehiculo();
			Membresia m = v.getMembresia();

			if (m != null && salida.before(m.getFechaFin())) {
				registro.setTotalAPagar(0);
				JOptionPane.showMessageDialog(null, "Vehículo con membresía activa. No debe pagar.");
			} else {
				double total = calcularTotalAPagar(registro.getIngreso(), salida, v.getTipoVehiculo());
				registro.setTotalAPagar(total);
				JOptionPane.showMessageDialog(null, "Total a pagar: $" + total);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Vehículo con placa: " + placa + " No esta ingresado.");
		}
	}

	public Parqueadero getParqueadero() {
		return parqueadero;
	}

	public void setParqueadero(Parqueadero parqueadero) {
		this.parqueadero = parqueadero;
	}

}
