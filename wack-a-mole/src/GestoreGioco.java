/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author cucchiarini.cesare
 */
public class GestoreGioco {
    Talpa talpa;
    Giocatore g;

    public GestoreGioco(Talpa talpa, Giocatore g) {
        this.g = g;
        this.talpa = talpa;
        this.talpa.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                g.aggiungiPunti(talpa.colpita());
            }
        });
    }
    
    public int scegliBuco(){
        return new Random().nextInt(9);
    }
    public void assegnaTalpa(JPanel buco){
        talpa.setBuco(buco);
        buco.add(talpa);
    }
    public Talpa getTalpa(){
        return talpa;
    }
    
    public void startThread(){
        talpa.startThread(); //la talpa non si resiza bene
    }
    
}
