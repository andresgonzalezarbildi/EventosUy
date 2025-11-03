
package ws.eventos;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para nivel.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>{@code
 * <simpleType name="nivel">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="PLATINO"/>
 *     <enumeration value="ORO"/>
 *     <enumeration value="PLATA"/>
 *     <enumeration value="BRONCE"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "nivel")
@XmlEnum
public enum Nivel {

    PLATINO,
    ORO,
    PLATA,
    BRONCE;

    public String value() {
        return name();
    }

    public static Nivel fromValue(String v) {
        return valueOf(v);
    }

}
