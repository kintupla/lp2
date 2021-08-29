import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

class EllipseApp {
    public static void main (String[] args) {
        EllipseFrame frame = new EllipseFrame();
        frame.setVisible(true);
    }
}

class EllipseFrame extends JFrame {
    Ellipse e1, e2, e3;

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
        this.e1 = new Ellipse(50, 100, 100, 30, 0, 0, 0, 255, 0, 0);
	this.e2 = new Ellipse(30, 150, 160, 50, 106, 90, 205, 255, 0, 0);
	this.e3 = new Ellipse(80, 180, 130, 80, 47, 79, 79, 255, 255, 255);
    }

    public void paint (Graphics g) {
        super.paint(g);
        this.e1.paint(g);
	this.e2.paint(g);
	this.e3.paint(g);
    }
}

class Ellipse {
    int x, y;
    int w, h;
    int x1, x2, x3, d1, d2, d3;
    Ellipse (int x, int y, int w, int h, int x1, int x2, int x3, int d1, int d2, int d3) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
	this.x1=x1;
	this.x2=x2;
	this.x3=x3;
	this.d1=d1;
	this.d2=d2;
	this.d3=d3;
    }

    void print () {
        System.out.format("Elipse de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
	g.setColor(new Color(this.x1, this.x2, this.x3));
	g.fillOval(this.x, this.y, this.w, this.h);
	g.setColor(new Color(this.d1, this.d2, this.d3));
	g.drawOval(this.x, this.y, this.w, this.h);
    }
}