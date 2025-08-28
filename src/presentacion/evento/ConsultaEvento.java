package presentacion.evento;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import logica.datatypes.DataEvento;
import logica.clases.Evento;
import logica.interfaces.IControladorEvento;
import java.util.List;

public class ConsultaEvento extends JInternalFrame {
	private static final long serialVersionUID = 1L;
	private JList<DataEvento> listaEventos;
    private DefaultListModel<DataEvento> listaModel;
    private JTextArea infoEvento;
    private JTextField buscador;
    // Panel inferior dinámico
    private JLabel lblExtra;
    private JTextArea extraInfo;
    private DataEvento[] eventos;
    public ConsultaEvento(IControladorEvento iEV) {
        setSize(600, 400);

        // Panel izquierdo (buscador + lista)
        JPanel panelIzq = new JPanel(new BorderLayout());
        panelIzq.setBounds(0, 0, 200, 370);
        panelIzq.setPreferredSize(new Dimension(200, 0));
        JLabel lblLista = new JLabel("Eventos", SwingConstants.CENTER);
        panelIzq.add(lblLista, BorderLayout.NORTH);

        listaModel = new DefaultListModel<>();
        getContentPane().setLayout(null);
        listaEventos = new JList<>(listaModel);
        JScrollPane scrollLista = new JScrollPane(listaEventos);
        panelIzq.add(scrollLista, BorderLayout.CENTER);

        buscador = new JTextField("Buscar por nombre o sigla...");
        buscador.setForeground(Color.GRAY);
        panelIzq.add(buscador, BorderLayout.SOUTH);

        getContentPane().add(panelIzq);

        // Panel derecho con split
        infoEvento = new JTextArea();
        infoEvento.setEditable(false);
        JScrollPane scrollInfo = new JScrollPane(infoEvento);

        JPanel panelInferior = new JPanel(new BorderLayout());
        lblExtra = new JLabel("Información de Edicion seleccionada", SwingConstants.CENTER);
        panelInferior.add(lblExtra, BorderLayout.NORTH);

        extraInfo = new JTextArea();
        extraInfo.setEditable(false);
        panelInferior.add(new JScrollPane(extraInfo), BorderLayout.CENTER);

        JSplitPane splitDer = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollInfo, panelInferior);
        splitDer.setBounds(200, 0, 384, 370);
        
        JLabel lblInformacionDeEvento = new JLabel("Informacion de Evento seleccionado");
        scrollInfo.setColumnHeaderView(lblInformacionDeEvento);
        splitDer.setDividerLocation(200);
        splitDer.setResizeWeight(0.5);
        getContentPane().add(splitDer);

        // Acción al seleccionar un evento
        listaEventos.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarInfoEvento(listaEventos.getSelectedValue());
            }
        });

        // Filtrado dinámico
        buscador.getDocument().addDocumentListener(new DocumentListener() {
            private void filtrar() {
                String filtro = buscador.getText().trim();
                if (filtro.equals("Buscar por nombre o sigla...")) filtro = "";

                listaModel.clear();
                if (eventos != null) {
                    for (DataEvento ev : eventos) {
                        if (ev.getNombre().toLowerCase().contains(filtro.toLowerCase()) ||
                            ev.getSigla().toLowerCase().contains(filtro.toLowerCase())) {
                            listaModel.addElement(ev);
                        }
                    }
                }
            }
            public void insertUpdate(DocumentEvent e) { filtrar(); }
            public void removeUpdate(DocumentEvent e) { filtrar(); }
            public void changedUpdate(DocumentEvent e) { filtrar(); }
        });
    }

    // Método para cargar eventos desde la lógica
    public void cargarEventos(DataEvento[] lista) {
        this.eventos = lista;
        listaModel.clear();
        for (DataEvento ev : lista) {
            listaModel.addElement(ev);
        }
    }    
       
        
        
    private void mostrarInfoEvento(DataEvento evento) {
        if (evento == null) return;
        StringBuilder sb = new StringBuilder();
        sb.append("Nombre: ").append(evento.getNombre()).append("\n");
        sb.append("Sigla: ").append(evento.getSigla()).append("\n");
        sb.append("Descripción: ").append(evento.getDescripcion()).append("\n");
        sb.append("Fecha Alta: ").append(evento.getFechaAlta()).append("\n");
    }       
    
    
    
    public DataEvento getEventoSeleccionado() {
        return listaEventos.getSelectedValue();
    }
}
