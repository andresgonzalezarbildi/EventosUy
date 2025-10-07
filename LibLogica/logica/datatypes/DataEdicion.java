package logica.datatypes;

import java.time.LocalDate;
import java.util.List;

public class DataEdicion {
    private String nombre;
    private LocalDate fechaIni;
    private LocalDate fechaFin;
    private String ciudad;
    private String pais;
    private String sigla;
    private LocalDate fechaAltaEnPlataforma;
    
    private String organizador;
    private List<DataTipoRegistro> tiposRegistro;
    private List<DataPatrocinio> patrocinios;

    public DataEdicion(String nombre, LocalDate fechaIni, LocalDate fechaFin,
                       String ciudad, String pais, String sigla, LocalDate fechaAltaEnPlataforma,
                       String organizador, List<DataTipoRegistro> tiposRegistro, List<DataPatrocinio> patrocinios) {
        this.nombre = nombre;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.ciudad = ciudad;
        this.pais = pais;
        this.sigla = sigla;
        this.fechaAltaEnPlataforma = fechaAltaEnPlataforma;
        this.organizador = organizador;
        this.tiposRegistro = tiposRegistro;
        this.patrocinios = patrocinios;
    }

    // Getters
    public String getNombre() { return nombre; }
    public LocalDate getFechaIni() { return fechaIni; }
    public LocalDate getFechaFin() { return fechaFin; }
    public String getCiudad() { return ciudad; }
    public String getPais() { return pais; }
    public String getSigla() { return sigla; }
    public LocalDate getFechaAltaEnPlataforma() { return fechaAltaEnPlataforma; }
    public String getOrganizador() { return organizador; }
    public List<DataTipoRegistro> getTiposRegistro() { return tiposRegistro; }
    public List<DataPatrocinio> getPatrocinios() { return patrocinios; }
    @Override
    public String toString() {
        return nombre;
    }
}
