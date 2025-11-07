
package ws.eventos;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para getInfoEdicion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="getInfoEdicion">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="idEdicion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getInfoEdicion", propOrder = {
    "idEdicion"
})
public class GetInfoEdicion {

    protected String idEdicion;

    /**
     * Obtiene el valor de la propiedad idEdicion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdEdicion() {
        return idEdicion;
    }

    /**
     * Define el valor de la propiedad idEdicion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdEdicion(String value) {
        this.idEdicion = value;
    }

}
