package ws.exceptions;

import jakarta.xml.ws.WebFault;

@SuppressWarnings("serial")
@WebFault(name = "EventoConEdicionesPendientesFaultFault") 
public class EventoConEdicionesPendientesFault extends Exception {

  public EventoConEdicionesPendientesFault(String string) {
      super(string);
  }

}
