package assignmentPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

public class testGUI extends JFrame implements Runnable{


    private CanvasComponent canvas;

    @Override
    public void run() {
        createGUI();
    }

    private void createGUI() {
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        canvas = new CanvasComponent();
        getContentPane().add(canvas, BorderLayout.CENTER);
        repaint();
        setVisible(true);
    }

    public static void main(String[] args) throws Exception{
        SwingUtilities.invokeLater(new testGUI());
    }
}

