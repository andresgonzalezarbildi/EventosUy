package ws.exceptions;

import jakarta.xml.ws.WebFault; 

@SuppressWarnings("serial") 
@WebFault(name = "TipoRegistroRepetidoFault") 
public class TipoRegistroRepetidoFault extends Exception { 
  public TipoRegistroRepetidoFault(String message) { super(message); } 
}
