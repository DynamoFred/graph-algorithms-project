package grafosoto2022;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Queue;
import javax.swing.filechooser.*;

public class PintaGrafo extends JPanel{
    private final ArrayList<Nodo> nodos;
    private final ArrayList<Aristas> aristas;
    private final Polygon arrow;
    private int auxNodo;
    private int conta=1;
    private double dx,dy;
    private boolean dirigido;
    private String cadena="";
    //--------------------------------------------------------------------------
    public PintaGrafo(ArrayList<Nodo> nodos,ArrayList<Aristas> aristas, boolean t){
        setBackground(Color.DARK_GRAY);
        this.nodos=nodos;
        this.aristas=aristas;
        arrow=new Polygon();
        arrow.addPoint(0,-15);
        arrow.addPoint(-5,-28);
        arrow.addPoint(0, -22);
        arrow.addPoint(5,-28);
        if (t) {//Si t=true puedes imprimir mas nodos y moverlos, si es false no puedes crear ni mover
        //************************************************************
        addMouseMotionListener(new MouseMotionAdapter(){
            @Override
            public void mouseMoved(MouseEvent e){//PONER MANO SOBRE LOS NODOS
                if(find(e.getPoint())==null)
                    setCursor(Cursor.getDefaultCursor());
                else setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
            }
            
        });
        //************************************************************
        MouseAdapter ma=new MouseAdapter(){
            boolean movimiento=false;
            @Override
            public void mouseClicked(MouseEvent e){//IMPRIME LOS NODOS EN PANEL CON CADA CLICK
                if(find(e.getPoint())==null){
                    Nodo n = new Nodo();
                    n.setDato(conta);
                    n.setX(e.getX());
                    n.setY(e.getY());
                    n.setNode(new Ellipse2D.Double(
                    n.getX()-15,n.getY()-15,30,30));
                    nodos.add(n);
                    conta++;
                    repaint();
                }
            }
            @Override
            public void mouseDragged(MouseEvent e1){//para mover grafo
                
                if(movimiento){
                    nodos.get(auxNodo).setX(e1.getX());
                    nodos.get(auxNodo).setY(e1.getY());
                }
                repaint();
                
            }
            @Override
            public void mousePressed(MouseEvent e2){
                if (find(e2.getPoint())!=null){//para mover grafo
                    movimiento=true;
                    auxNodo=find(e2.getPoint()).getDato()-1;
                }
            }
        };
        //************************************************************
        addMouseListener(ma);
        addMouseMotionListener(ma);   
        }
    }
    //--------------------------------------------------------------------------
    public void SetDirigido(boolean t){
        dirigido=t;
    }
    //--------------------------------------------------------------------------
    public boolean isDirigido() {
        return dirigido;
    }
    //--------------------------------------------------------------------------
    public Nodo find(Point2D p){//METODO PARA VER SI EXISTE UN NODO
        for (Nodo n: nodos){
            if (n.getNode().contains(p)) {
                return n;
            }
        }
        return null;
    }
    //--------------------------------------------------------------------------
    public boolean ContainsObjectNodoDato (int d){
        for (Nodo n: nodos) {
            if (n.getDato()==d) {
                return true;
            }
        }
        return false;
    }
    //--------------------------------------------------------------------------
    public void setCadena(String cadena) {
        this.cadena = cadena;
    }
    //--------------------------------------------------------------------------
    public String EscribeMatriz(int A[][]){//CREA UNA MATRIZ DE TEXTO   
        cadena="   ";
        for (int i = 0; i < A.length; i++) {
            cadena+="\n ["+(i+1)+"]";
            for (int j = 0; j < A.length; j++) {
                if (A[i][j]>8000) {
                    cadena+="  ∞  ";
                }
                else
                cadena+="  "+A[i][j]+"  ";
            }
        }
        cadena+="\n";
        return cadena;   
    }
    //--------------------------------------------------------------------------
    public String EscribeMatrices(int A[][]){ //cuando se ejecuta borra la cadena y concatena
        for (int[] A1 : A) {
            cadena=cadena+"\n";
            for (int j = 0; j < A.length; j++) {
                if (A1[j] > 8000) {
                    cadena+="  ∞  ";
                } else {
                    cadena += " " + A1[j] + "   ";
                }
            }
        }
        cadena=cadena+"\n";
        return cadena;
    }
    //--------------------------------------------------------------------------
    public String EscribeDijktra(ArrayList<Integer> D){
        cadena="  Dijktra\n [";
        for (int i = 0; i < D.size(); i++) {
            if (D.get(i)>8000) {
                cadena+=" ∞ ";
            }
            else
                cadena+=" "+D.get(i)+" ";
        }
        cadena+="]";
        return cadena;
    }
    //--------------------------------------------------------------------------
    public int[][] CreaMatriz(){
        int A[][] = new int[nodos.size()][nodos.size()];
        for (Aristas a : aristas) {
            A[a.getOrigen() - 1][a.getDestino() - 1] = a.getPeso();
        }
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                if (A[i][j] == 0) {
                    A[i][j] = 9999; // infinito
                }
                if (i == j) {
                    A[i][j] = 0;
                }
            }
        }
        return A;
    }
    //--------------------------------------------------------------------------
    public ArrayList<ArrayList<Aristas>> CreaMD(){
        ArrayList<ArrayList<Aristas>> MatrizDispersa = new ArrayList<>();
        ArrayList<Aristas> Elementos = new ArrayList<>();
        Elementos.clear();MatrizDispersa.clear();
        //crea la matriz dispersa
        for (int i = 0; i < nodos.size(); i++) {
            Elementos.clear();
            for (int j = 0; j < nodos.size(); j++) {
                Aristas a=new Aristas();
                a.setOrigen(i+1);
                a.setDestino(j+1);
                a.setPeso(9999);
                Elementos.add(a);
            }
            for (int j = 0; j < aristas.size(); j++) {
                int min=Math.min(aristas.get(j).getOrigen(), aristas.get(j).getDestino());
                int max=Math.max(aristas.get(j).getOrigen(), aristas.get(j).getDestino());
                if (i==min-1) {
                    Aristas a=new Aristas();
                    a.setOrigen(i+1);
                    a.setDestino(max);
                    a.setPeso(aristas.get(j).getPeso());
                    Elementos.set(max-1, a);
                }
            }
            MatrizDispersa.add(new ArrayList<>(Elementos));
        }
        return MatrizDispersa;
    }
    //--------------------------------------------------------------------------
    public void Cargar(){
        File archselect;
        JFileChooser selectarch = new JFileChooser("C:\\Users\\USER\\Desktop\\BUAP\\buapDISK\\4TO SEMESTRE\\Est. Datos\\Programs\\GrafosOto2022");
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.go2","go2");
        selectarch.setFileFilter(filtro);
        selectarch.showOpenDialog(null);
        archselect=selectarch.getSelectedFile();
        BufferedReader br = null;
        String Destino;
        String Dato;
        try {
            br=new BufferedReader(new FileReader(archselect));
            while (!"----------".equals(Dato = br.readLine())) {
                Nodo n=new Nodo();
                n.setDato(Integer.parseInt(Dato));
                setConta(Integer.parseInt(Dato)+1);
                n.setX(Integer.parseInt(br.readLine()));
                n.setY(Integer.parseInt(br.readLine()));
                n.setNode(new Ellipse2D.Double(
                n.getX()+15,n.getY()+15,30,30));
                nodos.add(n);
                repaint();   
            }
            while ((Destino = br.readLine())!=null) {
                Aristas a=new Aristas();
                a.setDestino(Integer.parseInt(Destino));
                a.setOrigen(Integer.parseInt(br.readLine()));
                a.setPeso(Integer.parseInt(br.readLine()));
                aristas.add(a);
                repaint();     
            }
        } 
        catch (IOException | NumberFormatException e) {
            System.out.println("Error "+e.getMessage());
        }
        finally{
            try{
                if(br !=null)
                    br.close();
            }
            catch(IOException e){
                //e.printStackTrace();
            }
        }
    }
    //--------------------------------------------------------------------------
    public void Guardar(){
        JFileChooser selectarch = new JFileChooser("C:\\Users\\USER\\Desktop\\BUAP\\buapDISK\\4TO SEMESTRE\\Est. Datos\\Programs\\GrafosOto2022");
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.go2","go2");
        selectarch.setFileFilter(filtro);
        PrintWriter pw;
        int select=selectarch.showSaveDialog(null);
        if (select==JFileChooser.APPROVE_OPTION) {
            File savearch = selectarch.getSelectedFile();
            try(FileWriter archivo = new FileWriter(savearch);){
                pw= new PrintWriter(archivo);
                for(Nodo n:nodos){
                    pw.println(n.getDato());
                    pw.println(n.getX());
                    pw.println(n.getY());
                }
                pw.println("----------");
                JOptionPane.showMessageDialog(null, "Nodos Guardados en el archivo");
                for(Aristas a: aristas){
                    pw.println(a.getDestino());
                    pw.println(a.getOrigen());
                    pw.println(a.getPeso());
                }
                JOptionPane.showMessageDialog(null, "Aristas Guardadas en el archivo");
            }
            catch(IOException e){
                //e.printStackTrace();
            }
        }
    }
    //--------------------------------------------------------------------------
    public int getConta() {
        return conta;
    }
    //--------------------------------------------------------------------------
    public void setConta(int conta) {
        this.conta = conta;
    }
    //--------------------------------------------------------------------------
    public Aristas minK(ArrayList<Aristas> k){
        Aristas Kmin=null;
        int pesoMin=Integer.MAX_VALUE;
        for (Aristas ks: k){
            if (ks.getPeso()<pesoMin) {
                pesoMin=ks.getPeso();
                Kmin=ks;
            }
        }
        return Kmin;
    }
    //--------------------------------------------------------------------------
    public Aristas minPrim(ArrayList<ArrayList<Aristas>> minPrim){
        Aristas Kmin=null;
        int pesoMin=Integer.MAX_VALUE;
        for (int i = 0; i < minPrim.size(); i++) {
            for (int j = 0; j < minPrim.get(i).size(); j++) {
                    if (minPrim.get(i).get(j).getPeso()<pesoMin) {
                    pesoMin=minPrim.get(i).get(j).getPeso();
                    Kmin=minPrim.get(i).get(j);
                }   
            }
        }
        return Kmin;
    }
    //--------------------------------------------------------------------------
    public Aristas ArisMinPrim(Aristas a,Aristas b){
        if (a.getPeso()==b.getPeso()) {
            if (a.getOrigen()<b.getOrigen()) {
                return a;
            }
            else{
                return b;  
            }
        }
        else{
            if (a.getPeso()<b.getPeso()) 
                return a;
            else 
                return b;
        }
    }
    //--------------------------------------------------------------------------
    public ArrayList<Integer> Profundidad(int v,ArrayList<ArrayList<Aristas>> L,ArrayList<Boolean> marca,ArrayList<Integer> recorrido){
        marca.set(v, true);
        //System.out.println(marca);
        for (int w=0;w<L.get(v).size();++w) {
            if (!marca.get(L.get(v).get(w).getDestino()-1)) {
                recorrido.add(L.get(v).get(w).getDestino());
                //System.out.println(L.get(v).get(w).getDestino()+" no visitado");
                Profundidad(L.get(v).get(w).getDestino()-1, L,marca,recorrido);
            }
        }
        return recorrido;
    }
    //--------------------------------------------------------------------------
//NO TERMINADO ALGORITMO DE NIVELES
    public ArrayList<Integer> Niveles(int v, ArrayList<Boolean> marca, Queue<Integer> C){
        ArrayList<Integer> recorrido= new ArrayList<>();
        //int x,y;
        marca.set(v, true);
        C.add(v);
        while(!C.isEmpty()){
            //x=C.element();
            C.remove();
            for (int i = 0; i < 10; i++) {
                
            }
        }
        return recorrido;
    }
    //--------------------------------------------------------------------------
    public int MinW(ArrayList<Aristas> a){
        int w=0;
        int pesoMin=Integer.MAX_VALUE;
        for (Aristas ks: a){
            if (ks.getDestino()<pesoMin) {
                pesoMin=ks.getDestino();
                w=ks.getDestino();
            }
        }
        return w;
    }
    //--------------------------------------------------------------------------
    public int Eligew(ArrayList<Integer> D,ArrayList<Integer> DTem,ArrayList<Integer> V_S){
        int w = 0;
        for (Integer vs:V_S) {
            if (Objects.equals(D.get(vs), Collections.min(DTem))) {
                w=vs;
                System.out.println("");
            }
        }
        return w;
    }
    //--------------------------------------------------------------------------
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        AffineTransform at = new AffineTransform();
        at.setTransform(g2.getTransform());
        g2.setColor(Color.WHITE);//color de la linea      
        for (Aristas a: aristas) {//Pinta la arista respecto al origen y destino            
            a.setArista(new Line2D.Double(nodos.get(a.getOrigen()-1).getPunto(),nodos.get(a.getDestino()-1).getPunto()));
            g2.setColor(Color.WHITE);
            g2.draw((Line2D)a.getArista());
            dx=a.getArista().getX2()-a.getArista().getX1();
            dy=a.getArista().getY2()-a.getArista().getY1();
            double angle=Math.atan2(dy,dx);
            g2.setColor(Color.WHITE);//color del peso
            g2.setFont(new java.awt.Font("consolas",0,20));
            g2.drawString(""+a.getPeso(),//PINTA EL PESO EN LAS COORDENADAS
                    (nodos.get(a.getDestino()-1).getX()+nodos.get(a.getOrigen()-1).getX())/2,
                    (nodos.get(a.getDestino()-1).getY()+nodos.get(a.getOrigen()-1).getY())/2);
            if(dirigido){//Pinta la flecha
                g2.translate(a.getArista().getX2(),a.getArista().getY2());
                g2.rotate(angle-Math.PI/2d);//aliena las flechas
                g2.setColor(Color.RED);//color de la flecha
                g2.fill(arrow);
                g2.setTransform(at);
            }
        }
        for(Nodo n:nodos){//CREA NODO CON DATO EN EL INTERIOR
            g2.setColor(Color.WHITE);
            g2.fill((Ellipse2D)n.getNode());
            g2.setColor(Color.BLACK);
            g2.setFont(new java.awt.Font("consolas",0,18));
            g2.drawString(""+n.getDato(),(int)n.getX()-4,(int)n.getY()+6);          
        } 
    }
}