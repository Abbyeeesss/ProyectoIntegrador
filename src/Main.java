import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    // las estructuras Map y ArrayList se utilizan para almacenar y gestionar información sobre libros, estudiantes y préstamos en el sistema
    private static Map<String, Libro> libros = new HashMap<>();
    private static Map<String, Estudiante> estudiantes = new HashMap<>();
    private static ArrayList<Prestamo> prestamos = new ArrayList<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> { // asegura que la creación y actualización de la GUI se realicen en el hilo de eventos de Swing
            JFrame frame = new JFrame("Sistema de Gestión de Biblioteca"); //Crea una nueva ventana
            frame.setSize(600, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Configura la ventana para que la aplicación se cierre

            JPanel panel = new JPanel();//Crea un nuevo panel
            panel.setBorder(new EmptyBorder(10, 10, 10, 10));
            panel.setLayout(new GridLayout(0, 1));

            JLabel titleLabel = new JLabel("Menú Principal");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(titleLabel);

            JButton estudianteButton = new JButton("Visualizar Información del Estudiante");
            estudianteButton.addActionListener(e -> mostrarInformacionEstudiante());
            panel.add(estudianteButton);

            JButton librosButton = new JButton("Visualizar Información de los Libros");
            librosButton.addActionListener(e -> mostrarInformacionLibros());
            panel.add(librosButton);

            JButton modificarButton = new JButton("Modificar Información de un Libro");
            modificarButton.addActionListener(e -> modificarInformacionLibro());
            panel.add(modificarButton);

            JButton prestamoButton = new JButton("Registrar un Préstamo");
            prestamoButton.addActionListener(e -> registrarPrestamo());
            panel.add(prestamoButton);

            JButton devolucionButton = new JButton("Registrar la Devolución de un Libro");
            devolucionButton.addActionListener(e -> registrarDevolucion());
            panel.add(devolucionButton);

            JButton salirButton = new JButton("Salir");
            salirButton.addActionListener(e -> System.exit(0));
            panel.add(salirButton);

            frame.add(panel);
            frame.setVisible(true);//Hace visible la ventana, mostrando la GUI al usuario.
        });

        cargarDatosEjemplo();
    }

    private static void mostrarInformacionEstudiante() {
        String codigo = JOptionPane.showInputDialog("Ingrese el código del estudiante:");
        Estudiante estudiante = estudiantes.get(codigo);
        if (estudiante != null) { //Verifica si el estudiante fue encontrado en el Map.
            StringBuilder info = new StringBuilder();//construir la cadena de texto que contendrá la información del estudiante.
            info.append("Información del estudiante:\n");
            info.append("Nombre: ").append(estudiante.getNombre()).append("\n");
            info.append("Apellido: ").append(estudiante.getApellido()).append("\n");
            info.append("Código: ").append(estudiante.getCodigo()).append("\n");
            info.append("Libros prestados:\n");
            for (Libro libro : estudiante.getLibrosPrestados()) {
                info.append("\t").append(libro.getTitulo()).append(" - Estado: ").append(libro.getEstado()).append("\n");//Añade diferentes piezas de información
            }
            JOptionPane.showMessageDialog(null, info.toString(), "Información del Estudiante", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Estudiante no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void mostrarInformacionLibros() {
        StringBuilder info = new StringBuilder();
        info.append("Información de los libros:\n");
        for (Libro libro : libros.values()) {
            info.append(libro).append("\n");
        }
        JOptionPane.showMessageDialog(null, info.toString(), "Información de los Libros", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void modificarInformacionLibro() {
        String isbn = JOptionPane.showInputDialog("Ingrese el ISBN del libro que desea modificar:");
        Libro libro = libros.get(isbn);
        if (libro != null) {
            String nuevaEdicion = JOptionPane.showInputDialog("Ingrese la nueva edición:");
            int nuevoAnoPublicacion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo año de publicación:"));

            libro.modificarInformacion(nuevaEdicion, nuevoAnoPublicacion);
            JOptionPane.showMessageDialog(null, "Información modificada correctamente.", "Modificación Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Libro no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void registrarPrestamo() {
        String codigoEstudiante = JOptionPane.showInputDialog("Ingrese el código del estudiante:");
        Estudiante estudiante = estudiantes.get(codigoEstudiante);
        if (estudiante == null) { //Verifica si el estudiante no fue encontrado en el Map.
            JOptionPane.showMessageDialog(null, "Estudiante no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);//solicita al usuario que ingrese el código del estudiante
            return;//Sale del método si el estudiante no es encontrado.
        }

        String isbnLibro = JOptionPane.showInputDialog("Ingrese el ISBN del libro:");//solicita al usuario que ingrese el ISBN del libro
        Libro libro = libros.get(isbnLibro);//Busca el objeto Libro en el Map de libros usando el ISBN ingresado como clave
        if (libro == null) {//Si el libro no existe, libro será null.
            JOptionPane.showMessageDialog(null, "Libro no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!libro.getEstado().equals("Disponible")) {//Verifica si el estado del libro no es "Disponible".
            JOptionPane.showMessageDialog(null, "El libro no está disponible para préstamo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Prestamo prestamo = new Prestamo(estudiante, libro);
        prestamos.add(prestamo);
        libro.setEstado("Prestado");

        estudiante.prestarLibro(libro);

        JOptionPane.showMessageDialog(null, "Préstamo registrado correctamente.", "Préstamo Exitoso", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void registrarDevolucion() {
        String isbnLibro = JOptionPane.showInputDialog("Ingrese el ISBN del libro que se devuelve:");//Muestra una ventana de diálogo
        Libro libro = libros.get(isbnLibro);
        if (libro == null) {//Verifica si el libro no fue encontrado en el Map
            JOptionPane.showMessageDialog(null, "Libro no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (libro.getEstado().equals("Disponible")) {
            JOptionPane.showMessageDialog(null, "El libro ya está disponible.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        libro.setEstado("Disponible");

        for (Prestamo prestamo : prestamos) {
            if (prestamo.getLibro().equals(libro)) {
                prestamos.remove(prestamo);
                break;
            }
        }

        JOptionPane.showMessageDialog(null, "Devolución registrada correctamente.", "Devolución Exitosa", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void cargarDatosEjemplo() {
        // Cargar datos
        Libro libro1 = new Libro("Principito", "Antoine", "1st Edition", 2020, "12345", "Disponible");
        Libro libro2 = new Libro("Carrie", "King", "2nd Edition", 2019, "54321", "Disponible");
        libros.put(libro1.getIsbn(), libro1);
        libros.put(libro2.getIsbn(), libro2);

        Estudiante estudiante1 = new Estudiante("Abi", "Espinosa", "001");
        Estudiante estudiante2 = new Estudiante("Dana", "Boada", "002");
        estudiante1.prestarLibro(libro1);
        estudiante2.prestarLibro(libro2);
        estudiantes.put(estudiante1.getCodigo(), estudiante1);
        estudiantes.put(estudiante2.getCodigo(), estudiante2);
    }
}