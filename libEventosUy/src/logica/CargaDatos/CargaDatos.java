package logica.CargaDatos;

import java.time.LocalDate;
import java.util.List;

import excepciones.CategoriaRepetidaException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import excepciones.EdicionNoExisteException;
import excepciones.TransicionEstadoInvalidaException;

import logica.interfaces.ICargaDatos;
import logica.interfaces.IControladorEvento;
import logica.interfaces.IControladorUsuario;

public class CargaDatos implements ICargaDatos {
    private final IControladorUsuario cu;
    private final IControladorEvento ce;

    public CargaDatos(IControladorUsuario cu , IControladorEvento ce) {
        this.cu = cu;
        this.ce = ce;
    }

    public void CargarDatosIniciales() {
    	
    	
    	//Carga Organizadores:
    	try {
    		//organizador: altaUsuario(nicknameU, nombreU, correoU, "Organizador", descripcionU, linkU, null, null);
    		// altaUsuario( nickname,  nombre,  correo,  imagen,  password,  tipo, descripcion,  link,  apellido,  fechaNac)
        	cu.altaUsuario("miseventos", "MisEventos", "contacto@miseventos.com", "IMG-US04.jpeg", "22miseventos","Organizador","Empresa de organización de eventos.", "https://miseventos.com", null, null);   
        } catch (UsuarioRepetidoException ignored) {
            // Si ya existe, lo ignoramos 
        }
	
    	try {
    		//organizador: altaUsuario(nicknameU, nombreU, correoU, "Organizador", descripcionU, linkU, null, null);
        	cu.altaUsuario("techcorp", "Corporación Tecnológica", "info@techcorp.com", "PerfilSinFoto.jpg", "tech25corp", "Organizador","Empresa líder en tecnologías de la información.", "https://techcorp.com", null, null);  
        } catch (UsuarioRepetidoException ignored) {
            // Si ya existe, lo ignoramos 
        }

    	try {
    		//organizador: altaUsuario(nicknameU, nombreU, correoU, "Organizador", descripcionU, linkU, null, null);
        	cu.altaUsuario("imm", "Intendencia de Montevideo", "contacto@imm.gub.uy","IMG-US06.png", "imm2025", "Organizador","Gobierno departamental de Montevideo.", "https://montevideo.gub.uy",null, null);
    	} catch (UsuarioRepetidoException ignored) {
            // Si ya existe, lo ignoramos 
        }
    	
        try {
        	//organizador: altaUsuario(nicknameU, nombreU, correoU, "Organizador", descripcionU, linkU, null, null);
            cu.altaUsuario("udelar", "Universidad de la República", "contacto@udelar.edu.uy", "PerfilSinFoto.jpg", "25udelar","Organizador","Universidad pública de Uruguay.", "https://udelar.edu.uy",null, null);
        } catch (UsuarioRepetidoException ignored) {
            // Si ya existe, lo ignoramos 
        }

        try {
            //organizador: altaUsuario(nicknameU, nombreU, correoU, "Organizador", descripcionU, linkU, null, null);
            cu.altaUsuario("mec", "Ministerio de Educación y Cultura", "mec@mec.gub.uy", "IMG-US11.png", "mec2025ok", "Organizador", "Institución pública promotora de cultura.", "https://mec.gub.uy",null, null);
        } catch (UsuarioRepetidoException ignored) {
            // Si ya existe, lo ignoramos 
        }
                	
                	
     // Carga Asistentes:
        try {
        	//asisente: altaUsuario(nicknameU, nombreU, correoU, "Asistente", null, null, apellidoU, fechaNacLD);
            cu.altaUsuario("atorres", "Ana", "atorres@gmail.com", "IMG-US01.jpg", "123.torres", "Asistente",null, null, "Torres", LocalDate.of(1990, 5, 12));
        } catch (UsuarioRepetidoException ignored) {
            // Si ya existe, lo ignoramos
        }

        try {
        	//asisente: altaUsuario(nicknameU, nombreU, correoU, "Asistente", null, null, apellidoU, fechaNacLD);
            cu.altaUsuario("msilva", "Martin", "martin.silva@fing.edu.uy", "PerfilSinFoto.jpg", "msilva2025", "Asistente",null, null, "Silva", LocalDate.of(1987, 8, 21));
        } catch (UsuarioRepetidoException ignored) {
            // Si ya existe, lo ignoramos
        }

        try {
        	//asisente: altaUsuario(nicknameU, nombreU, correoU, "Asistente", null, null, apellidoU, fechaNacLD);
            cu.altaUsuario("sofirod", "Sofia", "srodriguez@outlook.com", "IMG-US03.jpeg", "srod.abc1", "Asistente",null, null, "Rodriguez", LocalDate.of(1995, 2, 3));
        } catch (UsuarioRepetidoException US03) {
            // Si ya existe, lo ignoramos
        }

        try {
        	//asisente: altaUsuario(nicknameU, nombreU, correoU, "Asistente", null, null, apellidoU, fechaNacLD);
            cu.altaUsuario("vale23", "Valentina", "valentina.costa@mail.com", "IMG-US07.jpeg", "valen11c", "Asistente",null, null, "Costa", LocalDate.of(1992, 12, 1));
        } catch (UsuarioRepetidoException ignored) {
            // Si ya existe, lo ignoramos
        }

        try {
        	//asisente: altaUsuario(nicknameU, nombreU, correoU, "Asistente", null, null, apellidoU, fechaNacLD);
            cu.altaUsuario("luciag", "Lucía", "lucia.garcia@mail.com", "IMG-US08.jpeg", "garcia.22l", "Asistente",null, null, "Garcia", LocalDate.of(1993, 11, 9));
        } catch (UsuarioRepetidoException ignored) {
            // Si ya existe, lo ignoramos
        }

        try {
        	//asisente: altaUsuario(nicknameU, nombreU, correoU, "Asistente", null, null, apellidoU, fechaNacLD);
            cu.altaUsuario("andrearod", "Andrea", "andrea.rod@mail.com", "IMG-US09.jpeg", "rod77and", "Asistente",null, null, "Rodriguez", LocalDate.of(2000, 6, 10));
        } catch (UsuarioRepetidoException ignored) {
            // Si ya existe, lo ignoramos
        }

        try {
        	//asisente: altaUsuario(nicknameU, nombreU, correoU, "Asistente", null, null, apellidoU, fechaNacLD);
            cu.altaUsuario("AnaG", "Ana", "ana.gomez@hotmail.com","IMG-US12.png", "gomez88a", "Asistente", null, null, "Gomez", LocalDate.of(1998, 3, 15));
        } catch (UsuarioRepetidoException ignored) {
            // Si ya existe, lo ignoramos
        }

        try {
        	//asisente: altaUsuario(nicknameU, nombreU, correoU, "Asistente", null, null, apellidoU, fechaNacLD);
            cu.altaUsuario("JaviL", "Javier", "javier.lopez@outlook.com","IMG-US13.jpeg", "jl99lopez", "Asistente",null, null, "Lopez", LocalDate.of(1995, 7, 22));
        } catch (UsuarioRepetidoException ignored) {
            // Si ya existe, lo ignoramos
        }

        try {
        	//asisente: altaUsuario(nicknameU, nombreU, correoU, "Asistente", null, null, apellidoU, fechaNacLD);
            cu.altaUsuario("MariR", "Maria", "maria.rodriguez@gmail.com", "IMG-US14.jpeg", "maria55r", "Asistente",null, null, "Rodriguez", LocalDate.of(2000, 11, 10));
        } catch (UsuarioRepetidoException ignored) {
            // Si ya existe, lo ignoramos
        }

        try {
        	//asisente: altaUsuario(nicknameU, nombreU, correoU, "Asistente", null, null, apellidoU, fechaNacLD);
            cu.altaUsuario("SofiM", "Sofia", "sofia.martinez@yahoo.com", "IMG-US15.jpeg", "smarti99z", "Asistente", null, null, "Martinez", LocalDate.of(1997, 2, 5));
        } catch (UsuarioRepetidoException ignored) {
            // Si ya existe, lo ignoramos
        }
    
        
        // Carga Categorías
        //a ltaCategoria(String nombre)
        try {
            ce.altaCategoria("Tecnología");
        } catch (CategoriaRepetidaException ignored) {}
        
        try {
            ce.altaCategoria("Innovación");
        } catch (CategoriaRepetidaException ignored) {}
        
        try {
            ce.altaCategoria("Literatura");
        } catch (CategoriaRepetidaException ignored) {}
        
        try {
            ce.altaCategoria("Cultura");
        } catch (CategoriaRepetidaException ignored) {}
        try {
            ce.altaCategoria("Música");
        } catch (CategoriaRepetidaException ignored) {}
        
        try {
            ce.altaCategoria("Deporte");
        } catch (CategoriaRepetidaException ignored) {}
        
        try {
            ce.altaCategoria("Salud");
        } catch (CategoriaRepetidaException ignored) {}
        
        try {
            ce.altaCategoria("Entretenimiento");
        } catch (CategoriaRepetidaException ignored) {}
        try {
            ce.altaCategoria("Agro");
        } catch (CategoriaRepetidaException ignored) {}
        
        try {
            ce.altaCategoria("Negocios");
        } catch (CategoriaRepetidaException ignored) {}
        
        try {
            ce.altaCategoria("Moda");
        } catch (CategoriaRepetidaException ignored) {}
        
        try {
            ce.altaCategoria("Investigación");
        } catch (CategoriaRepetidaException ignored) {}

        

        //Carga Eventos:
        //altaEvento(String nombre, String descripcion, String sigla, List<String> nombresCategorias) 
        try {
        	//	10/01/2025 
            ce.altaEvento("Conferencia de Tecnología", "Evento sobre innovación tecnolígica", "CONFTEC", List.of("Tecnología","Innovación"),LocalDate.of(2025,1,10), "EventoSinFoto.png");
        } catch (IllegalArgumentException ignored) {}
        
        try {
        	//	01/02/2025
            ce.altaEvento("Feria del Libro", "Encuentro anual de literatura", "FERLIB", List.of("Literatura","Cultura"),LocalDate.of(2025,2,1), "IMG-EV02.jpeg");
        } catch (IllegalArgumentException ignored) {}
        
        try {
        	// 15/03/2023 
            ce.altaEvento("Montevideo Rock", "Festival de rock con artistas nacionales e internacionales", "MONROCK", List.of("Cultura","Música"),LocalDate.of(2023,3,15), "IMG-EV03.jpeg");
        } catch (IllegalArgumentException ignored) {}
        
        try {
        	//	01/01/2022 
            ce.altaEvento("Maratón de Montevideo", "Competencia deportiva anual en la capital", "MARATON", List.of("Deporte","Salud"),LocalDate.of(2022,1,1), "IMG-EV04.png");
        } catch (IllegalArgumentException ignored) {}
        
        try {
        	//	10/04/2024 
            ce.altaEvento("Montevideo Comics", "Convención de historietas, cine y cultura geek", "COMICS", List.of("Cultura","Entretenimiento"),LocalDate.of(2024,4,10), "IMG-EV05.png");
        } catch (IllegalArgumentException ignored) {}
        
        try {
        	//	12/12/2024
            ce.altaEvento("Expointer Uruguay", "Exposición internacional agropecuaria y ganadera", "EXPOAGRO", List.of("Agro","Negocios"), LocalDate.of(2024,12,12), "IMG-EV06.png");
        } catch (IllegalArgumentException ignored) {}
        try {
        	//	20/07/2025 
            ce.altaEvento("Montevideo Fashion Week", "Pasarela de moda uruguaya e internacional", "MFASHION", List.of("Cultura","Investigación"),LocalDate.of(2025,7,20), "EventoSinFoto.png");
        } catch (IllegalArgumentException ignored) {}
        
        
     // Carga edición de evento
        //public void altaEdicionEvento(String nombreEvento, String nombreEdicion, String sigla, String ciudad, String pais, LocalDate fechaInicio, LocalDate fechaFin, LocalDate fechaAltaEnPlataforma, String organizadorNick) throws UsuarioNoExisteException {

        try {
            ce.altaEdicionEvento("Montevideo Rock","Montevideo Rock 2025", "MONROCK25","Montevideo", "Uruguay",LocalDate.of(2025,11,20),LocalDate.of(2025,11,22),LocalDate.of(2025,3,12),"imm", "IMG-EDEV01.jpeg");
            try {
            	ce.aceptarEdicion("Montevideo Rock 2025", true);
            } catch (EdicionNoExisteException ignored) {}
            catch (TransicionEstadoInvalidaException ignored) {}
        } catch (UsuarioNoExisteException ignored) {}
        
        try {
            ce.altaEdicionEvento("Maratón de Montevideo","Maratón de Montevideo 2025", "MARATON25","Montevideo", "Uruguay",LocalDate.of(2025,9,14),LocalDate.of(2025,9,14),LocalDate.of(2025,2,5),"imm", "IMG-EDEV02.png");
             
        } catch (UsuarioNoExisteException ignored) {}
        
        try {
            ce.altaEdicionEvento("Maratón de Montevideo","Maratón de Montevideo 2024", "MARATON24","Montevideo", "Uruguay",LocalDate.of(2024,9,14),LocalDate.of(2024,9,14),LocalDate.of(2024,4,21),"imm", "IMG-EDEV03.jpeg");
            try {
            	ce.aceptarEdicion("Maratón de Montevideo 2024", true);
            }catch (EdicionNoExisteException ignored) {}
	        catch (TransicionEstadoInvalidaException ignored) {}
        } catch (UsuarioNoExisteException ignored) {}
        
        try {
            ce.altaEdicionEvento("Maratón de Montevideo","Maratón de Montevideo 2022", "MARATON22","Montevideo", "Uruguay",LocalDate.of(2022,9,14),LocalDate.of(2022,9,14),LocalDate.of(2022,5,21),"imm", "IMG-EDEV04.jpeg");
            try {
            	ce.aceptarEdicion("Maratón de Montevideo 2022", false);
            }catch (EdicionNoExisteException ignored) {}
	        catch (TransicionEstadoInvalidaException ignored) {}
        } catch (UsuarioNoExisteException ignored) {}

        try {
            ce.altaEdicionEvento("Montevideo Comics","Montevideo Comics 2024", "COMICS24","Montevideo", "Uruguay",LocalDate.of(2024,7,18),LocalDate.of(2024,7,21),LocalDate.of(2024,6,20),"miseventos", "IMG-EDEV05.jpeg");
            try {
            	ce.aceptarEdicion("Montevideo Comics 2024", true);
            }catch (EdicionNoExisteException ignored) {}
	        catch (TransicionEstadoInvalidaException ignored) {}
        } catch (UsuarioNoExisteException ignored) {}
        
        try {
            ce.altaEdicionEvento("Montevideo Comics","Montevideo Comics 2025", "COMICS25","Montevideo", "Uruguay",LocalDate.of(2025,8,4),LocalDate.of(2025,8,6),LocalDate.of(2025,7,4),"miseventos", "IMG-EDEV06.jpeg");
            try {
            	ce.aceptarEdicion("Montevideo Comics 2025", true);
            }catch (EdicionNoExisteException ignored) {}
	        catch (TransicionEstadoInvalidaException ignored) {}
        } catch (UsuarioNoExisteException ignored) {}
        
        try {
            ce.altaEdicionEvento("Expointer Uruguay","Expointer Uruguay 2025", "EXPOAGRO25","Durazno", "Uruguay",LocalDate.of(2025,9,11),LocalDate.of(2025,9,17),LocalDate.of(2025,2,1),"miseventos", "IMG-EDEV07.jpeg");
            //solo ingresada
        } catch (UsuarioNoExisteException ignored) {}
        
        try {
            ce.altaEdicionEvento("Conferencia de Tecnología","Tecnología Punta del Este 2026", "CONFTECH26","Punta del Este", "Uruguay",LocalDate.of(2026,4,6),LocalDate.of(2026,4,10),LocalDate.of(2025,8,1),"udelar", "IMG-EDEV08.jpeg");
            try {
            	ce.aceptarEdicion("Tecnología Punta del Este 2026", true);
            }catch (EdicionNoExisteException ignored) {}
	        catch (TransicionEstadoInvalidaException ignored) {}
        } catch (UsuarioNoExisteException ignored) {}
        
        try {
            ce.altaEdicionEvento("Conferencia de Tecnología","Mobile World Congress 2025", "MWC","Barcelona", "España",LocalDate.of(2025,12,12),LocalDate.of(2025,12,15),LocalDate.of(2025,8,21),"techcorp", "EdicionSinFoto.png");
            try {
            	ce.aceptarEdicion("Mobile World Congress 2025", true);
            }catch (EdicionNoExisteException ignored) {}
	        catch (TransicionEstadoInvalidaException ignored) {}
        } catch (UsuarioNoExisteException ignored) {}
        
        try {
            ce.altaEdicionEvento("Conferencia de Tecnología","Web Summit 2026", "WS26","Lisboa", "Portugal",LocalDate.of(2026,1,13),LocalDate.of(2026,2,1),LocalDate.of(2025,6,4),"techcorp", "EdicionSinFoto.png");
            try {
            	ce.aceptarEdicion("Web Summit 2026", true);
            }catch (EdicionNoExisteException ignored) {}
	        catch (TransicionEstadoInvalidaException ignored) {}
        } catch (UsuarioNoExisteException ignored) {}
        
        try {
            ce.altaEdicionEvento("Montevideo Fashion Week","Montevideo Fashion Week 2026", "MFW26","Nueva York", "Estados Unidos",LocalDate.of(2026,2,16),LocalDate.of(2026,2,20),LocalDate.of(2025,10,2),"mec", "IMG-EDEV11.jpeg");
            //solo ingresada
        } catch (UsuarioNoExisteException ignored) {}

        //Carga tipos de registro
        //altaTipoRegistro(String nombreEvento, String nombreEdicion, String nombreTipoRegistro, String descripcion, Integer costo,  Integer cupos)

        try {
            ce.altaTipoRegistro("Montevideo Rock", "Montevideo Rock 2025", "General", "Acceso general a Montevideo Rock (2 días)", 1500, 2000);
        } catch (IllegalArgumentException ignored) {}

        try {
            ce.altaTipoRegistro("Montevideo Rock", "Montevideo Rock 2025", "VIP", "Incluye backstage + acceso preferencial", 4000, 200);
        } catch (IllegalArgumentException ignored) {}

        try {
            ce.altaTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2025", "Corredor 42K", "Inscripción a la maratón completa", 1200, 499);
        } catch (IllegalArgumentException ignored) {}
        
        try {
            ce.altaTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2025", "Corredor 21K", "Inscripción a la media maratón", 800, 700);
        } catch (IllegalArgumentException ignored) {}
       
        try {
            ce.altaTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2025", "Corredor 10K", "Inscripción a la carrera 10K", 500, 1000);
        } catch (IllegalArgumentException ignored) {}
        
        try {
            ce.altaTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2024", "Corredor 42K", "Inscripción a la maratón completa", 1000, 300);
        } catch (IllegalArgumentException ignored) {}
        
        try {
            ce.altaTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2024", "Corredor 21K", "Inscripción a la media maratón", 500, 500);
        } catch (IllegalArgumentException ignored) {}
        
        try {
            ce.altaTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2022", "Corredor 42K", "Inscripción a la maratón completa", 1100, 450);
        } catch (IllegalArgumentException ignored) {}
        
        try {
            ce.altaTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2022", "Corredor 21K", "Inscripción a la media maratón", 900, 750);
        } catch (IllegalArgumentException ignored) {}

        try {
            ce.altaTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2022", "Corredor 10K", "Inscripción a la carrera 10K", 650, 1400);
        } catch (IllegalArgumentException ignored) {}

        try {
            ce.altaTipoRegistro("Montevideo Comics", "Montevideo Comics 2024", "General", "Entrada para los 4 días de Montevideo Comics", 600, 1500);
        } catch (IllegalArgumentException ignored) {}

        try {
            ce.altaTipoRegistro("Montevideo Comics", "Montevideo Comics 2024", "Cosplayer", "Entrada especial con acreditación para concurso cosplay", 300, 50);
        } catch (IllegalArgumentException ignored) {}

        try {
            ce.altaTipoRegistro("Montevideo Comics", "Montevideo Comics 2025", "General", "Entrada para los 4 días de Montevideo Comics", 800, 1000);
        } catch (IllegalArgumentException ignored) {}

        try {
            ce.altaTipoRegistro("Montevideo Comics", "Montevideo Comics 2025", "Cosplayer", "Entrada especial con acreditación para concurso cosplay", 500, 100);
        } catch (IllegalArgumentException ignored) {}

        try {
            ce.altaTipoRegistro("Expointer Uruguay", "Expointer Uruguay 2025", "General", "Acceso a la exposición agropecuaria", 300, 5000);
        } catch (IllegalArgumentException ignored) {}

        try {
            ce.altaTipoRegistro("Expointer Uruguay", "Expointer Uruguay 2025", "Empresarial", "Acceso para empresas + networking", 2000, 5);
        } catch (IllegalArgumentException ignored) {}
        
        try {
            ce.altaTipoRegistro("Conferencia de Tecnología", "Tecnología Punta del Este 2026", "Full", "Acceso ilimitado + Cena de gala", 1800, 300);
        } catch (IllegalArgumentException ignored) {}

        try {
            ce.altaTipoRegistro("Conferencia de Tecnología", "Tecnología Punta del Este 2026", "General", "Acceso general", 1500, 500);
        } catch (IllegalArgumentException ignored) {}

        try {
            ce.altaTipoRegistro("Conferencia de Tecnología", "Tecnología Punta del Este 2026", "Estudiante", "Acceso para estudiantes", 1000, 50);
        } catch (IllegalArgumentException ignored) {}

        try {
            ce.altaTipoRegistro("Conferencia de Tecnología", "Mobile World Congress 2025", "Full", "Acceso ilimitado + Cena de gala", 750, 550);
        } catch (IllegalArgumentException ignored) {}

        try {
            ce.altaTipoRegistro("Conferencia de Tecnología", "Mobile World Congress 2025", "General", "Acceso general", 500, 400);
        } catch (IllegalArgumentException ignored) {}

        try {
            ce.altaTipoRegistro("Conferencia de Tecnología", "Mobile World Congress 2025", "Estudiante", "Acceso para estudiantes", 250, 400);
        } catch (IllegalArgumentException ignored) {}

        try {
            ce.altaTipoRegistro("Conferencia de Tecnología", "Web Summit 2026", "Full", "Acceso ilimitado + Cena de gala", 900, 30);
        } catch (IllegalArgumentException ignored) {}

        try {
            ce.altaTipoRegistro("Conferencia de Tecnología", "Web Summit 2026", "General", "Acceso general", 650, 5);
        } catch (IllegalArgumentException ignored) {}

        try {
            ce.altaTipoRegistro("Conferencia de Tecnología", "Web Summit 2026", "Estudiante", "Acceso para estudiantes", 300, 1);
        } catch (IllegalArgumentException ignored) {}
        try {
            ce.altaTipoRegistro("Montevideo Fashion Week", "Montevideo Fashion Week 2026", "Full", "Acceso a todos los eventos de la semana", 450, 50);
        } catch (IllegalArgumentException ignored) {}
        try {
            ce.altaTipoRegistro("Montevideo Fashion Week", "Montevideo Fashion Week 2026", "Visitante", "Acceso parcial a los eventos de la semana", 150, 25);
        } catch (IllegalArgumentException ignored) {}

        
        
     // Carga registros a ediciones de eventos
        try {
            ce.altaRegistro("Montevideo Rock", "Montevideo Rock 2025", "VIP", "sofirod",
                LocalDate.of(2025, 5, 14));

            ce.altaRegistro("Maratón de Montevideo", "Maratón de Montevideo 2024", "Corredor 21K", "sofirod",
                LocalDate.of(2024, 7, 30));

            ce.altaRegistro("Conferencia de Tecnología", "Web Summit 2026", "Estudiante", "andrearod",
                LocalDate.of(2025, 8, 21));

            ce.altaRegistro("Maratón de Montevideo", "Maratón de Montevideo 2025", "Corredor 42K", "sofirod",
                LocalDate.of(2025, 3, 3));

            ce.altaRegistro("Conferencia de Tecnología", "Mobile World Congress 2025", "Full", "vale23",
                LocalDate.of(2025, 8, 22));

            ce.altaRegistro("Maratón de Montevideo", "Maratón de Montevideo 2025", "Corredor 10K", "AnaG",
                LocalDate.of(2025, 4, 9));

            ce.altaRegistro("Maratón de Montevideo", "Maratón de Montevideo 2025", "Corredor 21K", "JaviL",
                LocalDate.of(2025, 4, 10));

            ce.altaRegistro("Montevideo Comics", "Montevideo Comics 2025", "Cosplayer", "MariR",
                LocalDate.of(2025, 8, 3));

            ce.altaRegistro("Montevideo Comics", "Montevideo Comics 2024", "General", "SofiM",
                LocalDate.of(2024, 7, 16));
            
            
            ce.altaRegistro("Conferencia de Tecnología", "Tecnología Punta del Este 2026", "Estudiante", "msilva",
                    LocalDate.of(2025, 10, 1));
            ce.setCostoRegistro("msilva","Tecnología Punta del Este 2026","Estudiante", 0);
            
            ce.altaRegistro("Conferencia de Tecnología", "Tecnología Punta del Este 2026", "General", "andrearod",
                    LocalDate.of(2025, 10, 6));
            ce.setCostoRegistro("msilva" ,"Tecnología Punta del Este 2026" , "General", 0);
            
            ce.altaRegistro("Conferencia de Tecnología", "Tecnología Punta del Este 2026", "General", "MariR",
                    LocalDate.of(2025, 10, 10));
            
        } catch (IllegalArgumentException ignored) {
        } catch (UsuarioNoExisteException ignored) {
        }

        
//        //Carga registro a edicion a evento:
//         1- //altaRegistro(String nombreEvento, String nombreEdicion, String nombreTipoRegistro, String nombreAsistente, LocalDate fecha) throws UsuarioNoExisteException
//         2- try {
//              ce.altaRegistro(String nombreEvento, String nombreEdicion, String nombreTipoRegistro, String nombreAsistente, LocalDate fecha);
//          } catch (IllegalArgumentException ignored) {}
//          
//      }
//      3-/*/
//       "sofirod" 	 "Montevideo Rock" 			 EDEV01 "Montevideo Rock 2025" 			TR02 VIP	 14/05/2025 4000
//  	 "sofirod"	 "Maratón de Montevideo"	 EDEV03 "Maratón de Montevideo 2024"	TR07 Corredor 21K	30/07/2024 500
//  	 "andrearod" "Conferencia de Tecnología" EDEV10 "Web Summit 2026" 				TR25 Estudiante	21/08/2025 300
//  	 "sofirod" 	"Maratón de Montevideo" 	 EDEV02 "Maratón de Montevideo 2025"	TR03 Corredor 42K	 03/03/2025 1200
//  	 "vale23" 	"Conferencia de Tecnología"  EDEV09	"Mobile World Congress 2025"	TR20 Full	22/08/2025 750
//  	 "AnaG" 	"Maratón de Montevideo" 	 EDEV02 "Maratón de Montevideo 2025" 	TR05 Corredor 10K	09/04/2025 500
//  	 "JaviL"	"Maratón de Montevideo" 	 EDEV02 "Maratón de Montevideo 2025" 	TR04 Corredor 21K	10/04/2025 800
//  	 "MariR" 	 "Montevideo Comics" 		 EDEV06 "Montevideo Comics 2025" 		TR14 Cosplayer	03/08/2025 500
//  	 "SofiM" 	"Montevideo Comics"  		 EDEV05 "Montevideo Comics 2024" 		TR11 General	16/07/2024 600
//  */
//      
//  }


        
        
        
        

    }
}