package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.Cliente;
import model.Factura;
import model.Membresia;
import model.Registro;
import model.Vehiculo;

public class FacturaService {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private ParqueaderoService parqueadero;

    public FacturaService(ParqueaderoService parqueadero) {
        this.parqueadero = parqueadero;
    }


    /**
     * metodo para generar factura para vehiculo temporal
     * @param factura
     * @return
     */
    public String generarFacturaTemporal(Factura factura) {
        Registro reg = factura.getRegistro();
        StringBuilder sb = new StringBuilder();
        sb.append("-------- FACTURA TEMPORAL --------\n");
        sb.append("Parqueadero: ").append(parqueadero.getParqueadero().getNombre()).append("\n");
        sb.append("Dirección: ").append(parqueadero.getParqueadero().getDireccion()).append("\n\n");
        sb.append("Placa: ").append(reg.getVehiculo().getPlaca()).append("\n");
        sb.append("Tipo: ").append(reg.getVehiculo().getTipoVehiculo()).append("\n");
        sb.append("Ingreso: ").append(sdf.format(reg.getIngreso())).append("\n");
        sb.append("Salida: ").append(sdf.format(reg.getSalida())).append("\n");
        sb.append("Total a pagar: $").append(reg.getTotalAPagar()).append("\n");
        return sb.toString();
    }
    /**
     * metodo para generar factura para vehiculo con membresia
     * @param factura
     * @return
     */

    // Generar factura para vehículo con membresía
    public String generarFacturaMembresia(Factura factura) {
        Vehiculo vehiculo = factura.getRegistro().getVehiculo();
        Membresia membresia = vehiculo.getMembresia();
        StringBuilder sb = new StringBuilder();
        sb.append("-------- FACTURA MEMBRESÍA --------\n");
        sb.append("Parqueadero: ").append(parqueadero.getParqueadero().getNombre()).append("\n");
        sb.append("Dirección: ").append(parqueadero.getParqueadero().getDireccion()).append("\n\n");
        sb.append("Placa: ").append(vehiculo.getPlaca()).append("\n");
        sb.append("Tipo: ").append(vehiculo.getTipoVehiculo()).append("\n");
        sb.append("Inicio membresía: ").append(sdf.format(membresia.getFechaInicio())).append("\n");
        sb.append("Fin membresía: ").append(sdf.format(membresia.getFechaFin())).append("\n");
        sb.append("Periodo: ").append(membresia.getPeriodo()).append("\n");
        return sb.toString();
    }
/**
 * Metodo para el historial de vehiculos por cliente
 * @param cliente
 * @return
 */
    // Historial de vehículos por cliente
    public ArrayList<String> historialVehiculosPorCliente(Cliente cliente) {
        ArrayList<String> historial = new ArrayList<>();
        for (Vehiculo v : cliente.getVehiculos()) {
            historial.add(v.getPlaca() + " - " + v.getTipoVehiculo());
        }
        return historial;
    }
    /**
     * Metodo para calcular ingresos entre fechas
     * @param desde
     * @param hasta
     * @return
     */

    // Ingresos entre fechas
    public double calcularIngresos(Date desde, Date hasta) {
        double total = 0;
        for (Registro r : parqueadero.getParqueadero().getRegistros()) {
            if (r.getSalida() != null &&
                !r.getSalida().before(desde) && !r.getSalida().after(hasta)) {
                total += r.getTotalAPagar();
            }
        }
        return total;
    }
    /**
     * Metodo para obtener vehiculos actuales dentro del parqueadero
     * @return
     */

    public ArrayList<Vehiculo> obtenerVehiculosActuales() {
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        for (Registro r : parqueadero.getParqueadero().getRegistros()) {
            if (r.getSalida() == null) {
                vehiculos.add(r.getVehiculo());
            }
        }
        return vehiculos;
    }
    /**
     * Metodo para obtener clientes con membresias activas
     * @return
     */

    
    public ArrayList<Cliente> obtenerClientesConMembresiaActiva() {
        ArrayList<Cliente> resultado = new ArrayList<>();
        Date hoy = new Date();
        for (Cliente c : parqueadero.getParqueadero().getClientes()) {
            for (Vehiculo v : c.getVehiculos()) {
                if (v.getMembresia() != null && hoy.before(v.getMembresia().getFechaFin())) {
                    resultado.add(c);
                    break;
                }
            }
        }
        return resultado;
    }
}
