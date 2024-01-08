package raf.dsw.components;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.classyrepository.composite.ClassyNode;
import raf.dsw.state.Tacka;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties({"rectangleCoordinates"})
public abstract class InterClass extends DiagramElement{

    private String vidljivost;

    private double width, height;
    private boolean selected = false;

    private List<ClassContent> cl = new ArrayList<>();
    private List<ClanEnuma> nEnum = new ArrayList<>();
    @JsonIgnore
    private transient List<Connection> listaVeza = new ArrayList<>(); // mora transient

    private transient double  x, y;

    public InterClass() {
    }
    public InterClass(String name, ClassyNode parent, double x, double y) {
        super(name, parent);
        this.x = x;
        this.y = y;
    }

    public void setWidth(double width) {
        if (this.width == width)
            return;
        this.width = width;
        updateChanged();
    }

    public void setHeight(double height) {
        if(this.height == height)
            return;
        this.height = height;
        updateChanged();
    }

    public void setWidthAndHeight(double width, double height){
        setHeight(height);
        setWidth(width);
    }

    public void setX(double x){
        if (this.x == x)
            return;
        this.x = x;
        updateChanged();
        if (getParent() != null)
        {

            getParent().notifySubscriber("REPAINT");
        }
    }

    public void setY(double y){
        if(this.y ==y)
            return;
        this.y = y;
        updateChanged();
        if (getParent() != null)
        {

            getParent().notifySubscriber("REPAINT");
        }
    }
    public void addToList(ClassContent cc){
        cl.add(cc);
        updateChanged();
    }

//    public void setCl(List<ClassContent> cl) {
//        this.cl = cl;
//        updateChanged();
//    }
//
//    public void setnEnum(List<ClanEnuma> nEnum) {
//        this.nEnum = nEnum;
//        updateChanged();
//    }

    public void setListaVeza(List<Connection> listaVeza) {
        if(this.listaVeza == listaVeza)
            return;
        this.listaVeza = listaVeza;
        updateChanged();
    }

    public void addToListE(ClanEnuma ce) {
        nEnum.add(ce);
        updateChanged();
    }

    public void setVidljivost(String vidljivost) {
        if(this.vidljivost == null && vidljivost == null)
            return;
        if(this.vidljivost != null && vidljivost.equals(vidljivost))
            return;
        this.vidljivost = vidljivost;
        updateChanged();
    }

    public ArrayList<Tacka> getRectangleCoordinates()
    {
        ArrayList<Tacka> tacke = new ArrayList<>();
        tacke.add(new Tacka((int)x,(int)y));
        tacke.add(new Tacka((int)x + (int)width,(int)y));
        tacke.add(new Tacka((int)x + (int)width,(int)y + (int)height));
        tacke.add(new Tacka((int)x,(int)y + (int)height));
        return tacke;
    }
    public void addToListVeza(Connection c){
        if(listaVeza == null)
        {
            listaVeza = new ArrayList<>();
        }
        listaVeza.add(c);
        updateChanged();
    }

    public abstract String tipKlase();

}