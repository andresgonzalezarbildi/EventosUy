package logica.datatypes;

import java.time.LocalDate;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import logica.clases.Nivel;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataPatrocinio {

    @XmlJavaTypeAdapter(FormateoFecha.class)
    private LocalDate fechaDeRealizacion;
    private Integer montoAportado;
    private Integer cantRegistrosGratis;
    private String codigoDePatrocinio;
    private Nivel nivelDePatrocinio;

    public DataPatrocinio() {}

    public DataPatrocinio(LocalDate fechaDeRealizacion, Integer montoAportado, Integer cantRegistrosGratis, String codigoDePatrocinio, Nivel nivelDePatrocinio) {
        this.fechaDeRealizacion = fechaDeRealizacion;
        this.montoAportado = montoAportado;
        this.cantRegistrosGratis = cantRegistrosGratis;
        this.codigoDePatrocinio = codigoDePatrocinio;
        this.nivelDePatrocinio = nivelDePatrocinio;
    }

    public LocalDate getFechaDeRealizacion() { return fechaDeRealizacion; }
    public void setFechaDeRealizacion(LocalDate fechaDeRealizacion) { this.fechaDeRealizacion = fechaDeRealizacion; }

    public Integer getMontoAportado() { return montoAportado; }
    public void setMontoAportado(Integer montoAportado) { this.montoAportado = montoAportado; }

    public Integer getCantRegistrosGratis() { return cantRegistrosGratis; }
    public void setCantRegistrosGratis(Integer cantRegistrosGratis) { this.cantRegistrosGratis = cantRegistrosGratis; }

    public String getCodigoDePatrocinio() { return codigoDePatrocinio; }
    public void setCodigoDePatrocinio(String codigoDePatrocinio) { this.codigoDePatrocinio = codigoDePatrocinio; }

    public Nivel getNivelDePatrocinio() { return nivelDePatrocinio; }
    public void setNivelDePatrocinio(Nivel nivelDePatrocinio) { this.nivelDePatrocinio = nivelDePatrocinio; }

    @Override
    public String toString() {
        return codigoDePatrocinio + " (" + nivelDePatrocinio + ")";
    }
}
