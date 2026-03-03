/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wack.a.mole;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;

/**
 *
 * @author cucchiarini.cesare
 */
public class GestoreGioco {
    Talpa talpa;
    Giocatore g;
    JButton[] bottoni = new JButton[9];
    private int bottoneAssociato = 0;

    public GestoreGioco(Talpa talpa, Giocatore g) {
        this.g = g;
        this.talpa = talpa;
        this.talpa.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                g.aggiungiPunti(talpa.colpita());
                bottoni[bottoneAssociato] = new JButton();
            }           
        });
        
        for(int i =0; i< 9; i++){
            bottoni[i] = new JButton("bottone "+i);
        }
    }
    
    public void assegnaTalpa(){
        bottoneAssociato = new Random().nextInt(9);
        bottoni[bottoneAssociato] = talpa;
        new Thread(talpa).start();
    }
    
    public JButton[] getBottoni(){
        return bottoni;
    }
    
}
