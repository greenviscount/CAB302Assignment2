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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class CanvasComponent extends JComponent implements MouseListener {
    ArrayList<Shape> shapes = new ArrayList<>();
    ArrayList<Point> points = new ArrayList<>();
    Point startDrag, endDrag, startClick, endClick;
    boolean clickStatus = true;
    boolean drawMode = true;

    @Override
    public void mouseClicked(MouseEvent e) {
        if (clickStatus == true) {
            startClick = new Point(e.getX(), e.getY());
            endClick = startClick;
            clickStatus = false;
        }
        else{
            endClick = new Point(e.getX(), e.getY());
            Shape l = makeRectangle(startDrag.x, startDrag.y, e.getX(), e.getY());
            clickStatus = true;
            repaint();
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        startDrag = new Point(e.getX(), e.getY());
        points.add(startDrag);
        endDrag = startDrag;
        repaint();
    }
    @Override
    public void mouseReleased(MouseEvent e) {

        if (drawMode == true){
            endDrag = new Point(e.getX(), e.getY());
            Shape r = makeRectangle(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
            shapes.add(r);
            points.add(endDrag);
            startDrag = null;
            endDrag = null;
            repaint();
            drawMode = false;
        }
        else{
            endDrag = new Point(e.getX(), e.getY());
            Shape r = makeLine(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
            shapes.add(r);
            points.add(endDrag);
            startDrag = null;
            endDrag = null;
            repaint();
            drawMode = true;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void PaintSurface() {
            this.addMouseListener(this);
            this.addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent e) {
                    endDrag = new Point(e.getX(), e.getY());
                    repaint();
                }
            });
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
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            paintBackground(g2);

            g2.setStroke(new BasicStroke(2));
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));

            for (Shape s : shapes) {
                g2.setPaint(Color.BLACK);
                g2.draw(s);
            }

            if (startDrag != null && endDrag != null) {
                g2.setPaint(Color.LIGHT_GRAY);
                Shape r = makeRectangle(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
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
