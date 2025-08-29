package logica.datatypes;

import logica.clases.Usuario;

public class DataOrganizador extends Usuario {
    
    private String descripcionGeneral;
    private String linkSitioWeb; 

    // Constructor
    public DataOrganizador(String nickname, String nombre, String correoElectronico,
                       String descripcionGeneral, String linkSitioWeb) {
        super(nickname, nombre, correoElectronico);
        this.descripcionGeneral = descripcionGeneral;
        this.linkSitioWeb = linkSitioWeb;
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
}