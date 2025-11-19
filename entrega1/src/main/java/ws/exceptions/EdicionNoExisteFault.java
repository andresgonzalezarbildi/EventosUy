package ws.exceptions;

import jakarta.xml.ws.WebFault; 

@SuppressWarnings("serial") 
@WebFault(name = "EdicionNoExisteFault") 
public class EdicionNoExisteFault extends Exception { 
  public EdicionNoExisteFault(String message) { super(message); } 
}
