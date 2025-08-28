package logica.clases;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class EdicionEvento {
	private String nombre;
	private LocalDate fechaIni;
	private LocalDate fechaFin;
	private String ciudad;
	private String pais;
	private String sigla;
	private LocalDate fechaAltaEnPlataforma;
	private Map<String, TipoRegistro> listaTipoRegistro;
	
	public EdicionEvento(String nombre, LocalDate fechaIni, LocalDate fechaFin, String ciudad, String pais, String sigla, LocalDate fechaAltaEnPlataforma) {
		this.nombre = nombre;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
	    this.ciudad = ciudad;
	    this.pais = pais;
	    this.sigla = sigla;
	    this.fechaAltaEnPlataforma = fechaAltaEnPlataforma;
	    this.listaTipoRegistro = new HashMap<>();
	    
	}
	
	public String getNombre() {
	    return nombre;
	}

	public LocalDate getFechaIni() {
	    return fechaIni;
	}

	public LocalDate getFechaFin() {
	    return fechaFin;
	}

	public String getCiudad() {
	    return ciudad;
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
	
	public Map<String, TipoRegistro> getCategorias() { 
		return listaTipoRegistro ;
	}
	
	public void agregarTipoRegistro(String clave, TipoRegistro tipoRegistro) {
	    if (!listaTipoRegistro.containsKey(clave)) {
	        listaTipoRegistro.put(clave, tipoRegistro);
	    }
	}

	
	

	
	
}
