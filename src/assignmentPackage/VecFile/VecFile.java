package assignmentPackage.VecFile;

import assignmentPackage.VecCommand.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;

import static assignmentPackage.VecCommand.VecCommandType.*;

public class VecFile extends JPanel implements MouseListener {
    private Color color = Color.BLACK;
    private boolean fill = false;
    private File file;
    private Stack<VecCommand> VecCommandStack = new Stack<VecCommand>();
    private String name;
    private Point2D.Double startDrag;
    private Point2D.Double endDrag;
    private Point2D.Double startClick;
    private Point2D.Double endClick;
    private boolean clickStatus;
    private VecCommandType type = LINE;
    private int drawMode;
    private JButton button;
    private ArrayList<Point2D.Double> points;

    public VecFile(File f, JButton button){
        super();
        this.file = f;
        this.button = button;
        clickStatus = true;
        this.name = f.getName();
        drawMode = 1;
        clickStatus = true;
        points = new ArrayList<Point2D.Double>();
        try(BufferedReader br = new BufferedReader(new FileReader(f))) {
            for(String line; (line = br.readLine()) != null; ) {
                VecCommand command = VecCommandFactory.ParseCommandString(line);
                if(command != null){
                    VecCommandStack.push(command);
                }
            }
        }catch (Exception e){}
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                endDrag = new Point2D.Double(e.getX(), e.getY());
                repaint();
            }
        });
    }

    public String GetName(){
        return this.name;
    }

    public void Save(){

    }

    public void AddCommand(VecCommand c){
        VecCommandStack.push(c);
    }

    public void RemoveLastCommand(){
        VecCommandStack.pop();
    }

    public VecCommand GetLastCommand(){
        return VecCommandStack.peek();
    }

    public void SetColor(Color c){
        this.color = c;
    }

    public Color GetColor(){
        return this.color;
    }

    public boolean GetFill(){
        return this.fill;
    }

    public void SetFill(boolean t){
        this.fill = t;
    }

    public void SetType(VecCommandType t){
        this.type = t;
    }

    private void RenderFile(Graphics2D  g){
        for (VecCommand command : VecCommandStack) {
            ((IVecCommandExecutable)command).Execute(g, this);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {
        startDrag = new Point2D.Double(e.getX(), e.getY());
        points.add( new Point2D.Double(e.getX(), e.getY()));
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endDrag = new Point2D.Double(e.getX(), e.getY());
        points.add(new Point2D.Double(e.getX(), e.getY()));

        switch(this.type){
            case RECTANGLE:
                if(points.size()==2){
                    VecCommandStack.push(VecCommandFactory.GetShapeCommand(RECTANGLE, points, null));
                    points = new ArrayList<Point2D.Double>();;
                    break;
                }else{
                    break;
                }

            case LINE:
                if(points.size()==2){
                    VecCommandStack.push(VecCommandFactory.GetShapeCommand(LINE, points, null));
                    points = new ArrayList<Point2D.Double>();;
                    break;
                }else{
                    break;
                }

            default:
        }

        startDrag = null;
        endDrag = null;
        color = button.getBackground();

        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    public void changeMode(int num) {
        drawMode = num;
    }

    private void paintBackground(Graphics2D g2){
        g2.setPaint(Color.LIGHT_GRAY);
        for (int i = 0; i < getSize().width; i += 10) {
            Shape line = new Line2D.Float(i, 0, i, getSize().height);
            g2.draw(line);
        }

        for (int i = 0; i < getSize().height; i += 10) {
            Shape line = new Line2D.Float(0, i, getSize().width, i);
            g2.draw(line);
        }

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paintBackground(g2);

        g2.setStroke(new BasicStroke(2));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));

        RenderFile(g2);
//        for (int i=0;i<shapes.size();i++) {
//            g2.setPaint(colours.get(i));
//            g2.draw(shapes.get(i));
//        }

        if (startDrag != null && endDrag != null) {
            System.out.println(startDrag.toString()+" "+endDrag.toString());

            g2.setPaint(Color.LIGHT_GRAY);
            Shape r;
            if (type == RECTANGLE) {
                r = makeRectangle((int)startDrag.x, (int)startDrag.y, (int)endDrag.x, (int)endDrag.y);
            }
            else {
                r = makeLine((int)startDrag.x,(int)startDrag.y,(int)endDrag.x,(int)endDrag.y);
            }
            g2.draw(r);
            g2.setPaint(this.color);
        }
        this.color = button.getBackground();
    }

    private Line2D.Float makeLine(int x1, int y1, int x2, int y2) {
        return new Line2D.Float(x1, y1, x2, y2);
    }

    private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
        return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }
}
