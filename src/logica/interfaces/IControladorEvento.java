package logica.interfaces;
import java.util.List;

import excepciones.EventoNoExisteException;

import java.time.LocalDate;

import logica.clases.Categoria;
import logica.datatypes.DataEvento;

public interface IControladorEvento {
    void altaEvento(String nom, String desc, String sigla, LocalDate fecha,List<Categoria> categorias) ;
    public void altaCategoria(String nombre);
    public DataEvento[] getEventosDTO() ;
    public DataEvento[] listarEventoExistentes() throws EventoNoExisteException;
}