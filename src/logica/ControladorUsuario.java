package logica;

import java.util.HashMap;
import java.util.Map;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;

public class ControladorUsuario implements IControladorUsuario {

    private Map<String, DataUsuario> usuarios;

    public ControladorUsuario() {
        usuarios = new HashMap<>();
    }

    @Override
    public void altaUsuario(String nickname, String nombre, String correo, String tipo, 
                            String descripcion, String link, String apellido, String fechaNac) 
                            throws UsuarioRepetidoException {
        if (usuarios.containsKey(nickname)) {
            throw new UsuarioRepetidoException("El usuario ya existe: " + nickname);
        }

        DataUsuario nuevo;
        if (tipo.equals("Organizador")) {
            nuevo = new DataUsuario(nickname, nombre, correo, tipo);
            nuevo.setDescripcion(descripcion);
            nuevo.setLink(link);
        } else if (tipo.equals("Asistente")) {
            nuevo = new DataUsuario(nickname, nombre, correo, tipo);
            nuevo.setApellido(apellido);
            nuevo.setFechaNacimiento(fechaNac);
        } else {
            throw new IllegalArgumentException("Tipo de usuario desconocido: " + tipo);
        }

        usuarios.put(nickname, nuevo);
    }

    @Override
    public DataUsuario verInfoUsuario(String nickname) throws UsuarioNoExisteException {
        if (!usuarios.containsKey(nickname)) {
            throw new UsuarioNoExisteException("Usuario no encontrado: " + nickname);
        }
        return usuarios.get(nickname);
    }

    @Override
    public DataUsuario[] getUsuarios() throws UsuarioNoExisteException {
        if (usuarios.isEmpty()) throw new UsuarioNoExisteException("No hay usuarios registrados.");
        return usuarios.values().toArray(new DataUsuario[0]);
    }
    
    @Override
    public void modificarUsuario(String nickname, String nombre, String tipo, 
                                 String descripcion, String link, String apellido, String fechaNac) 
                                 throws UsuarioNoExisteException {
        if (!usuarios.containsKey(nickname)) {
            throw new UsuarioNoExisteException("Usuario no encontrado: " + nickname);
        }

        DataUsuario u = usuarios.get(nickname);
        u.setNombre(nombre);
        u.setTipo(tipo);

        // limpiar campos opcionales
        u.setDescripcion(null);
        u.setLink(null);
        u.setApellido(null);
        u.setFechaNacimiento(null);

        if ("Organizador".equals(tipo)) {
            u.setDescripcion(descripcion);
            u.setLink(link);
        } else if ("Asistente".equals(tipo)) {
            u.setApellido(apellido);
            u.setFechaNacimiento(fechaNac);
        }
    }

}
