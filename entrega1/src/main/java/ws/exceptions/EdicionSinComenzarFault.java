package ws.exceptions;

import jakarta.xml.ws.WebFault; 

@SuppressWarnings("serial") 
@WebFault(name = "EdicionSinComenzarFault") 
public class EdicionSinComenzarFault extends Exception { 
  public EdicionSinComenzarFault(String message) { super(message); } 
}
