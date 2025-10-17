package logica.cargadatos;


import excepciones.CategoriaRepetidaException;
import excepciones.EdicionNoExisteException;
import excepciones.EventoRepetidoException;
import excepciones.TipoRegistroRepetidoException;
import excepciones.TransicionEstadoInvalidaException;
import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioRepetidoException;
import java.time.LocalDate;
import java.util.List;
import logica.interfaces.ICargaDatos;
import logica.interfaces.IControladorEvento;
import logica.interfaces.IControladorUsuario;


/**
 * Clase para la carga de datos, pre-cargados.
 */
public class CargaDatos implements ICargaDatos {
  private final IControladorUsuario controladorUsuario;
  private final IControladorEvento controladorEvento;

  /**
   * Constructor que inicializa los controladores de usuario y evento
   * para la carga de datos de la consigna.
   *
   * @param controladorUsuario controlador de usuarios
   * @param controladorEvento  controlador de eventos
   */
  public CargaDatos(IControladorUsuario controladorUsuario, 
      IControladorEvento controladorEvento) {
    this.controladorUsuario = controladorUsuario;
    this.controladorEvento = controladorEvento;
  } 

  /**
   * Funcion que inicializa los controladores de usuario y evento
   * para la carga de datos de la consigna.
   */
  public void cargarDatosIniciales() {
    //Carga Organizadores:
    try {
      //organizador: altaUsuario(nicknameU, nombreU, correoU, 
      //"Organizador", descripcionU, linkU, null, null);
      // altaUsuario( nickname,  nombre,  correo,  imagen,  
      //password,  tipo, descripcion,  link,  apellido,  fechaNac)
      controladorUsuario.altaUsuario("miseventos", "MisEventos", "contacto@miseventos.com", 
          "IMG-US04.jpeg", "22miseventos", "Organizador", "Empresa de organización de eventos.", 
          "https://miseventos.com", null, null);   
    } catch (UsuarioRepetidoException ignored) {
        // Si ya existe, lo ignoramos 
    }
    try {
      //organizador: altaUsuario(nicknameU, nombreU, correoU, "Organizador", descripcionU, 
      //linkU, null, null);
      controladorUsuario.altaUsuario("techcorp", "Corporación Tecnológica", "info@techcorp.com", 
          "PerfilSinFoto.jpg", "tech25corp", "Organizador", 
          "Empresa líder en tecnologías de la información.", "https://techcorp.com", null, null);  
    } catch (UsuarioRepetidoException ignored) {
        // Si ya existe, lo ignoramos 
    }

    try {
      //organizador: altaUsuario(nicknameU, nombreU, correoU, "Organizador", descripcionU, 
      //linkU, null, null);
      controladorUsuario.altaUsuario("imm", "Intendencia de Montevideo", "contacto@imm.gub.uy",
          "IMG-US06.png", "imm2025", "Organizador", "Gobierno departamental de Montevideo.", 
          "https://montevideo.gub.uy", null, null);
    } catch (UsuarioRepetidoException ignored) {
        // Si ya existe, lo ignoramos 
    }

    try {
      //organizador: altaUsuario(nicknameU, nombreU, correoU, "Organizador", 
      //descripcionU, linkU, null, null);
      controladorUsuario.altaUsuario("udelar", "Universidad de la República", "contacto@udelar.edu.uy", "PerfilSinFoto.jpg", "25udelar", "Organizador", "Universidad pública de Uruguay.", "https://udelar.edu.uy", null, null);
    } catch (UsuarioRepetidoException ignored) {
        // Si ya existe, lo ignoramos 
    }

    try {
      //organizador: altaUsuario(nicknameU, nombreU, correoU, "Organizador", descripcionU,
      //linkU, null, null);
      controladorUsuario.altaUsuario("mec", "Ministerio de Educación y Cultura", "mec@mec.gub.uy", 
          "IMG-US11.png", "mec2025ok", "Organizador", "Institución pública promotora de cultura.", 
          "https://mec.gub.uy", null, null);
    } catch (UsuarioRepetidoException ignored) {
        // Si ya existe, lo ignoramos 
    }

    // Carga Asistentes:
    try {
      //asisente: altaUsuario(nicknameU, nombreU, correoU, "Asistente", null, 
      //null, apellidoU, fechaNacLD);
      controladorUsuario.altaUsuario("atorres", "Ana", "atorres@gmail.com", "IMG-US01.jpg", 
          "123.torres", "Asistente", null, null, "Torres", LocalDate.of(1990, 5, 12));
    } catch (UsuarioRepetidoException ignored) {
        // Si ya existe, lo ignoramos
    }

    try {
      //asisente: altaUsuario(nicknameU, nombreU, correoU, 
      //"Asistente", null, null, apellidoU, fechaNacLD);
      controladorUsuario.altaUsuario("msilva", "Martin", "martin.silva@fing.edu.uy", 
          "PerfilSinFoto.jpg", "msilva2025", "Asistente", null, null, "Silva", 
          LocalDate.of(1987, 8, 21));
    } catch (UsuarioRepetidoException ignored) {
        // Si ya existe, lo ignoramos
    }

    try {
      controladorUsuario.altaUsuario("sofirod", "Sofia", "srodriguez@outlook.com", 
          "IMG-US03.jpeg", "srod.abc1", "Asistente", null, null, "Rodriguez",
          LocalDate.of(1995, 2, 3));
    } catch (UsuarioRepetidoException usuario03) {
        // Si ya existe, lo ignoramos
    }

    try {
      controladorUsuario.altaUsuario("vale23", "Valentina", "valentina.costa@mail.com",
          "IMG-US07.jpeg", "valen11c", "Asistente", null, null, "Costa", 
          LocalDate.of(1992, 12, 1));
    } catch (UsuarioRepetidoException ignored) {
        // Si ya existe, lo ignoramos
    }

    try {
      controladorUsuario.altaUsuario("luciag", "Lucía", "lucia.garcia@mail.com", 
          "IMG-US08.jpeg", "garcia.22l", "Asistente", null, null, "Garcia",
          LocalDate.of(1993, 11, 9));
    } catch (UsuarioRepetidoException ignored) {
        // Si ya existe, lo ignoramos
    }

    try {
      controladorUsuario.altaUsuario("andrearod", "Andrea", "andrea.rod@mail.com", "IMG-US09.jpeg",
          "rod77and", "Asistente", null, null, "Rodriguez", 
          LocalDate.of(2000, 6, 10));
    } catch (UsuarioRepetidoException ignored) {
        // Si ya existe, lo ignoramos
    }

    try {
      controladorUsuario.altaUsuario("AnaG", "Ana", "ana.gomez@hotmail.com", "IMG-US12.png", 
          "gomez88a", "Asistente", null, null, "Gomez", LocalDate.of(1998, 3, 15));
    } catch (UsuarioRepetidoException ignored) {
        // Si ya existe, lo ignoramos
    }

    try {
      controladorUsuario.altaUsuario("JaviL", "Javier", "javier.lopez@outlook.com", "IMG-US13.jpeg",
          "jl99lopez", "Asistente", null, null, "Lopez", LocalDate.of(1995, 7, 22));
    } catch (UsuarioRepetidoException ignored) {
        // Si ya existe, lo ignoramos
    }

    try {
      controladorUsuario.altaUsuario("MariR", "Maria", "maria.rodriguez@gmail.com", "IMG-US14.jpeg",
          "maria55r", "Asistente", null, null, "Rodriguez", LocalDate.of(2000, 11, 10));
    } catch (UsuarioRepetidoException ignored) {
        // Si ya existe, lo ignoramos
    }

    try {
      controladorUsuario.altaUsuario("SofiM", "Sofia", "sofia.martinez@yahoo.com", "IMG-US15.jpeg",
          "smarti99z", "Asistente", null, null, "Martinez", LocalDate.of(1997, 2, 5));
    } catch (UsuarioRepetidoException ignored) {
        // Si ya existe, lo ignoramos
    }
    // Carga Categorías
    //a ltaCategoria(String nombre)
    try {
      controladorEvento.altaCategoria("Tecnología");
    } catch (CategoriaRepetidaException ignored) {
      // si existe ignoro
    }
    try {
      controladorEvento.altaCategoria("Innovación");
    } catch (CategoriaRepetidaException ignored) {
      // si existe ignoro
    }
    
    try {
      controladorEvento.altaCategoria("Literatura");
    } catch (CategoriaRepetidaException ignored) {
      // si existe ignoro
    }
    
    try {
      controladorEvento.altaCategoria("Cultura");
    } catch (CategoriaRepetidaException ignored) {
      // si existe ignoro
    }
    try {
      controladorEvento.altaCategoria("Música");
    } catch (CategoriaRepetidaException ignored) {
      // si existe ignoro
    }
    
    try {
      controladorEvento.altaCategoria("Deporte");
    } catch (CategoriaRepetidaException ignored) {
      // si existe ignoro
    }
    
    try {
      controladorEvento.altaCategoria("Salud");
    } catch (CategoriaRepetidaException ignored) {
      // si existe ignoro
    }
    
    try {
      controladorEvento.altaCategoria("Entretenimiento");
    } catch (CategoriaRepetidaException ignored) {
      // si existe ignoro
    }
    try {
      controladorEvento.altaCategoria("Agro");
    } catch (CategoriaRepetidaException ignored) {
      // si existe ignoro
    }
    
    try {
      controladorEvento.altaCategoria("Negocios");
    } catch (CategoriaRepetidaException ignored) {
      // si existe ignoro
    }
    
    try {
      controladorEvento.altaCategoria("Moda");
    } catch (CategoriaRepetidaException ignored) {
      // si existe ignoro
    }
    
    try {
      controladorEvento.altaCategoria("Investigación");
    } catch (CategoriaRepetidaException ignored) {
      // si existe ignoro
    }

    

    //Carga Eventos:
    //altaEvento(String nombre, String descripcion, String sigla, List<String> nombresCategorias) 
    try {
      //10/01/2025
      controladorEvento.altaEvento("Conferencia de Tecnología", 
          "Evento sobre innovación tecnolígica", "CONFTEC", List.of("Tecnología", "Innovación"), 
          LocalDate.of(2025, 1, 10), "EventoSinFoto.png");
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (EventoRepetidoException ignored) {
      // si existe ignoro
    }
    
    try {
      //01/02/2025
      controladorEvento.altaEvento("Feria del Libro", "Encuentro anual de literatura", "FERLIB", 
          List.of("Literatura", "Cultura"), LocalDate.of(2025, 2, 1), "IMG-EV02.jpeg");
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (EventoRepetidoException ignored) {
      // si existe ignoro
    }
    
    try {
      //15/03/2023 
      controladorEvento.altaEvento("Montevideo Rock", 
          "Festival de rock con artistas nacionales e internacionales", "MONROCK", 
          List.of("Cultura", "Música"), LocalDate.of(2023, 3, 15), "IMG-EV03.jpeg");
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (EventoRepetidoException ignored) {
      // si existe ignoro
    }
    
    try {
      //01/01/2022
      controladorEvento.altaEvento("Maratón de Montevideo", 
          "Competencia deportiva anual en la capital", "MARATON", List.of("Deporte", "Salud"), 
          LocalDate.of(2022, 1, 1), "IMG-EV04.png");
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (EventoRepetidoException ignored) {
      // si existe ignoro
    }
    
    try {
      //10/04/2024 
      controladorEvento.altaEvento("Montevideo Comics", 
          "Convención de historietas, cine y cultura geek", "COMICS", 
          List.of("Cultura", "Entretenimiento"), LocalDate.of(2024, 4, 10), "IMG-EV05.png");
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (EventoRepetidoException ignored) {
      // si existe ignoro
    }
    
    try {
      //12/12/2024
      controladorEvento.altaEvento("Expointer Uruguay", 
          "Exposición internacional agropecuaria y ganadera", "EXPOAGRO", 
          List.of("Agro", "Negocios"), LocalDate.of(2024, 12, 12), "IMG-EV06.png");
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (EventoRepetidoException ignored) {
      // si existe ignoro
    }
    try {
      //20/07/2025 
      controladorEvento.altaEvento("Montevideo Fashion Week", 
          "Pasarela de moda uruguaya e internacional", "MFASHION", 
          List.of("Cultura", "Investigación"), LocalDate.of(2025, 7, 20), "EventoSinFoto.png");
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (EventoRepetidoException ignored) {
      // si existe ignoro
    }
    
    // Carga edición de evento
    //public void altaEdicionEvento(String nombreEvento, String nombreEdicion, String sigla, 
    //String ciudad, String pais, LocalDate fechaInicio, LocalDate fechaFin, 
    // LocalDate fechaAltaEnPlataforma, String organizadorNick) throws UsuarioNoExisteException {

    try {
      controladorEvento.altaEdicionEvento("Montevideo Rock", "Montevideo Rock 2025", "MONROCK25",
          "Montevideo", "Uruguay", LocalDate.of(2025, 11, 20),
          LocalDate.of(2025, 11, 22), LocalDate.of(2025, 3, 12), "imm", "IMG-EDEV01.jpeg");
      try {
        controladorEvento.aceptarEdicion("Montevideo Rock 2025", true);
      } catch (EdicionNoExisteException ignored) {
        // si existe ignoro
      } catch (TransicionEstadoInvalidaException ignored) {
        // si existe ignoro
      }
    } catch (UsuarioNoExisteException ignored) {
      // si existe ignoro
    }
    
    try {
      controladorEvento.altaEdicionEvento("Maratón de Montevideo", "Maratón de Montevideo 2025",
          "MARATON25", "Montevideo", "Uruguay", LocalDate.of(2025, 9, 14),
          LocalDate.of(2025, 9, 14), LocalDate.of(2025, 2, 5), "imm", "IMG-EDEV02.png");
      
      controladorEvento.aceptarEdicion("Maratón de Montevideo 2025", true);
         
    } catch (UsuarioNoExisteException | EdicionNoExisteException | TransicionEstadoInvalidaException  ignored) {
      // si existe ignoro
    } 
    try {
      controladorEvento.altaEdicionEvento("Maratón de Montevideo", "Maratón de Montevideo 2024", 
          "MARATON24", "Montevideo", "Uruguay", LocalDate.of(2024, 9, 14),
          LocalDate.of(2024, 9, 14), LocalDate.of(2024, 4, 21), "imm", "IMG-EDEV03.jpeg");
      try {
        controladorEvento.aceptarEdicion("Maratón de Montevideo 2024", true);
      } catch (EdicionNoExisteException ignored) {
        // si existe ignoro
      } catch (TransicionEstadoInvalidaException ignored) {
        // si existe ignoro
      }
    } catch (UsuarioNoExisteException ignored) {
      // si existe ignoro
    }
    
    try {
      controladorEvento.altaEdicionEvento("Maratón de Montevideo", "Maratón de Montevideo 2022", 
          "MARATON22", "Montevideo", "Uruguay", LocalDate.of(2022, 9, 14), 
          LocalDate.of(2022, 9, 14), LocalDate.of(2022, 5, 21), "imm", "IMG-EDEV04.jpeg");
      try {
        controladorEvento.aceptarEdicion("Maratón de Montevideo 2022", false);
      } catch (EdicionNoExisteException ignored) {
        // si existe ignoro
      } catch (TransicionEstadoInvalidaException ignored) {
         // si existe ignoro
      }
    } catch (UsuarioNoExisteException ignored) {
      // si existe ignoro
    }

    try {
      controladorEvento.altaEdicionEvento("Montevideo Comics", "Montevideo Comics 2024",
          "COMICS24", "Montevideo", "Uruguay", LocalDate.of(2024, 7, 18), 
          LocalDate.of(2024, 7, 21), LocalDate.of(2024, 6, 20), "miseventos", "IMG-EDEV05.jpeg");
      try {
        controladorEvento.aceptarEdicion("Montevideo Comics 2024", true);
      } catch (EdicionNoExisteException ignored) {
        // si existe ignoro
      } catch (TransicionEstadoInvalidaException ignored) {
        // si existe ignoro
      }
    } catch (UsuarioNoExisteException ignored) {
      // si existe ignoro
    }
    
    try {
      controladorEvento.altaEdicionEvento("Montevideo Comics", "Montevideo Comics 2025", "COMICS25",
          "Montevideo", "Uruguay", LocalDate.of(2025, 8, 4), LocalDate.of(2025, 8, 6),
          LocalDate.of(2025, 7, 4), "miseventos", "IMG-EDEV06.jpeg");
      try {
        controladorEvento.aceptarEdicion("Montevideo Comics 2025", true);
      } catch (EdicionNoExisteException ignored) {
        // si existe ignoro
      } catch (TransicionEstadoInvalidaException ignored) {
        // si existe ignoro
      }
    } catch (UsuarioNoExisteException ignored) {
      // si existe ignoro
    }
    
    try {
      controladorEvento.altaEdicionEvento("Expointer Uruguay", "Expointer Uruguay 2025", 
          "EXPOAGRO25", "Durazno", "Uruguay", LocalDate.of(2025, 9, 11), 
          LocalDate.of(2025, 9, 17), LocalDate.of(2025, 2, 1), "miseventos", "IMG-EDEV07.jpeg");
      //solo ingresada
    } catch (UsuarioNoExisteException ignored) {
      // si existe ignoro
    }
    
    try {
      controladorEvento.altaEdicionEvento("Conferencia de Tecnología", 
          "Tecnología Punta del Este 2026", "CONFTECH26", "Punta del Este", "Uruguay", 
          LocalDate.of(2026, 4, 6), LocalDate.of(2026, 4, 10), LocalDate.of(2025, 8, 1), 
          "udelar", "IMG-EDEV08.jpeg");
      try {
      	controladorEvento.aceptarEdicion("Tecnología Punta del Este 2026", true);
      } catch (EdicionNoExisteException ignored) {
        // si existe ignoro
      } catch (TransicionEstadoInvalidaException ignored) {
        // si existe ignoro
      }
    } catch (UsuarioNoExisteException ignored) {
      // si existe ignoro
    }
    
    try {
      controladorEvento.altaEdicionEvento("Conferencia de Tecnología", "Mobile World Congress 2025",
          "MWC", "Barcelona", "España", LocalDate.of(2025, 12, 12), LocalDate.of(2025, 12, 15),
          LocalDate.of(2025, 8, 21), "techcorp", "EdicionSinFoto.png");
      try {
        controladorEvento.aceptarEdicion("Mobile World Congress 2025", true);
      } catch (EdicionNoExisteException ignored) {
        // si existe ignoro
      } catch (TransicionEstadoInvalidaException ignored) {
        // si existe ignoro
      }
    } catch (UsuarioNoExisteException ignored) {
      // si existe ignoro
    }
    
    try {
      controladorEvento.altaEdicionEvento("Conferencia de Tecnología", "Web Summit 2026", 
          "WS26", "Lisboa", "Portugal", LocalDate.of(2026, 1, 13), LocalDate.of(2026, 2, 1), 
          LocalDate.of(2025, 6, 4), "techcorp", "EdicionSinFoto.png");
      try {
        controladorEvento.aceptarEdicion("Web Summit 2026", true);
      } catch (EdicionNoExisteException ignored) {
        // si existe ignoro
      } catch (TransicionEstadoInvalidaException ignored) {
        // si existe ignoro
      }
    } catch (UsuarioNoExisteException ignored) {
      // si existe ignoro
    }
    
    try {
      controladorEvento.altaEdicionEvento("Montevideo Fashion Week", "Montevideo Fashion Week 2026",
          "MFW26", "Nueva York", "Estados Unidos", LocalDate.of(2026, 2, 16), 
          LocalDate.of(2026, 2, 20), LocalDate.of(2025, 10, 2), "mec", "IMG-EDEV11.jpeg");
      //solo ingresada
    } catch (UsuarioNoExisteException ignored) {
      // si existe ignoro
    }

    //Carga tipos de registro
    //altaTipoRegistro(String nombreEvento, String nombreEdicion, String nombreTipoRegistro, 
    // String descripcion, Integer costo,  Integer cupos)
    try {
      controladorEvento.altaTipoRegistro("Montevideo Rock", "Montevideo Rock 2025", "General",
          "Acceso general a Montevideo Rock (2 días)", 1500, 2000);
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (TipoRegistroRepetidoException ignored) {
      // si existe ignoro
    }

    try {
      controladorEvento.altaTipoRegistro("Montevideo Rock", "Montevideo Rock 2025", "VIP",
          "Incluye backstage + acceso preferencial", 4000, 200);
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (TipoRegistroRepetidoException ignored) {
      // si existe ignoro
    }

    try {
      controladorEvento.altaTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2025", 
          "Corredor 42K", "Inscripción a la maratón completa", 1200, 499);
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (TipoRegistroRepetidoException ignored) {
      // si existe ignoro
    }
    
    try {
      controladorEvento.altaTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2025", 
          "Corredor 21K", "Inscripción a la media maratón", 800, 700);
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (TipoRegistroRepetidoException ignored) {
      // si existe ignoro
    }
   
    try {
      controladorEvento.altaTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2025", 
          "Corredor 10K", "Inscripción a la carrera 10K", 500, 1000);
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (TipoRegistroRepetidoException ignored) {
      // si existe ignoro
    }
    
    try {
      controladorEvento.altaTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2024", 
          "Corredor 42K", "Inscripción a la maratón completa", 1000, 300);
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (TipoRegistroRepetidoException ignored) {
      // si existe ignoro
    }
    
    try {
      controladorEvento.altaTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2024", 
          "Corredor 21K", "Inscripción a la media maratón", 500, 500);
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (TipoRegistroRepetidoException ignored) {
      // si existe ignoro
    }
    
    try {
      controladorEvento.altaTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2022", 
          "Corredor 42K", "Inscripción a la maratón completa", 1100, 450);
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (TipoRegistroRepetidoException ignored) {
      // si existe ignoro
    } 
    
    try {
      controladorEvento.altaTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2022", 
          "Corredor 21K", "Inscripción a la media maratón", 900, 750);
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (TipoRegistroRepetidoException ignored) {
      // si existe ignoro
    }

    try {
      controladorEvento.altaTipoRegistro("Maratón de Montevideo", "Maratón de Montevideo 2022", 
          "Corredor 10K", "Inscripción a la carrera 10K", 650, 1400);
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (TipoRegistroRepetidoException ignored) {
      // si existe ignoro
    }

    try {
      controladorEvento.altaTipoRegistro("Montevideo Comics", "Montevideo Comics 2024", "General",
          "Entrada para los 4 días de Montevideo Comics", 600, 1500);
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (TipoRegistroRepetidoException ignored) {
      // si existe ignoro
    }

    try {
      controladorEvento.altaTipoRegistro("Montevideo Comics", "Montevideo Comics 2024", 
          "Cosplayer", "Entrada especial con acreditación para concurso cosplay", 300, 50);
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (TipoRegistroRepetidoException ignored) {
      // si existe ignoro
    }

    try {
      controladorEvento.altaTipoRegistro("Montevideo Comics", "Montevideo Comics 2025", "General", 
          "Entrada para los 4 días de Montevideo Comics", 800, 1000);
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (TipoRegistroRepetidoException ignored) {
      // si existe ignoro
    }

    try {
      controladorEvento.altaTipoRegistro("Montevideo Comics", "Montevideo Comics 2025", 
          "Cosplayer", "Entrada especial con acreditación para concurso cosplay", 500, 100);
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (TipoRegistroRepetidoException ignored) {
      // si existe ignoro
    }

    try {
      controladorEvento.altaTipoRegistro("Expointer Uruguay", "Expointer Uruguay 2025", "General",
          "Acceso a la exposición agropecuaria", 300, 5000);
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (TipoRegistroRepetidoException ignored) {
      // si existe ignoro
    }

    try {
      controladorEvento.altaTipoRegistro("Expointer Uruguay", "Expointer Uruguay 2025", 
          "Empresarial", "Acceso para empresas + networking", 2000, 5);
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (TipoRegistroRepetidoException ignored) {
      // si existe ignoro
    }
    
    try {
      controladorEvento.altaTipoRegistro("Conferencia de Tecnología",
          "Tecnología Punta del Este 2026", "Full", "Acceso ilimitado + Cena de gala", 1800, 300);
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (TipoRegistroRepetidoException ignored) {
      // si existe ignoro
    }

    try {
      controladorEvento.altaTipoRegistro("Conferencia de Tecnología", 
          "Tecnología Punta del Este 2026", "General", "Acceso general", 1500, 500);
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (TipoRegistroRepetidoException ignored) {
      // si existe ignoro
    }

    try {
      controladorEvento.altaTipoRegistro("Conferencia de Tecnología", 
          "Tecnología Punta del Este 2026", "Estudiante", "Acceso para estudiantes", 1000, 50);
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (TipoRegistroRepetidoException ignored) {
      // si existe ignoro
    }

    try {
      controladorEvento.altaTipoRegistro("Conferencia de Tecnología", "Mobile World Congress 2025",
          "Full", "Acceso ilimitado + Cena de gala", 750, 550);
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (TipoRegistroRepetidoException ignored) {
      // si existe ignoro
    }

    try {
      controladorEvento.altaTipoRegistro("Conferencia de Tecnología", "Mobile World Congress 2025",
          "General", "Acceso general", 500, 400);
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (TipoRegistroRepetidoException ignored) {
      // si existe ignoro
    }

    try {
      controladorEvento.altaTipoRegistro("Conferencia de Tecnología", "Mobile World Congress 2025",
          "Estudiante", "Acceso para estudiantes", 250, 400);
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (TipoRegistroRepetidoException ignored) {
      // si existe ignoro
    }

    try {
      controladorEvento.altaTipoRegistro("Conferencia de Tecnología", "Web Summit 2026", "Full",
          "Acceso ilimitado + Cena de gala", 900, 30);
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (TipoRegistroRepetidoException ignored) {
      // si existe ignoro
    }

    try {
      controladorEvento.altaTipoRegistro("Conferencia de Tecnología", "Web Summit 2026", "General",
          "Acceso general", 650, 5);
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (TipoRegistroRepetidoException ignored) {
      // si existe ignoro
    }

    try {
      controladorEvento.altaTipoRegistro("Conferencia de Tecnología", "Web Summit 2026", 
          "Estudiante", "Acceso para estudiantes", 300, 1);
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (TipoRegistroRepetidoException ignored) {
      // si existe ignoro
    }
    try {
      controladorEvento.altaTipoRegistro("Montevideo Fashion Week", "Montevideo Fashion Week 2026",
          "Full", "Acceso a todos los eventos de la semana", 450, 50);
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (TipoRegistroRepetidoException ignored) {
      // si existe ignoro
    }
    try {
      controladorEvento.altaTipoRegistro("Montevideo Fashion Week", "Montevideo Fashion Week 2026",
          "Visitante", "Acceso parcial a los eventos de la semana", 150, 25);
    } catch (IllegalArgumentException ignored) {
      // si existe ignoro
    } catch (TipoRegistroRepetidoException ignored) {
      // si existe ignoro
    }
    
    // Carga registros a ediciones de eventos
    try {
        controladorEvento.altaRegistro("Montevideo Rock", "Montevideo Rock 2025", "VIP", "sofirod",
            LocalDate.of(2025, 5, 14));

        controladorEvento.altaRegistro("Maratón de Montevideo", "Maratón de Montevideo 2024", "Corredor 21K", "sofirod",
            LocalDate.of(2024, 7, 30));

        controladorEvento.altaRegistro("Conferencia de Tecnología", "Web Summit 2026", "Estudiante", "andrearod",
            LocalDate.of(2025, 8, 21));

        controladorEvento.altaRegistro("Maratón de Montevideo", "Maratón de Montevideo 2025", "Corredor 42K", "sofirod",
            LocalDate.of(2025, 3, 3));

        controladorEvento.altaRegistro("Conferencia de Tecnología", "Mobile World Congress 2025", "Full", "vale23",
            LocalDate.of(2025, 8, 22));

        controladorEvento.altaRegistro("Maratón de Montevideo", "Maratón de Montevideo 2025", "Corredor 10K", "AnaG",
            LocalDate.of(2025, 4, 9));

        controladorEvento.altaRegistro("Maratón de Montevideo", "Maratón de Montevideo 2025", "Corredor 21K", "JaviL",
            LocalDate.of(2025, 4, 10));

        controladorEvento.altaRegistro("Montevideo Comics", "Montevideo Comics 2025", "Cosplayer", "MariR",
            LocalDate.of(2025, 8, 3));

        controladorEvento.altaRegistro("Montevideo Comics", "Montevideo Comics 2024", "General", "SofiM",
            LocalDate.of(2024, 7, 16));
        
        
        controladorEvento.altaRegistro("Conferencia de Tecnología", "Tecnología Punta del Este 2026", "Estudiante", "msilva",
                LocalDate.of(2025, 10, 1));
        controladorEvento.setCostoRegistro("msilva", "Tecnología Punta del Este 2026", "Estudiante", 0);
        
        controladorEvento.altaRegistro("Conferencia de Tecnología", "Tecnología Punta del Este 2026", "General", "andrearod",
                LocalDate.of(2025, 10, 6));
        controladorEvento.setCostoRegistro("msilva" , "Tecnología Punta del Este 2026" , "General", 0);
        
        controladorEvento.altaRegistro("Conferencia de Tecnología", "Tecnología Punta del Este 2026", "General", "MariR",
                LocalDate.of(2025, 10, 10));
        
    } catch (IllegalArgumentException ignored) {
      // No hago nada
    } catch (UsuarioNoExisteException ignored) {
      // No hago nada
    }

  }
}