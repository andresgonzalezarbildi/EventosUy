package ws.exceptions;

import jakarta.xml.ws.WebFault; 

@SuppressWarnings("serial") 
@WebFault(name = "CategoriaRepetidaFault") 
public class CategoriaRepetidaFault extends Exception { 
  public CategoriaRepetidaFault(String message) { super(message); } 
}
