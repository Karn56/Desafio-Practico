
public class Estudiante {

    // Información del Estudiante
    private String nombre;
    private int edad;
    private String carrera;
    private String materia;

    // Constructor
    public Estudiante(String nombre, int edad, String carrera, String materia) {
        this.nombre = nombre;
        this.edad = edad;
        this.carrera = carrera;
        this.materia = materia;
    }

    //Obtener los datos
    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public String getCarrera() { return carrera; }
    public String getMateria() { return materia; }

    // Esto es para que cuando imprima el estudiante salga todo ordenado
    @Override
    public String toString() {
        return "Nombre: " + nombre + "\nEdad: " + edad +
                "\nCarrera: " + carrera + "\nMateria: " + materia;
    }
}