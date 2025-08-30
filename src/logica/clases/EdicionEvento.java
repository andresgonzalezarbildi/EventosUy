package logica.clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logica.datatypes.DataPatrocinio;
import logica.datatypes.DataTipoRegistro;


public class EdicionEvento {

    private String nombre;
    private LocalDate fechaIni;
    private LocalDate fechaFin;
    private String ciudad;
    private String pais;
    private String sigla;
    private LocalDate fechaAltaEnPlataforma;
    private Map<String, TipoRegistro> listaTipoDeRegistro;
    private Organizador organizador;
    private List<Patrocinio> patrocinios;
    private Map<String, Registro> listaRegistros;


    public EdicionEvento(String nombre, LocalDate fechaIni, LocalDate fechaFin, String ciudad, String pais, String sigla, LocalDate fechaAltaEnPlataforma) {
        this.nombre = nombre;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.ciudad = ciudad;
        this.pais = pais;
        this.sigla = sigla;
        this.fechaAltaEnPlataforma = fechaAltaEnPlataforma;
        this.listaTipoDeRegistro = new HashMap<>();
        this.patrocinios = new ArrayList<>();
        this.listaRegistros = new HashMap<>();
    }

    public String getNombre() { return nombre; }
    public LocalDate getFechaIni() { return fechaIni; }
    public LocalDate getFechaFin() { return fechaFin; }
    public String getCiudad() { return ciudad; }
    public String getPais() { return pais; }
    public String getSigla() { return sigla; }
    public Organizador getOrganizador() { return organizador; }
    public List<Patrocinio> getPatrocinios() { return patrocinios; }
    public LocalDate getFechaAltaEnPlataforma() { return fechaAltaEnPlataforma; }
    public Map<String, TipoRegistro> getCategorias() { return listaTipoDeRegistro; }

    public void agregarTipoDeRegistro(String clave, TipoRegistro TipoDeRegistro) {
        if (!listaTipoDeRegistro.containsKey(clave)) {
            listaTipoDeRegistro.put(clave, TipoDeRegistro);
        }
    }

    public boolean existeTipoDeRegistro(String nombreTipoDeRegistro) {
        return listaTipoDeRegistro.containsKey(nombreTipoDeRegistro);
    }

    public TipoRegistro getTipoDeRegistro(String nombre) {
        return listaTipoDeRegistro.get(nombre);
    }

    public Map<String, TipoRegistro> getTipoDeRegistro() {
        return listaTipoDeRegistro;
    }
    
    public String getOrganizadorDTO() {
        return organizador != null ? organizador.getNickname() : "";
    }
    public List<DataTipoRegistro> getTiposRegistroDTO() {
        List<DataTipoRegistro> dtos = new ArrayList<>();
        for (TipoRegistro tr : listaTipoDeRegistro.values()) {
        	dtos.add(new DataTipoRegistro(tr.getNombre(), tr.getDescripcion(), tr.getCosto(), tr.getCupo()));
        }
        return dtos;
    }
    public List<DataPatrocinio> getPatrociniosDTO() {
        List<DataPatrocinio> dtos = new ArrayList<>();
        for (Patrocinio p : patrocinios) {
            dtos.add(new DataPatrocinio(
                p.getFechaDeRealizacion(),
                p.getMontoAportado(),
                p.getCantRegistrosGratis(),
                p.getCodigoDePatrocinio(),
                p.getNivelDePatrocinio()  
            ));
        }
        return dtos;
    }
    public void setOrganizador(Organizador o) { this.organizador = o; }
    public void agregarPatrocinio(Patrocinio p) { this.patrocinios.add(p); }
    
    public TipoRegistro getTipoRegistro(String nombre) {
        return listaTipoDeRegistro.get(nombre);
    }
    
    public boolean hayCupo(TipoRegistro tipo) {
    	long usados = listaRegistros.values().stream()
    	        .filter(r -> r.getTipoRegistro().equals(tipo))
    	        .count();
    	   return usados < tipo.getCupo();
    }
    public boolean estaRegistrado(Asistente a) {
        return listaRegistros.containsKey(a.getNickname());
    }
    public void agregarRegistro(Registro r) {
        listaRegistros.put(r.getAsistente().getNickname(), r);
    }
    

}
