package logica.datatypes;
import java.time.LocalDate;

public class DataEdicion {
	// Atributos
    private String nombre;
    private LocalDate fechaIni;
    private LocalDate fechaFin;
    private String cuidad;
    private String pais;
    private String sigla;
    private LocalDate fechaAltaEnPlataforma;

    // Constructor
    public DataEdicion(String nombre, LocalDate fechaIni, LocalDate fechaFin, String cuidad, String pais, String sigla, LocalDate fechaAltaEnPlataforma) {
        this.nombre = nombre;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.cuidad = cuidad;
        this.pais = pais;
        this.sigla = sigla;
        this.fechaAltaEnPlataforma = fechaAltaEnPlataforma;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaIni() {
        return fechaIni;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public String getCuidad() {
        return cuidad;
    }

    public String getPais() {
        return pais;
    }

    public String getSigla() {
        return sigla;
    }

    public LocalDate getFechaAltaEnPlataforma() {
        return fechaAltaEnPlataforma;
    }
}
