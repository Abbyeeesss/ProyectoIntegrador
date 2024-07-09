import java.util.ArrayList;

// Clase Estudiante que representa a un estudiante
public class Estudiante {
    private String nombre;
    private String apellido;
    private String codigo;
    private ArrayList<Libro> librosPrestados;

    //Inicializa un objeto Estudiante con los valores proporcionados
    public Estudiante(String nombre, String apellido, String codigo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.codigo = codigo;
        this.librosPrestados = new ArrayList<>();//inicializa la lista librosPrestados como una nueva instancia de ArrayList.
    }

    // Getters y setters
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public ArrayList<Libro> getLibrosPrestados() {
        return librosPrestados;
    }

    public void prestarLibro(Libro libro) {
        librosPrestados.add(libro);
    }

    public void devolverLibro(Libro libro) {
        librosPrestados.remove(libro);
    }

    @Override
    public String toString() { //Devuelve una representación en cadena de texto
        StringBuilder librosPrestadosStr = new StringBuilder();
        for (Libro libro : librosPrestados) {
            librosPrestadosStr.append("\n").append(libro.getTitulo());
        }
        return "";
    }
}
