
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sergi
 */
public class GestorePanels {
    Container container;
    GestoreGioco g;
    JPanel[] panels;

    public GestorePanels(Container container, GestoreGioco g, JPanel[] panels) {
        this.container = container;
        this.g = g;
        this.panels = panels;
    }
    
    public void talpaUscita(){
        int buco = g.scegliBuco();
        g.assegnaTalpa(panels[buco]);
        
        panels[buco].revalidate();
        panels[buco].repaint();
        
        g.startThread();
    }
    
}
