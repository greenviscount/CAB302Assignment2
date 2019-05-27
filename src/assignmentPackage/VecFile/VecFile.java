package assignmentPackage.VecFile;

import assignmentPackage.VecCommand.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;

import static assignmentPackage.VecCommand.VecCommandType.*;

public class VecFile extends JPanel implements MouseListener {
    private Color penColor = Color.BLACK;
    private Color fillColor = Color.WHITE;
    private boolean fill = false;
    private boolean penChanged = false;
    private File file;
    private Stack<VecCommand> VecCommandStack = new Stack<VecCommand>();
    private String name;
    private Point2D.Double startDrag;
    private Point2D.Double endDrag;
    private Point2D.Double startClick;
    private Point2D.Double endClick;
    private boolean clickStatus;
    private boolean canSetFill = true;
    private boolean canSetPen = true;
    private boolean usedShapeCommand = false;
    private VecCommandType type = LINE;
    private VecCommandType colorType = PEN;
    private int drawMode;
    private JButton pen;
    private JButton filling;
    private ArrayList<Point2D.Double> points;

    public VecFile(File f, JButton pen, JButton fill){
        super();
        this.file = f;
        this.pen = pen;
        this.filling = fill;
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

    public void AddCommand(VecCommand c){
        VecCommandStack.push(c);
    }

    public void RemoveLastCommand(){
        VecCommandStack.pop();
    }

    public VecCommand GetLastCommand(){
        return VecCommandStack.peek();
    }

    public void SetPenColor(Color c){
        this.penColor = c;
    }

    public Color GetPenColor(){
        return this.penColor;
    }

    public void SetFillColor(Color c){ this.fillColor = c;}

    public Color GetFillColor(){return  this.fillColor;}

    public boolean GetFill(){
        return this.fill;
    }

    public void SetFill(boolean t){this.fill = t;}

    public void SetType(VecCommandType t){
        if (t==PEN || t==FILL) {
            this.colorType = t;
        }
        else {
            this.type = t;
        }
    }

    public void SetUseShapeCommand(boolean b){
        this.usedShapeCommand = b;
    }

    public boolean LastCommandSameType(VecCommandType t){
        return(t==VecCommandStack.peek().GetType());
    }

    public void RemoveLastColorCommand(VecCommandType t){
        Stack<VecCommand> tempStack = new Stack<VecCommand>();
        boolean removed = false;
        while(!removed && VecCommandStack.iterator().hasNext()){
            if (!(VecCommandStack.peek().GetType() == t)) {
                tempStack.push(VecCommandStack.pop());
            } else {
                VecCommandStack.pop();
                removed = true;
            }
        }
        while(tempStack.iterator().hasNext()){
            VecCommandStack.push(tempStack.pop());
        }
    }

    public void SetColourCommand(VecCommandType t) {
        switch (t) {
            case FILL:
                if(fill){
                    //if fill status is true and a VecShapeCommand has been used set a new command in the stack
                    if(canSetFill){
                        VecCommandStack.push(VecCommandFactory.GetShapeCommand(FILL, null, this.filling.getBackground()));
                        canSetFill = false;
                    }else{
                        ChangeLastColorCommandColor(this.penColor, FILL);
                    }
                }else{
                    if(usedShapeCommand){
                        VecCommandStack.push(VecCommandFactory.GetShapeCommand(FILL, null, null));
                    }else{
                        RemoveLastColorCommand(FILL);
                    }
                }
            case PEN:
                if(penChanged){
                    //if fill status is true and a VecShapeCommand has been used set a new command in the stack
                    if(canSetPen){
                        SetPenColor(this.pen.getBackground());
                        VecCommandStack.push(VecCommandFactory.GetShapeCommand(PEN, null, this.pen.getBackground()));
                        canSetPen = false;
                    }else{
                        ChangeLastColorCommandColor(this.pen.getBackground(), PEN);
                    }
                }else{
                    if(usedShapeCommand){
                        VecCommandStack.push(VecCommandFactory.GetShapeCommand(PEN, null, null));
                    }else{
                        RemoveLastColorCommand(PEN);
                    }
                }
            default:break;
        }
    }

    public void ChangeLastColorCommandColor(Color c , VecCommandType t){
        Stack<VecCommand> tempStack = new Stack<VecCommand>();
        boolean changed = false;
        while(!changed){
            if(!(VecCommandStack.peek().GetType() ==t)){
                tempStack.push(VecCommandStack.pop());
            }else{
                ((VecColorCommand)VecCommandStack.peek()).SetColor(c);
                changed = true;
            }
        }
        while(tempStack.iterator().hasNext()){
            VecCommandStack.push(tempStack.pop());
        }
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
        if(!SwingUtilities.isRightMouseButton(e)){
            if(type == PLOT){
                points = new ArrayList<Point2D.Double>();
                points.add(new Point2D.Double(e.getX(),e.getY()));
            }else{
                endDrag = new Point2D.Double(e.getX(), e.getY());
                points.add(new Point2D.Double(e.getX(), e.getY()));
            }
        }

        if (this.penColor != this.pen.getBackground()) {
            penChanged = true;
            SetColourCommand(PEN);
        }
        switch(this.type){
            case RECTANGLE:
                if(points.size()==2){
                    VecCommandStack.push(VecCommandFactory.GetShapeCommand(RECTANGLE, points, null));
                    points = new ArrayList<Point2D.Double>();
                    usedShapeCommand = true;
                    canSetFill = true;
                    canSetPen = true;
                    break;
                }else{
                    break;
                }

            case LINE:
                if(points.size()==2){
                    VecCommandStack.push(VecCommandFactory.GetShapeCommand(LINE, points, null));
                    points = new ArrayList<Point2D.Double>();
                    usedShapeCommand = true;
                    canSetFill = true;
                    canSetPen = true;
                    break;
                }else{
                    break;
                }
            case PLOT:
                if(points.size()==1){
                    VecCommandStack.push(VecCommandFactory.GetShapeCommand(PLOT, points, null));
                    points = new ArrayList<Point2D.Double>();
                    usedShapeCommand = true;
                    canSetFill = true;
                    canSetPen = true;
                    break;
                }else{
                    points = new ArrayList<Point2D.Double>();
                    break;
                }
            case POLYGON:
                if(SwingUtilities.isRightMouseButton(e)){
                    if(points.size()>2){
                        VecCommandStack.push(VecCommandFactory.GetShapeCommand(POLYGON, points, null));
                        points = new ArrayList<Point2D.Double>();
                        usedShapeCommand = true;
                        canSetFill = true;
                        canSetPen = true;
                        break;
                    }else{
                        points = new ArrayList<Point2D.Double>();
                        break;
                    }
                }else{
                    break;
                }
            case ELLIPSE:
                if(points.size()==2){
                    VecCommandStack.push(VecCommandFactory.GetShapeCommand(ELLIPSE, points, null));
                    points = new ArrayList<Point2D.Double>();
                    usedShapeCommand = true;
                    canSetFill = true;
                    canSetPen = true;
                    break;
                }else{
                    break;
                }
            default:
        }

        startDrag = (type==POLYGON)? null:endDrag;
        endDrag = null;

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

        SetPenColor(Color.BLACK);
        SetFill(false);
            RenderFile(g2);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));

        if (startDrag != null && endDrag != null) {
            System.out.println(startDrag.toString()+" "+endDrag.toString());

            g2.setPaint(Color.LIGHT_GRAY);
            Shape r;
            if (type == RECTANGLE) {
                r = makeRectangle((int)startDrag.x, (int)startDrag.y, (int)endDrag.x, (int)endDrag.y);
                g2.draw(r);
            }
            else if(type == LINE || type == POLYGON){
                r = makeLine((int)startDrag.x,(int)startDrag.y,(int)endDrag.x,(int)endDrag.y);
                g2.draw(r);
            }else if(type == ELLIPSE){
                r = makeEllipse((int)startDrag.x, (int)startDrag.y, (int)endDrag.x, (int)endDrag.y);
                g2.draw(r);
            }
            g2.setPaint(this.penColor);
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));

    }

    public void SaveFile(){
        if(!this.file.exists()){
            try{
                this.file.createNewFile();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        try{
            StringBuilder sb = new StringBuilder();
            FileWriter fw = new FileWriter(this.file.getAbsolutePath(), false);
            for (VecCommand command: VecCommandStack) {
                sb.append(((IVecCommandPrintable)command).PrintToFile());
            }
            fw.write(sb.toString());
            fw.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private Line2D.Float makeLine(int x1, int y1, int x2, int y2) {
        return new Line2D.Float(x1, y1, x2, y2);
    }

    private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
        return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }

    private Ellipse2D.Float makeEllipse(int x1, int y1, int x2, int y2){
        return new Ellipse2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }
}
