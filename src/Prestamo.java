import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Prestamo {
    private Estudiante estudiante;
    private Libro libro;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
 //Inicializa un objeto Prestamo con el estudiante y el libro proporcionados
    public Prestamo(Estudiante estudiante, Libro libro) {
        this.estudiante = estudiante;
        this.libro = libro;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = LocalDate.now().plusDays(15); // 15 días después de la fecha de préstamo
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public Libro getLibro() {
        return libro;
    }
}


