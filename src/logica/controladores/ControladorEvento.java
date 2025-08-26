package logica.controladores;

import java.util.HashMap;
import java.util.Map;

import logica.clases.Evento;
import logica.interfaces.IControladorEvento;
import logica.manejadores.ManejadorEvento;

import java.time.LocalDate;

public class ControladorEvento implements IControladorEvento {

	private ManejadorEvento manejador;

    public  ControladorEvento() {
     manejador = ManejadorEvento.getInstance();
    }

    public void altaEvento(String nombre,String descripcion,String sigla,LocalDate date) {
    	Evento nuevo = new Evento(nombre,descripcion,sigla);
        manejador.agregarEvento(nuevo);
    };
}