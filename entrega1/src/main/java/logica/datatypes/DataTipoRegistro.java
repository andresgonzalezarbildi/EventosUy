package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataTipoRegistro {

    private String nombre;
    private String descripcion;
    private Integer costo;
    private Integer cupo;

    public DataTipoRegistro() {}

    public DataTipoRegistro(String nombre, String descripcion, Integer costo, Integer cupo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
        this.cupo = cupo;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Integer getCosto() { return costo; }
    public void setCosto(Integer costo) { this.costo = costo; }

    public Integer getCupo() { return cupo; }
    public void setCupo(Integer cupo) { this.cupo = cupo; }

    @Override
    public String toString() {
        return nombre + " (costo: " + costo + ", cupo: " + cupo + ")";
    }
}
