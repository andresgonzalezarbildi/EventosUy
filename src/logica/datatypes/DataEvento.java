package logica.datatypes;
import java.util.List;
import java.time.LocalDate;

public class DataEvento {
    private String nombre;
    private String descripcion;
    private String sigla;
    private LocalDate fechaAlta; // equivalente a FechaAltaEnPlataforma
    private List<String> categorias;

    // Constructor mínimo
    public DataEvento(String nombre, String descripcion, String sigla, LocalDate fechaAlta,List<String> categorias) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.sigla = sigla;
        this.fechaAlta = fechaAlta;
        this.categorias = categorias;
    }


    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public String getSigla() { return sigla; }
    public LocalDate getFechaAlta() { return fechaAlta; }
    public List<String> getCategorias() { return categorias; }

    // Setters (si querés permitir modificación opcional)
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setSigla(String sigla) { this.sigla = sigla; }
    public void setFechaAlta(LocalDate fechaAlta) { this.fechaAlta = fechaAlta; }

    // ToString para mostrar en UI, por ejemplo en JList
    @Override
    public String toString() {
        return nombre + " (" + sigla + ")";
    }
}
