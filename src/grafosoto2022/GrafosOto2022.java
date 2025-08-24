package grafosoto2022;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GrafosOto2022 extends JFrame{
    private PintaGrafo panel;
    private ArrayList<Nodo> nodos;
    private ArrayList<Aristas> aristas;
    private int C[][];
    private JTextField nodo1,nodo2,peso;
    private JButton  creaArista;
    private JButton  creaMatriz;
    private JButton BorrarNodo;
    private JButton BorraArista;
    private JLabel d,o;
    private JLabel etiPeso;
    private JMenuBar menus;
    private JMenu Mgrafos;
    private JMenu Algoritmos;
    private JMenu Archivo;
    private JMenu Recorridos;
    private JMenuItem Nuevo;
    private JMenuItem Guardar;
    private JMenuItem Abrir;
    private JMenuItem Salir;
    private JMenuItem Cerrar;
    private JMenuItem dirigidos;
    private JMenuItem noDirigidos;
    private JMenuItem AlgoDijskstra;
    private JMenuItem AlgoFloyd;
    private JMenuItem AlgoWarshall;
    private JMenuItem AlgoExcentricidad;
    private JMenuItem AlgoPrim;
    private JMenuItem AlgoKruskall;
    private JMenuItem AlgoNiveles;
    private JMenuItem AlgoProfund;
    private JInternalFrame resultado;
    private JTextArea area;
    private JScrollPane jsp;
    private boolean t;
    //--------------------------------------------------------------------------
    GrafosOto2022(){
    setSize(825,775);
    setTitle("Grafos Otoño 2022");
    initComponents();
    this.setLocationRelativeTo(null);//inicia programa en el centro de la pantalla
    }
    //--------------------------------------------------------------------------
    private void initComponents(){
        nodo1=new JTextField();
        nodo2=new JTextField();
        peso=new JTextField();
        area = new JTextArea();
        jsp =new JScrollPane(area);
        resultado =new JInternalFrame("Resultados");
        creaArista=new JButton("Crea Arista");
        creaMatriz=new JButton("Crea Matriz");
        BorrarNodo=new JButton("Borrar Ultimo Nodo");
        BorraArista=new JButton("Borrar Ultima Arista");
        nodos=new ArrayList<>();
        aristas=new ArrayList<>();
        panel=new PintaGrafo(nodos,aristas,t=true);
        d=new JLabel("Destino");
        o=new JLabel("Origen");
        etiPeso=new JLabel("Peso");
        setLayout(null);
        panel.setBounds(200, 25, 600, 470);
        add(panel);
        nodo1.setBounds(20, 50, 100, 25);
        add(nodo1);
        nodo2.setBounds(20, 120, 100, 25);
        add(nodo2);
        peso.setBounds(20, 180, 100, 25);
        add(peso);
        creaArista.setBounds(20, 250, 150, 25);
        add(creaArista);
        creaMatriz.setBounds(20, 300, 150, 25);
        add(creaMatriz);
        BorrarNodo.setBounds(20, 350, 150, 25);
        add(BorrarNodo);
        BorraArista.setBounds(20, 400, 150, 25);
        add(BorraArista);
        d.setBounds(20,90,100,25);
        add(d);
        o.setBounds(20,20,100,25);
        add(o);
        etiPeso.setBounds(20, 150, 100, 25);
        add(etiPeso);
        //MENUS
        dirigidos=new JMenuItem("Dirigidos");
        noDirigidos=new JMenuItem("NoDirigidos");
        Nuevo=new JMenuItem("Nuevo");
        Guardar=new JMenuItem("Guardar");
        Abrir=new JMenuItem("Abrir");
        Salir=new JMenuItem("Salir del programa");
        Cerrar=new JMenuItem("Cerrar");
        AlgoDijskstra=new JMenuItem("Dijskstra");
        AlgoFloyd=new JMenuItem("Floyd");
        AlgoWarshall = new JMenuItem("Warshall");
        AlgoExcentricidad=new JMenuItem("Excentricidad");
        AlgoPrim=new JMenuItem("Prim");
        AlgoKruskall=new JMenuItem("Kruskall");
        AlgoNiveles=new JMenuItem("Niveles");
        AlgoProfund=new JMenuItem("Profundidad Grafo Dirigido");
        Mgrafos=new JMenu("Grafos");
        Algoritmos=new JMenu("Algoritmos");
        Archivo=new JMenu("Archivo");
        Archivo.add(Nuevo);
        Archivo.add(Guardar);
        Archivo.add(Abrir);
        Archivo.add(Cerrar);
        Archivo.add(Salir);
        Mgrafos.add(dirigidos);
        Mgrafos.add(noDirigidos);
        Algoritmos.add(AlgoDijskstra);
        Algoritmos.add(AlgoFloyd);
        Algoritmos.add(AlgoWarshall);
        Algoritmos.add(AlgoExcentricidad);
        Algoritmos.add(AlgoPrim);
        Algoritmos.add(AlgoKruskall);
        Recorridos=new JMenu("Recorridos");
        Recorridos.add(AlgoProfund);
        Recorridos.add(AlgoNiveles);
        menus=new JMenuBar();
        menus.add(Archivo);
        menus.add(Mgrafos);
        menus.add(Algoritmos);
        menus.add(Recorridos);
        menus.setBounds(0, 0, 800, 25);
        add(menus);
        jsp.setBounds(7, 500, 800, 220);
        add(jsp);
        //internalframe
        resultado.setVisible(false);
        resultado.setBounds(0, 100, 200, 200);
        resultado.setMaximizable(true);
        resultado.setClosable(true);
        resultado.setBackground(Color.RED);
        panel.add(resultado);
        //Algoritmos inicialidados en false
        AlgoDijskstra.setEnabled(false);
        AlgoFloyd.setEnabled(false);
        AlgoExcentricidad.setEnabled(false);
        AlgoWarshall.setEnabled(false);
        AlgoPrim.setEnabled(true);
        AlgoKruskall.setEnabled(true);
        AlgoNiveles.setEnabled(true);
        AlgoProfund.setEnabled(false);
        //
        Nuevo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if ((!nodos.isEmpty()&&!aristas.isEmpty())||!nodos.isEmpty()||!aristas.isEmpty()) {
                    int ventanaYesNot = JOptionPane.showConfirmDialog(null, "¿Desea guardar antes de abrir un nuevo archivo?", "Javadesde0.com", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    //0=yes, 1=no
                    if(ventanaYesNot == 0) {
                        panel.Guardar();
                        nodos.clear();
                        aristas.clear();
                        panel.setConta(1);
                        panel.repaint();
                        area.setText("");
                    }
                    else{  
                        if(ventanaYesNot == 1){
                            nodos.clear();
                            aristas.clear();
                            panel.setConta(1);
                            panel.repaint();
                            area.setText("");
                            JOptionPane.showMessageDialog(null, "Se ha cerrado sin guardar el grafo");
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Ya puedes empezar y despues guardar :3");
                } 
            }});
        //**********************************************************************
        BorrarNodo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (nodos.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay nodos que borrar");
                }
                else{
                    ArrayList<Aristas> aristemp=new ArrayList<>();
                    //borrar nodo y sus aristas
                    int i=nodos.size()-1;//indice de elemento a eliminar
                    aristemp.clear();
                    aristemp.addAll(aristas);
                    for (int j = 0; j < aristas.size(); j++) {
                        if (aristas.get(j).getOrigen()==nodos.get(i).getDato()
                                ||aristas.get(j).getDestino()==nodos.get(i).getDato()) {
                            aristemp.remove(aristas.get(j));
                        }
                    }
                    aristas.clear();
                    aristas.addAll(aristemp);
                    nodos.remove(i);
                    panel.setConta(panel.getConta()-1);
                    panel.repaint(); 
                }
            }
        });
        //**********************************************************************
        BorraArista.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (aristas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay aristas que borrar");
                }
                else{
                    //borrar arista
                    aristas.remove(aristas.size()-1);
                    panel.repaint();
                }
            }
        });
        //**********************************************************************
        Guardar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (nodos.isEmpty()&&aristas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay nada que guardar");
                }
                else
                panel.Guardar();
            }
        });
        //**********************************************************************
        Abrir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (nodos.isEmpty()&&aristas.isEmpty()) {
                    panel.Cargar();
                }
                else
                    JOptionPane.showMessageDialog(null, "Ya esta abierto un archivo");
            }
        });
        //**********************************************************************
        Cerrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if ((!nodos.isEmpty()&&!aristas.isEmpty())||!nodos.isEmpty()||!aristas.isEmpty()) {
                    int ventanaYesNot = JOptionPane.showConfirmDialog(null, "¿Desea cerrar y guardar?", "Javadesde0.com", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                    //0=yes, 1=no
                    if(ventanaYesNot == 0) {
                        panel.Guardar();
                        nodos.clear();
                        aristas.clear();
                        panel.setConta(1);
                        panel.repaint();
                        area.setText("");
                    }
                    else{  
                        if(ventanaYesNot == 1){
                            nodos.clear();
                            aristas.clear();
                            panel.setConta(1);
                            panel.repaint();
                            area.setText("");
                            JOptionPane.showMessageDialog(null, "Se ha cerrado sin guardar el grafo");
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "No hay grafos abiertos");
                }                
            }
        });
        //**********************************************************************
        Salir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int ventanaYesNot = JOptionPane.showConfirmDialog(null, "¿Desea salir del programa?", "Javadesde0.com", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		//0=yes, 1=no
		if(ventanaYesNot == 0) {
                    if (nodos.isEmpty()&&aristas.isEmpty()) {
                        System.exit(0);
                    }
                    else{
                        int ventanaYesNot2 = JOptionPane.showConfirmDialog(null, "¿Desea Guardar El Grafo En Pantalla?", "Javadesde0.com", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        if (ventanaYesNot2==0) {
                            panel.Guardar();
                            System.exit(0);
                        }
                        else{
                            if (ventanaYesNot2==1) {
                                System.exit(0);
                            }
                        }
                    }
		}
                else{  
                    if(ventanaYesNot == 1){
                    }
                }                
            }
        });
        //**********************************************************************
        dirigidos.addActionListener(new ActionListener(){//con flecha
            @Override
            public void actionPerformed(ActionEvent e){
                AlgoDijskstra.setEnabled(true);
                AlgoFloyd.setEnabled(true);
                AlgoWarshall.setEnabled(true);
                AlgoExcentricidad.setEnabled(true);
                AlgoPrim.setEnabled(false);
                AlgoKruskall.setEnabled(false);
                AlgoNiveles.setEnabled(true);
                AlgoProfund.setEnabled(true);
                panel.SetDirigido(true);
                panel.repaint();
            }
        });
        //**********************************************************************
        noDirigidos.addActionListener(new ActionListener(){//sin flecha
            @Override
            public void actionPerformed(ActionEvent e){
                AlgoDijskstra.setEnabled(false);
                AlgoFloyd.setEnabled(false);
                AlgoWarshall.setEnabled(false);
                AlgoExcentricidad.setEnabled(false);
                AlgoPrim.setEnabled(true);
                AlgoKruskall.setEnabled(true);
                AlgoNiveles.setEnabled(false);
                AlgoProfund.setEnabled(false);
                panel.SetDirigido(false);
                panel.repaint();
            }
        });  
        //**********************************************************************
        creaArista.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    if (nodos.isEmpty()&&aristas.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay un grafo para crear una Arista");
                    }
                    else {
                        if ((panel.ContainsObjectNodoDato (Integer.parseInt(nodo1.getText())))&&(panel.ContainsObjectNodoDato (Integer.parseInt(nodo2.getText())))){ 
                            Aristas a= new Aristas();
                            a.setPeso(Integer.parseInt(peso.getText()));
                            a.setOrigen(Integer.parseInt(nodo1.getText()));
                            a.setDestino(Integer.parseInt(nodo2.getText()));
                            aristas.add(a);
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "No existe ese Origen o Destino");
                        }
                    }
                }
                catch (HeadlessException | NumberFormatException E){
                    JOptionPane.showMessageDialog(null, "Ingrese Datos Numericos o No Deje Espacios Vacios");
                }
                panel.repaint();
                nodo1.setText("");
                nodo2.setText("");
                peso.setText("");
            }
        });
        //**********************************************************************
        creaMatriz.addActionListener(new ActionListener(){//MATRIZ
            @Override
            public void actionPerformed(ActionEvent e){
                if (nodos.isEmpty()&&aristas.isEmpty()) {
                    area.setText("");
                    JOptionPane.showMessageDialog(null, "No hay un grafo para crear una Matriz de Adyacencia");
                }
                if (!nodos.isEmpty()&&aristas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Primero cree aristas");
                }
                if (!nodos.isEmpty()&&!aristas.isEmpty()) {
                    if (panel.isDirigido()) {
                        int[][] A=panel.CreaMatriz();
                        area.setText(panel.EscribeMatriz(A));
                    }
                    else {
                        panel.CreaMD();
                        //imprime matriz disperza
                        if (!panel.CreaMD().isEmpty()) {
                            String cad="    Matriz Disperza \n";
                            for (int i = 0; i < panel.CreaMD().size(); i++) {
                                cad+=(i+1)+" ";
                                for (int j = 0; j< panel.CreaMD().size();j++) {
                                    if (panel.CreaMD().get(i).get(j).getPeso()>8000) {
                                        //cad+=(j+1)+"->[  ∞  ] ";
                                        cad+="";
                                    }
                                    else
                                        cad+=(j+1)+" ->[  "+panel.CreaMD().get(i).get(j).getPeso()+"  ] ";
                                }
                                cad+="->[null]\n";
                            }
                            area.setText(cad);
                        }
                    }
                }
            }
        });
        //**********************************************************************
        AlgoDijskstra.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //resultado.setVisible(true);
                panel.setCadena("");
                if (nodos.isEmpty()&&aristas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Primero cree un grafo");
                }
                if (!nodos.isEmpty()&&aristas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Primero cree aristas");
                }
                if (!nodos.isEmpty()&&!aristas.isEmpty()) {
                    //Crea matriz
                    int[][] A=panel.CreaMatriz();
                    //Fin Crea matriz
                    //Inicio AlgoDijskstra 
                    ArrayList<Integer> V = new ArrayList<>();
                    ArrayList<Integer> D = new ArrayList<>();
                    ArrayList<Integer> S = new ArrayList<>();
                    ArrayList<Integer> V_S= new ArrayList<>();
                    V.clear();D.clear();S.clear();V_S.clear();//Cada que se ejecute se borran los valores anteriores
                    S.add(0); //S={1}
                    for (int i = 0; i < nodos.size(); i++) {//Lista de vertices V 
                        V.add(i);
                    }
                    System.out.println((V)+" V");
                    V_S.addAll(V);//Lista V-S
                    V_S.removeAll(S);
                    for (int i = 0; i < nodos.size(); i++) {//Asigna el valor inicial a D 
                        D.add(A[0][i]);                      
                    }
                    System.out.println(D+" D Inicio");
                    for (int i = 0; i < nodos.size()-1; i++) {
                        System.out.println((i+1)+"__ITERACION__");
                        ArrayList<Integer> DTem= new ArrayList<>();
                        DTem.clear();//Vacia en cada iteracion
                        //Escoge un vertice w en V-S que haga que D[w] sea un minimo.
                        for (int w:V_S){ //crea un Vector D temporal que almacena valores de D en la posicion v de cada VmenosS
                            DTem.add(D.get(w));
                        }
                        
                        System.out.println(D+" D Interacion "+(i+1));
                        System.out.println(V_S+" V-S");
                        System.out.println(DTem+" DTEM");
                        int w=panel.Eligew(D,DTem,V_S);
                        System.out.println("D[w]= "+D.get(w));
                        System.out.println("agrea a s "+w);
                        S.add(w);//Agrega w a S
                        // Fin Escoge un vertice w en V-S que haga que D[w] sea un minimo.
                        System.out.println(S+" S");
                        V_S.removeAll(S);
                        System.out.println(V_S+" V-S");
                        for (int v:V_S){ //for cada vertice v en V-S do
                            D.set(v,Math.min(D.get(v),D.get(w)+A[w][v]));
                            System.out.println(D+" D");
                        }
                    }
                    area.setText(String.valueOf(panel.EscribeDijktra(D)));
                    //end AlgoDijskstra
                } 
            }
        });
       //**********************************************************************
        AlgoFloyd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                panel.setCadena("");
                if (nodos.isEmpty()&&aristas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Primero cree un grafo");
                }
                if (!nodos.isEmpty()&&aristas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Primero cree aristas");
                }
                if (!nodos.isEmpty()&&!aristas.isEmpty()) {
                    ArrayList<Matrices> matrices=new ArrayList<>();
                    //Crea matriz
                    int[][] a=panel.CreaMatriz();
                    //Fin Crea matriz
                    //Inicio Floyd 
                    for (int i = 0; i == nodos.size(); i++) {
                        for (int j = 0; j == nodos.size(); j++) {
                            C=new int[nodos.size()][nodos.size()];
                            a[i][j]=C[i][j];
                        }
                    }
                    for (int k = 0; k < nodos.size(); k++) {
                        //agrega la matriz al ArrayList
                        Matrices m=new Matrices();
                        m.setM(a);
                        matrices.add(m);//ERROR SOLO SE GUARDA EL ULTIMO DE LA ITERACION K
//                        area.setText(panel.EscribeMatrices(matrices.get(k).getM()));
                        //Fin agrega la matriz al ArrayList
                        for (int i = 0; i < nodos.size(); i++) {
                            for (int j = 0; j < nodos.size(); j++) {
                                if ((a[i][k]+a[k][j])<a[i][j]){
                                    a[i][j]=(a[i][k]+a[k][j]);
                                     
                                }
                            }
                        }
                    }
                    //
                        area.setText(panel.EscribeMatrices(matrices.get(matrices.size()-1).getM()));
                    matrices.clear();
                    //end Floyd
                }
            }
        });
        //**********************************************************************
        AlgoWarshall.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //Crea matriz
                if (nodos.isEmpty()&&aristas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Primero cree un grafo");
                }
                if (!nodos.isEmpty()&&aristas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Primero cree aristas");
                }
                if (!nodos.isEmpty()&&!aristas.isEmpty()) {
                    int A[][] = null,C[][];
                    panel.setCadena("");
                    ArrayList<Matrices> matrices=new ArrayList<>();
                    if (A!=null){
                        A=new int[nodos.size()][nodos.size()];
                        for (Aristas a:aristas){
                            A[a.getOrigen()-1][a.getDestino()-1]=1;                           
                        }
                    }
                    else{
                        //A=null;
                        A=new int[nodos.size()][nodos.size()];
                        for (Aristas a:aristas){
                            A[a.getOrigen()-1][a.getDestino()-1]=1;
                        } 
                    }
                    for (int i = 0; i < A.length; i++) {
                        for (int j = 0; j < A.length; j++) {
                            if(i==j){
                                A[i][j]=0;
                            }   
                        }
                    }
                    //Fin Crea matriz
                    //Inicio AlgoWarshall                
                    for (int i = 0; i == nodos.size(); i++) {
                        for (int j = 0; j == nodos.size(); j++) {
                            C=new int[nodos.size()][nodos.size()];
                            A[i][j]=C[i][j];
                        }
                    }
                    for (int k = 0; k < nodos.size(); k++) {
                        Matrices m=new Matrices();
                        m.setM(A);
                        matrices.add(m);
                        for (int i = 0; i < nodos.size(); i++) {
                            for (int j = 0; j < nodos.size(); j++) {
                                if (A[i][j]==0 ) {
                                    if (i==j) {
                                        A[i][j]=0;
                                    }
                                    else
                                    A[i][j]=A[i][k]&A[k][j];   
                                }
                            }
                        }
                    }
                    for (Matrices m:matrices) {
                        area.setText(panel.EscribeMatriz(m.getM()));
                    }
                    matrices.clear();
                    //end AlgoWarshall
                }
            }
        });
        //**********************************************************************
        AlgoExcentricidad.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (nodos.isEmpty()&&aristas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Primero cree un grafo");
                }
                if (!nodos.isEmpty()&&aristas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Primero cree aristas");
                }
                if (!nodos.isEmpty()&&!aristas.isEmpty()) {
                    //Inicio AlgoExcentricidad
                    //Crea matriz
                    int[][] a=panel.CreaMatriz();
                    //Fin Crea matriz
                    //Inicio AlgoExcentricidad
                        //Ejecuta Floyd
                    for (int i = 0; i == nodos.size(); i++) {
                        for (int j = 0; j == nodos.size(); j++) {
                            C=new int[nodos.size()][nodos.size()];
                            a[i][j]=C[i][j];
                        }
                    }
                    for (int k = 0; k < nodos.size(); k++) {
                        //Fin agrega la matriz al ArrayList
                        for (int i = 0; i < nodos.size(); i++) {
                            for (int j = 0; j < nodos.size(); j++) {
                                if (a[i][k]+a[k][j]<a[i][j]){
                                    a[i][j]=a[i][k]+a[k][j];   
                                }
                            }
                        }
                    }
                        //Fin ejecuta Floyd
                    //Guarda los elementos de la matriz A en un arraylist de arraylist
                    ArrayList<ArrayList<Integer>> MatrizCentricidad = new ArrayList<>();
                    ArrayList<Integer> Elementos = new ArrayList<>();
                    ArrayList<Integer> Maximos = new ArrayList<>();
                    ArrayList<Nodo> nodoC = new ArrayList<>();
                    ArrayList<Aristas> aristas = new ArrayList<>();
                    aristas.clear();//ingresa un aristas vacio para evitar errores
                    int c;//variable del nodo central
                    Elementos.clear();MatrizCentricidad.clear();
                    for (int i = 0; i < nodos.size(); i++) {
                        Elementos.clear();
                        for (int j = 0; j < nodos.size(); j++) {
                            Elementos.add(a[j][i]);
                        }
                        MatrizCentricidad.add(new ArrayList<>(Elementos));
                    }
                    //
                    for (int j = 0; j < nodos.size(); j++) {
                        Maximos.add(Collections.max(MatrizCentricidad.get(j)));
                    }
                    c=Maximos.indexOf(Collections.min(Maximos))+1;
                    for (int i = 0; i < nodos.size(); i++) {//GUARDA EL NODO CENTRAL PARA SER IMPRESO 
                        if (nodos.get(i).getDato()==c) {
                            nodoC.add(nodos.get(i));
                        }
                    }
                    //
                    ImprimeResultado IR = new ImprimeResultado(nodoC,aristas,"Excentricidad");//RECIBE EL NODO A IMPRIMIR Y ARISTAS VACIOS
                    IR.setVisible(true);
                    IR.setLocationRelativeTo(null);
                    IR.repaint();
                    //end AlgoExcentricidad
                }
            }
        }); 
        //**********************************************************************
        AlgoKruskall.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (nodos.isEmpty()&&aristas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Primero cree un grafo");
                }
                if (!nodos.isEmpty()&&aristas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Primero cree aristas");
                }
                if (!nodos.isEmpty()&&!aristas.isEmpty()) {
                    try{
                        ArrayList<ArrayList<Aristas>> MD;
                        MD = panel.CreaMD();//Crea matriz dispersa
                        //Fin Crea matriz
                        //Inicio AlgoKruskall
                        ArrayList<Aristas> K = new ArrayList<>();
                        ArrayList<Integer> EleVS = new ArrayList<>();
                        ArrayList<ArrayList<Integer>> VS = new ArrayList<>();
                        ArrayList<Aristas> aristasQ = new ArrayList<>();
                        K.clear();VS.clear();aristasQ.clear();
                        for (int i = 0; i < MD.size(); i++) {//Carga conjuntos K's
                            for (int j = 0; j < MD.size(); j++) {
                                if (MD.get(i).get(j).getPeso()!=9999) {
                                    Aristas k=new Aristas();
                                    k.setOrigen(MD.get(i).get(j).getOrigen());
                                    k.setDestino(MD.get(i).get(j).getDestino());
                                    k.setPeso(MD.get(i).get(j).getPeso());
                                    K.add(k);
                                }
                            }
                        }
                        for (int i = 0; i < nodos.size(); i++) {//Carga conjunto de conjuntos VS
                            EleVS.clear();
                            EleVS.add(nodos.get(i).getDato());
                            VS.add(new ArrayList<>(EleVS));
                        }
                        System.out.println(VS+"VS");
                        int o=1;
                        while(VS.size()>1){
                            System.out.println(o+"------------ITERACION-------------");
                            o++;
                            Aristas min = panel.minK(K);
                            int x = min.getOrigen();
                            int y = min.getDestino();
                            System.out.println("x"+x);
                            System.out.println("y"+y);
                            K.remove(min);
                            ArrayList<Integer> w1=new ArrayList<>();
                            ArrayList<Integer> w2=new ArrayList<>();
                            w1.clear();w2.clear();
                            for (int i = 0; i < VS.size(); i++) {
                                if (VS.get(i).contains(x)) {
                                    for (int k = 0; k < VS.get(i).size(); k++) {
                                        w1.add(VS.get(i).get(k));
                                    }
                                    System.out.println("w1"+w1);
                                    if (!(VS.get(i).contains(x)&&VS.get(i).contains(y))) {
                                        VS.remove(VS.get(i));
                                    }
                                }
                            }
                            for (int i = 0; i < VS.size(); i++) {
                                if (VS.get(i).contains(y)) {
                                    for (int k = 0; k < VS.get(i).size(); k++) {
                                        w2.add(VS.get(i).get(k));
                                    }
                                    System.out.println("w2"+w2);
                                    if (!(VS.get(i).contains(x)&&VS.get(i).contains(y))) {
                                        VS.remove(VS.get(i));
                                    }
                                }
                            }
                            if (!w1.equals(w2)){
                                for (Aristas a:aristas) {
                                    if (a.getOrigen()==x&&a.getDestino()==y||
                                            a.getDestino()==x&&a.getOrigen()==y) {
                                        aristasQ.add(a);
                                        System.out.println("QaO"+a.getOrigen());
                                        System.out.println("QaD"+a.getDestino());
                                        System.out.println("QaP"+a.getPeso());
                                    }
                                }
                                ArrayList<Integer> aux=new ArrayList<>();
                                aux.addAll(w1);
                                aux.addAll(w2);
                                VS.add(aux);
                            }
                            System.out.println(VS+" VS");
                        }
                        ImprimeResultado IR = new ImprimeResultado(nodos,aristasQ,"Kruskal");
                        IR.setVisible(true);
                        IR.setLocationRelativeTo(null);
                        IR.repaint();
                    
                        //end AlgoKruskall
                        }
                        catch(Exception ex){
                            JOptionPane.showMessageDialog(null, "No puede dejar nodos sin aristas");
                        }
                    }
            }
        }); 
        //**********************************************************************
        AlgoPrim.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (nodos.isEmpty()&&aristas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Primero cree un grafo");
                }
                if (!nodos.isEmpty()&&aristas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Primero cree aristas");
                }
                if (!nodos.isEmpty()&&!aristas.isEmpty()) {
                    ArrayList<ArrayList<Aristas>> MD = new ArrayList<>();
                    MD.addAll(panel.CreaMD());
                    //Fin Crea matriz
                    //Inicio AlgoPrim
                    ArrayList<Integer> V=new ArrayList<>();
                    ArrayList<Integer> U=new ArrayList<>();//Conjunto de vertices
                    ArrayList<Integer> V_U=new ArrayList<>();
                    ArrayList<Aristas> T=new ArrayList<>();
                    V.clear();U.clear();
                    int u,v;//Vetices
                    for (int i = 0; i < nodos.size(); i++) {//Lista de vertices V 
                        V.add(i+1);
                    }
                    V_U.addAll(V);
                    System.out.println(V+"V");
                    //begin
                    T.clear();//T=vacio
                    U.add(1);//S={1}
                    V_U.removeAll(U);//V-U 
                    System.out.println(U+"U");
                    System.out.println(V_U+"V-U-------");
                    int itera=1;
                    //-----------------------------------------------------------------------------------------------
                    while(!U.equals(V)){
                        System.out.println("Iteracion----------"+itera);
                        itera++;
                        System.out.println(U+"U");
                        System.out.println(V_U+"V-U-------");
                        //Sea (u,v), una arista de costo minimo tal que u esta en U y v en V-U ;
                        ArrayList<ArrayList<Aristas>> BusMin = new ArrayList<>();
                        ArrayList<ArrayList<Aristas>> BusMin2 = new ArrayList<>();
                        ArrayList<Aristas> EleBusMin = new ArrayList<>();
                        ArrayList<Aristas> EleBusMin2 = new ArrayList<>();
                        BusMin.clear();BusMin2.clear();EleBusMin.clear();EleBusMin2.clear();
                        for (Integer i:U) {
                            for (Integer j:V_U) {
                                EleBusMin.add(MD.get(i-1).get(j-1));
                            }
                            BusMin.add(new ArrayList<>(EleBusMin));
                            EleBusMin.clear();
                        }
                        for (Integer i:U) {
                            for (Integer j:V_U) {
                                EleBusMin2.add(MD.get(j-1).get(i-1));
                            }
                            BusMin2.add(new ArrayList<>(EleBusMin2));
                            EleBusMin2.clear();
                        }
//                        for (int i=0;i<BusMin.size();i++) {
//                            for (int j=0;j<BusMin.get(i).size();j++) {
//                                System.out.println(BusMin.get(i).size()+"BusMin.get(i).size()");
//                                System.out.println("---------------1"); 
//                                System.out.println(BusMin.get(i).get(j).getOrigen()+"mdO--");
//                                System.out.println(BusMin.get(i).get(j).getDestino()+"mdD--");
//                                System.out.println(BusMin.get(i).get(j).getPeso()+"mdP--");
//                            }
//                        }
//                        for (int i=0;i<BusMin2.size();i++) {
//                            for (int j=0;j<BusMin2.get(i).size();j++) {
//                                System.out.println(BusMin2.get(i).size()+"BusMin2.get(i).size()");
//                                System.out.println("---------------2");
//                                System.out.println(BusMin2.get(i).get(j).getOrigen()+"mdO--");
//                                System.out.println(BusMin2.get(i).get(j).getDestino()+"mdD--");
//                                System.out.println(BusMin2.get(i).get(j).getPeso()+"mdP--");
//                            }
//                        }
                        //Busca los minimos
                        Aristas a;
                        Aristas b;
                        Aristas EleT;
                        a=panel.minPrim(BusMin);
                        b=panel.minPrim(BusMin2);
                        EleT=panel.ArisMinPrim(a, b);
                        if (EleT==a){
                            u=EleT.getOrigen();
                            v=EleT.getDestino(); 
                        }
                        else{
                            v=EleT.getOrigen();
                            u=EleT.getDestino(); 
                        }
                        for (Aristas ar:aristas) {
                            if (ar.getOrigen()==u&&ar.getDestino()==v||ar.getOrigen()==v&&ar.getDestino()==u) {
                                T.add(ar);//T:=T union {(u,v)}
                            }
                        }
                        System.out.println("CONJUNTO T-------------------");
                        for (int i = 0; i < T.size(); i++) {
                            System.out.println(T.get(i).getOrigen()+"Torigen");
                            System.out.println(T.get(i).getDestino()+"Tdestino");
                            System.out.println(T.get(i).getPeso()+"Tpeso");
                        }
                        //Termina sea (u,v), una arista de costo minimo tal que u esta en U y v en V-U ;
                        
                        U.add(v);//U:=U union {v}
                        V_U.removeAll(U);//V-U 
                        Collections.sort(U);//Ordena para comparar
                        Collections.sort(V_U);//Ordena para comparar
                    }
                    //-----------------------------------------------------------------------------------------------
                    ImprimeResultado IR = new ImprimeResultado(nodos,T,"Prim");
                    IR.setVisible(true);
                    IR.setLocationRelativeTo(null);
                    IR.repaint();
                    //end AlgoPrim-----------------------------------------------
                }
            }
        }); 
        //********************************************************************** 
        AlgoNiveles.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (nodos.isEmpty()&&aristas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Primero cree un grafo");
                }
                if (!nodos.isEmpty()&&aristas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Primero cree aristas");
                }
                if (!nodos.isEmpty()&&!aristas.isEmpty()) {
                    //Inicio AlgoNiveles
                    System.out.println("Sin terminar");
                    
                    //end AlgoNiveles
                }
            }
        }); 
        //**********************************************************************
        AlgoProfund.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if (nodos.isEmpty()&&aristas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Primero cree un grafo");
                }
                if (!nodos.isEmpty()&&aristas.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Primero cree aristas");
                }
                if (!nodos.isEmpty()&&!aristas.isEmpty()) {
                    //Inicio AlgoProfund
                    ArrayList<ArrayList<Aristas>> L = new ArrayList<>();
                    ArrayList<Boolean> marca = new ArrayList<> ();
                    ArrayList<Integer> recorrido = new ArrayList<> ();
                    L.clear();
                    recorrido.add(1);
                    int i=1;
                    for (int idx = 0; idx < nodos.size(); idx++) {
                        ArrayList<Aristas> a = new ArrayList<>();
                        for (Aristas ar:aristas) {
                            if(i==ar.getOrigen()){
                                Aristas Elema = new Aristas();
                                Elema.setOrigen(ar.getOrigen());
                                Elema.setDestino(ar.getDestino());
                                a.add(Elema);
                            }
                        }
                        L.add(a);
                        i++;
                    }
//                    System.out.println("Lista de vertices L");
//                    for (int j = 0; j < L.size(); j++) {
//                        if (!L.get(j).isEmpty()) {
//                            System.out.println("L["+(j+1)+"]***************");
//                        }
//                        for (int k = 0; k < L.get(j).size(); k++) {
//                            System.out.println(L.get(j).get(k).getDestino()+"  Destino");
//                        }
//                    }
                    for (int j = 0; j < L.size(); j++) {
                        L.get(j).sort(Comparator.comparingInt(Aristas::getDestino));
                    }
                    for (int j = 0; j < L.size(); j++) {
                        marca.add(false);
                    }
                    recorrido=panel.Profundidad(0, L,marca,recorrido);
                    String cadena=" Recorrido Profundidad :\n";
                    for (Integer r : recorrido) {
                        cadena+=" "+r+" ";  
                    }
                    area.setText(cadena);
                    marca.clear();recorrido.clear();
                    //end AlgoProfund
                }
            }
        }); 
    }
    public static void main(String[] args) {  
        GrafosOto2022 fr = new GrafosOto2022();
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}