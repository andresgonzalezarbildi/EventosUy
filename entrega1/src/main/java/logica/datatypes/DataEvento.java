package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataEvento {

    private String nombre;
    private String descripcion;
    private String sigla;

    @XmlJavaTypeAdapter(FormateoFecha.class)
    private LocalDate fechaAlta;

    private String imagen;
    private List<String> categorias = new ArrayList<String>();
    private boolean finalizado;

    public DataEvento() {}

    public DataEvento(String nombre, String descripcion, String sigla,
                      LocalDate fechaAlta, List<String> categorias, String imagen, boolean finalizado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.sigla = sigla;
        this.fechaAlta = fechaAlta;
        this.imagen = imagen;
        this.categorias = categorias;
        this.finalizado = finalizado;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getSigla() { return sigla; }
    public void setSigla(String sigla) { this.sigla = sigla; }

    public LocalDate getFechaAlta() { return fechaAlta; }
    public void setFechaAlta(LocalDate fechaAlta) { this.fechaAlta = fechaAlta; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public List<String> getCategorias() { return categorias; }
    public void setCategorias(List<String> categorias) { this.categorias = categorias; }
    
    public boolean getFinalizado() { return finalizado; }
    public void setfinalizado(boolean finalizado) { this.finalizado = finalizado; }

    @Override
    public String toString() { return nombre; }
}
