
package ws.estado;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws.estado package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetFechaInicio_QNAME = new QName("http://publicar.ws/", "getFechaInicio");
    private final static QName _GetFechaInicioResponse_QNAME = new QName("http://publicar.ws/", "getFechaInicioResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws.estado
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetFechaInicio }
     * 
     * @return
     *     the new instance of {@link GetFechaInicio }
     */
    public GetFechaInicio createGetFechaInicio() {
        return new GetFechaInicio();
    }

    /**
     * Create an instance of {@link GetFechaInicioResponse }
     * 
     * @return
     *     the new instance of {@link GetFechaInicioResponse }
     */
    public GetFechaInicioResponse createGetFechaInicioResponse() {
        return new GetFechaInicioResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFechaInicio }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetFechaInicio }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "getFechaInicio")
    public JAXBElement<GetFechaInicio> createGetFechaInicio(GetFechaInicio value) {
        return new JAXBElement<>(_GetFechaInicio_QNAME, GetFechaInicio.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFechaInicioResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetFechaInicioResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "getFechaInicioResponse")
    public JAXBElement<GetFechaInicioResponse> createGetFechaInicioResponse(GetFechaInicioResponse value) {
        return new JAXBElement<>(_GetFechaInicioResponse_QNAME, GetFechaInicioResponse.class, null, value);
    }

}
