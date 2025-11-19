package ws.exceptions;

import jakarta.xml.ws.WebFault; 

@SuppressWarnings("serial") 
@WebFault(name = "TransicionEstadoInvalidaFault") 
public class TransicionEstadoInvalidaFault extends Exception { 
  public TransicionEstadoInvalidaFault(String message) { super(message); } 
}
