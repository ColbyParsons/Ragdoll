
import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.lang.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.text.DecimalFormat;


public class View extends JFrame implements Observer {

    private Model model;
    JFrame mainWindow;

    /**
     * Create a new View.
     */
    public View(Model model) {
        // Set up the window.
        this.setTitle("Ragdoll");
        this.setMinimumSize(new Dimension(1024, 768));
        this.setMaximumSize(new Dimension(1024, 768));
        this.setSize(1024, 768);
        this.setLocation(20, 50);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        // Hook up this observer so that it will be notified when the model
        // changes.
        this.model = model;
        model.addObserver(this);

        mainWindow = this;

        this.setLayout(new BorderLayout());
        JPanel inner = new JPanel();
        inner.setPreferredSize(new Dimension(1024,700));
        inner.setBackground(Color.white);
        inner.setLayout(new BorderLayout());
        inner.setLayout(new BorderLayout());
        DrawArea drawspace = new DrawArea(model);
        inner.add(drawspace, BorderLayout.CENTER);
        inner.add(new TopBar(model,this,drawspace), BorderLayout.NORTH);
        this.setContentPane(inner);
        setVisible(true);
    }

    //This code is taken from 2nd assignment and modifyed
    public class TopBar extends JPanel implements Observer {

        private Model model;

        public TopBar(Model model,JFrame parent,DrawArea drawspace) {
            SpringLayout layout = new SpringLayout();
            this.setPreferredSize(new Dimension(1050, 35));
            this.setLayout(layout);

            JPopupMenu menu = new JPopupMenu();

            menu.add(new JMenuItem(new AbstractAction("Save") {
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Specify a file to save");

                    //FILE CHOOSER CODE TAKEN FROM MY A2 SUBMISSION
                    int userSelection = fileChooser.showSaveDialog(mainWindow);
                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        File fileToSave = fileChooser.getSelectedFile();
                        try {
                            DecimalFormat df = new DecimalFormat("#");
                            df.setMaximumFractionDigits(16);
                            PrintWriter writer = new PrintWriter(fileToSave.getAbsolutePath()+".txt", "UTF-8");
                            writer.println(model.getBodyLoc().getX()+"|"+
                                    df.format(model.getBodyLoc().getY())+"|"+
                                    df.format(model.getHeadAng())+"|"+
                                    df.format(model.getLUppArmAng())+"|"+
                                    df.format(model.getRUppArmAng())+"|"+
                                    df.format(model.getLLowArmAng())+"|"+
                                    df.format(model.getRLowArmAng())+"|"+
                                    df.format(model.getRUppLegAng())+"|"+
                                    df.format(model.getLUppLegAng())+"|"+
                                    df.format(model.getRLowLegAng())+"|"+
                                    df.format(model.getLLowLegAng())+"|"+
                                    df.format(model.getLHandAng())+"|"+
                                    df.format(model.getRHandAng())+"|"+
                                    df.format(model.getLFootAng())+"|"+
                                    df.format(model.getRFootAng())+"|"+
                                    df.format(model.getRUppLegH())+"|"+
                                    df.format(model.getLUppLegH())+"|"+
                                    df.format(model.getRLowLegH())+"|"+
                                    df.format(model.getLLowLegH()));
                            writer.close();
                        }catch (IOException err){
                            System.out.println(err);
                        }
                    }
                }
            }));

            menu.add(new JMenuItem(new AbstractAction("Load") {
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Specify a file to load");

                    int userSelection = fileChooser.showOpenDialog(mainWindow);
                    //CODE BASED ON WEBSITE EXAMPLE
                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        File fileToLoad = fileChooser.getSelectedFile();
                        try (BufferedReader br = new BufferedReader(new FileReader(fileToLoad))) {
                            String line = br.readLine();
                            String[] s = line.split("\\|");
                            model.setBodyLoc(new Point2D.Double(Double.parseDouble(s[0]),Double.parseDouble(s[1])));
                            model.setHeadAng(Double.parseDouble(s[2]));
                            model.setLUppArmAng(Double.parseDouble(s[3]));
                            model.setRUppArmAng(Double.parseDouble(s[4]));
                            model.setLLowArmAng(Double.parseDouble(s[5]));
                            model.setRLowArmAng(Double.parseDouble(s[6]));
                            model.setRUppLegAng(Double.parseDouble(s[7]));
                            model.setLUppLegAng(Double.parseDouble(s[8]));
                            model.setRLowLegAng(Double.parseDouble(s[9]));
                            model.setLLowLegAng(Double.parseDouble(s[10]));
                            model.setLHandAng(Double.parseDouble(s[11]));
                            model.setRHandAng(Double.parseDouble(s[12]));
                            model.setLFootAng(Double.parseDouble(s[13]));
                            model.setRFootAng(Double.parseDouble(s[14]));
                            model.setRUppLegH(Double.parseDouble(s[15]));
                            model.setLUppLegH(Double.parseDouble(s[16]));
                            model.setRLowLegH(Double.parseDouble(s[17]));
                            model.setLLowLegH(Double.parseDouble(s[18]));
                            br.close();
                        } catch (IOException err) {
                            System.out.println(err);
                        }
                    }
                    drawspace.repaint();
                }
            }));


            menu.add(new JMenuItem(new AbstractAction("Reset") {
                public void actionPerformed(ActionEvent e) {
                    model.setBodyLoc(new Point2D.Double(400,100));
                    model.setHeadAng(0.0);
                    model.setLUppArmAng(0.0);
                    model.setRUppArmAng(0.0);
                    model.setLLowArmAng(0.0);
                    model.setRLowArmAng(0.0);
                    model.setRUppLegAng(0.0);
                    model.setLUppLegAng(0.0);
                    model.setRLowLegAng(0.0);
                    model.setLLowLegAng(0.0);
                    model.setLHandAng(0.0);
                    model.setRHandAng(0.0);
                    model.setLFootAng(0.0);
                    model.setRFootAng(0.0);
                    model.setRUppLegH(100.0);
                    model.setLUppLegH(100.0);
                    model.setRLowLegH(90.0);
                    model.setLLowLegH(90.0);
                    drawspace.repaint();
                }
            }));

            menu.add(new JMenuItem(new AbstractAction("Quit") {
                public void actionPerformed(ActionEvent e) {
                    parent.dispose();
                }
            }));
/*
            model.setBodyLoc(new Point2D.Double(400,100));
            model.setHeadAng();
            model.setLUppArmAng();
            model.setRUppArmAng();
            model.setLLowArmAng();
            model.setRLowArmAng();
            model.setRUppLegAng();
            model.setLUppLegAng();
            model.setRLowLegAng();
            model.setLLowLegAng();
            model.setLHandAng();
            model.setRHandAng();
            model.setLFootAng();
            model.setRFootAng();
            model.setRUppLegH();
                 model.setLUppLegH();
                    model.setRLowLegH();
                    model.setLLowLegH();
                    drawspace.repaint();*/


            JButton file = new JButton("File");
            file.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    menu.show(e.getComponent(), e.getX(), e.getY());
                }
            });


            this.add(file);

            layout.putConstraint(SpringLayout.WEST, file, 5, SpringLayout.WEST, this);
            layout.putConstraint(SpringLayout.NORTH, file, 5, SpringLayout.NORTH, this);

            this.model = model;
            model.addObserver(this);

            setVisible(true);
        }
        public void update(Object observable) {
            // XXX Fill this in with the logic for updating the view when the model
            // changes.

        }

    }

    //This class is taken from my A2 and modified
    public class DrawArea extends JPanel implements Observer{
        private Model model;
        boolean dragged = false;
        int taken = 0;
        boolean started = false;
        public Double startDist;
        public Double startLenUp,startLenLow;
        public Point2D startPos, lastOrigin, startLoc;
        public Point2D mousePos = new Point2D.Double(0,0);
        public RoundRectangle2D bodyrect;
        public Ellipse2D head, rUpArm, lUpArm,rLowArm, lLowArm,lHand,rHand,lUpLeg,rUpLeg,lLowLeg,rLowLeg,lFoot,rFoot;
        public AffineTransform inverse;
        public Point2D inverseMouse = new Point2D.Double(0,0);


        public DrawArea(Model model) {
            this.setBackground(Color.white);

            this.addMouseMotionListener(new MouseAdapter(){
                public void mouseDragged(MouseEvent e){
                    dragged = true;
                    mousePos = new Point2D.Double(e.getX(),e.getY());
                    repaint();
                }
            });

            this.addMouseListener(new MouseAdapter() {


                public void mouseReleased(MouseEvent mouseEvent) {
                    dragged = false;
                    started = false;
                    taken = 0;
                    repaint();
                }

            });


            this.model = model;
            model.addObserver(this);
            setVisible(true);

        }



        public Double calcAngle(Point2D v1, Point2D P2, Point2D origin){
            //v1 is the direction vector indicating the default position of the appendage
            double v1x, v1y, v2x, v2y;
            v1x = v1.getX();
            v2x = P2.getX() - origin.getX();
            v1y = v1.getY();
            v2y = origin.getY() - P2.getY();
            return Math.atan2(v1x*v2y-v1y*v2x,v1x*v2x+v1y*v2y);
        }
        public Double calcAngleY(Point2D v1, Point2D P2, Point2D origin){
            //v1 is the direction vector indicating the default position of the appendage
            double v1x, v1y, v2x, v2y;
            v1x = v1.getX();
            v2x = origin.getX() - P2.getX();
            v1y = v1.getY();
            v2y = origin.getY() - P2.getY();
            return Math.atan2(v1x*v2y-v1y*v2x,v1x*v2x+v1y*v2y);
        }

        public void drawBody(Graphics2D g){
            bodyrect = new RoundRectangle2D.Double(model.getBodyLoc().getX(), model.getBodyLoc().getY(),model.getBodyW(),model.getBodyH(),model.getBodyW()/4,model.getBodyH()/4);
            g.draw(bodyrect);
        }

        public void drawHead(Graphics2D g){
            g.translate(model.getHeadOrigin().getX(),model.getHeadOrigin().getY());
            g.rotate(model.getHeadAng());
            g.translate(-model.getHeadOrigin().getX(),-model.getHeadOrigin().getY());
            AffineTransform s = g.getTransform();
            try {
                inverse = s.createInverse();
                inverse.transform(mousePos, inverseMouse);
                if(head.contains(inverseMouse.getX(), inverseMouse.getY())&&dragged&&(taken==0||taken==1)){
                    taken = 1;
                    model.setHeadAng(-calcAngle(model.getHeadVec(),mousePos,model.getHeadOrigin()));
                }
            }catch(Exception e){}
            head = new Ellipse2D.Double(model.getHeadOrigin().getX() - model.getHeadW()/2, model.getHeadOrigin().getY() - model.getHeadH(),model.getHeadW(),model.getHeadH());
            g.draw(head);
        }

        public void drawLeftUpperArm(Graphics2D g){
            g.translate(model.getLArmOrigin().getX(),model.getLArmOrigin().getY());
            g.rotate(model.getLUppArmAng());
            g.translate(-model.getLArmOrigin().getX(),-model.getLArmOrigin().getY());
            AffineTransform s = g.getTransform();
            try {
                inverse = s.createInverse();
                inverse.transform(mousePos, inverseMouse);
                if(lUpArm.contains(inverseMouse.getX(), inverseMouse.getY())&&dragged&&(taken==0||taken==2)){
                    taken = 2;
                    model.setLUppArmAng(-calcAngle(model.getLeftArmVec(),mousePos,model.getLArmOrigin()));
                }
            }catch(Exception e){}
            lUpArm = new Ellipse2D.Double(model.getLArmOrigin().getX() - model.getUppArmW(), model.getLArmOrigin().getY() - model.getUppArmH()/2,model.getUppArmW(),model.getUppArmH());
            g.draw(lUpArm);
        }

        public void drawRightUpperArm(Graphics2D g){
            g.translate(model.getRArmOrigin().getX(),model.getRArmOrigin().getY());
            g.rotate(model.getRUppArmAng());
            g.translate(-model.getRArmOrigin().getX(),-model.getRArmOrigin().getY());
            AffineTransform s = g.getTransform();
            try {
                inverse = s.createInverse();
                inverse.transform(mousePos, inverseMouse);
                if(rUpArm.contains(inverseMouse.getX(), inverseMouse.getY())&&dragged&&(taken==0||taken==3)){
                    taken = 3;
                    model.setRUppArmAng(-calcAngle(model.getRightArmVec(),mousePos,model.getRArmOrigin()));
                }
            }catch(Exception e){}
            rUpArm = new Ellipse2D.Double(model.getRArmOrigin().getX(), model.getRArmOrigin().getY() - model.getUppArmH()/2,model.getUppArmW(),model.getUppArmH());
            g.draw(rUpArm);
        }

        public void drawLeftLowerArm(Graphics2D g){
            g.translate(model.getLLowArmOrigin().getX(),model.getLLowArmOrigin().getY());
            g.rotate(model.getLLowArmAng());
            g.translate(-model.getLLowArmOrigin().getX(),-model.getLLowArmOrigin().getY());
            AffineTransform s = g.getTransform();
            try {
                inverse = s.createInverse();
                inverse.transform(mousePos, inverseMouse);
                Point2D newVec = new Point2D.Double(-Math.cos(model.getLUppArmAng()),Math.sin(model.getLUppArmAng()));
                Point2D newOrigin = new Point2D.Double();
                lastOrigin = newOrigin;
                s.transform(model.getLLowArmOrigin(), newOrigin);
                if(lLowArm.contains(inverseMouse.getX(), inverseMouse.getY())&&dragged&&(taken==0||taken==4)){
                    taken = 4;
                    model.setLLowArmAng(-calcAngle(newVec,mousePos,newOrigin));

                }
            }catch(Exception e){}
            lLowArm = new Ellipse2D.Double(model.getLLowArmOrigin().getX() - model.getLowArmW(), model.getLLowArmOrigin().getY() - model.getLowArmH()/2,model.getLowArmW(),model.getLowArmH());
            g.draw(lLowArm);
        }

        public void drawRightLowerArm(Graphics2D g){
            g.translate(model.getRLowArmOrigin().getX(),model.getRLowArmOrigin().getY());
            g.rotate(model.getRLowArmAng());
            g.translate(-model.getRLowArmOrigin().getX(),-model.getRLowArmOrigin().getY());
            AffineTransform s = g.getTransform();
            try {
                inverse = s.createInverse();
                inverse.transform(mousePos, inverseMouse);
                Point2D newVec = new Point2D.Double(Math.cos(model.getRUppArmAng()),-Math.sin(model.getRUppArmAng()));
                Point2D newOrigin = new Point2D.Double();
                s.transform(model.getRLowArmOrigin(), newOrigin);
                lastOrigin = newOrigin;
                if(rLowArm.contains(inverseMouse.getX(), inverseMouse.getY())&&dragged&&(taken==0||taken==5)){
                    taken = 5;
                   model.setRLowArmAng(-calcAngle(newVec,mousePos,newOrigin));
                }
            }catch(Exception e){}
            rLowArm = new Ellipse2D.Double(model.getRLowArmOrigin().getX(), model.getRLowArmOrigin().getY() - model.getLowArmH()/2,model.getLowArmW(),model.getLowArmH());
            g.draw(rLowArm);
        }

        public void drawRightUpperLeg(Graphics2D g){
            g.translate(model.getRLegOrigin().getX(),model.getRLegOrigin().getY());
            g.rotate(model.getRUppLegAng());
            g.translate(-model.getRLegOrigin().getX(),-model.getRLegOrigin().getY());
            AffineTransform s = g.getTransform();
            Double dist;
            try {
                inverse = s.createInverse();
                inverse.transform(mousePos, inverseMouse);
                if(rUpLeg.contains(inverseMouse.getX(), inverseMouse.getY())&&dragged&&(taken==0||taken==6)){
                    if(!started){
                        startDist = Math.sqrt(Math.pow(model.getRLegOrigin().getX()-mousePos.getX(),2)+Math.pow(model.getRLegOrigin().getY()-mousePos.getY(),2));
                        startLenUp = model.getRUppLegH();
                        startLenLow = model.getRLowLegH();
                        started = true;
                    }
                    dist = Math.sqrt(Math.pow(model.getRLegOrigin().getX()-mousePos.getX(),2)+Math.pow(model.getRLegOrigin().getY()-mousePos.getY(),2));
                    model.setRUppLegH(startLenUp + (dist - startDist));
                    model.setRLowLegH(startLenLow + (dist - startDist));
                    taken = 6;
                    model.setRUppLegAng(-calcAngle(model.getLegVec(),mousePos,model.getRLegOrigin()));
                }
            }catch(Exception e){}
            rUpLeg = new Ellipse2D.Double(model.getRLegOrigin().getX()-model.getUppLegW()/2, model.getRLegOrigin().getY(),model.getUppLegW(),model.getRUppLegH());
            g.draw(rUpLeg);
        }

        public void drawLeftUpperLeg(Graphics2D g){
            g.translate(model.getLLegOrigin().getX(),model.getLLegOrigin().getY());
            g.rotate(model.getLUppLegAng());
            g.translate(-model.getLLegOrigin().getX(),-model.getLLegOrigin().getY());
            AffineTransform s = g.getTransform();
            Double dist;
            try {
                inverse = s.createInverse();
                inverse.transform(mousePos, inverseMouse);
                if(lUpLeg.contains(inverseMouse.getX(), inverseMouse.getY())&&dragged&&(taken==0||taken==7)){
                    if(!started){
                        startDist = Math.sqrt(Math.pow(model.getLLegOrigin().getX()-mousePos.getX(),2)+Math.pow(model.getLLegOrigin().getY()-mousePos.getY(),2));
                        startLenUp = model.getLUppLegH();
                        startLenLow = model.getLLowLegH();
                        started = true;
                    }
                    dist = Math.sqrt(Math.pow(model.getLLegOrigin().getX()-mousePos.getX(),2)+Math.pow(model.getLLegOrigin().getY()-mousePos.getY(),2));
                    model.setLUppLegH(startLenUp + (dist - startDist));
                    model.setLLowLegH(startLenLow + (dist - startDist));
                    taken = 7;
                    model.setLUppLegAng(-calcAngle(model.getLegVec(),mousePos,model.getLLegOrigin()));
                }
            }catch(Exception e){}
            lUpLeg = new Ellipse2D.Double(model.getLLegOrigin().getX()-model.getUppLegW()/2, model.getLLegOrigin().getY(),model.getUppLegW(),model.getLUppLegH());
            g.draw(lUpLeg);
        }

        public void drawRightLowerLeg(Graphics2D g){
            g.translate(model.getRLowLegOrigin().getX(),model.getRLowLegOrigin().getY());
            g.rotate(model.getRLowLegAng());
            g.translate(-model.getRLowLegOrigin().getX(),-model.getRLowLegOrigin().getY());
            AffineTransform s = g.getTransform();
            Double dist;
            try {
                inverse = s.createInverse();
                inverse.transform(mousePos, inverseMouse);
                Point2D newVec = new Point2D.Double(Math.cos(3*Math.PI/2+model.getRUppLegAng()),Math.sin(3*Math.PI/2+model.getRUppLegAng()));
                Point2D newOrigin = new Point2D.Double();
                s.transform(model.getRLowLegOrigin(), newOrigin);
                lastOrigin = newOrigin;
                if(rLowLeg.contains(inverseMouse.getX(), inverseMouse.getY())&&dragged&&(taken==0||taken==8)){
                    if(!started){
                        startDist = Math.sqrt(Math.pow(newOrigin.getX()-mousePos.getX(),2)+Math.pow(newOrigin.getY()-mousePos.getY(),2));
                        startLenLow = model.getRLowLegH();
                        started = true;
                    }
                    dist = Math.sqrt(Math.pow(newOrigin.getX()-mousePos.getX(),2)+Math.pow(newOrigin.getY()-mousePos.getY(),2));
                    model.setRLowLegH(startLenLow + (dist - startDist));
                    taken = 8;
                    model.setRLowLegAng(calcAngleY(newVec,mousePos,newOrigin));
                }
            }catch(Exception e){}
            rLowLeg = new Ellipse2D.Double(model.getRLowLegOrigin().getX()-model.getLowLegW()/2, model.getRLowLegOrigin().getY(),model.getLowLegW(),model.getRLowLegH());
            g.draw(rLowLeg);
        }

        public void drawLeftLowerLeg(Graphics2D g){
            g.translate(model.getLLowLegOrigin().getX(),model.getLLowLegOrigin().getY());
            g.rotate(model.getLLowLegAng());
            g.translate(-model.getLLowLegOrigin().getX(),-model.getLLowLegOrigin().getY());
            AffineTransform s = g.getTransform();
            Double dist;
            try {
                inverse = s.createInverse();
                inverse.transform(mousePos, inverseMouse);
                Point2D newVec = new Point2D.Double(Math.cos(3*Math.PI/2+model.getLUppLegAng()),Math.sin(3*Math.PI/2+model.getLUppLegAng()));
                Point2D newOrigin = new Point2D.Double();
                s.transform(model.getLLowLegOrigin(), newOrigin);
                lastOrigin = newOrigin;
                if(lLowLeg.contains(inverseMouse.getX(), inverseMouse.getY())&&dragged&&(taken==0||taken==9)){
                    if(!started){
                        startDist = Math.sqrt(Math.pow(newOrigin.getX()-mousePos.getX(),2)+Math.pow(newOrigin.getY()-mousePos.getY(),2));
                        startLenLow = model.getLLowLegH();
                        started = true;
                    }
                    dist = Math.sqrt(Math.pow(newOrigin.getX()-mousePos.getX(),2)+Math.pow(newOrigin.getY()-mousePos.getY(),2));
                    model.setLLowLegH(startLenLow + (dist - startDist));
                    taken = 9;
                    model.setLLowLegAng(calcAngleY(newVec,mousePos,newOrigin));
                }
            }catch(Exception e){}
            lLowLeg = new Ellipse2D.Double(model.getLLowLegOrigin().getX()-model.getLowLegW()/2, model.getLLowLegOrigin().getY(),model.getLowLegW(),model.getLLowLegH());
            g.draw(lLowLeg);
        }

        public void drawLeftFoot(Graphics2D g){
            g.translate(model.getLFootOrigin().getX(),model.getLFootOrigin().getY());
            g.rotate(model.getLFootAng());
            g.translate(-model.getLFootOrigin().getX(),-model.getLFootOrigin().getY());
            AffineTransform s = g.getTransform();
            try {
                inverse = s.createInverse();
                inverse.transform(mousePos, inverseMouse);
                Point2D newOrigin = new Point2D.Double();
                s.transform(model.getLFootOrigin(), newOrigin);
                Point2D newVec = new Point2D.Double(lastOrigin.getY()-newOrigin.getY(),lastOrigin.getX()-newOrigin.getX());
                if(lFoot.contains(inverseMouse.getX(), inverseMouse.getY())&&dragged&&(taken==0||taken==10)){
                    taken = 10;
                    model.setLFootAng(-calcAngle(newVec,mousePos,newOrigin));
                }
            }catch(Exception e){}
            lFoot = new Ellipse2D.Double(model.getLFootOrigin().getX()-model.getFootW(), model.getLFootOrigin().getY()-model.getFootH()/2,model.getFootW(),model.getFootH());
            g.draw(lFoot);
        }

        public void drawRightFoot(Graphics2D g){
            g.translate(model.getRFootOrigin().getX(),model.getRFootOrigin().getY());
            g.rotate(model.getRFootAng());
            g.translate(-model.getRFootOrigin().getX(),-model.getRFootOrigin().getY());
            AffineTransform s = g.getTransform();
            try {
                inverse = s.createInverse();
                inverse.transform(mousePos, inverseMouse);
                Point2D newOrigin = new Point2D.Double();
                s.transform(model.getRFootOrigin(), newOrigin);
                Point2D newVec = new Point2D.Double(newOrigin.getY()-lastOrigin.getY(),newOrigin.getX()-lastOrigin.getX());
                if(rFoot.contains(inverseMouse.getX(), inverseMouse.getY())&&dragged&&(taken==0||taken==11)){
                    taken = 11;
                    model.setRFootAng(-calcAngle(newVec,mousePos,newOrigin));
                }
            }catch(Exception e){}
            rFoot = new Ellipse2D.Double(model.getRFootOrigin().getX(), model.getRFootOrigin().getY()-model.getFootH()/2,model.getFootW(),model.getFootH());
            g.draw(rFoot);
        }

        public void drawRightHand(Graphics2D g){
            g.translate(model.getRHandOrigin().getX(),model.getRHandOrigin().getY());
            g.rotate(model.getRHandAng());
            g.translate(-model.getRHandOrigin().getX(),-model.getRHandOrigin().getY());
            AffineTransform s = g.getTransform();
            try {
                inverse = s.createInverse();
                inverse.transform(mousePos, inverseMouse);
                Point2D newOrigin = new Point2D.Double();
                s.transform(model.getRHandOrigin(), newOrigin);
                Point2D newVec = new Point2D.Double(newOrigin.getX()-lastOrigin.getX(),lastOrigin.getY()-newOrigin.getY());
                if(rHand.contains(inverseMouse.getX(), inverseMouse.getY())&&dragged&&(taken==0||taken==12)){
                    taken = 12;
                    model.setRHandAng(-calcAngle(newVec,mousePos,newOrigin));
                }
            }catch(Exception e){}
            rHand = new Ellipse2D.Double(model.getRHandOrigin().getX(), model.getRHandOrigin().getY()-model.getHandH()/2,model.getHandW(),model.getHandH());
            g.draw(rHand);
        }

        public void drawLeftHand(Graphics2D g){
            g.translate(model.getLHandOrigin().getX(),model.getLHandOrigin().getY());
            g.rotate(model.getLHandAng());
            g.translate(-model.getLHandOrigin().getX(),-model.getLHandOrigin().getY());
            AffineTransform s = g.getTransform();
            try {
                inverse = s.createInverse();
                inverse.transform(mousePos, inverseMouse);
                Point2D newOrigin = new Point2D.Double();
                s.transform(model.getLHandOrigin(), newOrigin);
                Point2D newVec = new Point2D.Double(newOrigin.getX()-lastOrigin.getX(),lastOrigin.getY()-newOrigin.getY());
                if(lHand.contains(inverseMouse.getX(), inverseMouse.getY())&&dragged&&(taken==0||taken==13)){
                    taken = 13;
                    model.setLHandAng(-calcAngle(newVec,mousePos,newOrigin));
                }
            }catch(Exception e){}
            lHand = new Ellipse2D.Double(model.getLHandOrigin().getX()-model.getHandW(), model.getLHandOrigin().getY()-model.getHandH()/2,model.getHandW(),model.getHandH());
            g.draw(lHand);
        }

        public void paintComponent(Graphics g) {

            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            g2.setStroke(new BasicStroke(5));
            g2.setColor(Color.black);
            g2.scale(1,1);
            AffineTransform save = g2.getTransform();


            drawBody(g2);
            g2.setTransform(save);

            drawHead(g2);
            g2.setTransform(save);

            drawLeftUpperLeg(g2);
            drawLeftLowerLeg(g2);
            drawLeftFoot(g2);
            g2.setTransform(save);

            drawRightUpperLeg(g2);
            drawRightLowerLeg(g2);
            drawRightFoot(g2);
            g2.setTransform(save);

            drawRightUpperArm(g2);
            drawRightLowerArm(g2);
            drawRightHand(g2);
            g2.setTransform(save);

            drawLeftUpperArm(g2);
            drawLeftLowerArm(g2);
            drawLeftHand(g2);
            g2.setTransform(save);
            try{
                if(bodyrect.contains(mousePos.getX(),mousePos.getY())&&dragged&&(taken==0||taken==14)){
                    taken = 14;
                    if(!started){
                        started = true;
                        startPos = new Point2D.Double(mousePos.getX(),mousePos.getY());
                        startLoc = model.getBodyLoc();
                    }
                    model.setBodyLoc(new Point2D.Double(startLoc.getX()-startPos.getX()+mousePos.getX(),startLoc.getY()-startPos.getY()+mousePos.getY()));
                }
            }catch(Exception e){}
        }

        public void update(Object observable) {
            repaint();

        }
    }

    /**
     * Update with data from the model.
     */
    public void update(Object observable) {

    }
}
