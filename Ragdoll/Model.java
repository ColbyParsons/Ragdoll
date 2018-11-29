
import java.util.*;
import java.lang.*;
import java.awt.geom.Point2D;

//This file was provided in the a2 starter
public class Model {
    /** The observers that are watching this model for changes. */
    private List<Observer> observers;

    private Point2D BodyLoc= new Point2D.Double(400,100);

    public Point2D getBodyLoc(){return BodyLoc;}

    public void setBodyLoc(Point2D d){BodyLoc = d;}

    private Point2D HeadVec = new Point2D.Double(0,1);
    private Point2D LeftArmVec = new Point2D.Double(-1,0);
    private Point2D RightArmVec = new Point2D.Double(1,0);
    private Point2D LegVec = new Point2D.Double(0,-1);

    private Point2D mousePos = new Point2D.Double(0,0);

    public Point2D getMousePos(){
        return mousePos;
    }

    public void setMousePos(double x, double y){
        mousePos = new Point2D.Double(x,y);
    }

    private int BodyW = 100;
    private int BodyH = 150;
    private int UppArmW = 90;
    private int UppArmH = 25;
    private int LowArmW = 90;
    private int LowArmH = 25;
    private int UppLegW = 30;
    private Double RUppLegH = 100.0;
    private Double LUppLegH = 100.0;
    private int LowLegW = 30;
    private Double RLowLegH = 90.0;
    private Double LLowLegH = 90.0;
    private int HeadH = 80;
    private int HeadW = 50;
    private int FootW = 60;
    private int FootH = 20;
    private int HandH = 25;
    private int HandW = 40;

    private Double HeadAng = 0.0;
    private Double LUppArmAng = 0.0;
    private Double RUppArmAng = 0.0;
    private Double LLowArmAng = 0.0;
    private Double RLowArmAng = 0.0;
    private Double RUppLegAng = 0.0;
    private Double LUppLegAng = 0.0;
    private Double RLowLegAng = 0.0;
    private Double LLowLegAng = 0.0;
    private Double LHandAng = 0.0;
    private Double RHandAng = 0.0;
    private Double LFootAng = 0.0;
    private Double RFootAng = 0.0;

    public Double getRLowArmAng(){
        return RLowArmAng;
    }

    public void setRLowArmAng(Double d){
        RLowArmAng = (d > Math.toRadians(135))?Math.toRadians(135):d;
        RLowArmAng = (RLowArmAng < Math.toRadians(-135))?Math.toRadians(-135):RLowArmAng;
    }

    public Double getLLowArmAng(){
        return LLowArmAng;
    }

    public void setLLowArmAng(Double d){
        LLowArmAng = (d > Math.toRadians(135))?Math.toRadians(135):d;
        LLowArmAng = (LLowArmAng < Math.toRadians(-135))?Math.toRadians(-135):LLowArmAng;
    }

    public Double getRLowLegAng(){
        return RLowLegAng;
    }

    public void setRLowLegAng(Double d){
        RLowLegAng = (d > Math.toRadians(90))?Math.toRadians(90):d;
        RLowLegAng = (RLowLegAng < Math.toRadians(-90))?Math.toRadians(-90):RLowLegAng;
    }

    public Double getLLowLegAng(){
        return LLowLegAng;
    }

    public void setLLowLegAng(Double d){
        LLowLegAng = (d > Math.toRadians(90))?Math.toRadians(90):d;
        LLowLegAng = (LLowLegAng < Math.toRadians(-90))?Math.toRadians(-90):LLowLegAng;
    }

    public Double getRHandAng(){
        return RHandAng;
    }

    public void setRHandAng(Double d){
        RHandAng = (d > Math.toRadians(35))?Math.toRadians(35):d;
        RHandAng = (RHandAng < Math.toRadians(-35))?Math.toRadians(-35):RHandAng;
    }

    public Double getLHandAng(){
        return LHandAng;
    }

    public void setLHandAng(Double d){
        LHandAng = (d > Math.toRadians(35))?Math.toRadians(35):d;
        LHandAng = (LHandAng < Math.toRadians(-35))?Math.toRadians(-35):LHandAng;
    }

    public Double getLFootAng(){
        return LFootAng;
    }

    public void setLFootAng(Double d){
        LFootAng = (d > Math.toRadians(35))?Math.toRadians(35):d;
        LFootAng = (LFootAng < Math.toRadians(-35))?Math.toRadians(-35):LFootAng;
    }

    public Double getRFootAng(){
        return RFootAng;
    }

    public void setRFootAng(Double d){
        RFootAng = (d > Math.toRadians(35))?Math.toRadians(35):d;
        RFootAng = (RFootAng < Math.toRadians(-35))?Math.toRadians(-35):RFootAng;
    }

    public Double getRUppLegAng(){
        return RUppLegAng;
    }

    public void setRUppLegAng(Double d){
        RUppLegAng = (d > Math.toRadians(90))?Math.toRadians(90):d;
        RUppLegAng = (RUppLegAng < Math.toRadians(-90))?Math.toRadians(-90):RUppLegAng;
    }

    public Double getLUppLegAng(){
        return LUppLegAng;
    }

    public void setLUppLegAng(Double d){
        LUppLegAng = (d > Math.toRadians(90))?Math.toRadians(90):d;
        LUppLegAng = (LUppLegAng < Math.toRadians(-90))?Math.toRadians(-90):LUppLegAng;
    }

    public Double getRUppArmAng(){
        return RUppArmAng;
    }

    public void setRUppArmAng(Double d){
        RUppArmAng = d;
    }

    public Double getLUppArmAng(){
        return LUppArmAng;
    }

    public void setLUppArmAng(Double d){
        LUppArmAng = d;
    }

    public Double getHeadAng(){
        return HeadAng;
    }

    public void setHeadAng(Double d){
        HeadAng = (d > Math.toRadians(50))?Math.toRadians(50):d;
        HeadAng = (HeadAng < Math.toRadians(-50))?Math.toRadians(-50):HeadAng;
    }

    public Point2D getHeadOrigin(){
        return new Point2D.Double(BodyLoc.getX() + BodyW/2,BodyLoc.getY());
    }

    public Point2D getRArmOrigin(){
        return new Point2D.Double(BodyLoc.getX() + BodyW,BodyLoc.getY()+UppArmH/2);
    }

    public Point2D getLArmOrigin(){
        return new Point2D.Double(BodyLoc.getX(),BodyLoc.getY()+UppArmH/2);
    }

    public Point2D getLLegOrigin(){
        return new Point2D.Double(BodyLoc.getX()+UppLegW/2,BodyLoc.getY()+BodyH);
    }

    public Point2D getRLegOrigin(){
        return new Point2D.Double(BodyLoc.getX()+BodyW-UppLegW/2,BodyLoc.getY()+BodyH);
    }

    public Point2D getRLowLegOrigin(){
        return new Point2D.Double(BodyLoc.getX()-UppLegW/2+BodyW,BodyLoc.getY()+BodyH+RUppLegH);
    }

    public Point2D getLLowLegOrigin(){
        return new Point2D.Double(BodyLoc.getX()+UppLegW/2,BodyLoc.getY()+BodyH+LUppLegH);
    }

    public Point2D getLLowArmOrigin(){
        return new Point2D.Double(BodyLoc.getX()-UppArmW,BodyLoc.getY()+UppArmH/2);
    }

    public Point2D getRLowArmOrigin(){
        return new Point2D.Double(BodyLoc.getX()+BodyW+UppArmW,BodyLoc.getY()+UppArmH/2);
    }

    public Point2D getLHandOrigin(){
        Point2D p = getLLowArmOrigin();
        return new Point2D.Double(p.getX()-LowArmW,p.getY());
    }

    public Point2D getRHandOrigin(){
        Point2D p = getRLowArmOrigin();
        return new Point2D.Double(p.getX()+LowArmW,p.getY());
    }

    public Point2D getLFootOrigin(){
        Point2D p = getLLowLegOrigin();
        return new Point2D.Double(p.getX(),LLowLegH+FootH/2+p.getY());
    }

    public Point2D getRFootOrigin(){
        Point2D p = getRLowLegOrigin();
        return new Point2D.Double(p.getX(),RLowLegH+FootH/2+p.getY());
    }

    public int getBodyW(){
        return BodyW;
    }
    public int getBodyH(){
        return BodyH;
    }
    public int getUppArmW(){
        return UppArmW;
    }
    public int getUppArmH(){
        return UppArmH;
    }
    public int getLowArmW(){
        return LowArmW;
    }
    public int getLowArmH(){
        return LowArmH;
    }
    public int getUppLegW(){
        return UppLegW;
    }
    public Double getRUppLegH(){
        return RUppLegH;
    }
    public void setRUppLegH(Double d){
        RUppLegH = (d>=1)?d:1;
    }
    public Double getLUppLegH(){
        return LUppLegH;
    }
    public void setLUppLegH(Double d){
        LUppLegH = (d>=1)?d:1;
    }
    public Double getRLowLegH(){
        return RLowLegH;
    }
    public void setRLowLegH(Double d){
        RLowLegH = (d>=1)?d:1;
    }
    public Double getLLowLegH(){
        return LLowLegH;
    }
    public void setLLowLegH(Double d){
        LLowLegH = (d>=1)?d:1;
    }
    public int getLowLegW(){
        return LowLegW;
    }
    public int getHeadH(){
        return HeadH;
    }
    public int getHeadW(){
        return HeadW;
    }
    public int getHandH(){
        return HandH;
    }
    public int getHandW(){
        return HandW;
    }
    public int getFootH(){
        return FootH;
    }
    public int getFootW(){
        return FootW;
    }


    public Point2D getHeadVec(){
        return HeadVec;
    }

    public Point2D getLeftArmVec(){
        return LeftArmVec;
    }

    public Point2D getRightArmVec(){
        return RightArmVec;
    }

    public Point2D getLegVec(){
        return LegVec;
    }

    /**
     * Create a new model.
     */
    public Model() {
        this.observers = new ArrayList<Observer>();
    }

    /**
     * Add an observer to be notified when this model changes.
     */
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    /**
     * Remove an observer from this model.
     */
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    /**
     * Notify all observers that the model has changed.
     */
    public void notifyObservers() {
        for (Observer observer: this.observers) {
            observer.update(this);
        }
    }
}
