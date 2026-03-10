
import java.io.*;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.Set;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author cucchiarini.cesare
 */
public class ManagerClassifica {
    private static TreeMap<Integer, String> punteggi = new TreeMap<>();
    
    public static void creaClassifica(){
        try(BufferedReader r = new BufferedReader(new FileReader("classifica.csv"))){
            String l;
            String[] split;
            while((l=r.readLine()) != null){
                split = l.split(",");
                punteggi.put( (Integer) Integer.parseInt(split[0]), split[1]);
            }
        }
        catch(IOException e){}
    }
    
    public static boolean controllaClassifica(int punti){
        for(Integer p : punteggi.keySet()){
            if(punti > p) return true;
        }
        if(punteggi.size() < 5) return true;
        return false;
    }
    
    public static void aggiungiGiocatore(Giocatore giocatore){
        punteggi.put(giocatore.getPunti(), giocatore.getNome());
    }
    
    public static void aggiornaClassifica(){
        try(BufferedWriter w = new BufferedWriter(new FileWriter("classifica.csv"))){
            for(Set<Integer,String> es : punteggi.entrySet()){
                w.write(Entry.);
            }
            
        }
        catch(IOException e){}
    }
}
