package presentacion;
import java.util.*;
import javax.swing.*;

public class UIUtils  {
    
    public static void ordenarJList(DefaultListModel<String> model) {
        List<String> elementos = new ArrayList<>();
        for (int i = 0; i < model.size(); i++) {
            elementos.add(model.getElementAt(i));
        }
        Collections.sort(elementos);
        model.clear();
        for (String elem : elementos) {
            model.addElement(elem);
        }
    }

    public static void ordenarJComboBox(JComboBox<String> combo) {
        List<String> elementos = new ArrayList<>();
        for (int i = 0; i < combo.getItemCount(); i++) {
            elementos.add(combo.getItemAt(i));
        }
        Collections.sort(elementos);
        combo.removeAllItems();
        for (String elem : elementos) {
            combo.addItem(elem);
        }
    }
}