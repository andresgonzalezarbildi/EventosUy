package logica.clases;

import java.time.LocalDate;


public class Registro {
    private LocalDate fechaRegistro;
    private Integer costoRegistro;
    private TipoRegistro tipoRegistro;
    private EdicionEvento edicionEvento;
    private Asistente asistente;
    private boolean confirmarAsistentcia;

    public Registro(LocalDate fechaRegistro, Integer costoRegistro, TipoRegistro tipoRegistro, EdicionEvento edicionEvento, Asistente asistente) {
        this.fechaRegistro = fechaRegistro;
        this.costoRegistro = costoRegistro;
        this.tipoRegistro = tipoRegistro;
        this.edicionEvento = edicionEvento;
        this.asistente = asistente;
        this.confirmarAsistentcia = false;
    }

    public LocalDate getFechaRegistro() { 
    	return fechaRegistro; 
    }
    public Integer getCostoRegistro() { 
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
    public boolean getConfirmarAsistencia() { 
    	return confirmarAsistentcia; 
    }
    public void setCosto(int precio) { 
    	this.costoRegistro = precio;
    }
    public void setConfirmarAsistencia() {
    	this.confirmarAsistentcia = true;
    }
    
}