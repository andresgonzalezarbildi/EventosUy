package ws.exceptions;

import jakarta.xml.ws.WebFault; 

@SuppressWarnings("serial") 
@WebFault(name = "UsuarioNoExisteFault") 
public class UsuarioNoExisteFault extends Exception { 
  public UsuarioNoExisteFault(String message) { super(message); } 
}