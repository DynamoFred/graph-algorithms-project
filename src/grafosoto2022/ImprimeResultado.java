
package grafosoto2022;
import java.awt.Color;
import javax.swing.*;
import java.util.*;

public class ImprimeResultado extends JFrame{
    private PintaGrafo panel;
    private ArrayList<Nodo> nodos;
    private ArrayList<Aristas> aristas;
    private boolean t;
    
    ImprimeResultado(ArrayList<Nodo> nodos,ArrayList<Aristas> aristas, String S){
        this.nodos=nodos;
        this.aristas=aristas;
        setSize(600,600);
        setTitle(S);
        initComponents();
        this.setLocationRelativeTo(null);//inicia programa en el centro de la pantalla
    }
    private void initComponents(){
        panel=new PintaGrafo(nodos,aristas,t=false);
        panel.setBounds(10, 10, 400, 400);
        panel.setBackground(Color.BLACK);
        add(panel);
    }
    
    public static void main(String[] args) {
        GrafosOto2022 fr = new GrafosOto2022();
        fr.setVisible(false);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
