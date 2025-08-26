package logica;
import java.time.LocalDate;

public class Evento {
	
	private String nombre;
    private String DescripcionEvento;
    private String sigla;
    private LocalDate FechaAltaEnPlataforma;  

    public Evento(String nom, String desc, String sigla, LocalDate fecha) {
        this.DescripcionEvento = desc;
        this.nombre = nom;
        this.sigla = sigla;
        this.FechaAltaEnPlataforma = fecha;
    }
    
    public LocalDate getFecha() {
        return FechaAltaEnPlataforma;
    }
    
    public void setFecha(LocalDate fecha) {
        this.FechaAltaEnPlataforma = fecha;
    }
}
    

    
    