package grafosoto2022;

import java.awt.geom.*;
public class Aristas {
    private Line2D arista;
    private int peso;
    private int Origen;
    private int Destino;
    
    public int getOrigen() {
        return Origen;
    }

    public void setOrigen(int O) {
        Origen = O;
    }

    public int getDestino() {
        return Destino;
    }

    public void setDestino(int D) {
        Destino = D;
    }
    
    public Line2D getArista() { 
        return arista;
    }

    public void setArista(Line2D arista) {
        this.arista = arista;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }
}
