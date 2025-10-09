package logica.datatypes;

public class DataTipoRegistro {
    private String nombre;
    private String descripcion;
    private Integer costo;
    private Integer cupo;

    // Constructor
    public DataTipoRegistro(String nombre, String descripcion, Integer costo, Integer cupo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
        this.cupo = cupo;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getCosto() {
        return costo;
    }

    public Integer getCupo() {
        return cupo;
    }

    // toString (opcional, útil para debug o para mostrar en listas)
    @Override
    public String toString() {
        return nombre + " (costo: " + costo + ", cupo: " + cupo + ")";
    }
}
