package logica.datatypes;

import java.time.LocalDate;

import logica.clases.Nivel;

public class DataPatrocinio {
    private LocalDate fechaDeRealizacion;
    private int montoAportado;
    private int cantRegistrosGratis;
    private String codigoDePatrocinio;
    private Nivel nivelDePatrocinio;

    public DataPatrocinio(LocalDate fechaDeRealizacion, int montoAportado, int cantRegistrosGratis, String codigoDePatrocinio, Nivel nivelDePatrocinio) {
        this.fechaDeRealizacion = fechaDeRealizacion;
        this.montoAportado = montoAportado;
        this.cantRegistrosGratis = cantRegistrosGratis;
        this.codigoDePatrocinio = codigoDePatrocinio;
        this.nivelDePatrocinio = nivelDePatrocinio;
    }

    public LocalDate getFechaDeRealizacion() {
        return fechaDeRealizacion;
    }

    public int getMontoAportado() {
        return montoAportado;
    }

    public int getCantRegistrosGratis() {
        return cantRegistrosGratis;
    }

    public String getCodigoDePatrocinio() {
        return codigoDePatrocinio;
    }

    public Nivel getNivelDePatrocinio() {
        return nivelDePatrocinio;
    }

    @Override
    public String toString() {
        return codigoDePatrocinio + " (" + nivelDePatrocinio + ")";
    }
}
