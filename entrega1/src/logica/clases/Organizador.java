	package logica.clases;

import java.util.HashMap;

public class Organizador extends Usuario {
    
    private String descripcionGeneral;
    private String linkSitioWeb; 
    private HashMap<String, EdicionEvento> edicionesOrganizadas;

    public Organizador(String nickname, String nombre, String correoElectronico,
                       String descripcionGeneral, String linkSitioWeb) {
        super(nickname, nombre, correoElectronico);
        this.descripcionGeneral = descripcionGeneral;
        this.linkSitioWeb = linkSitioWeb;
        this.edicionesOrganizadas = new HashMap<>();  
    }

    // Getters y Setters
    public String getDescripcionGeneral() {
        return descripcionGeneral;
    }

    public void setDescripcionGeneral(String descripcionGeneral) {
        this.descripcionGeneral = descripcionGeneral;
    }

    public String getLinkSitioWeb() {
        return linkSitioWeb;
    }

    public void setLinkSitioWeb(String linkSitioWeb) {
        this.linkSitioWeb = linkSitioWeb;
    }
    
    public void agregarEdicion(EdicionEvento edicion) {
        edicionesOrganizadas.put(edicion.getNombre(), edicion);
    }
    
    public HashMap<String, EdicionEvento> getEdicionesOrganizadas() {
        return edicionesOrganizadas;
    }
}
