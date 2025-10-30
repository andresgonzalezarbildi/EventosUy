
package ws.usuario;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws.usuario package. 
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

    private static final QName _PasswordIncorrectaFault_QNAME = new QName("http://publicar.ws/", "PasswordIncorrectaFault");
    private static final QName _UsuarioNoExisteFault_QNAME = new QName("http://publicar.ws/", "UsuarioNoExisteFault");
    private static final QName _UsuarioRepetidoFault_QNAME = new QName("http://publicar.ws/", "UsuarioRepetidoFault");
    private static final QName _AltaUsuario_QNAME = new QName("http://publicar.ws/", "altaUsuario");
    private static final QName _AltaUsuarioResponse_QNAME = new QName("http://publicar.ws/", "altaUsuarioResponse");
    private static final QName _CambiarContrasenia_QNAME = new QName("http://publicar.ws/", "cambiarContrasenia");
    private static final QName _CambiarContraseniaResponse_QNAME = new QName("http://publicar.ws/", "cambiarContraseniaResponse");
    private static final QName _GetAsistente_QNAME = new QName("http://publicar.ws/", "getAsistente");
    private static final QName _GetAsistenteResponse_QNAME = new QName("http://publicar.ws/", "getAsistenteResponse");
    private static final QName _GetAsistentes_QNAME = new QName("http://publicar.ws/", "getAsistentes");
    private static final QName _GetAsistentesResponse_QNAME = new QName("http://publicar.ws/", "getAsistentesResponse");
    private static final QName _GetOrganizador_QNAME = new QName("http://publicar.ws/", "getOrganizador");
    private static final QName _GetOrganizadorResponse_QNAME = new QName("http://publicar.ws/", "getOrganizadorResponse");
    private static final QName _GetOrganizadores_QNAME = new QName("http://publicar.ws/", "getOrganizadores");
    private static final QName _GetOrganizadoresResponse_QNAME = new QName("http://publicar.ws/", "getOrganizadoresResponse");
    private static final QName _GetUsuarios_QNAME = new QName("http://publicar.ws/", "getUsuarios");
    private static final QName _GetUsuariosResponse_QNAME = new QName("http://publicar.ws/", "getUsuariosResponse");
    private static final QName _Login_QNAME = new QName("http://publicar.ws/", "login");
    private static final QName _LoginResponse_QNAME = new QName("http://publicar.ws/", "loginResponse");
    private static final QName _ModificarUsuario_QNAME = new QName("http://publicar.ws/", "modificarUsuario");
    private static final QName _ModificarUsuarioResponse_QNAME = new QName("http://publicar.ws/", "modificarUsuarioResponse");
    private static final QName _VerInfoUsuario_QNAME = new QName("http://publicar.ws/", "verInfoUsuario");
    private static final QName _VerInfoUsuarioResponse_QNAME = new QName("http://publicar.ws/", "verInfoUsuarioResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws.usuario
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PasswordIncorrectaFault }
     * 
     * @return
     *     the new instance of {@link PasswordIncorrectaFault }
     */
    public PasswordIncorrectaFault createPasswordIncorrectaFault() {
        return new PasswordIncorrectaFault();
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
     * Create an instance of {@link UsuarioRepetidoFault }
     * 
     * @return
     *     the new instance of {@link UsuarioRepetidoFault }
     */
    public UsuarioRepetidoFault createUsuarioRepetidoFault() {
        return new UsuarioRepetidoFault();
    }

    /**
     * Create an instance of {@link AltaUsuario }
     * 
     * @return
     *     the new instance of {@link AltaUsuario }
     */
    public AltaUsuario createAltaUsuario() {
        return new AltaUsuario();
    }

    /**
     * Create an instance of {@link AltaUsuarioResponse }
     * 
     * @return
     *     the new instance of {@link AltaUsuarioResponse }
     */
    public AltaUsuarioResponse createAltaUsuarioResponse() {
        return new AltaUsuarioResponse();
    }

    /**
     * Create an instance of {@link CambiarContrasenia }
     * 
     * @return
     *     the new instance of {@link CambiarContrasenia }
     */
    public CambiarContrasenia createCambiarContrasenia() {
        return new CambiarContrasenia();
    }

    /**
     * Create an instance of {@link CambiarContraseniaResponse }
     * 
     * @return
     *     the new instance of {@link CambiarContraseniaResponse }
     */
    public CambiarContraseniaResponse createCambiarContraseniaResponse() {
        return new CambiarContraseniaResponse();
    }

    /**
     * Create an instance of {@link GetAsistente }
     * 
     * @return
     *     the new instance of {@link GetAsistente }
     */
    public GetAsistente createGetAsistente() {
        return new GetAsistente();
    }

    /**
     * Create an instance of {@link GetAsistenteResponse }
     * 
     * @return
     *     the new instance of {@link GetAsistenteResponse }
     */
    public GetAsistenteResponse createGetAsistenteResponse() {
        return new GetAsistenteResponse();
    }

    /**
     * Create an instance of {@link GetAsistentes }
     * 
     * @return
     *     the new instance of {@link GetAsistentes }
     */
    public GetAsistentes createGetAsistentes() {
        return new GetAsistentes();
    }

    /**
     * Create an instance of {@link GetAsistentesResponse }
     * 
     * @return
     *     the new instance of {@link GetAsistentesResponse }
     */
    public GetAsistentesResponse createGetAsistentesResponse() {
        return new GetAsistentesResponse();
    }

    /**
     * Create an instance of {@link GetOrganizador }
     * 
     * @return
     *     the new instance of {@link GetOrganizador }
     */
    public GetOrganizador createGetOrganizador() {
        return new GetOrganizador();
    }

    /**
     * Create an instance of {@link GetOrganizadorResponse }
     * 
     * @return
     *     the new instance of {@link GetOrganizadorResponse }
     */
    public GetOrganizadorResponse createGetOrganizadorResponse() {
        return new GetOrganizadorResponse();
    }

    /**
     * Create an instance of {@link GetOrganizadores }
     * 
     * @return
     *     the new instance of {@link GetOrganizadores }
     */
    public GetOrganizadores createGetOrganizadores() {
        return new GetOrganizadores();
    }

    /**
     * Create an instance of {@link GetOrganizadoresResponse }
     * 
     * @return
     *     the new instance of {@link GetOrganizadoresResponse }
     */
    public GetOrganizadoresResponse createGetOrganizadoresResponse() {
        return new GetOrganizadoresResponse();
    }

    /**
     * Create an instance of {@link GetUsuarios }
     * 
     * @return
     *     the new instance of {@link GetUsuarios }
     */
    public GetUsuarios createGetUsuarios() {
        return new GetUsuarios();
    }

    /**
     * Create an instance of {@link GetUsuariosResponse }
     * 
     * @return
     *     the new instance of {@link GetUsuariosResponse }
     */
    public GetUsuariosResponse createGetUsuariosResponse() {
        return new GetUsuariosResponse();
    }

    /**
     * Create an instance of {@link Login }
     * 
     * @return
     *     the new instance of {@link Login }
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     * @return
     *     the new instance of {@link LoginResponse }
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link ModificarUsuario }
     * 
     * @return
     *     the new instance of {@link ModificarUsuario }
     */
    public ModificarUsuario createModificarUsuario() {
        return new ModificarUsuario();
    }

    /**
     * Create an instance of {@link ModificarUsuarioResponse }
     * 
     * @return
     *     the new instance of {@link ModificarUsuarioResponse }
     */
    public ModificarUsuarioResponse createModificarUsuarioResponse() {
        return new ModificarUsuarioResponse();
    }

    /**
     * Create an instance of {@link VerInfoUsuario }
     * 
     * @return
     *     the new instance of {@link VerInfoUsuario }
     */
    public VerInfoUsuario createVerInfoUsuario() {
        return new VerInfoUsuario();
    }

    /**
     * Create an instance of {@link VerInfoUsuarioResponse }
     * 
     * @return
     *     the new instance of {@link VerInfoUsuarioResponse }
     */
    public VerInfoUsuarioResponse createVerInfoUsuarioResponse() {
        return new VerInfoUsuarioResponse();
    }

    /**
     * Create an instance of {@link DataUsuario }
     * 
     * @return
     *     the new instance of {@link DataUsuario }
     */
    public DataUsuario createDataUsuario() {
        return new DataUsuario();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PasswordIncorrectaFault }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PasswordIncorrectaFault }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "PasswordIncorrectaFault")
    public JAXBElement<PasswordIncorrectaFault> createPasswordIncorrectaFault(PasswordIncorrectaFault value) {
        return new JAXBElement<>(_PasswordIncorrectaFault_QNAME, PasswordIncorrectaFault.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link UsuarioRepetidoFault }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link UsuarioRepetidoFault }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "UsuarioRepetidoFault")
    public JAXBElement<UsuarioRepetidoFault> createUsuarioRepetidoFault(UsuarioRepetidoFault value) {
        return new JAXBElement<>(_UsuarioRepetidoFault_QNAME, UsuarioRepetidoFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AltaUsuario }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AltaUsuario }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "altaUsuario")
    public JAXBElement<AltaUsuario> createAltaUsuario(AltaUsuario value) {
        return new JAXBElement<>(_AltaUsuario_QNAME, AltaUsuario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AltaUsuarioResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AltaUsuarioResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "altaUsuarioResponse")
    public JAXBElement<AltaUsuarioResponse> createAltaUsuarioResponse(AltaUsuarioResponse value) {
        return new JAXBElement<>(_AltaUsuarioResponse_QNAME, AltaUsuarioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CambiarContrasenia }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CambiarContrasenia }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "cambiarContrasenia")
    public JAXBElement<CambiarContrasenia> createCambiarContrasenia(CambiarContrasenia value) {
        return new JAXBElement<>(_CambiarContrasenia_QNAME, CambiarContrasenia.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CambiarContraseniaResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CambiarContraseniaResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "cambiarContraseniaResponse")
    public JAXBElement<CambiarContraseniaResponse> createCambiarContraseniaResponse(CambiarContraseniaResponse value) {
        return new JAXBElement<>(_CambiarContraseniaResponse_QNAME, CambiarContraseniaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAsistente }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAsistente }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "getAsistente")
    public JAXBElement<GetAsistente> createGetAsistente(GetAsistente value) {
        return new JAXBElement<>(_GetAsistente_QNAME, GetAsistente.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAsistenteResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAsistenteResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "getAsistenteResponse")
    public JAXBElement<GetAsistenteResponse> createGetAsistenteResponse(GetAsistenteResponse value) {
        return new JAXBElement<>(_GetAsistenteResponse_QNAME, GetAsistenteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAsistentes }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAsistentes }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "getAsistentes")
    public JAXBElement<GetAsistentes> createGetAsistentes(GetAsistentes value) {
        return new JAXBElement<>(_GetAsistentes_QNAME, GetAsistentes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAsistentesResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetAsistentesResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "getAsistentesResponse")
    public JAXBElement<GetAsistentesResponse> createGetAsistentesResponse(GetAsistentesResponse value) {
        return new JAXBElement<>(_GetAsistentesResponse_QNAME, GetAsistentesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOrganizador }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetOrganizador }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "getOrganizador")
    public JAXBElement<GetOrganizador> createGetOrganizador(GetOrganizador value) {
        return new JAXBElement<>(_GetOrganizador_QNAME, GetOrganizador.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOrganizadorResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetOrganizadorResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "getOrganizadorResponse")
    public JAXBElement<GetOrganizadorResponse> createGetOrganizadorResponse(GetOrganizadorResponse value) {
        return new JAXBElement<>(_GetOrganizadorResponse_QNAME, GetOrganizadorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOrganizadores }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetOrganizadores }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "getOrganizadores")
    public JAXBElement<GetOrganizadores> createGetOrganizadores(GetOrganizadores value) {
        return new JAXBElement<>(_GetOrganizadores_QNAME, GetOrganizadores.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetOrganizadoresResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetOrganizadoresResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "getOrganizadoresResponse")
    public JAXBElement<GetOrganizadoresResponse> createGetOrganizadoresResponse(GetOrganizadoresResponse value) {
        return new JAXBElement<>(_GetOrganizadoresResponse_QNAME, GetOrganizadoresResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsuarios }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetUsuarios }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "getUsuarios")
    public JAXBElement<GetUsuarios> createGetUsuarios(GetUsuarios value) {
        return new JAXBElement<>(_GetUsuarios_QNAME, GetUsuarios.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsuariosResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetUsuariosResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "getUsuariosResponse")
    public JAXBElement<GetUsuariosResponse> createGetUsuariosResponse(GetUsuariosResponse value) {
        return new JAXBElement<>(_GetUsuariosResponse_QNAME, GetUsuariosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Login }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Login }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "login")
    public JAXBElement<Login> createLogin(Login value) {
        return new JAXBElement<>(_Login_QNAME, Login.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "loginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModificarUsuario }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ModificarUsuario }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "modificarUsuario")
    public JAXBElement<ModificarUsuario> createModificarUsuario(ModificarUsuario value) {
        return new JAXBElement<>(_ModificarUsuario_QNAME, ModificarUsuario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModificarUsuarioResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ModificarUsuarioResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "modificarUsuarioResponse")
    public JAXBElement<ModificarUsuarioResponse> createModificarUsuarioResponse(ModificarUsuarioResponse value) {
        return new JAXBElement<>(_ModificarUsuarioResponse_QNAME, ModificarUsuarioResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerInfoUsuario }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link VerInfoUsuario }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "verInfoUsuario")
    public JAXBElement<VerInfoUsuario> createVerInfoUsuario(VerInfoUsuario value) {
        return new JAXBElement<>(_VerInfoUsuario_QNAME, VerInfoUsuario.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VerInfoUsuarioResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link VerInfoUsuarioResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar.ws/", name = "verInfoUsuarioResponse")
    public JAXBElement<VerInfoUsuarioResponse> createVerInfoUsuarioResponse(VerInfoUsuarioResponse value) {
        return new JAXBElement<>(_VerInfoUsuarioResponse_QNAME, VerInfoUsuarioResponse.class, null, value);
    }

}
