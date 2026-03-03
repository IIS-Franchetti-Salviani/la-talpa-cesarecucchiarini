/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    
    public Talpa(int punti){
        this.punti=punti;
        this.setPreferredSize(new Dimension(100,10));
        this.setText("talpa");
    }
    
    public void esci(){
        cliccabile = true;
        while(this.getSize().height < 100 && cliccabile){
            try{
                Thread.sleep(100);
            }
            catch(InterruptedException e){}
            SwingUtilities.invokeLater(() ->{
                altezza+=10;
                this.setPreferredSize(new Dimension(this.getSize().width, altezza));
                buco.revalidate();
                buco.repaint();
            });
        }
    }
    
    public void entra(){
        cliccabile = false;
        while(altezza > 0){
            try{
                Thread.sleep(100);
            }
            catch(InterruptedException e){}
            altezza-=10;
            SwingUtilities.invokeLater(()->{
                altezza-=10;
                this.setPreferredSize(new Dimension(this.getSize().width, altezza));
                buco.revalidate();
                buco.repaint();
            });
        }
        buco.remove(this);
        buco.revalidate();
        buco.repaint();
    }
    
    public int colpita(){
        if(cliccabile){
            cliccabile = false;
            thread.interrupt();
            return punti;
        }       
        return 0;
    }
    
    @Override
    public void run(){
        esci();
        try {
            Thread.sleep(2000);
        } 
        catch (InterruptedException ex){System.out.println(ex.getMessage());}
        
        entra();
    }

    public void setBuco(JPanel buco) {
        this.buco = buco;
    }
    
    public void startThread(){
        thread = new Thread(this);
        thread.start();
    }
 
}
