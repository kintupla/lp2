import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Point;
import figures.*;

class EditorGrafico {
    public static void main(String[] args) {
        PackFrame frame = new PackFrame();
        frame.setVisible(true);
    }
}

class PackFrame extends JFrame {
    ArrayList<Figure> figs = new ArrayList<Figure>();
    Figure focus = null;

    Point mousePosition = new Point(0, 0);

    PackFrame() {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyChar() == 'r') {
                    Rect rectangle = new Rect(mousePosition.x, mousePosition.y, 100, 100);
                    figs.add(rectangle);
                }

                repaint();
            }
        });
        this.setTitle("Editor Grafico");
        this.setSize(500, 500);
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (Figure fig : this.figs) {
            fig.Paint(g);

        }
    }

}
