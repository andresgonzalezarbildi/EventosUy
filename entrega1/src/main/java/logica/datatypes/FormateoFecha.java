package logica.datatypes;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormateoFecha extends XmlAdapter<String, LocalDate> {
    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ISO_LOCAL_DATE;
    @Override 
    public LocalDate unmarshal(String texto) { 
      return LocalDate.parse(texto, FORMATO); 
    }
    @Override 
    public String marshal(LocalDate fecha) {
      return fecha.format(FORMATO);
    }
}