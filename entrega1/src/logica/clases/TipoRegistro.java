package logica.clases;

public class TipoRegistro {
	private String nombre;
    private String descripcion;
    private Integer costo;
    private Integer cupo;
    
 // Constructor
    public TipoRegistro(String nombre, String descripcion, Integer costo, Integer cupo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costo = costo;
        this.cupo = cupo;
    }
    
 // Metodos getters
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

}
 