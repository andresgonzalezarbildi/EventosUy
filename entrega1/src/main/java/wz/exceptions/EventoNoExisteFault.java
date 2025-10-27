package wz.exceptions;

import jakarta.xml.ws.WebFault; 

@SuppressWarnings("serial") 
@WebFault(name = "EventoNoExisteFault") 
public class EventoNoExisteFault extends Exception { 
  public EventoNoExisteFault(String message) { super(message); } 
}
