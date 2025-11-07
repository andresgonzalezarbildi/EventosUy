
package ws.media;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws.media package. 
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

    private final static QName _IOException_QNAME = new QName("http://publicar.ws/", "IOException");
    private final static QName _ObtenerImagen_QNAME = new QName("http://publicar.ws/", "obtenerImagen");
    private final static QName _ObtenerImagenResponse_QNAME = new QName("http://publicar.ws/", "obtenerImagenResponse");
    private final static QName _SubirImagen_QNAME = new QName("http://publicar.ws/", "subirImagen");
    private final static QName _SubirImagenResponse_QNAME = new QName("http://publicar.ws/", "subirImagenResponse");
    private final static QName _SubirImagenImagenBytes_QNAME = new QName("", "imagenBytes");
    private final static QName _ObtenerImagenResponseReturn_QNAME = new QName("", "return");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws.media
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link IOException }
     * 
     * @return
     *     the new instance of {@link IOException }
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link ObtenerImagen }
     * 
     * @return
     *     the new instance of {@link ObtenerImagen }
     */
    public ObtenerImagen createObtenerImagen() {
        return new ObtenerImagen();
    }

    /**
     * Create an instance of {@link ObtenerImagenResponse }
     * 
     * @return
     *     the new instance of {@link ObtenerImagenResponse }
     */
    public ObtenerImagenResponse createObtenerImagenResponse() {
        return new ObtenerImagenResponse();
    }

    /**
     * Create an instance of {@link SubirImagen }
     * 
     * @return
     *     the new instance of {@link SubirImagen }
     */
    public SubirImagen createSubirImagen() {
        return new SubirImagen();
    }

    /**
     * Create an instance of {@link SubirImagenResponse }
     * 
     * @return
     *     the new instance of {@link SubirImagenResponse }
     */
    public SubirImagenResponse createSubirImagenResponse() {
        return new SubirImagenResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerImagen }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ObtenerImagen }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "obtenerImagen")
    public JAXBElement<ObtenerImagen> createObtenerImagen(ObtenerImagen value) {
        return new JAXBElement<>(_ObtenerImagen_QNAME, ObtenerImagen.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerImagenResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ObtenerImagenResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "obtenerImagenResponse")
    public JAXBElement<ObtenerImagenResponse> createObtenerImagenResponse(ObtenerImagenResponse value) {
        return new JAXBElement<>(_ObtenerImagenResponse_QNAME, ObtenerImagenResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubirImagen }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SubirImagen }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "subirImagen")
    public JAXBElement<SubirImagen> createSubirImagen(SubirImagen value) {
        return new JAXBElement<>(_SubirImagen_QNAME, SubirImagen.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubirImagenResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SubirImagenResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "subirImagenResponse")
    public JAXBElement<SubirImagenResponse> createSubirImagenResponse(SubirImagenResponse value) {
        return new JAXBElement<>(_SubirImagenResponse_QNAME, SubirImagenResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     */
    @XmlElementDecl(namespace = "", name = "imagenBytes", scope = SubirImagen.class)
    public JAXBElement<byte[]> createSubirImagenImagenBytes(byte[] value) {
        return new JAXBElement<>(_SubirImagenImagenBytes_QNAME, byte[].class, SubirImagen.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     */
    @XmlElementDecl(namespace = "", name = "return", scope = ObtenerImagenResponse.class)
    public JAXBElement<byte[]> createObtenerImagenResponseReturn(byte[] value) {
        return new JAXBElement<>(_ObtenerImagenResponseReturn_QNAME, byte[].class, ObtenerImagenResponse.class, ((byte[]) value));
    }

}
