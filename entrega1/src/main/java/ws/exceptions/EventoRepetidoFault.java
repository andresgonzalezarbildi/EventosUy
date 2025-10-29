package ws.exceptions;

import jakarta.xml.ws.WebFault; 

@SuppressWarnings("serial") 
@WebFault(name = "EventoRepetidoFault") 
public class EventoRepetidoFault extends Exception { 
  public EventoRepetidoFault(String message) { super(message); } 
}
