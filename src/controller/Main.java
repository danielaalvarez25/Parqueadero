package controller;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import model.Cliente;
import model.Vehiculo;
import model.enumeration.TipoVehiculoEnum;
import service.FacturaService;
import service.ParqueaderoService;

public class Main {

    private static ParqueaderoService parqueaderoService = new ParqueaderoService();
    private static FacturaService facturaService = new FacturaService(parqueaderoService);

    public static void main(String[] args) {

        boolean salir = false;

        while (!salir) {
            String opcion = JOptionPane.showInputDialog(null, "Menú Parqueadero:\n"
                    + "1. Configurar Parqueadero\n"
                    + "2. Gestionar Clientes\n"
                    + "3. Gestionar Vehículos\n"
                    + "4. Registrar Ingreso\n"
                    + "5. Registrar Salida\n"
                    + "6. Reportes\n"
                    + "7. Salir", "Menú", JOptionPane.QUESTION_MESSAGE);
            
            

            if (opcion == null) {
                JOptionPane.showMessageDialog(null, "Saliendo del sistema.");
                break;
            }

            switch (opcion) {
                case "1" -> configurarParqueadero();
                case "2" -> mostrarGestionClientes();
                case "3" -> mostrarGestionVehiculos();
                case "4" -> registrarIngreso();
                case "5" -> registrarSalida();
                case "6" -> mostrarReportes();
                case "7" -> salir = true;
                default -> JOptionPane.showMessageDialog(null, "Opción no válida");
            }
        }
    }

    private static void configurarParqueadero() {
        String nombre = JOptionPane.showInputDialog("Nombre del parqueadero:");
        String direccion = JOptionPane.showInputDialog("Dirección:");
        String representante = JOptionPane.showInputDialog("Representante:");
        parqueaderoService.configurarDatosParqueadero(nombre, direccion, representante);

        int motos = Integer.parseInt(JOptionPane.showInputDialog("Cupo de motos:"));
        int carros = Integer.parseInt(JOptionPane.showInputDialog("Cupo de carros:"));
        int camiones = Integer.parseInt(JOptionPane.showInputDialog("Cupo de camiones:"));
        parqueaderoService.configurarCupos(motos, carros, camiones);
        
        double tarifaMotos = Integer.parseInt(JOptionPane.showInputDialog("Tarifa de motos:"));
        double tarifaCarros = Integer.parseInt(JOptionPane.showInputDialog("Tarifa de carros:"));
        double tarifaCamiones = Integer.parseInt(JOptionPane.showInputDialog("Tarifa de camiones:"));
        parqueaderoService.configurarTarifas(tarifaMotos, tarifaCarros, tarifaCamiones);

        JOptionPane.showMessageDialog(null, "Parqueadero configurado correctamente.");
    }
    
    private static void mostrarGestionClientes() {
        String subop = JOptionPane.showInputDialog("Gestión de Clientes:\n"
                + "1. Agregar cliente\n"
                + "2. Eliminar cliente\n"
                + "3. Actualizar cliente\n"
                + "4. Buscar cliente");

        if (subop == null) return;

        switch (subop) {
            case "1" -> {
                String nombreCliente = JOptionPane.showInputDialog("Nombre del cliente:");
                String cedula = JOptionPane.showInputDialog("Cédula:");
                String telefono = JOptionPane.showInputDialog("Teléfono:");
                String correo = JOptionPane.showInputDialog("Correo:");

                if (nombreCliente != null && cedula != null && telefono != null && correo != null) {
                    Cliente cliente = new Cliente(nombreCliente, cedula, telefono, correo, new ArrayList<>());
                    parqueaderoService.registrarCliente(cliente);
                } else {
                    JOptionPane.showMessageDialog(null, "Datos incompletos. No se registró el cliente.");
                }
            }

            case "2" -> {
                String cedulaEliminar = JOptionPane.showInputDialog("Cédula del cliente a eliminar:");
                if (cedulaEliminar != null) {
                    boolean eliminado = parqueaderoService.eliminarCliente(cedulaEliminar);
                    
                    if (eliminado) {
                        JOptionPane.showMessageDialog(null, "Cliente eliminado.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                    }
                }
            }

            case "3" -> {
                String cedulaActualizar = JOptionPane.showInputDialog("Cédula del cliente a actualizar:");
                if (cedulaActualizar != null) {
                    Cliente cliente = parqueaderoService.buscarCliente(cedulaActualizar);
                    if (cliente != null) {
                        String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre:", cliente.getNombre());
                        String nuevoTelefono = JOptionPane.showInputDialog("Nuevo teléfono:", cliente.getTelefono());
                        String nuevoCorreo = JOptionPane.showInputDialog("Nuevo correo:", cliente.getCorreo());

                        cliente.setNombre(nuevoNombre);
                        cliente.setTelefono(nuevoTelefono);
                        cliente.setCorreo(nuevoCorreo);

                        JOptionPane.showMessageDialog(null, "Cliente actualizado.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                    }
                }
            }

            case "4" -> {
                String cedulaBuscar = JOptionPane.showInputDialog("Cédula del cliente a buscar:");
                if (cedulaBuscar != null) {
                    Cliente encontrado = parqueaderoService.buscarCliente(cedulaBuscar);
                    if (encontrado != null) {
                        JOptionPane.showMessageDialog(null, "Cliente encontrado:\n"
                                + "Nombre: " + encontrado.getNombre()
                                + "\nCédula: " + encontrado.getCedula()
                                + "\nTeléfono: " + encontrado.getTelefono()
                                + "\nCorreo: " + encontrado.getCorreo());
                    } else {
                        JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                    }
                }
            }

            default -> JOptionPane.showMessageDialog(null, "Opción no válida.");
        }
    }
    
    private static void mostrarGestionVehiculos() {
        String subop = JOptionPane.showInputDialog("Gestión de Vehículos:\n"
                + "1. Registrar vehículo\n"
                + "2. Buscar vehículo por placa\n"
                + "3. Actualizar datos del vehículo");

        if (subop == null) return;

        switch (subop) {
            case "1" -> {
            	   String ced = JOptionPane.showInputDialog("Cédula del cliente:");
                   Cliente cli = parqueaderoService.buscarCliente(ced);
                   if (cli != null) {
                       String placa = JOptionPane.showInputDialog("Placa:");
                       String color = JOptionPane.showInputDialog("Color:");
                       String modelo = JOptionPane.showInputDialog("Modelo:");
                       String tipo = JOptionPane.showInputDialog("Tipo (MOTO, CARRO, CAMION):");
                       try {
                           TipoVehiculoEnum tipoVehiculo = TipoVehiculoEnum.valueOf(tipo.toUpperCase());
                           Vehiculo vehiculo = new Vehiculo(placa, color, modelo, tipoVehiculo, null);
                           boolean registrado = parqueaderoService.registrarVehiculo(vehiculo, cli);
                           if (registrado) {
                               JOptionPane.showMessageDialog(null, "Vehículo registrado correctamente.");
                           }
                       } catch (IllegalArgumentException e) {
                           JOptionPane.showMessageDialog(null, "Tipo de vehículo inválido.");
                       }
                   } else {
                       JOptionPane.showMessageDialog(null, "Cliente no encontrado");
                   }
            }

            case "2" -> {
                String cedula = JOptionPane.showInputDialog("Ingrese la cédula del cliente:");
                Cliente cliente = parqueaderoService.buscarCliente(cedula);

                if (cliente == null) {
                    JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                    return;
                }

                String placaBuscar = JOptionPane.showInputDialog("Placa del vehículo a buscar (dejar en blanco si no desea filtrar):");

                String[] opciones = {"MOTO", "CARRO", "CAMION", "TODOS"};
                int seleccion = JOptionPane.showOptionDialog(
                    null,
                    "Seleccione el tipo de vehículo:",
                    "Tipo de Vehículo",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
                );

                TipoVehiculoEnum tipoVehiculo = null;
                if (seleccion >= 0 && seleccion < 3) {
                    tipoVehiculo = TipoVehiculoEnum.valueOf(opciones[seleccion]);
                }

                ArrayList<Vehiculo> vehiculosEncontrados = parqueaderoService.buscarVehiculo(placaBuscar, tipoVehiculo, cliente);

                if (vehiculosEncontrados.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vehículo no encontrado.");
                } else {
                    StringBuilder sb = new StringBuilder("Vehículos encontrados:\n");
                    for (Vehiculo v : vehiculosEncontrados) {
                        sb.append("- Placa: ").append(v.getPlaca())
                          .append(" | Modelo: ").append(v.getModelo())
                          .append(" | Tipo: ").append(v.getTipoVehiculo()).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                }
            }


            case "3" -> {
                String placaActualizar = JOptionPane.showInputDialog("Placa del vehículo a actualizar:");
//                Vehiculo v = parqueaderoService.actualizarVehiculo(null, null);
//                if (v != null) {
//                    String nuevaMarca = JOptionPane.showInputDialog("Nueva marca:", v.getPlaca());
//                    String nuevoModelo = JOptionPane.showInputDialog("Nuevo modelo:", v.getModelo());
//
//                    if (nuevaMarca != null && !nuevaMarca.trim().isEmpty()) v.setMarca(nuevaMarca);
//                    if (nuevoModelo != null && !nuevoModelo.trim().isEmpty()) v.setModelo(nuevoModelo);
//
//                    JOptionPane.showMessageDialog(null, "Vehículo actualizado.");
//                } else {
//                    JOptionPane.showMessageDialog(null, "Vehículo no encontrado.");
//                }
            }

            default -> JOptionPane.showMessageDialog(null, "Opción no válida.");
        }
    }




    private static void gestionarVehiculos() {
        String ced = JOptionPane.showInputDialog("Cédula del cliente:");
        Cliente cli = parqueaderoService.buscarCliente(ced);
        if (cli != null) {
            String placa = JOptionPane.showInputDialog("Placa:");
            String color = JOptionPane.showInputDialog("Color:");
            String modelo = JOptionPane.showInputDialog("Modelo:");
            String tipo = JOptionPane.showInputDialog("Tipo (MOTO, CARRO, CAMION):");
            try {
                TipoVehiculoEnum tipoVehiculo = TipoVehiculoEnum.valueOf(tipo.toUpperCase());
                Vehiculo vehiculo = new Vehiculo(placa, color, modelo, tipoVehiculo, null);
                boolean registrado = parqueaderoService.registrarVehiculo(vehiculo, cli);
                if (registrado) {
                    JOptionPane.showMessageDialog(null, "Vehículo registrado correctamente.");
                }
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, "Tipo de vehículo inválido.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado");
        }
    }

    private static void registrarIngreso() {
        String placaIngreso = JOptionPane.showInputDialog("Placa del vehículo:");
        String tipoIng = JOptionPane.showInputDialog("Tipo (MOTO, CARRO, CAMION):");
        try {
            TipoVehiculoEnum tipoVehIngreso = TipoVehiculoEnum.valueOf(tipoIng.toUpperCase());
            Vehiculo vehiculoIngreso = new Vehiculo(placaIngreso, "NO", "NO", tipoVehIngreso, null);
            parqueaderoService.registrarIngresoVehiculo(vehiculoIngreso);
            JOptionPane.showMessageDialog(null, "Ingreso registrado.");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Tipo de vehículo inválido.");
        }
    }

    private static void registrarSalida() {
        String placaSalida = JOptionPane.showInputDialog("Placa del vehículo a salir:");
        if (placaSalida != null && !placaSalida.isBlank()) {
        	parqueaderoService.registrarSalidaVehiculo(placaSalida);
        	//facturaService.generarFacturaSalida(placaSalida);
        }
    }

    private static void mostrarReportes() {
        String subop = JOptionPane.showInputDialog("Reportes:\n"
                + "1. Historial por cliente\n"
                + "2. Total de ingresos\n"
                + "3. Vehículos actuales\n"
                + "4. Membresías activas");

        if (subop == null) return;

        switch (subop) {
            case "1" -> {
                String cedHist = JOptionPane.showInputDialog("Cédula del cliente:");
                Cliente cHist = parqueaderoService.buscarCliente(cedHist);
                if (cHist != null) {
                    ArrayList<String> hist = facturaService.historialVehiculosPorCliente(cHist);
                    JOptionPane.showMessageDialog(null, String.join("\n", hist));
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
                }
            }
            case "2" -> {
                double ingresos = facturaService.calcularIngresos(new Date(), new Date());
                JOptionPane.showMessageDialog(null, "Total ingresos: $" + ingresos);
            }
            case "3" -> {
                ArrayList<Vehiculo> actuales = facturaService.obtenerVehiculosActuales();
                StringBuilder lista = new StringBuilder();
                for (Vehiculo r : actuales) {
                    lista.append("Placa: ").append(r.getPlaca())
                            .append(" | Tipo: ").append(r.getTipoVehiculo()).append("\n");
                }
                JOptionPane.showMessageDialog(null, lista.toString());
            }
            case "4" -> {
                ArrayList<Cliente> activos = facturaService.obtenerClientesConMembresiaActiva();
                StringBuilder salidaMembresias = new StringBuilder();
                for (Cliente cl : activos) {
                    salidaMembresias.append("Cliente: ").append(cl.getNombre())
                            .append(" | Cédula: ").append(cl.getCedula()).append("\n");
                }
                JOptionPane.showMessageDialog(null, salidaMembresias.toString());
            }
            default -> JOptionPane.showMessageDialog(null, "Opción no válida");
        }
    }
}
