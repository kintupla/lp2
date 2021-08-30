import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import figures.Ellipse1;
import figures.Ellipse2;
import figures.Ellipse3;


class PackApp {
    public static void main (String[] args) {
        EllipseFrame frame = new EllipseFrame();
        frame.setVisible(true);
    }
}

class EllipseFrame extends JFrame {
    Ellipse1 e1;
    Ellipse2 e2;
    Ellipse3 e3;

    EllipseFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Ellipse");
        this.setSize(350, 350);
        this.e1 = new Ellipse1(50, 100, 100, 30, 0, 0, 0, 255, 0, 0);
	this.e2 = new Ellipse2(30, 150, 160, 50, 106, 90, 205, 255, 0, 0);
	this.e3 = new Ellipse3(80, 180, 130, 80, 47, 79, 79, 255, 255, 255);
    }

    public void paint (Graphics g) {
        super.paint(g);
        this.e1.paint(g);
	this.e2.paint(g);
	this.e3.paint(g);
    }
}
