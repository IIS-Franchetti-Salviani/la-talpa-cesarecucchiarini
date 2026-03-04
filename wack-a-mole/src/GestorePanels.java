

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
    GestoreGioco g;
    JPanel[] panels;

    public GestorePanels(GestoreGioco g, JPanel[] panels) {
        this.g = g;
        this.panels = panels;
    }
    
    public void talpaUscita(Talpa talpa, int buco){
        g.assegnaTalpa(panels[buco]);
        
        panels[buco].revalidate();
        panels[buco].repaint();
    }
    
}
