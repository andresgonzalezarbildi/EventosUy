package logica.clases;

import java.time.LocalDate;

public class Patrocinio {
    private LocalDate fechaDeRealizacion;
    private Integer montoAportado;
    private Integer cantRegistrosGratis;
    private String codigoDePatrocinio;
    private Nivel nivelDePatrocinio;

    public Patrocinio(LocalDate fechaDeRealizacion, Integer montoAportado, Integer cantRegistrosGratis, 
                      String codigoDePatrocinio, Nivel nivelDePatrocinio) {
        this.fechaDeRealizacion = fechaDeRealizacion;
        this.montoAportado = montoAportado;
        this.cantRegistrosGratis = cantRegistrosGratis;
        this.codigoDePatrocinio = codigoDePatrocinio;
        this.nivelDePatrocinio = nivelDePatrocinio;
    }

    public LocalDate getFechaDeRealizacion() { return fechaDeRealizacion; }
    public Integer getMontoAportado() { return montoAportado; }
    public Integer getCantRegistrosGratis() { return cantRegistrosGratis; }
    public String getCodigoDePatrocinio() { return codigoDePatrocinio; }
    public Nivel getNivelDePatrocinio() { return nivelDePatrocinio; }

    public void setFechaDeRealizacion(LocalDate fechaDeRealizacion) { this.fechaDeRealizacion = fechaDeRealizacion; }
    public void setMontoAportado(Integer montoAportado) { this.montoAportado = montoAportado; }
    public void setCantRegistrosGratis(Integer cantRegistrosGratis) { this.cantRegistrosGratis = cantRegistrosGratis; }
    public void setCodigoDePatrocinio(String codigoDePatrocinio) { this.codigoDePatrocinio = codigoDePatrocinio; }
    public void setNivelDePatrocinio(Nivel nivelDePatrocinio) { this.nivelDePatrocinio = nivelDePatrocinio; }
}
