/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author cucchiarini.cesare
 */
public class Talpa extends JButton implements Runnable{
    private int punti;
    private boolean cliccabile;
    private JPanel buco;
    private Thread thread;
    private volatile int altezza = 10;
    private int crescita;
    private int larghezza;
    private int altezzaMax;
    private IntBox box;
    private boolean gioco = true;
    
    public Talpa(int punti, int larghezza, int altezza){
        this.punti=punti;
        crescita = (int) Math.ceil(altezza/10.0);
        this.altezzaMax = altezza;
        this.larghezza = larghezza;
        this.setPreferredSize(new Dimension(larghezza,crescita));
        
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setOpaque(false);
        this.setVerticalAlignment(TOP);
        
        this.setIcon(new ImageIcon("src/talpa.png"));
    }
    
    public void setBox(IntBox box){
        this.box = box;
    }
    public void setThread(Thread thread){
        this.thread = thread;
    }
    
    public void esci(){
        cliccabile = true;
        altezza = crescita;
        while(altezza < altezzaMax && cliccabile){
            try{
                Thread.sleep(50);
                SwingUtilities.invokeLater(() ->{
                    altezza+=crescita;
                    this.setPreferredSize(new Dimension(larghezza, altezza));
                    buco.revalidate();
                    buco.repaint();
                });
            }
            catch(InterruptedException e){}         
        }
    }
    
    public void entra(){
        synchronized(box){
            box.setPunti((!cliccabile && gioco) ? punti : -punti);
            box.notifyAll();
        }
        cliccabile = false;
        while(altezza > crescita){
            try{
                Thread.sleep(50);
            }
            catch(InterruptedException e){}
            SwingUtilities.invokeLater(()->{
                altezza-=crescita;
                this.setPreferredSize(new Dimension(larghezza, altezza));
                buco.revalidate();
                buco.repaint();
            });
        }
        buco.remove(this);
        buco.revalidate();
        buco.repaint();
    }
    
    public void colpita(){
        if(cliccabile){
            cliccabile = false;
            thread.interrupt();
        }
    }
    public void fermata(){
        gioco = false;
        thread.interrupt();
    }
    
    @Override
    public  void run(){
        synchronized(box){
            esci();
            try {
                if(cliccabile && gioco)
                    Thread.sleep(2000);
            }
            catch (InterruptedException ex){}
            entra();
        }
    }

    public void setBuco(JPanel buco) {
        this.buco = buco;
    }
    
    public void startThread(){
        thread = new Thread(this);
        thread.start();
    }
    
    public boolean getStato(){
        return cliccabile;
    }
    
    public void stopThread(){
        thread.interrupt();
    }
}
