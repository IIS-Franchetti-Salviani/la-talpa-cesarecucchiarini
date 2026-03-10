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
    private ActionListener l;

    public GestoreGioco(Talpa talpa, Giocatore g, JPanel[] panels, IntBox box) {
        this.g = g;
        this.box = box;
        this.talpa = talpa;
        talpa.setBox(box);
        this.panels = panels;
        
        l = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                talpa.colpita();
            }
        };
        this.talpa.addActionListener(l);
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

    public Giocatore getGiocatore() {
        return g;
    }
    
    @Override
    public void run(){
        boolean flag = true;       
        synchronized(box){
            while(flag){
                scegliBuco();
                try {
                    box.wait();
                } 
                catch (InterruptedException ex) {
                    flag = false;
                    talpa.fermata();
                    fineGioco();
                }
                if(flag)
                    g.aggiungiPunti(box.getPunti());
            }
        }
    }
    
    public void fineGioco(){
        ManagerClassifica.creaClassifica();
        if(ManagerClassifica.controllaClassifica(g.getPunti())){
            ManagerClassifica.aggiungiGiocatore(g);
        }
    }
    
    public void impostaNome(String nome){
        g.setNome(nome);
    }
}
