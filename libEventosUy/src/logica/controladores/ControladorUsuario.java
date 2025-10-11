package logica.controladores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import excepciones.PasswordIncorrectaException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import logica.clases.Asistente;
import logica.clases.Organizador;
import logica.clases.Usuario;
import logica.datatypes.DataUsuario;
import logica.interfaces.IControladorUsuario;
import logica.manejadores.ManejadorUsuario;

public class ControladorUsuario implements IControladorUsuario {
	
	private static ControladorUsuario instancia;

	
    public static ControladorUsuario getInstance() {
        if (instancia == null) {
            instancia = new ControladorUsuario();
        }
        return instancia;
    }

    private final ManejadorUsuario manejador;

    public ControladorUsuario() {
        this.manejador = ManejadorUsuario.getInstance();
    }

    public void altaUsuario(String nickname, String nombre, String correo, String imagen, String password, String tipo,
                            String descripcion, String link, String apellido, LocalDate fechaNac)
                            throws UsuarioRepetidoException {

        if (nickname == null || nickname.isBlank())
            throw new IllegalArgumentException("El nickname no puede ser vacío.");
        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("El nombre no puede ser vacío.");
        if (correo == null || correo.isBlank())
            throw new IllegalArgumentException("El correo no puede ser vacío.");

        if (manejador.existeNickname(nickname)) {
            throw new UsuarioRepetidoException("El usuario " + nickname + " ya existe");
        }
        if (manejador.existeCorreo(correo)) {
            throw new UsuarioRepetidoException("El correo: " + correo + " ya pertenece a un usuario");
        }
        
        if(imagen == null ) {
        	imagen = "PerfilSinFoto.jpg";
        }       

        if ("Organizador".equalsIgnoreCase(tipo)) {
            Organizador org = new Organizador(nickname, nombre, correo, imagen, password,
                                              descripcion, link);
            manejador.agregarOrganizador(org);

        } else if ("Asistente".equalsIgnoreCase(tipo)) {
            Asistente asis = new Asistente(nickname, nombre, correo, imagen, password,
                                           apellido, fechaNac);
            manejador.agregarAsistente(asis);

        } else {
            throw new IllegalArgumentException("Tipo de usuario inválido: " + tipo);
        }
    }

    public DataUsuario verInfoUsuario(String nickname) throws UsuarioNoExisteException {
        Usuario u = manejador.obtenerPorNickname(nickname);
        if (u == null)
            throw new UsuarioNoExisteException("Usuario no encontrado: " + nickname);
        return aDataUsuario(u);
    }

    public void modificarUsuario(String nickname, String nombre, String descripcion, String imagen,
                                 String link, String apellido, LocalDate fechaNac)
                                 throws UsuarioNoExisteException {
        Usuario u = manejador.obtenerPorNickname(nickname);
        if (u == null)
            throw new UsuarioNoExisteException("Usuario no encontrado: " + nickname);

        // comunes
        if (nombre != null && !nombre.isBlank()) {
            u.setNombre(nombre);
        }
        if (imagen != null) {
            u.setImagen(imagen.isBlank() ? "PerfilSinFoto.jpg" : imagen);
        }

        if (u instanceof Organizador o) {
            if (descripcion != null) o.setDescripcionGeneral(descripcion);
            if (link != null)        o.setLinkSitioWeb(link);

        } else if (u instanceof Asistente a) {
            if (apellido != null)   a.setApellido(apellido);
            if (fechaNac != null)   a.setFechaNacimiento(fechaNac);
        }
    }
    
    public Organizador getOrganizador(String nickname) throws UsuarioNoExisteException {
        return manejador.getOrganizador(nickname);
    }
    public Asistente getAsistente(String nickname) throws UsuarioNoExisteException {
        return manejador.getAsistente(nickname);
    }
    
    
    public DataUsuario[] getUsuarios() throws UsuarioNoExisteException {
        Collection<Organizador> orgs = manejador.obtenerTodosOrganizadores();
        Collection<Asistente>  asis  = manejador.obtenerTodosAsistentes();

        if (orgs.isEmpty() && asis.isEmpty())
            throw new UsuarioNoExisteException("No hay usuarios registrados.");

        List<DataUsuario> lista = new ArrayList<>(orgs.size() + asis.size());
        for (Organizador o : orgs) lista.add(aDataUsuario(o));
        for (Asistente  a : asis)  lista.add(aDataUsuario(a));

        lista.sort(Comparator.comparing(DataUsuario::getNickname, String.CASE_INSENSITIVE_ORDER));
        return lista.toArray(new DataUsuario[0]);
    }
    
    public DataUsuario[] getOrganizadores(){
        Collection<Organizador> orgs = manejador.obtenerTodosOrganizadores();

        
        List<DataUsuario> lista = new ArrayList<>(orgs.size());
        for (Organizador o : orgs) lista.add(aDataUsuario(o));
        lista.sort(Comparator.comparing(DataUsuario::getNickname));
        return lista.toArray(new DataUsuario[0]);
    }
    
    public DataUsuario[] getAsistentes(){
        Collection<Asistente> orgs = manejador.obtenerTodosAsistentes();

        
        List<DataUsuario> lista = new ArrayList<>(orgs.size());
        for (Asistente o : orgs) lista.add(aDataUsuario(o));
        lista.sort(Comparator.comparing(DataUsuario::getNickname));
        return lista.toArray(new DataUsuario[0]);
    }

    private DataUsuario aDataUsuario(Usuario u) {
        DataUsuario du = new DataUsuario(u.getNickname(), u.getNombre(), u.getCorreo(), u.getImagen(),
                                         (u instanceof Organizador) ? "Organizador" : "Asistente");
        if (u instanceof Organizador o) {
            du.setDescripcion(o.getDescripcionGeneral());
            du.setLink(o.getLinkSitioWeb());
        } else if (u instanceof Asistente a) {
            du.setApellido(a.getApellido());
            du.setFechaNacimiento(a.getFechaNacimiento());
        }
        return du;
    }
    
    @Override
    public Usuario login(String ident, String password) throws UsuarioNoExisteException, PasswordIncorrectaException {

        if (ident == null || ident.isBlank())
            throw new UsuarioNoExisteException("Identificador vacío: ingrese nickname o correo");
        if (password == null)
            throw new PasswordIncorrectaException("Contraseña inválida (null)");

        Usuario u = manejador.obtenerPorIdentificador(ident);
        if (u == null) {
            throw new UsuarioNoExisteException("No existe usuario con nickname/correo: " + ident.trim());
        }

        if (!u.verificarPassword(password)) {
            throw new PasswordIncorrectaException("Credenciales inválidas");
        }
        return u; // puede ser Asistente u Organizador
    }
    
    public void limpar() {
    	manejador.limpiar();
    }
}
