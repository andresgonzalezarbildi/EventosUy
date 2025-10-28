
package ws.eventos;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws.eventos package. 
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

    private final static QName _EventoNoExisteFault_QNAME = new QName("http://publicar.wz/", "EventoNoExisteFault");
    private final static QName _ListarCategorias_QNAME = new QName("http://publicar.wz/", "listarCategorias");
    private final static QName _ListarCategoriasResponse_QNAME = new QName("http://publicar.wz/", "listarCategoriasResponse");
    private final static QName _ListarEventoExistentes_QNAME = new QName("http://publicar.wz/", "listarEventoExistentes");
    private final static QName _ListarEventoExistentesResponse_QNAME = new QName("http://publicar.wz/", "listarEventoExistentesResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws.eventos
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EventoNoExisteFault }
     * 
     * @return
     *     the new instance of {@link EventoNoExisteFault }
     */
    public EventoNoExisteFault createEventoNoExisteFault() {
        return new EventoNoExisteFault();
    }

    /**
     * Create an instance of {@link ListarCategorias }
     * 
     * @return
     *     the new instance of {@link ListarCategorias }
     */
    public ListarCategorias createListarCategorias() {
        return new ListarCategorias();
    }

    /**
     * Create an instance of {@link ListarCategoriasResponse }
     * 
     * @return
     *     the new instance of {@link ListarCategoriasResponse }
     */
    public ListarCategoriasResponse createListarCategoriasResponse() {
        return new ListarCategoriasResponse();
    }

    /**
     * Create an instance of {@link ListarEventoExistentes }
     * 
     * @return
     *     the new instance of {@link ListarEventoExistentes }
     */
    public ListarEventoExistentes createListarEventoExistentes() {
        return new ListarEventoExistentes();
    }

    /**
     * Create an instance of {@link ListarEventoExistentesResponse }
     * 
     * @return
     *     the new instance of {@link ListarEventoExistentesResponse }
     */
    public ListarEventoExistentesResponse createListarEventoExistentesResponse() {
        return new ListarEventoExistentesResponse();
    }

    /**
     * Create an instance of {@link DataEvento }
     * 
     * @return
     *     the new instance of {@link DataEvento }
     */
    public DataEvento createDataEvento() {
        return new DataEvento();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EventoNoExisteFault }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EventoNoExisteFault }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.wz/", name = "EventoNoExisteFault")
    public JAXBElement<EventoNoExisteFault> createEventoNoExisteFault(EventoNoExisteFault value) {
        return new JAXBElement<>(_EventoNoExisteFault_QNAME, EventoNoExisteFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarCategorias }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarCategorias }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.wz/", name = "listarCategorias")
    public JAXBElement<ListarCategorias> createListarCategorias(ListarCategorias value) {
        return new JAXBElement<>(_ListarCategorias_QNAME, ListarCategorias.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarCategoriasResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarCategoriasResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.wz/", name = "listarCategoriasResponse")
    public JAXBElement<ListarCategoriasResponse> createListarCategoriasResponse(ListarCategoriasResponse value) {
        return new JAXBElement<>(_ListarCategoriasResponse_QNAME, ListarCategoriasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarEventoExistentes }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarEventoExistentes }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.wz/", name = "listarEventoExistentes")
    public JAXBElement<ListarEventoExistentes> createListarEventoExistentes(ListarEventoExistentes value) {
        return new JAXBElement<>(_ListarEventoExistentes_QNAME, ListarEventoExistentes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarEventoExistentesResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarEventoExistentesResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.wz/", name = "listarEventoExistentesResponse")
    public JAXBElement<ListarEventoExistentesResponse> createListarEventoExistentesResponse(ListarEventoExistentesResponse value) {
        return new JAXBElement<>(_ListarEventoExistentesResponse_QNAME, ListarEventoExistentesResponse.class, null, value);
    }

}
