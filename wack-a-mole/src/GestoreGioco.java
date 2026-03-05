/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author cucchiarini.cesare
 */
public class GestoreGioco extends Thread{
    private Talpa talpa;
    private Giocatore g;
    private JPanel[] panels;
    private IntBox box;

    public GestoreGioco(Talpa talpa, Giocatore g, JPanel[] panels, IntBox box) {
        this.g = g;
        this.box = box;
        this.talpa = talpa;
        talpa.setBox(box);
        this.panels = panels;
        
        this.talpa.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                talpa.colpita();
            }
        });
    }
    
    public void scegliBuco(){
        int buco = new Random().nextInt(9);
        assegnaTalpa(panels[buco]);
        
        panels[buco].revalidate();
        panels[buco].repaint();
        talpa.startThread();
    }
    
    public void assegnaTalpa(JPanel buco){
        talpa.setBuco(buco);
        buco.add(talpa, BorderLayout.SOUTH);
    }
    
    public Talpa getTalpa(){
        return talpa;
    }
    
    @Override
    public void run(){
        boolean flag = true;
        while(flag){
            synchronized(box){
                scegliBuco();
                try {
                    box.wait();
                } catch (InterruptedException ex) {
                    flag = false;
                    
                }
                g.aggiungiPunti(box.getPunti());
            }
        }
    }
}
