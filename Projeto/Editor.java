import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

import figures.*;

class EditorGraficoApp {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}

class ListFrame extends JFrame {
    ArrayList<Rect> rs = new ArrayList<Rect>();
    ArrayList<Ellipse> es = new ArrayList<Ellipse>();
    Random rand = new Random();

    ListFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );

        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {
                    if (evt.getKeyChar() == 'r') {
                        int x = rand.nextInt(500);
                        int y = rand.nextInt(500);
                        int width = rand.nextInt(100);
                        int height = rand.nextInt(100);
                        int rFill = rand.nextInt(255);
                        int gFill = rand.nextInt(255);
                        int bFill = rand.nextInt(255);
                        int stroke = rand.nextInt(3);
                        int rStroke = rand.nextInt(255);
                        int gStroke = rand.nextInt(255);
                        int bStroke = rand.nextInt(255);
                        rs.add(new Rect(x,y, width,height, rFill,gFill,bFill, stroke, rStroke,gStroke,bStroke));
                        repaint();
                    }
                }
            }
        );

        this.addKeyListener (
            new KeyAdapter() {
                public void keyPressed (KeyEvent evt) {
                    if (evt.getKeyChar() == 'e') {
                        int x = rand.nextInt(500);
                        int y = rand.nextInt(500);
                        int width = rand.nextInt(100);
                        int height = rand.nextInt(100);
                        int rFill = rand.nextInt(255);
                        int gFill = rand.nextInt(255);
                        int bFill = rand.nextInt(255);
                        int stroke = rand.nextInt(3);
                        int rStroke = rand.nextInt(255);
                        int gStroke = rand.nextInt(255);
                        int bStroke = rand.nextInt(255);
                        es.add(new Ellipse(x,y, width,height, rFill,gFill,bFill, stroke, rStroke,gStroke,bStroke));
                        repaint();
                    }
                }
            }
        );

        this.setTitle("Editor Grafico");
        this.setSize(500, 500);
    }

    public void paint (Graphics g) {
        super.paint(g);
        for (Rect r: this.rs) {
            r.paint(g);
        }
        for (Ellipse e: this.es) {
            e.paint(g);
        }
    }
}
