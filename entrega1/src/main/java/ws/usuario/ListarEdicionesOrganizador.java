
package ws.usuario;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para listarEdicionesOrganizador complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="listarEdicionesOrganizador">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nombreOrganizador" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listarEdicionesOrganizador", propOrder = {
    "nombreOrganizador"
})
public class ListarEdicionesOrganizador {

    protected String nombreOrganizador;

    /**
     * Obtiene el valor de la propiedad nombreOrganizador.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreOrganizador() {
        return nombreOrganizador;
    }

    /**
     * Define el valor de la propiedad nombreOrganizador.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreOrganizador(String value) {
        this.nombreOrganizador = value;
    }

}
