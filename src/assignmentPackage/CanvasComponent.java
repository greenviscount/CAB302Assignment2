package assignmentPackage;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.*;

public class CanvasComponent extends JPanel implements MouseListener{
    private ArrayList<Shape> shapes;
    private ArrayList<Color> colours;
    private ArrayList<Point> points;
    private Point startDrag, endDrag, startClick, endClick;
    private boolean clickStatus;
    private int drawMode;
    private JButton button;
    public CanvasComponent(JButton button) {
        super();
        this.button = button;
        shapes = new ArrayList<>();
        colours = new ArrayList<>();
        points = new ArrayList<>();
        clickStatus = true;
        drawMode = 1;
        this.addMouseListener(this);
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                endDrag = new Point(e.getX(), e.getY());
                repaint();
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mousePressed(MouseEvent e) {
        startDrag = new Point(e.getX(), e.getY());
        points.add(startDrag);
        repaint();
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        Shape r;
        if (drawMode == 2){
            endDrag = new Point(e.getX(), e.getY());
            r = makeRectangle(startDrag.x, startDrag.y, endDrag.x, endDrag.y);

        }
        else{
            endDrag = new Point(e.getX(), e.getY());
            r = makeLine(startDrag.x, startDrag.y, endDrag.x, endDrag.y);

        }
        shapes.add(r);
        points.add(endDrag);
        startDrag = null;
        endDrag = null;
        colours.add(button.getBackground());
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

            for (int i=0;i<shapes.size();i++) {
                g2.setPaint(colours.get(i));
                g2.draw(shapes.get(i));
            }

            if (startDrag != null && endDrag != null) {
                g2.setPaint(Color.LIGHT_GRAY);
                Shape r;
                if (drawMode == 2) {
                    r = makeRectangle(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
                }
                else {
                    r = makeLine(startDrag.x,startDrag.y,endDrag.x,endDrag.y);
                }
                g2.draw(r);
            }
        }

        private Line2D.Float makeLine(int x1, int y1, int x2, int y2) {
            return new Line2D.Float(x1, y1, x2, y2);
        }

        private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
            return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
        }
    }
