
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author sergi
 */
public class GeneratoreInterfaccia{
    
    public static JPanel preparaCampo(JPanel[] panels){
        JPanel campo = new JPanel();
        campo.setBackground(Color.GREEN);
        campo.setLayout(new GridLayout(3, 3, 30, 0));      
        
        for (int i = 0; i < 9; i++) {
            JPanel p = new JPanel(){
                @Override
                protected void paintComponent(Graphics g) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    g2d.setColor(Color.black);
                    g2d.fillOval(0, 0, getWidth() - 1, getHeight() - 1);

                    g2d.dispose();
                }
            };
            p.setLayout(new BorderLayout());
            p.setOpaque(false);
            panels[i] = p;
            campo.add(p);
        }
        
        return campo;
    }
    public static JPanel preparaPunteggio(JLabel tempo, JLabel punteggio){
        JPanel infos = new JPanel();
        infos.setLayout(new BoxLayout(infos, BoxLayout.Y_AXIS));
        
        tempo.setAlignmentX(CENTER_ALIGNMENT);
        punteggio.setAlignmentX(CENTER_ALIGNMENT);
        
        Font f = new Font(tempo.getFont().getFontName(), Font.PLAIN, 40);
        tempo.setFont(f);
        punteggio.setFont(f);
        
        Border b = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3), 
                BorderFactory.createLineBorder(Color.BLACK));
        b = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), b);
        tempo.setBorder(b);
        punteggio.setBorder(b);
        
        infos.add(Box.createVerticalGlue());     
        infos.add(tempo);
        infos.add(Box.createRigidArea(new Dimension(0, 30)));       
        infos.add(punteggio);
        infos.add(Box.createVerticalGlue());
        
        infos.setBackground(Color.WHITE);
        return infos;
    }
    
    public static JPanel preparaIntro(JButton bottoneAvvio){
        JPanel intro = new JPanel();
        intro.setLayout(new BoxLayout(intro, BoxLayout.Y_AXIS));
        
        return intro;
    }
}
