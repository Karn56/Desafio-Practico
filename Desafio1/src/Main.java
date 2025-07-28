// Importamos todo lo necesario
import java.util.*;

public class Main {
    // Aquí se guardan los estudiantes, el carnet es como la llave
    private static HashMap<String, Estudiante> estudiantes = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in); // Para leer lo que uno escribe

    public static void main(String[] args) {
        int opcion;

        // Menú que se repite hasta que uno decida salir
        do {
            mostrarMenu();
            try {
                opcion = Integer.parseInt(scanner.nextLine()); // lee opción
                switch (opcion) {
                    case 1: ingresarEstudiante(); break;  // si quiere meter estudiante
                    case 2: verEstudiantes(); break;      // si quiere ver todos
                    case 3: buscarEstudiante(); break;    // si quiere buscar uno
                    case 4: System.out.println("¡Programa finalizado!"); break; // salir
                    default: System.out.println("Opción inválida."); // por si se equivoca
                }
            } catch (NumberFormatException e) {
                // si mete letras en vez de números
                System.out.println("Error: Ingrese un número válido.");
                opcion = 0;
            }
        } while (opcion != 4); // hasta que elija salir
    }

    // Menú de las opciones
    public static void mostrarMenu() {
        System.out.println("\n--- MENÚ DE OPCIONES ---");
        System.out.println("1. Ingreso de estudiante");
        System.out.println("2. Ver estudiantes");
        System.out.println("3. Buscar estudiante");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }

    // Aquí se ingresa un estudiante nuevo
    public static void ingresarEstudiante() {
        System.out.print("Carnet: ");
        String carnet = scanner.nextLine();
        if (estudiantes.containsKey(carnet)) {
            System.out.println("Error: Carnet ya registrado.");
            return;
        }

        // Validar que el nombre no esté vacío
        String nombre;
        do {
            System.out.print("Nombre: ");
            nombre = scanner.nextLine().trim();
            if (nombre.isEmpty()) System.out.println("El nombre no puede estar vacío.");
        } while (nombre.isEmpty());

        // Validar edad positiva
        int edad;
        while (true) {
            System.out.print("Edad: ");
            try {
                edad = Integer.parseInt(scanner.nextLine());
                if (edad > 0) break;
                else System.out.println("La edad debe ser mayor que cero.");
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }

        // Validar carrera no vacía
        String carrera;
        do {
            System.out.print("Carrera: ");
            carrera = scanner.nextLine().trim();
            if (carrera.isEmpty()) System.out.println("La carrera no puede estar vacía.");
        } while (carrera.isEmpty());

        // Validar materia no vacía
        String materia;
        do {
            System.out.print("Materia: ");
            materia = scanner.nextLine().trim();
            if (materia.isEmpty()) System.out.println("La materia no puede estar vacía.");
        } while (materia.isEmpty());

        // Crear y guardar el estudiante
        Estudiante est = new Estudiante(nombre, edad, carrera, materia);
        estudiantes.put(carnet, est);
        System.out.println("Estudiante ingresado con éxito.");
    }

    // Aquí se muestran todos los estudiantes que hay guardados
    public static void verEstudiantes() {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }

        System.out.println("\n--- LISTA DE ESTUDIANTES ---");
        for (Map.Entry<String, Estudiante> entry : estudiantes.entrySet()) {
            System.out.println("Carnet: " + entry.getKey());
            System.out.println(entry.getValue()); // imprime los datos bonitos
            System.out.println("----------------------------");
        }
    }

    // Este es para buscar un estudiante por carnet
    public static void buscarEstudiante() {
        System.out.print("Ingrese el carnet a buscar: ");
        String carnet = scanner.nextLine();

        // Si lo encuentra, lo muestra. Si no, avisa
        if (estudiantes.containsKey(carnet)) {
            System.out.println("Estudiante encontrado:");
            System.out.println(estudiantes.get(carnet));
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }
}