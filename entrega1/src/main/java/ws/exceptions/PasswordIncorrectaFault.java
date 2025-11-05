package ws.exceptions;

import jakarta.xml.ws.WebFault; 

@SuppressWarnings("serial") 
@WebFault(name = "PasswordIncorrectaFault") 
public class PasswordIncorrectaFault extends Exception { 
  public PasswordIncorrectaFault(String message) { super(message); } 
}