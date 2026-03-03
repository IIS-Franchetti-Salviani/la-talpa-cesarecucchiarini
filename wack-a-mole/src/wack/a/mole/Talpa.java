/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wack.a.mole;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author cucchiarini.cesare
 */
public class Talpa extends JButton implements Runnable{
    private int punti;
    private boolean cliccabile;
    
    public Talpa(int punti){
        this.punti=punti;
        this.setPreferredSize(new Dimension(100,0));
        this.setText("talpa");
    }
    
    public void esci(){
        cliccabile = true;
        while(this.getSize().height < 100 && cliccabile){
            try{
                Thread.sleep(30);
            }
            catch(InterruptedException e){}
            this.setPreferredSize(new Dimension(this.getSize().width, this.getSize().height+10));
        }
    }
    
    public void entra(){
        cliccabile = false;
        while(this.getSize().height > 0){
            try{
                Thread.sleep(30);
            }
            catch(InterruptedException e){}
            this.setPreferredSize(new Dimension(this.getSize().width, this.getSize().height-10));
        }
    }
    
    public int colpita(){
        if(cliccabile){
            Thread.currentThread().interrupt();
            entra();
            return punti;
        }       
        return 0;
    }
    
    @Override
    public void run(){
        esci();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {}
        entra();
    }   
}
