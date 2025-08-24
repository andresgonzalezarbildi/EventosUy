package logica;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ManejadorUsuario {
    private Map<String, Usuario> usuarios; // map nickname -> usuario
    private static ManejadorUsuario instancia = null;


    public static ManejadorUsuario getinstance() {
        if (instancia == null)
            instancia = new ManejadorUsuario();
        return instancia;
    }

    public void addUsuario(Usuario usu) {
        usuarios.put(usu.getNickname(), usu);
    }

    public Usuario obtenerUsuario(String nickname) {
        return usuarios.get(nickname);
    }

    public Usuario[] getUsuarios() {
        if (usuarios.isEmpty())
            return null;
        Collection<Usuario> usrs = usuarios.values();
        return usrs.toArray(new Usuario[0]);
    }
}
