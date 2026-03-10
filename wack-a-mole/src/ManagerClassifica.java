
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
                punteggi.put( (Integer) Integer.parseInt(split[1]), split[0]);
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
        aggiornaClassifica();
    }
    
    public static void aggiornaClassifica(){
        try(BufferedWriter w = new BufferedWriter(new FileWriter("classifica.csv"))){
            int i = 0;
            for(Entry<Integer,String> entry : punteggi.reversed().entrySet()){
                w.write(entry.getValue()+","+entry.getKey()+"\n");
                if(++i > 5) break;
            }  
        }
        catch(IOException e){}
    }
    
    public static String[] getClassifica(){
        String[] strings = new String[punteggi.size()];
        int i = 0;
        for(Entry<Integer,String> entry : punteggi.reversed().entrySet()){
            strings[i++] = entry.getKey() + "," + entry.getValue();
        }
        return strings;
    }
}
