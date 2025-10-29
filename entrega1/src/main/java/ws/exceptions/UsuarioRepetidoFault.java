package ws.exceptions;

import jakarta.xml.ws.WebFault; 

@SuppressWarnings("serial") 
@WebFault(name = "UsuarioRepetidoFault") 
public class UsuarioRepetidoFault extends Exception { 
  public UsuarioRepetidoFault(String message) { super(message); } 
}
