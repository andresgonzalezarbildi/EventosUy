package logica.datatypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataEdicion {

    private String nombre;

    @XmlJavaTypeAdapter(FormateoFecha.class)
    private LocalDate fechaIni;

    @XmlJavaTypeAdapter(FormateoFecha.class)
    private LocalDate fechaFin;

    private String ciudad;
    private String pais;
    private String sigla;

    @XmlJavaTypeAdapter(FormateoFecha.class)
    private LocalDate fechaAltaEnPlataforma;

    private String imagen;
    private String estado;
    private String evento;
    private String organizador;

    private List<DataTipoRegistro> tiposRegistro = new ArrayList<DataTipoRegistro>();
    private List<DataPatrocinio> patrocinios = new ArrayList<DataPatrocinio>();

    public DataEdicion() {}

    public DataEdicion(String nombre, LocalDate fechaIni, LocalDate fechaFin,
                       String ciudad, String pais, String sigla, LocalDate fechaAltaEnPlataforma,
                       String organizador, List<DataTipoRegistro> tiposRegistro,
                       List<DataPatrocinio> patrocinios, String imagen, String estado, String evento) {
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
        this.imagen = imagen;
        this.estado = estado;
        this.evento = evento;
    }

    // Getters
    public String getNombre() { return nombre; }
    public LocalDate getFechaIni() { return fechaIni; }
    public LocalDate getFechaFin() { return fechaFin; }
    public String getCiudad() { return ciudad; }
    public String getPais() { return pais; }
    public String getSigla() { return sigla; }
    public String getImagen() { return imagen; }
    public LocalDate getFechaAltaEnPlataforma() { return fechaAltaEnPlataforma; }
    public String getOrganizador() { return organizador; }
    public List<DataTipoRegistro> getTiposRegistro() { return tiposRegistro; }
    public List<DataPatrocinio> getPatrocinios() { return patrocinios; }
    public String getEstado() { return estado; }
    public String getEvento() { return evento; }
    
    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setFechaIni(LocalDate fechaIni) { this.fechaIni = fechaIni; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public void setPais(String pais) { this.pais = pais; }
    public void setSigla(String sigla) { this.sigla = sigla; }
    public void setImagen(String imagen) { this.imagen = imagen; }
    public void setFechaAltaEnPlataforma(LocalDate fecha) { this.fechaAltaEnPlataforma = fecha; }
    public void setOrganizador(String organizador) { this.organizador = organizador; }
    public void setTiposRegistro(List<DataTipoRegistro> tiposRegistro) { this.tiposRegistro = tiposRegistro; }
    public void setPatrocinios(List<DataPatrocinio> patrocinios) { this.patrocinios = patrocinios; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setEvento(String evento) { this.evento = evento; }
    
    public String toString() {
      return nombre;
  }
}
