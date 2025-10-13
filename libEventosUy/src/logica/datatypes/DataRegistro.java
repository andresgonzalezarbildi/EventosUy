package logica.datatypes;

import java.time.LocalDate;

public class DataRegistro {
    private final String evento;
    private final String edicion;
    private final String tipoRegistro;
    private final Integer costo;    
    private final LocalDate fecha;   
    private final String asistente;

    public DataRegistro(String evento, String edicion, String tipoRegistro, 
                        Integer costo, LocalDate fecha, String asistente) {
        this.evento = evento;
        this.edicion = edicion;
        this.tipoRegistro = tipoRegistro;
        this.costo = costo;
        this.fecha = fecha;
        this.asistente = asistente;
    }
    public String getEvento()       {
    	return evento; 
    }
    public String getEdicion()      { 
    	return edicion; 
    }
    public String getTipoRegistro() { 
    	return tipoRegistro; 
    }
    public Integer getCosto()       { 
    	return costo; 
    }
    public LocalDate getFecha()     { 
    	return fecha; 
    }
    public String getAsistente()    { 
    	return asistente; 
    }
    @Override
    public String toString() {
        return edicion; 
    }
}
