/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.Semaphore;
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
    private Semaphore lock = new Semaphore(0);
    private JLabel labelPunti;
    private IntBox box = new IntBox();

    public GestoreGioco(Talpa talpa, Giocatore g, JPanel[] panels, JLabel labelPunti) {
        this.g = g;
        this.talpa = talpa;
        talpa.setSemaphore(lock);
        this.panels = panels;
        this.labelPunti = labelPunti;
        
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
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
        }
        while(true){
            scegliBuco();
            try{
                lock.acquire();
            }catch(Exception e){}
        }
    }
}
