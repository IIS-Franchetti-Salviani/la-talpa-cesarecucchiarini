/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author cucchiarini.cesare
 */
public class GestoreGioco {
    Talpa talpa;
    Giocatore g;
    GestorePanels gestore;

    public GestoreGioco(Talpa talpa, Giocatore g, GestorePanels gestore) {
        this.g = g;
        this.talpa = talpa;
        this.gestore = gestore;
        
        this.talpa.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                g.aggiungiPunti(talpa.colpita());
                
            }
        });
    }
    
    public void scegliBuco(){
        gestore.talpaUscita(talpa, new Random().nextInt(9));
        startThread();
    }
    public void assegnaTalpa(JPanel buco){
        talpa.setBuco(buco);
        buco.add(talpa, BorderLayout.SOUTH);
    }
    public Talpa getTalpa(){
        return talpa;
    }
    
    public void startThread(){
        talpa.startThread();
    }
    
}
