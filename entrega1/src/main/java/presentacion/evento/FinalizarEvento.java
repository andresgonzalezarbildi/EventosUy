package presentacion.evento;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import excepciones.EventoConEdicionesPedientesException;
import logica.datatypes.DataEvento;
import logica.interfaces.IControladorEvento;

public class FinalizarEvento extends JInternalFrame {
    private IControladorEvento controlEvento;
    private JComboBox<DataEvento> comboBoxEvento;
    private JButton btnFinalizar;

    public FinalizarEvento(IControladorEvento controlEvento) {
        super("Finalizar Evento", false, true, true, true);
        this.controlEvento = controlEvento;

        // Puedes comentar o reducir el setBounds para que no sea tan grande:
        // setBounds(100, 100, 800, 520);
        // En su lugar, usar pack() y centrar:
        // se hará al final

        // Layout general 
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{30, 39, 360, 0};
        gridBagLayout.rowHeights   = new int[]{22, 22, 40, 0};  // reduje la altura de la fila 2
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights    = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE}; // peso 0 para que no “estire”
        getContentPane().setLayout(gridBagLayout);

        // Etiqueta “Evento:”
        JLabel lblEvento = new JLabel("Evento:");
        GridBagConstraints gbc_lblEvento = new GridBagConstraints();
        gbc_lblEvento.anchor = GridBagConstraints.EAST;
        gbc_lblEvento.insets = new Insets(5, 5, 5, 5);
        gbc_lblEvento.gridx = 1;
        gbc_lblEvento.gridy = 0;
        getContentPane().add(lblEvento, gbc_lblEvento);

        // ComboBox
        comboBoxEvento = new JComboBox<>();
        GridBagConstraints gbc_comboBoxEvento = new GridBagConstraints();
        gbc_comboBoxEvento.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBoxEvento.insets = new Insets(5, 5, 5, 5);
        gbc_comboBoxEvento.gridx = 2;
        gbc_comboBoxEvento.gridy = 0;
        getContentPane().add(comboBoxEvento, gbc_comboBoxEvento);

        // Panel para el botón
        JPanel panelBotones = new JPanel(); 
        btnFinalizar = new JButton("Finalizar Evento");
        btnFinalizar.setEnabled(false);
        panelBotones.add(btnFinalizar);
        // Añadir listener
        
        comboBoxEvento.addItemListener(e -> {
            Object sel = comboBoxEvento.getSelectedItem();
            btnFinalizar.setEnabled(sel != null);
        });
        
        btnFinalizar.addActionListener(e -> {
        		DataEvento de = (DataEvento) comboBoxEvento.getSelectedItem();
        		finalizarEvento(de);
        	});

        GridBagConstraints gbc_panelBotones = new GridBagConstraints();
        gbc_panelBotones.insets = new Insets(10, 5, 5, 5);
        gbc_panelBotones.gridx = 2;
        gbc_panelBotones.gridy = 2;
        gbc_panelBotones.anchor = GridBagConstraints.CENTER;
        getContentPane().add(panelBotones, gbc_panelBotones);
        


    }

    private void finalizarEvento(DataEvento ev) {
        if (ev == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un evento.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            controlEvento.finalizarEvento(ev.getNombre());
            JOptionPane.showMessageDialog(this, "Evento finalizado.", "OK", JOptionPane.INFORMATION_MESSAGE);
            resetearVentana();

        } catch (EventoConEdicionesPedientesException ex) {
            JOptionPane.showMessageDialog(
                this,
                "No se puede finalizar el evento: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void cargarEventos() {
        comboBoxEvento.removeAllItems();
        DataEvento[] eventos = controlEvento.getEventosDTO();
        if (eventos != null) {
            java.util.List<DataEvento> lista = new java.util.ArrayList<>();
            for (DataEvento ev : eventos) if (ev != null) lista.add(ev);
            lista.sort(java.util.Comparator.comparing(DataEvento::getNombre, String.CASE_INSENSITIVE_ORDER));
            for (DataEvento ev : lista) comboBoxEvento.addItem(ev);
        }
        comboBoxEvento.setSelectedIndex(-1);
    }

    public void resetearVentana() {
        comboBoxEvento.removeAllItems();
        cargarEventos();
        setTitle("Finalizar Evento");
    }
}
