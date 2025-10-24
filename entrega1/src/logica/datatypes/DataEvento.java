package logica.datatypes;
import java.util.List;
import java.time.LocalDate;

public class DataEvento {
    private String nombre;
    private String descripcion;
    private String sigla;
    private LocalDate fechaAlta;
    private String imagen;
    private List<String> categorias;



    public DataEvento(String nombre, String descripcion, String sigla, LocalDate fechaAlta, List<String> categorias, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.sigla = sigla;
        this.fechaAlta = fechaAlta;
        this.imagen = imagen;
        this.categorias = categorias;
    }


    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public String getSigla() { return sigla; }
    public String getImagen() { return imagen; }
    public LocalDate getFechaAlta() { return fechaAlta; }
    public List<String> getCategorias() { return categorias; }


    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setSigla(String sigla) { this.sigla = sigla; }
    public void setimagen(String imagen) {this.imagen = imagen; }
    public void setFechaAlta(LocalDate fechaAlta) { this.fechaAlta = fechaAlta; }

    
    // ToString para mostrar en UI, por ejemplo en JList
    @Override
    public String toString() {
        return nombre;  
    }
}
