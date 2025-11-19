
package ws.eventos;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dataPatrocinio complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dataPatrocinio">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="fechaDeRealizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="montoAportado" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         <element name="cantRegistrosGratis" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         <element name="codigoDePatrocinio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nivelDePatrocinio" type="{http://publicar.ws/}nivel" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dataPatrocinio", propOrder = {
    "fechaDeRealizacion",
    "montoAportado",
    "cantRegistrosGratis",
    "codigoDePatrocinio",
    "nivelDePatrocinio"
})
public class DataPatrocinio {

    protected String fechaDeRealizacion;
    protected Integer montoAportado;
    protected Integer cantRegistrosGratis;
    protected String codigoDePatrocinio;
    @XmlSchemaType(name = "string")
    protected Nivel nivelDePatrocinio;

    /**
     * Obtiene el valor de la propiedad fechaDeRealizacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaDeRealizacion() {
        return fechaDeRealizacion;
    }

    /**
     * Define el valor de la propiedad fechaDeRealizacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaDeRealizacion(String value) {
        this.fechaDeRealizacion = value;
    }

    /**
     * Obtiene el valor de la propiedad montoAportado.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMontoAportado() {
        return montoAportado;
    }

    /**
     * Define el valor de la propiedad montoAportado.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMontoAportado(Integer value) {
        this.montoAportado = value;
    }

    /**
     * Obtiene el valor de la propiedad cantRegistrosGratis.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCantRegistrosGratis() {
        return cantRegistrosGratis;
    }

    /**
     * Define el valor de la propiedad cantRegistrosGratis.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCantRegistrosGratis(Integer value) {
        this.cantRegistrosGratis = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoDePatrocinio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoDePatrocinio() {
        return codigoDePatrocinio;
    }

    /**
     * Define el valor de la propiedad codigoDePatrocinio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoDePatrocinio(String value) {
        this.codigoDePatrocinio = value;
    }

    /**
     * Obtiene el valor de la propiedad nivelDePatrocinio.
     * 
     * @return
     *     possible object is
     *     {@link Nivel }
     *     
     */
    public Nivel getNivelDePatrocinio() {
        return nivelDePatrocinio;
    }

    /**
     * Define el valor de la propiedad nivelDePatrocinio.
     * 
     * @param value
     *     allowed object is
     *     {@link Nivel }
     *     
     */
    public void setNivelDePatrocinio(Nivel value) {
        this.nivelDePatrocinio = value;
    }

}
