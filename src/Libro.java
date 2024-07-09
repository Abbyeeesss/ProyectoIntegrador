
public class Libro {
    private String titulo;
    private String autor;
    private String edicion;
    private int anoPublicacion;
    private String isbn;
    private String estado; // Disponible o Prestado

    public Libro(String titulo, String autor, String edicion, int anoPublicacion, String isbn, String estado) {
        this.titulo = titulo;
        this.autor = autor;
        this.edicion = edicion;
        this.anoPublicacion = anoPublicacion;
        this.isbn = isbn;
        this.estado = estado;
    }

    // Getters y setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void modificarInformacion(String edicion, int anoPublicacion) {
        this.edicion = edicion;
        this.anoPublicacion = anoPublicacion;
    }

    @Override
    public String toString() {
        return "\nLibro " +
                "\ntitulo: " + titulo +
                "\nAutor: " + autor +
                "\nEdicion: " + edicion +
                "\nAnoPublicacion: " + anoPublicacion +
                "\nisbn: " + isbn +
                "\nEstado: " + estado ;
    }

    public String getIsbn() {
        return isbn;
    }
}
