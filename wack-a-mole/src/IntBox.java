/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sergi
 */
public class IntBox {
    int punti = 0;
    
    public int prendiPunti(){
        int t = punti;
        punti = 0;
        return t;
    }
    
    public void aggiungiPunti(int punti){
        this.punti += punti;
    }
}
