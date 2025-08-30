package logica.clases;

import java.time.LocalDate;


public class Registro {
    private LocalDate fechaRegistro;
    private String costoRegistro;
    private TipoRegistro tipoRegistro;
    private EdicionEvento edicionEvento;
    private Asistente asistente;

    public Registro(LocalDate fechaRegistro, String costoRegistro, TipoRegistro tipoRegistro, EdicionEvento edicionEvento, Asistente asistente) {
        this.fechaRegistro = fechaRegistro;
        this.costoRegistro = costoRegistro;
        this.tipoRegistro = tipoRegistro;
        this.edicionEvento = edicionEvento;
        this.asistente = asistente;
    }

    public LocalDate getFechaRegistro() { 
    	return fechaRegistro; 
    }
    public String getCostoRegistro() { 
    	return costoRegistro; 
    }
    public TipoRegistro getTipoRegistro() { 
    	return tipoRegistro; 
    }
    public EdicionEvento getEdicionEvento() { 
    	return edicionEvento; 
    }
    public Asistente getAsistente() { 
    	return asistente; 
    }
    }