package logica.clases;


public class Categoria {	
	
	private String nombre;  
	
	public Categoria(String nom){
		nombre = nom;
	}
	
    public String getNombre() {
    		return nombre;
    }


@Override
public String toString() {
    return nombre; // así JList muestra el nombre en lugar de "Categoria@hashcode"
}
}