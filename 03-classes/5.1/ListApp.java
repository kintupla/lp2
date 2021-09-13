import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

class ListApp {
    public static void main (String[] args) {
        ListFrame frame = new ListFrame();
        frame.setVisible(true);
    }
}

class ListFrame extends JFrame {
    ArrayList<Rect> rs = new ArrayList<Rect>();
    ArrayList<Ellipse> el = new ArrayList<Ellipse>();

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
                        int x = rand.nextInt(350);
                        int y = rand.nextInt(350);
                        int w = rand.nextInt(50);
                        int h = rand.nextInt(50);
                        rs.add(new Rect(x,y, w,h));
                        repaint();  // outer.repaint()
                    }

                    if (evt.getKeyChar() == 'e') {
                        int x = rand.nextInt(350);
                        int y = rand.nextInt(350);
                        int w = rand.nextInt(50);
                        int h = rand.nextInt(50);

                        
                        el.add(new Ellipse(x, y, w, h)); // outer.el.add();
                        repaint(); // outer.repaint()
                    }
                }
            }
        );

        this.setTitle("Lista de Retangulos e Elipses");
        this.setSize(300, 300);
    }

    public void paint (Graphics g) {
        super.paint(g);
        for (Rect r: this.rs) {
            r.paint(g);
        }

        for (Ellipse e: this.el){
            e.paint(g);
        }
    }
}

class Rect {
    int x, y;
    int w, h;

    Rect (int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawRect(this.x,this.y, this.w,this.h);
    }
}

class Ellipse {
    int x, y;
    int w, h;

    Ellipse(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    void Print() {
        System.out.format("Elipse de eixos (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(new Ellipse2D.Double(this.x, this.y, this.w, this.h));
    }
}
