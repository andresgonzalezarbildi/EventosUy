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
  private Evento eventoPadre;
  private String imagen;
  private EstadoEdicion estado;
  private String video;


  public EdicionEvento(String nombre, LocalDate fechaIni, LocalDate fechaFin, String ciudad, String pais, String sigla, LocalDate fechaAltaEnPlataforma, String imagen, String video) {
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
    setImagen(imagen);
    this.estado = EstadoEdicion.INGRESADA;
    this.video = (video == null || video.isBlank()) ? "EventoSinFoto.png" : video;

    // Hardcodeado hasta que este el alta
    this.agregarPatrocinio(new Patrocinio(LocalDate.now(), 5000, 10, "PAT001", Nivel.ORO));
    this.agregarPatrocinio(new Patrocinio(LocalDate.now(), 2000, 5, "PAT002", Nivel.PLATA));  
  }
  
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
    setImagen(null);
    this.estado = EstadoEdicion.INGRESADA;
    // Hardcodeado hasta que este el alta
    this.agregarPatrocinio(new Patrocinio(LocalDate.now(), 5000, 10, "PAT001", Nivel.ORO));
    this.agregarPatrocinio(new Patrocinio(LocalDate.now(), 2000, 5, "PAT002", Nivel.PLATA));     
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
  
  public Organizador getOrganizador() {
    return organizador;
  } 
  
  public List<Patrocinio> getPatrocinios() {
    return patrocinios;
  }
  
  public LocalDate getFechaAltaEnPlataforma() {
    return fechaAltaEnPlataforma;
  }

  public void agregarTipoDeRegistro(String clave, TipoRegistro tipoDeRegistro) {
    if (!listaTipoDeRegistro.containsKey(clave)) {
      listaTipoDeRegistro.put(clave, tipoDeRegistro);
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
      dtos.add(new DataTipoRegistro(
          tr.getNombre(), tr.getDescripcion(), tr.getCosto(), tr.getCupo())
      );
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
  
  public void setOrganizador(Organizador organizador) { 
    this.organizador = organizador; 
  }
  
  public void agregarPatrocinio(Patrocinio patrocionio) { 
    this.patrocinios.add(patrocionio); 
  }
  
  public TipoRegistro getTipoRegistro(String nombre) {
    return listaTipoDeRegistro.get(nombre);
  }
  
  public boolean hayCupo(TipoRegistro tipo) {
    long usados = listaRegistros.values().stream()
        .filter(r -> r.getTipoRegistro().equals(tipo))
        .count();
    return usados < tipo.getCupo();
  }
  
  public boolean estaRegistrado(Asistente asistente) {
    return listaRegistros.containsKey(asistente.getNickname());
  }
  
  public void agregarRegistro(Registro registro) {
    listaRegistros.put(registro.getAsistente().getNickname(), registro);
  }

  @Override
  public String toString() {
    return nombre + " (" + fechaIni + ")";
  }
      
  public java.util.Collection<Registro> getRegistros() {
    return listaRegistros.values();
  }
  

  public void setEventoPadre(Evento evento) {
    this.eventoPadre = evento;
  }
  
  public Evento getEvento() {
    return eventoPadre;
  }
  
  public String getImagen() {
    return (imagen == null || imagen.isBlank()) ? "EdicionSinFoto.png" : imagen;
  }
  
  public void setImagen(String imagen) {
    this.imagen = (imagen == null || imagen.isBlank()) ? "EdicionSinFoto.png" : imagen;
  }
  
  public EstadoEdicion getEstado() { 
    return estado; 
  }
  
  public String getEstadoString() { 
    return estado.name().toLowerCase(); 
  }
  
  public void setEstado(EstadoEdicion estado) {
    if (estado == null) {
      throw new IllegalArgumentException("Estado nulo");
    }
    this.estado = estado;
  }
  
  public void aceptar(){
    if (estado == EstadoEdicion.ACEPTADA) { 
      return;
    }
    this.estado = EstadoEdicion.ACEPTADA;
  }

  public void rechazar() {
    if (estado == EstadoEdicion.RECHAZADA) {
      return;
    }
    this.estado = EstadoEdicion.RECHAZADA;
  }
  
  public void setVideo(String video) {
	  this.video = video;
  }
  
  public String getVideo() {
	  return this.video;
  }
}
