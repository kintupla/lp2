import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import figures.Rect;
import figures.Ellipse;
import figures.Rect2;

class PackApp {
    public static void main (String[] args) {
        PackFrame frame = new PackFrame();
        frame.setVisible(true);
    }
}

class PackFrame extends JFrame {
    Rect r1;
    Ellipse e1;
    Rect2 r2;

    PackFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Java Packages");
        this.setSize(350, 350);
        this.r1 = new Rect(50,50, 100,30);
        this.e1 = new Ellipse(50,100, 100,30);
	this.r2 = new Rect2(50,80, 100,30);
    }

    public void paint (Graphics g) {
        super.paint(g);
        this.r1.paint(g);
        this.e1.paint(g);
	this.r2.paint(g);
    }
}
