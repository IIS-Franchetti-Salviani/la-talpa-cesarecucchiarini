/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author cucchiarini.cesare
 */
public class Giocatore {
    private int punti;
    private String nome;
    
    public void aggiungiPunti(int punti){
        this.punti+=punti;
    }
    
    public int getPunti(){
        return punti;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getNome(){
        return nome;
    }
}
