package grafosoto2022;
import java.awt.geom.*;

public class Nodo {
    private int Dato;
    private Ellipse2D node;
    private int x;
    private int y;
    private Point2D punto;
    
    public Point2D getPunto() {
        punto= new Point2D.Double(x,y);
        return punto;
    }

    public void setPunto(Point2D punto) {
        this.punto = punto;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public int getDato() {
        return Dato;
    }

    public void setDato(int Dato) {
        this.Dato = Dato;
    }

    public Ellipse2D getNode() {
        node = new Ellipse2D.Double(x-15,y-15,30,30);
        return node;
    }
    
    public void setNode(Ellipse2D node) {
        this.node = node;
    }    
}
