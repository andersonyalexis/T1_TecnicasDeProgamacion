

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ==========================================
// 1. CLASE MODELO (Paciente)
// ==========================================
class Paciente {
    private String nombre;
    private String tipoDocumento;
    private String dni;
    private String fechaNacimiento;
    private String tipoSangre;
    private String alergias;
    private String telefono;
    private String correo;

    // Constructor con validaciones simples
    public Paciente(String nombre, String tipoDocumento, String dni, String fechaNacimiento, 
                    String tipoSangre, String alergias, String telefono, String correo) {
        
        // Validar que no dejen campos obligatorios vacíos
        if (nombre.trim().isEmpty() || dni.trim().isEmpty() || tipoSangre.trim().isEmpty() || alergias.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre, documento, tipo de sangre y alergias son obligatorios.");
        }
        // Validar que el correo tenga @
        if (!correo.contains("@")) {
            throw new IllegalArgumentException("El correo debe incluir un '@' válido.");
        }
        // Validar que el teléfono tenga 9 dígitos
        if (telefono.trim().length() != 9) {
            throw new IllegalArgumentException("El teléfono debe tener exactamente 9 dígitos.");
        }

        this.nombre = nombre;
        this.tipoDocumento = tipoDocumento;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoSangre = tipoSangre;
        this.alergias = alergias;
        this.telefono = telefono;
        this.correo = correo;
    }

    public String getDni() {
        return dni;
    }

    @Override
    public String toString() 
    {
        return "------------------------------------------\n" +
               "Paciente: " + nombre + "\n" +
               "Documento: " + tipoDocumento + " - " + dni + "\n" +
               "F. Nacimiento: " + fechaNacimiento + "\n" +
               "Tipo Sangre: " + tipoSangre + " | Alergias: " + alergias + "\n" +
               "Contacto: " + telefono + " | " + correo + "\n" +
               "------------------------------------------";
    }
}

// ==========================================
// 2. CLASE CONTROLADOR (ControladorPaciente)
// ==========================================
class ControladorPaciente {
    private List<Paciente> listaPacientes = new ArrayList<>();

    // Método para verificar si el paciente ya existe por DNI/Documento
    public boolean existePaciente(String dni) 
    {
        for (Paciente p : listaPacientes)
        {
            if (p.getDni().equalsIgnoreCase(dni))
            {
                return true;
            }
        }
        return false;
    }

    // Método 1: Agregar paciente a la lista
    public void agregarPaciente(Paciente paciente)
    {
        if (existePaciente(paciente.getDni())) 
        {
            throw new IllegalArgumentException("Ya existe un paciente registrado con el documento " + paciente.getDni());
        }
        listaPacientes.add(paciente);
        System.out.println("\n Paciente registrado con éxito.");
    }

    // Método 2: Listar todos los pacientes
    public void listarPacientes()
    {
        if (listaPacientes.isEmpty()) 
        {
            System.out.println("\n No hay pacientes registrados en el sistema.");
            return;
        }
        System.out.println("\n=== LISTA DE PACIENTES REGISTRADOS ===");
        for (Paciente p : listaPacientes) 
        {
            System.out.println(p);
        }
    }
}

// ==========================================
// 3. CLASE PRINCIPAL (Main)
// ==========================================
public class T1
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ControladorPaciente controlador = new ControladorPaciente();
        boolean salir = false;

        System.out.println("=========================================");
        System.out.println("  SISTEMA DE PACIENTES - HOSPITAL XYZ    ");
        System.out.println("=========================================");

        while (!salir) {
            try {
                System.out.println("\n--- MENÚ PRINCIPAL ---");
                System.out.println("1. Registrar nuevo paciente");
                System.out.println("2. Listar todos los pacientes");
                System.out.println("3. Salir");
                System.out.print("Seleccione una opción: ");

                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("\n--- INGRESO DE DATOS ---");
                        System.out.print("Nombre completo: ");
                        String nombre = scanner.nextLine();

                        System.out.print("Tipo de documento (DNI/CE/Pasaporte): ");
                        String tipoDoc = scanner.nextLine();

                        System.out.print("Número de documento: ");
                        String dni = scanner.nextLine();

                        System.out.print("Fecha de nacimiento (DD/MM/AAAA): ");
                        String fechaNac = scanner.nextLine();

                        System.out.print("Tipo de sangre: ");
                        String tipoSangre = scanner.nextLine();

                        System.out.print("Alergias (Escriba 'NINGUNA' si no tiene): ");
                        String alergias = scanner.nextLine();

                        System.out.print("Teléfono (9 dígitos): ");
                        String telefono = scanner.nextLine();

                        System.out.print("Correo electrónico: ");
                        String correo = scanner.nextLine();

                        // Crear el objeto y agregarlo mediante el controlador
                        Paciente nuevoPaciente = new Paciente(nombre, tipoDoc, dni, fechaNac, tipoSangre, alergias, telefono, correo);
                        controlador.agregarPaciente(nuevoPaciente);
                        break;

                    case 2:
                        controlador.listarPacientes();
                        break;

                    case 3:
                        salir = true;
                        System.out.println("\n¡Gracias por usar el sistema!");
                        break;

                    default:
                        System.out.println(" Opción inválida. Elija 1, 2 o 3.");
                }

            } catch (NumberFormatException e) {
                System.out.println("\n Error: Debe ingresar un número entero.");
            } catch (IllegalArgumentException e) {
                System.out.println("\n Error de validación: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("\n Error inesperado: " + e.getMessage());
            }
        }

        scanner.close();
    }
}