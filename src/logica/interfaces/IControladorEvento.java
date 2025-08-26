package logica.interfaces;

import java.time.LocalDate;

public interface IControladorEvento {
	
    void altaEvento(String nom, String desc, String sigla, LocalDate fecha);
}