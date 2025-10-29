
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

    private final static QName _CategoriaRepetidaFault_QNAME = new QName("http://publicar.ws/", "CategoriaRepetidaFault");
    private final static QName _EdicionNoExisteFault_QNAME = new QName("http://publicar.ws/", "EdicionNoExisteFault");
    private final static QName _EventoNoExisteFault_QNAME = new QName("http://publicar.ws/", "EventoNoExisteFault");
    private final static QName _EventoRepetidoFault_QNAME = new QName("http://publicar.ws/", "EventoRepetidoFault");
    private final static QName _TipoRegistroRepetidoFault_QNAME = new QName("http://publicar.ws/", "TipoRegistroRepetidoFault");
    private final static QName _TransicionEstadoInvalidaFault_QNAME = new QName("http://publicar.ws/", "TransicionEstadoInvalidaFault");
    private final static QName _UsuarioNoExisteFault_QNAME = new QName("http://publicar.ws/", "UsuarioNoExisteFault");
    private final static QName _AceptarEdicion_QNAME = new QName("http://publicar.ws/", "aceptarEdicion");
    private final static QName _AceptarEdicionResponse_QNAME = new QName("http://publicar.ws/", "aceptarEdicionResponse");
    private final static QName _AltaCategoria_QNAME = new QName("http://publicar.ws/", "altaCategoria");
    private final static QName _AltaCategoriaResponse_QNAME = new QName("http://publicar.ws/", "altaCategoriaResponse");
    private final static QName _AltaEdicionEvento_QNAME = new QName("http://publicar.ws/", "altaEdicionEvento");
    private final static QName _AltaEdicionEventoResponse_QNAME = new QName("http://publicar.ws/", "altaEdicionEventoResponse");
    private final static QName _AltaEvento_QNAME = new QName("http://publicar.ws/", "altaEvento");
    private final static QName _AltaEventoResponse_QNAME = new QName("http://publicar.ws/", "altaEventoResponse");
    private final static QName _AltaRegistro_QNAME = new QName("http://publicar.ws/", "altaRegistro");
    private final static QName _AltaRegistroResponse_QNAME = new QName("http://publicar.ws/", "altaRegistroResponse");
    private final static QName _AltaTipoRegistro_QNAME = new QName("http://publicar.ws/", "altaTipoRegistro");
    private final static QName _AltaTipoRegistroResponse_QNAME = new QName("http://publicar.ws/", "altaTipoRegistroResponse");
    private final static QName _GetEventoDeUnaEdicion_QNAME = new QName("http://publicar.ws/", "getEventoDeUnaEdicion");
    private final static QName _GetEventoDeUnaEdicionResponse_QNAME = new QName("http://publicar.ws/", "getEventoDeUnaEdicionResponse");
    private final static QName _GetEventosDTO_QNAME = new QName("http://publicar.ws/", "getEventosDTO");
    private final static QName _GetEventosDTOResponse_QNAME = new QName("http://publicar.ws/", "getEventosDTOResponse");
    private final static QName _GetInfoEdicion_QNAME = new QName("http://publicar.ws/", "getInfoEdicion");
    private final static QName _GetInfoEdicionResponse_QNAME = new QName("http://publicar.ws/", "getInfoEdicionResponse");
    private final static QName _GetTipoRegistro_QNAME = new QName("http://publicar.ws/", "getTipoRegistro");
    private final static QName _GetTipoRegistroResponse_QNAME = new QName("http://publicar.ws/", "getTipoRegistroResponse");
    private final static QName _GetUnEventoDTO_QNAME = new QName("http://publicar.ws/", "getUnEventoDTO");
    private final static QName _GetUnEventoDTOResponse_QNAME = new QName("http://publicar.ws/", "getUnEventoDTOResponse");
    private final static QName _ListarCategorias_QNAME = new QName("http://publicar.ws/", "listarCategorias");
    private final static QName _ListarCategoriasResponse_QNAME = new QName("http://publicar.ws/", "listarCategoriasResponse");
    private final static QName _ListarEdiciones_QNAME = new QName("http://publicar.ws/", "listarEdiciones");
    private final static QName _ListarEdicionesAceptadas_QNAME = new QName("http://publicar.ws/", "listarEdicionesAceptadas");
    private final static QName _ListarEdicionesAceptadasEvento_QNAME = new QName("http://publicar.ws/", "listarEdicionesAceptadasEvento");
    private final static QName _ListarEdicionesAceptadasEventoResponse_QNAME = new QName("http://publicar.ws/", "listarEdicionesAceptadasEventoResponse");
    private final static QName _ListarEdicionesAceptadasResponse_QNAME = new QName("http://publicar.ws/", "listarEdicionesAceptadasResponse");
    private final static QName _ListarEdicionesIngresadas_QNAME = new QName("http://publicar.ws/", "listarEdicionesIngresadas");
    private final static QName _ListarEdicionesIngresadasEvento_QNAME = new QName("http://publicar.ws/", "listarEdicionesIngresadasEvento");
    private final static QName _ListarEdicionesIngresadasEventoResponse_QNAME = new QName("http://publicar.ws/", "listarEdicionesIngresadasEventoResponse");
    private final static QName _ListarEdicionesIngresadasResponse_QNAME = new QName("http://publicar.ws/", "listarEdicionesIngresadasResponse");
    private final static QName _ListarEdicionesOrganizador_QNAME = new QName("http://publicar.ws/", "listarEdicionesOrganizador");
    private final static QName _ListarEdicionesOrganizadorAceptadas_QNAME = new QName("http://publicar.ws/", "listarEdicionesOrganizadorAceptadas");
    private final static QName _ListarEdicionesOrganizadorAceptadasResponse_QNAME = new QName("http://publicar.ws/", "listarEdicionesOrganizadorAceptadasResponse");
    private final static QName _ListarEdicionesOrganizadorResponse_QNAME = new QName("http://publicar.ws/", "listarEdicionesOrganizadorResponse");
    private final static QName _ListarEdicionesRechazadas_QNAME = new QName("http://publicar.ws/", "listarEdicionesRechazadas");
    private final static QName _ListarEdicionesRechazadasEvento_QNAME = new QName("http://publicar.ws/", "listarEdicionesRechazadasEvento");
    private final static QName _ListarEdicionesRechazadasEventoResponse_QNAME = new QName("http://publicar.ws/", "listarEdicionesRechazadasEventoResponse");
    private final static QName _ListarEdicionesRechazadasResponse_QNAME = new QName("http://publicar.ws/", "listarEdicionesRechazadasResponse");
    private final static QName _ListarEdicionesResponse_QNAME = new QName("http://publicar.ws/", "listarEdicionesResponse");
    private final static QName _ListarEventoExistentes_QNAME = new QName("http://publicar.ws/", "listarEventoExistentes");
    private final static QName _ListarEventoExistentesResponse_QNAME = new QName("http://publicar.ws/", "listarEventoExistentesResponse");
    private final static QName _ListarRegistrosDeEdicion_QNAME = new QName("http://publicar.ws/", "listarRegistrosDeEdicion");
    private final static QName _ListarRegistrosDeEdicionResponse_QNAME = new QName("http://publicar.ws/", "listarRegistrosDeEdicionResponse");
    private final static QName _ListarRegistrosDeUsuario_QNAME = new QName("http://publicar.ws/", "listarRegistrosDeUsuario");
    private final static QName _ListarRegistrosDeUsuarioResponse_QNAME = new QName("http://publicar.ws/", "listarRegistrosDeUsuarioResponse");
    private final static QName _ListarTiposRegistro_QNAME = new QName("http://publicar.ws/", "listarTiposRegistro");
    private final static QName _ListarTiposRegistroResponse_QNAME = new QName("http://publicar.ws/", "listarTiposRegistroResponse");
    private final static QName _ListarUnRegistroDeUsuario_QNAME = new QName("http://publicar.ws/", "listarUnRegistroDeUsuario");
    private final static QName _ListarUnRegistroDeUsuarioResponse_QNAME = new QName("http://publicar.ws/", "listarUnRegistroDeUsuarioResponse");
    private final static QName _SetCostoRegistro_QNAME = new QName("http://publicar.ws/", "setCostoRegistro");
    private final static QName _SetCostoRegistroResponse_QNAME = new QName("http://publicar.ws/", "setCostoRegistroResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws.eventos
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CategoriaRepetidaFault }
     * 
     * @return
     *     the new instance of {@link CategoriaRepetidaFault }
     */
    public CategoriaRepetidaFault createCategoriaRepetidaFault() {
        return new CategoriaRepetidaFault();
    }

    /**
     * Create an instance of {@link EdicionNoExisteFault }
     * 
     * @return
     *     the new instance of {@link EdicionNoExisteFault }
     */
    public EdicionNoExisteFault createEdicionNoExisteFault() {
        return new EdicionNoExisteFault();
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
     * Create an instance of {@link EventoRepetidoFault }
     * 
     * @return
     *     the new instance of {@link EventoRepetidoFault }
     */
    public EventoRepetidoFault createEventoRepetidoFault() {
        return new EventoRepetidoFault();
    }

    /**
     * Create an instance of {@link TipoRegistroRepetidoFault }
     * 
     * @return
     *     the new instance of {@link TipoRegistroRepetidoFault }
     */
    public TipoRegistroRepetidoFault createTipoRegistroRepetidoFault() {
        return new TipoRegistroRepetidoFault();
    }

    /**
     * Create an instance of {@link TransicionEstadoInvalidaFault }
     * 
     * @return
     *     the new instance of {@link TransicionEstadoInvalidaFault }
     */
    public TransicionEstadoInvalidaFault createTransicionEstadoInvalidaFault() {
        return new TransicionEstadoInvalidaFault();
    }

    /**
     * Create an instance of {@link UsuarioNoExisteFault }
     * 
     * @return
     *     the new instance of {@link UsuarioNoExisteFault }
     */
    public UsuarioNoExisteFault createUsuarioNoExisteFault() {
        return new UsuarioNoExisteFault();
    }

    /**
     * Create an instance of {@link AceptarEdicion }
     * 
     * @return
     *     the new instance of {@link AceptarEdicion }
     */
    public AceptarEdicion createAceptarEdicion() {
        return new AceptarEdicion();
    }

    /**
     * Create an instance of {@link AceptarEdicionResponse }
     * 
     * @return
     *     the new instance of {@link AceptarEdicionResponse }
     */
    public AceptarEdicionResponse createAceptarEdicionResponse() {
        return new AceptarEdicionResponse();
    }

    /**
     * Create an instance of {@link AltaCategoria }
     * 
     * @return
     *     the new instance of {@link AltaCategoria }
     */
    public AltaCategoria createAltaCategoria() {
        return new AltaCategoria();
    }

    /**
     * Create an instance of {@link AltaCategoriaResponse }
     * 
     * @return
     *     the new instance of {@link AltaCategoriaResponse }
     */
    public AltaCategoriaResponse createAltaCategoriaResponse() {
        return new AltaCategoriaResponse();
    }

    /**
     * Create an instance of {@link AltaEdicionEvento }
     * 
     * @return
     *     the new instance of {@link AltaEdicionEvento }
     */
    public AltaEdicionEvento createAltaEdicionEvento() {
        return new AltaEdicionEvento();
    }

    /**
     * Create an instance of {@link AltaEdicionEventoResponse }
     * 
     * @return
     *     the new instance of {@link AltaEdicionEventoResponse }
     */
    public AltaEdicionEventoResponse createAltaEdicionEventoResponse() {
        return new AltaEdicionEventoResponse();
    }

    /**
     * Create an instance of {@link AltaEvento }
     * 
     * @return
     *     the new instance of {@link AltaEvento }
     */
    public AltaEvento createAltaEvento() {
        return new AltaEvento();
    }

    /**
     * Create an instance of {@link AltaEventoResponse }
     * 
     * @return
     *     the new instance of {@link AltaEventoResponse }
     */
    public AltaEventoResponse createAltaEventoResponse() {
        return new AltaEventoResponse();
    }

    /**
     * Create an instance of {@link AltaRegistro }
     * 
     * @return
     *     the new instance of {@link AltaRegistro }
     */
    public AltaRegistro createAltaRegistro() {
        return new AltaRegistro();
    }

    /**
     * Create an instance of {@link AltaRegistroResponse }
     * 
     * @return
     *     the new instance of {@link AltaRegistroResponse }
     */
    public AltaRegistroResponse createAltaRegistroResponse() {
        return new AltaRegistroResponse();
    }

    /**
     * Create an instance of {@link AltaTipoRegistro }
     * 
     * @return
     *     the new instance of {@link AltaTipoRegistro }
     */
    public AltaTipoRegistro createAltaTipoRegistro() {
        return new AltaTipoRegistro();
    }

    /**
     * Create an instance of {@link AltaTipoRegistroResponse }
     * 
     * @return
     *     the new instance of {@link AltaTipoRegistroResponse }
     */
    public AltaTipoRegistroResponse createAltaTipoRegistroResponse() {
        return new AltaTipoRegistroResponse();
    }

    /**
     * Create an instance of {@link GetEventoDeUnaEdicion }
     * 
     * @return
     *     the new instance of {@link GetEventoDeUnaEdicion }
     */
    public GetEventoDeUnaEdicion createGetEventoDeUnaEdicion() {
        return new GetEventoDeUnaEdicion();
    }

    /**
     * Create an instance of {@link GetEventoDeUnaEdicionResponse }
     * 
     * @return
     *     the new instance of {@link GetEventoDeUnaEdicionResponse }
     */
    public GetEventoDeUnaEdicionResponse createGetEventoDeUnaEdicionResponse() {
        return new GetEventoDeUnaEdicionResponse();
    }

    /**
     * Create an instance of {@link GetEventosDTO }
     * 
     * @return
     *     the new instance of {@link GetEventosDTO }
     */
    public GetEventosDTO createGetEventosDTO() {
        return new GetEventosDTO();
    }

    /**
     * Create an instance of {@link GetEventosDTOResponse }
     * 
     * @return
     *     the new instance of {@link GetEventosDTOResponse }
     */
    public GetEventosDTOResponse createGetEventosDTOResponse() {
        return new GetEventosDTOResponse();
    }

    /**
     * Create an instance of {@link GetInfoEdicion }
     * 
     * @return
     *     the new instance of {@link GetInfoEdicion }
     */
    public GetInfoEdicion createGetInfoEdicion() {
        return new GetInfoEdicion();
    }

    /**
     * Create an instance of {@link GetInfoEdicionResponse }
     * 
     * @return
     *     the new instance of {@link GetInfoEdicionResponse }
     */
    public GetInfoEdicionResponse createGetInfoEdicionResponse() {
        return new GetInfoEdicionResponse();
    }

    /**
     * Create an instance of {@link GetTipoRegistro }
     * 
     * @return
     *     the new instance of {@link GetTipoRegistro }
     */
    public GetTipoRegistro createGetTipoRegistro() {
        return new GetTipoRegistro();
    }

    /**
     * Create an instance of {@link GetTipoRegistroResponse }
     * 
     * @return
     *     the new instance of {@link GetTipoRegistroResponse }
     */
    public GetTipoRegistroResponse createGetTipoRegistroResponse() {
        return new GetTipoRegistroResponse();
    }

    /**
     * Create an instance of {@link GetUnEventoDTO }
     * 
     * @return
     *     the new instance of {@link GetUnEventoDTO }
     */
    public GetUnEventoDTO createGetUnEventoDTO() {
        return new GetUnEventoDTO();
    }

    /**
     * Create an instance of {@link GetUnEventoDTOResponse }
     * 
     * @return
     *     the new instance of {@link GetUnEventoDTOResponse }
     */
    public GetUnEventoDTOResponse createGetUnEventoDTOResponse() {
        return new GetUnEventoDTOResponse();
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
     * Create an instance of {@link ListarEdiciones }
     * 
     * @return
     *     the new instance of {@link ListarEdiciones }
     */
    public ListarEdiciones createListarEdiciones() {
        return new ListarEdiciones();
    }

    /**
     * Create an instance of {@link ListarEdicionesAceptadas }
     * 
     * @return
     *     the new instance of {@link ListarEdicionesAceptadas }
     */
    public ListarEdicionesAceptadas createListarEdicionesAceptadas() {
        return new ListarEdicionesAceptadas();
    }

    /**
     * Create an instance of {@link ListarEdicionesAceptadasEvento }
     * 
     * @return
     *     the new instance of {@link ListarEdicionesAceptadasEvento }
     */
    public ListarEdicionesAceptadasEvento createListarEdicionesAceptadasEvento() {
        return new ListarEdicionesAceptadasEvento();
    }

    /**
     * Create an instance of {@link ListarEdicionesAceptadasEventoResponse }
     * 
     * @return
     *     the new instance of {@link ListarEdicionesAceptadasEventoResponse }
     */
    public ListarEdicionesAceptadasEventoResponse createListarEdicionesAceptadasEventoResponse() {
        return new ListarEdicionesAceptadasEventoResponse();
    }

    /**
     * Create an instance of {@link ListarEdicionesAceptadasResponse }
     * 
     * @return
     *     the new instance of {@link ListarEdicionesAceptadasResponse }
     */
    public ListarEdicionesAceptadasResponse createListarEdicionesAceptadasResponse() {
        return new ListarEdicionesAceptadasResponse();
    }

    /**
     * Create an instance of {@link ListarEdicionesIngresadas }
     * 
     * @return
     *     the new instance of {@link ListarEdicionesIngresadas }
     */
    public ListarEdicionesIngresadas createListarEdicionesIngresadas() {
        return new ListarEdicionesIngresadas();
    }

    /**
     * Create an instance of {@link ListarEdicionesIngresadasEvento }
     * 
     * @return
     *     the new instance of {@link ListarEdicionesIngresadasEvento }
     */
    public ListarEdicionesIngresadasEvento createListarEdicionesIngresadasEvento() {
        return new ListarEdicionesIngresadasEvento();
    }

    /**
     * Create an instance of {@link ListarEdicionesIngresadasEventoResponse }
     * 
     * @return
     *     the new instance of {@link ListarEdicionesIngresadasEventoResponse }
     */
    public ListarEdicionesIngresadasEventoResponse createListarEdicionesIngresadasEventoResponse() {
        return new ListarEdicionesIngresadasEventoResponse();
    }

    /**
     * Create an instance of {@link ListarEdicionesIngresadasResponse }
     * 
     * @return
     *     the new instance of {@link ListarEdicionesIngresadasResponse }
     */
    public ListarEdicionesIngresadasResponse createListarEdicionesIngresadasResponse() {
        return new ListarEdicionesIngresadasResponse();
    }

    /**
     * Create an instance of {@link ListarEdicionesOrganizador }
     * 
     * @return
     *     the new instance of {@link ListarEdicionesOrganizador }
     */
    public ListarEdicionesOrganizador createListarEdicionesOrganizador() {
        return new ListarEdicionesOrganizador();
    }

    /**
     * Create an instance of {@link ListarEdicionesOrganizadorAceptadas }
     * 
     * @return
     *     the new instance of {@link ListarEdicionesOrganizadorAceptadas }
     */
    public ListarEdicionesOrganizadorAceptadas createListarEdicionesOrganizadorAceptadas() {
        return new ListarEdicionesOrganizadorAceptadas();
    }

    /**
     * Create an instance of {@link ListarEdicionesOrganizadorAceptadasResponse }
     * 
     * @return
     *     the new instance of {@link ListarEdicionesOrganizadorAceptadasResponse }
     */
    public ListarEdicionesOrganizadorAceptadasResponse createListarEdicionesOrganizadorAceptadasResponse() {
        return new ListarEdicionesOrganizadorAceptadasResponse();
    }

    /**
     * Create an instance of {@link ListarEdicionesOrganizadorResponse }
     * 
     * @return
     *     the new instance of {@link ListarEdicionesOrganizadorResponse }
     */
    public ListarEdicionesOrganizadorResponse createListarEdicionesOrganizadorResponse() {
        return new ListarEdicionesOrganizadorResponse();
    }

    /**
     * Create an instance of {@link ListarEdicionesRechazadas }
     * 
     * @return
     *     the new instance of {@link ListarEdicionesRechazadas }
     */
    public ListarEdicionesRechazadas createListarEdicionesRechazadas() {
        return new ListarEdicionesRechazadas();
    }

    /**
     * Create an instance of {@link ListarEdicionesRechazadasEvento }
     * 
     * @return
     *     the new instance of {@link ListarEdicionesRechazadasEvento }
     */
    public ListarEdicionesRechazadasEvento createListarEdicionesRechazadasEvento() {
        return new ListarEdicionesRechazadasEvento();
    }

    /**
     * Create an instance of {@link ListarEdicionesRechazadasEventoResponse }
     * 
     * @return
     *     the new instance of {@link ListarEdicionesRechazadasEventoResponse }
     */
    public ListarEdicionesRechazadasEventoResponse createListarEdicionesRechazadasEventoResponse() {
        return new ListarEdicionesRechazadasEventoResponse();
    }

    /**
     * Create an instance of {@link ListarEdicionesRechazadasResponse }
     * 
     * @return
     *     the new instance of {@link ListarEdicionesRechazadasResponse }
     */
    public ListarEdicionesRechazadasResponse createListarEdicionesRechazadasResponse() {
        return new ListarEdicionesRechazadasResponse();
    }

    /**
     * Create an instance of {@link ListarEdicionesResponse }
     * 
     * @return
     *     the new instance of {@link ListarEdicionesResponse }
     */
    public ListarEdicionesResponse createListarEdicionesResponse() {
        return new ListarEdicionesResponse();
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
     * Create an instance of {@link ListarRegistrosDeEdicion }
     * 
     * @return
     *     the new instance of {@link ListarRegistrosDeEdicion }
     */
    public ListarRegistrosDeEdicion createListarRegistrosDeEdicion() {
        return new ListarRegistrosDeEdicion();
    }

    /**
     * Create an instance of {@link ListarRegistrosDeEdicionResponse }
     * 
     * @return
     *     the new instance of {@link ListarRegistrosDeEdicionResponse }
     */
    public ListarRegistrosDeEdicionResponse createListarRegistrosDeEdicionResponse() {
        return new ListarRegistrosDeEdicionResponse();
    }

    /**
     * Create an instance of {@link ListarRegistrosDeUsuario }
     * 
     * @return
     *     the new instance of {@link ListarRegistrosDeUsuario }
     */
    public ListarRegistrosDeUsuario createListarRegistrosDeUsuario() {
        return new ListarRegistrosDeUsuario();
    }

    /**
     * Create an instance of {@link ListarRegistrosDeUsuarioResponse }
     * 
     * @return
     *     the new instance of {@link ListarRegistrosDeUsuarioResponse }
     */
    public ListarRegistrosDeUsuarioResponse createListarRegistrosDeUsuarioResponse() {
        return new ListarRegistrosDeUsuarioResponse();
    }

    /**
     * Create an instance of {@link ListarTiposRegistro }
     * 
     * @return
     *     the new instance of {@link ListarTiposRegistro }
     */
    public ListarTiposRegistro createListarTiposRegistro() {
        return new ListarTiposRegistro();
    }

    /**
     * Create an instance of {@link ListarTiposRegistroResponse }
     * 
     * @return
     *     the new instance of {@link ListarTiposRegistroResponse }
     */
    public ListarTiposRegistroResponse createListarTiposRegistroResponse() {
        return new ListarTiposRegistroResponse();
    }

    /**
     * Create an instance of {@link ListarUnRegistroDeUsuario }
     * 
     * @return
     *     the new instance of {@link ListarUnRegistroDeUsuario }
     */
    public ListarUnRegistroDeUsuario createListarUnRegistroDeUsuario() {
        return new ListarUnRegistroDeUsuario();
    }

    /**
     * Create an instance of {@link ListarUnRegistroDeUsuarioResponse }
     * 
     * @return
     *     the new instance of {@link ListarUnRegistroDeUsuarioResponse }
     */
    public ListarUnRegistroDeUsuarioResponse createListarUnRegistroDeUsuarioResponse() {
        return new ListarUnRegistroDeUsuarioResponse();
    }

    /**
     * Create an instance of {@link SetCostoRegistro }
     * 
     * @return
     *     the new instance of {@link SetCostoRegistro }
     */
    public SetCostoRegistro createSetCostoRegistro() {
        return new SetCostoRegistro();
    }

    /**
     * Create an instance of {@link SetCostoRegistroResponse }
     * 
     * @return
     *     the new instance of {@link SetCostoRegistroResponse }
     */
    public SetCostoRegistroResponse createSetCostoRegistroResponse() {
        return new SetCostoRegistroResponse();
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
     * Create an instance of {@link DataEdicion }
     * 
     * @return
     *     the new instance of {@link DataEdicion }
     */
    public DataEdicion createDataEdicion() {
        return new DataEdicion();
    }

    /**
     * Create an instance of {@link DataTipoRegistro }
     * 
     * @return
     *     the new instance of {@link DataTipoRegistro }
     */
    public DataTipoRegistro createDataTipoRegistro() {
        return new DataTipoRegistro();
    }

    /**
     * Create an instance of {@link DataPatrocinio }
     * 
     * @return
     *     the new instance of {@link DataPatrocinio }
     */
    public DataPatrocinio createDataPatrocinio() {
        return new DataPatrocinio();
    }

    /**
     * Create an instance of {@link DataRegistro }
     * 
     * @return
     *     the new instance of {@link DataRegistro }
     */
    public DataRegistro createDataRegistro() {
        return new DataRegistro();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CategoriaRepetidaFault }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CategoriaRepetidaFault }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "CategoriaRepetidaFault")
    public JAXBElement<CategoriaRepetidaFault> createCategoriaRepetidaFault(CategoriaRepetidaFault value) {
        return new JAXBElement<>(_CategoriaRepetidaFault_QNAME, CategoriaRepetidaFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EdicionNoExisteFault }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EdicionNoExisteFault }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "EdicionNoExisteFault")
    public JAXBElement<EdicionNoExisteFault> createEdicionNoExisteFault(EdicionNoExisteFault value) {
        return new JAXBElement<>(_EdicionNoExisteFault_QNAME, EdicionNoExisteFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EventoNoExisteFault }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EventoNoExisteFault }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "EventoNoExisteFault")
    public JAXBElement<EventoNoExisteFault> createEventoNoExisteFault(EventoNoExisteFault value) {
        return new JAXBElement<>(_EventoNoExisteFault_QNAME, EventoNoExisteFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EventoRepetidoFault }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EventoRepetidoFault }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "EventoRepetidoFault")
    public JAXBElement<EventoRepetidoFault> createEventoRepetidoFault(EventoRepetidoFault value) {
        return new JAXBElement<>(_EventoRepetidoFault_QNAME, EventoRepetidoFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TipoRegistroRepetidoFault }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TipoRegistroRepetidoFault }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "TipoRegistroRepetidoFault")
    public JAXBElement<TipoRegistroRepetidoFault> createTipoRegistroRepetidoFault(TipoRegistroRepetidoFault value) {
        return new JAXBElement<>(_TipoRegistroRepetidoFault_QNAME, TipoRegistroRepetidoFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransicionEstadoInvalidaFault }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TransicionEstadoInvalidaFault }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "TransicionEstadoInvalidaFault")
    public JAXBElement<TransicionEstadoInvalidaFault> createTransicionEstadoInvalidaFault(TransicionEstadoInvalidaFault value) {
        return new JAXBElement<>(_TransicionEstadoInvalidaFault_QNAME, TransicionEstadoInvalidaFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioNoExisteFault }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UsuarioNoExisteFault }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "UsuarioNoExisteFault")
    public JAXBElement<UsuarioNoExisteFault> createUsuarioNoExisteFault(UsuarioNoExisteFault value) {
        return new JAXBElement<>(_UsuarioNoExisteFault_QNAME, UsuarioNoExisteFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AceptarEdicion }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AceptarEdicion }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "aceptarEdicion")
    public JAXBElement<AceptarEdicion> createAceptarEdicion(AceptarEdicion value) {
        return new JAXBElement<>(_AceptarEdicion_QNAME, AceptarEdicion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AceptarEdicionResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AceptarEdicionResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "aceptarEdicionResponse")
    public JAXBElement<AceptarEdicionResponse> createAceptarEdicionResponse(AceptarEdicionResponse value) {
        return new JAXBElement<>(_AceptarEdicionResponse_QNAME, AceptarEdicionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AltaCategoria }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AltaCategoria }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "altaCategoria")
    public JAXBElement<AltaCategoria> createAltaCategoria(AltaCategoria value) {
        return new JAXBElement<>(_AltaCategoria_QNAME, AltaCategoria.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AltaCategoriaResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AltaCategoriaResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "altaCategoriaResponse")
    public JAXBElement<AltaCategoriaResponse> createAltaCategoriaResponse(AltaCategoriaResponse value) {
        return new JAXBElement<>(_AltaCategoriaResponse_QNAME, AltaCategoriaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AltaEdicionEvento }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AltaEdicionEvento }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "altaEdicionEvento")
    public JAXBElement<AltaEdicionEvento> createAltaEdicionEvento(AltaEdicionEvento value) {
        return new JAXBElement<>(_AltaEdicionEvento_QNAME, AltaEdicionEvento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AltaEdicionEventoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AltaEdicionEventoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "altaEdicionEventoResponse")
    public JAXBElement<AltaEdicionEventoResponse> createAltaEdicionEventoResponse(AltaEdicionEventoResponse value) {
        return new JAXBElement<>(_AltaEdicionEventoResponse_QNAME, AltaEdicionEventoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AltaEvento }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AltaEvento }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "altaEvento")
    public JAXBElement<AltaEvento> createAltaEvento(AltaEvento value) {
        return new JAXBElement<>(_AltaEvento_QNAME, AltaEvento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AltaEventoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AltaEventoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "altaEventoResponse")
    public JAXBElement<AltaEventoResponse> createAltaEventoResponse(AltaEventoResponse value) {
        return new JAXBElement<>(_AltaEventoResponse_QNAME, AltaEventoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AltaRegistro }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AltaRegistro }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "altaRegistro")
    public JAXBElement<AltaRegistro> createAltaRegistro(AltaRegistro value) {
        return new JAXBElement<>(_AltaRegistro_QNAME, AltaRegistro.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AltaRegistroResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AltaRegistroResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "altaRegistroResponse")
    public JAXBElement<AltaRegistroResponse> createAltaRegistroResponse(AltaRegistroResponse value) {
        return new JAXBElement<>(_AltaRegistroResponse_QNAME, AltaRegistroResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AltaTipoRegistro }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AltaTipoRegistro }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "altaTipoRegistro")
    public JAXBElement<AltaTipoRegistro> createAltaTipoRegistro(AltaTipoRegistro value) {
        return new JAXBElement<>(_AltaTipoRegistro_QNAME, AltaTipoRegistro.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AltaTipoRegistroResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AltaTipoRegistroResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "altaTipoRegistroResponse")
    public JAXBElement<AltaTipoRegistroResponse> createAltaTipoRegistroResponse(AltaTipoRegistroResponse value) {
        return new JAXBElement<>(_AltaTipoRegistroResponse_QNAME, AltaTipoRegistroResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEventoDeUnaEdicion }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetEventoDeUnaEdicion }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "getEventoDeUnaEdicion")
    public JAXBElement<GetEventoDeUnaEdicion> createGetEventoDeUnaEdicion(GetEventoDeUnaEdicion value) {
        return new JAXBElement<>(_GetEventoDeUnaEdicion_QNAME, GetEventoDeUnaEdicion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEventoDeUnaEdicionResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetEventoDeUnaEdicionResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "getEventoDeUnaEdicionResponse")
    public JAXBElement<GetEventoDeUnaEdicionResponse> createGetEventoDeUnaEdicionResponse(GetEventoDeUnaEdicionResponse value) {
        return new JAXBElement<>(_GetEventoDeUnaEdicionResponse_QNAME, GetEventoDeUnaEdicionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEventosDTO }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetEventosDTO }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "getEventosDTO")
    public JAXBElement<GetEventosDTO> createGetEventosDTO(GetEventosDTO value) {
        return new JAXBElement<>(_GetEventosDTO_QNAME, GetEventosDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEventosDTOResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetEventosDTOResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "getEventosDTOResponse")
    public JAXBElement<GetEventosDTOResponse> createGetEventosDTOResponse(GetEventosDTOResponse value) {
        return new JAXBElement<>(_GetEventosDTOResponse_QNAME, GetEventosDTOResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInfoEdicion }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetInfoEdicion }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "getInfoEdicion")
    public JAXBElement<GetInfoEdicion> createGetInfoEdicion(GetInfoEdicion value) {
        return new JAXBElement<>(_GetInfoEdicion_QNAME, GetInfoEdicion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetInfoEdicionResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetInfoEdicionResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "getInfoEdicionResponse")
    public JAXBElement<GetInfoEdicionResponse> createGetInfoEdicionResponse(GetInfoEdicionResponse value) {
        return new JAXBElement<>(_GetInfoEdicionResponse_QNAME, GetInfoEdicionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTipoRegistro }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetTipoRegistro }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "getTipoRegistro")
    public JAXBElement<GetTipoRegistro> createGetTipoRegistro(GetTipoRegistro value) {
        return new JAXBElement<>(_GetTipoRegistro_QNAME, GetTipoRegistro.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTipoRegistroResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetTipoRegistroResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "getTipoRegistroResponse")
    public JAXBElement<GetTipoRegistroResponse> createGetTipoRegistroResponse(GetTipoRegistroResponse value) {
        return new JAXBElement<>(_GetTipoRegistroResponse_QNAME, GetTipoRegistroResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUnEventoDTO }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetUnEventoDTO }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "getUnEventoDTO")
    public JAXBElement<GetUnEventoDTO> createGetUnEventoDTO(GetUnEventoDTO value) {
        return new JAXBElement<>(_GetUnEventoDTO_QNAME, GetUnEventoDTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUnEventoDTOResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetUnEventoDTOResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "getUnEventoDTOResponse")
    public JAXBElement<GetUnEventoDTOResponse> createGetUnEventoDTOResponse(GetUnEventoDTOResponse value) {
        return new JAXBElement<>(_GetUnEventoDTOResponse_QNAME, GetUnEventoDTOResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarCategorias }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarCategorias }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "listarCategorias")
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
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "listarCategoriasResponse")
    public JAXBElement<ListarCategoriasResponse> createListarCategoriasResponse(ListarCategoriasResponse value) {
        return new JAXBElement<>(_ListarCategoriasResponse_QNAME, ListarCategoriasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarEdiciones }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarEdiciones }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "listarEdiciones")
    public JAXBElement<ListarEdiciones> createListarEdiciones(ListarEdiciones value) {
        return new JAXBElement<>(_ListarEdiciones_QNAME, ListarEdiciones.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarEdicionesAceptadas }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarEdicionesAceptadas }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "listarEdicionesAceptadas")
    public JAXBElement<ListarEdicionesAceptadas> createListarEdicionesAceptadas(ListarEdicionesAceptadas value) {
        return new JAXBElement<>(_ListarEdicionesAceptadas_QNAME, ListarEdicionesAceptadas.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarEdicionesAceptadasEvento }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarEdicionesAceptadasEvento }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "listarEdicionesAceptadasEvento")
    public JAXBElement<ListarEdicionesAceptadasEvento> createListarEdicionesAceptadasEvento(ListarEdicionesAceptadasEvento value) {
        return new JAXBElement<>(_ListarEdicionesAceptadasEvento_QNAME, ListarEdicionesAceptadasEvento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarEdicionesAceptadasEventoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarEdicionesAceptadasEventoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "listarEdicionesAceptadasEventoResponse")
    public JAXBElement<ListarEdicionesAceptadasEventoResponse> createListarEdicionesAceptadasEventoResponse(ListarEdicionesAceptadasEventoResponse value) {
        return new JAXBElement<>(_ListarEdicionesAceptadasEventoResponse_QNAME, ListarEdicionesAceptadasEventoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarEdicionesAceptadasResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarEdicionesAceptadasResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "listarEdicionesAceptadasResponse")
    public JAXBElement<ListarEdicionesAceptadasResponse> createListarEdicionesAceptadasResponse(ListarEdicionesAceptadasResponse value) {
        return new JAXBElement<>(_ListarEdicionesAceptadasResponse_QNAME, ListarEdicionesAceptadasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarEdicionesIngresadas }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarEdicionesIngresadas }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "listarEdicionesIngresadas")
    public JAXBElement<ListarEdicionesIngresadas> createListarEdicionesIngresadas(ListarEdicionesIngresadas value) {
        return new JAXBElement<>(_ListarEdicionesIngresadas_QNAME, ListarEdicionesIngresadas.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarEdicionesIngresadasEvento }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarEdicionesIngresadasEvento }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "listarEdicionesIngresadasEvento")
    public JAXBElement<ListarEdicionesIngresadasEvento> createListarEdicionesIngresadasEvento(ListarEdicionesIngresadasEvento value) {
        return new JAXBElement<>(_ListarEdicionesIngresadasEvento_QNAME, ListarEdicionesIngresadasEvento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarEdicionesIngresadasEventoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarEdicionesIngresadasEventoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "listarEdicionesIngresadasEventoResponse")
    public JAXBElement<ListarEdicionesIngresadasEventoResponse> createListarEdicionesIngresadasEventoResponse(ListarEdicionesIngresadasEventoResponse value) {
        return new JAXBElement<>(_ListarEdicionesIngresadasEventoResponse_QNAME, ListarEdicionesIngresadasEventoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarEdicionesIngresadasResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarEdicionesIngresadasResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "listarEdicionesIngresadasResponse")
    public JAXBElement<ListarEdicionesIngresadasResponse> createListarEdicionesIngresadasResponse(ListarEdicionesIngresadasResponse value) {
        return new JAXBElement<>(_ListarEdicionesIngresadasResponse_QNAME, ListarEdicionesIngresadasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarEdicionesOrganizador }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarEdicionesOrganizador }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "listarEdicionesOrganizador")
    public JAXBElement<ListarEdicionesOrganizador> createListarEdicionesOrganizador(ListarEdicionesOrganizador value) {
        return new JAXBElement<>(_ListarEdicionesOrganizador_QNAME, ListarEdicionesOrganizador.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarEdicionesOrganizadorAceptadas }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarEdicionesOrganizadorAceptadas }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "listarEdicionesOrganizadorAceptadas")
    public JAXBElement<ListarEdicionesOrganizadorAceptadas> createListarEdicionesOrganizadorAceptadas(ListarEdicionesOrganizadorAceptadas value) {
        return new JAXBElement<>(_ListarEdicionesOrganizadorAceptadas_QNAME, ListarEdicionesOrganizadorAceptadas.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarEdicionesOrganizadorAceptadasResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarEdicionesOrganizadorAceptadasResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "listarEdicionesOrganizadorAceptadasResponse")
    public JAXBElement<ListarEdicionesOrganizadorAceptadasResponse> createListarEdicionesOrganizadorAceptadasResponse(ListarEdicionesOrganizadorAceptadasResponse value) {
        return new JAXBElement<>(_ListarEdicionesOrganizadorAceptadasResponse_QNAME, ListarEdicionesOrganizadorAceptadasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarEdicionesOrganizadorResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarEdicionesOrganizadorResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "listarEdicionesOrganizadorResponse")
    public JAXBElement<ListarEdicionesOrganizadorResponse> createListarEdicionesOrganizadorResponse(ListarEdicionesOrganizadorResponse value) {
        return new JAXBElement<>(_ListarEdicionesOrganizadorResponse_QNAME, ListarEdicionesOrganizadorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarEdicionesRechazadas }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarEdicionesRechazadas }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "listarEdicionesRechazadas")
    public JAXBElement<ListarEdicionesRechazadas> createListarEdicionesRechazadas(ListarEdicionesRechazadas value) {
        return new JAXBElement<>(_ListarEdicionesRechazadas_QNAME, ListarEdicionesRechazadas.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarEdicionesRechazadasEvento }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarEdicionesRechazadasEvento }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "listarEdicionesRechazadasEvento")
    public JAXBElement<ListarEdicionesRechazadasEvento> createListarEdicionesRechazadasEvento(ListarEdicionesRechazadasEvento value) {
        return new JAXBElement<>(_ListarEdicionesRechazadasEvento_QNAME, ListarEdicionesRechazadasEvento.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarEdicionesRechazadasEventoResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarEdicionesRechazadasEventoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "listarEdicionesRechazadasEventoResponse")
    public JAXBElement<ListarEdicionesRechazadasEventoResponse> createListarEdicionesRechazadasEventoResponse(ListarEdicionesRechazadasEventoResponse value) {
        return new JAXBElement<>(_ListarEdicionesRechazadasEventoResponse_QNAME, ListarEdicionesRechazadasEventoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarEdicionesRechazadasResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarEdicionesRechazadasResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "listarEdicionesRechazadasResponse")
    public JAXBElement<ListarEdicionesRechazadasResponse> createListarEdicionesRechazadasResponse(ListarEdicionesRechazadasResponse value) {
        return new JAXBElement<>(_ListarEdicionesRechazadasResponse_QNAME, ListarEdicionesRechazadasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarEdicionesResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarEdicionesResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "listarEdicionesResponse")
    public JAXBElement<ListarEdicionesResponse> createListarEdicionesResponse(ListarEdicionesResponse value) {
        return new JAXBElement<>(_ListarEdicionesResponse_QNAME, ListarEdicionesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarEventoExistentes }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarEventoExistentes }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "listarEventoExistentes")
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
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "listarEventoExistentesResponse")
    public JAXBElement<ListarEventoExistentesResponse> createListarEventoExistentesResponse(ListarEventoExistentesResponse value) {
        return new JAXBElement<>(_ListarEventoExistentesResponse_QNAME, ListarEventoExistentesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarRegistrosDeEdicion }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarRegistrosDeEdicion }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "listarRegistrosDeEdicion")
    public JAXBElement<ListarRegistrosDeEdicion> createListarRegistrosDeEdicion(ListarRegistrosDeEdicion value) {
        return new JAXBElement<>(_ListarRegistrosDeEdicion_QNAME, ListarRegistrosDeEdicion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarRegistrosDeEdicionResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarRegistrosDeEdicionResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "listarRegistrosDeEdicionResponse")
    public JAXBElement<ListarRegistrosDeEdicionResponse> createListarRegistrosDeEdicionResponse(ListarRegistrosDeEdicionResponse value) {
        return new JAXBElement<>(_ListarRegistrosDeEdicionResponse_QNAME, ListarRegistrosDeEdicionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarRegistrosDeUsuario }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarRegistrosDeUsuario }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "listarRegistrosDeUsuario")
    public JAXBElement<ListarRegistrosDeUsuario> createListarRegistrosDeUsuario(ListarRegistrosDeUsuario value) {
        return new JAXBElement<>(_ListarRegistrosDeUsuario_QNAME, ListarRegistrosDeUsuario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarRegistrosDeUsuarioResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarRegistrosDeUsuarioResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "listarRegistrosDeUsuarioResponse")
    public JAXBElement<ListarRegistrosDeUsuarioResponse> createListarRegistrosDeUsuarioResponse(ListarRegistrosDeUsuarioResponse value) {
        return new JAXBElement<>(_ListarRegistrosDeUsuarioResponse_QNAME, ListarRegistrosDeUsuarioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarTiposRegistro }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarTiposRegistro }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "listarTiposRegistro")
    public JAXBElement<ListarTiposRegistro> createListarTiposRegistro(ListarTiposRegistro value) {
        return new JAXBElement<>(_ListarTiposRegistro_QNAME, ListarTiposRegistro.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarTiposRegistroResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarTiposRegistroResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "listarTiposRegistroResponse")
    public JAXBElement<ListarTiposRegistroResponse> createListarTiposRegistroResponse(ListarTiposRegistroResponse value) {
        return new JAXBElement<>(_ListarTiposRegistroResponse_QNAME, ListarTiposRegistroResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarUnRegistroDeUsuario }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarUnRegistroDeUsuario }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "listarUnRegistroDeUsuario")
    public JAXBElement<ListarUnRegistroDeUsuario> createListarUnRegistroDeUsuario(ListarUnRegistroDeUsuario value) {
        return new JAXBElement<>(_ListarUnRegistroDeUsuario_QNAME, ListarUnRegistroDeUsuario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarUnRegistroDeUsuarioResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarUnRegistroDeUsuarioResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "listarUnRegistroDeUsuarioResponse")
    public JAXBElement<ListarUnRegistroDeUsuarioResponse> createListarUnRegistroDeUsuarioResponse(ListarUnRegistroDeUsuarioResponse value) {
        return new JAXBElement<>(_ListarUnRegistroDeUsuarioResponse_QNAME, ListarUnRegistroDeUsuarioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetCostoRegistro }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SetCostoRegistro }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "setCostoRegistro")
    public JAXBElement<SetCostoRegistro> createSetCostoRegistro(SetCostoRegistro value) {
        return new JAXBElement<>(_SetCostoRegistro_QNAME, SetCostoRegistro.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetCostoRegistroResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SetCostoRegistroResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "setCostoRegistroResponse")
    public JAXBElement<SetCostoRegistroResponse> createSetCostoRegistroResponse(SetCostoRegistroResponse value) {
        return new JAXBElement<>(_SetCostoRegistroResponse_QNAME, SetCostoRegistroResponse.class, null, value);
    }

}
