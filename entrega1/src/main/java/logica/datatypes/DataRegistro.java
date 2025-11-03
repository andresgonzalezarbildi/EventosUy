package logica.datatypes;

import java.time.LocalDate;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataRegistro {
    
  private String evento;
    
  private String edicion;
  
  private String tipoRegistro;
    
  private Integer costo;
    
  @XmlJavaTypeAdapter(FormateoFecha.class)
  private LocalDate fecha;   
    
  private String asistente;
  
  private boolean confirmarAsistencia;
  
  public DataRegistro(){}

    public DataRegistro(String evento, String edicion, String tipoRegistro, 
                        Integer costo, LocalDate fecha, String asistente, boolean confirmarAsistencia) {
        this.evento = evento;
        this.edicion = edicion;
        this.tipoRegistro = tipoRegistro;
        this.costo = costo;
        this.fecha = fecha;
        this.asistente = asistente;
        this.confirmarAsistencia = confirmarAsistencia;
    }
    public String getEvento(){return evento; }
    public void setEvento(String evento) {this.evento = evento; }
    
    public String getEdicion(){return edicion; }
    public void setEdicion(String edicion) {this.edicion=edicion; }
    
    public String getTipoRegistro() { return tipoRegistro; }
    public void setTipoRegistro(String tipoRegistro) { this.tipoRegistro = tipoRegistro; }
    
    public Integer getCosto(){ return costo; }
    public void setCosto(Integer costo) { this.costo = costo; }
    
    public LocalDate getFecha(){ return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    
    public String getAsistente(){ return asistente; }
    public void setAsistente(String asistente) { this.asistente = asistente; }
    
    public boolean isConfirmarAsistencia(){ return confirmarAsistencia; }
    public void setConfirmarAsistencia(boolean confirmarAsistentcia) { this.confirmarAsistencia = confirmarAsistentcia; }
    
    @Override
    public String toString() {
        return edicion; 
    }
}
