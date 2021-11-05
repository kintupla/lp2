import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import java.util.ArrayList;
import java.util.Random;
import java.awt.Point;
import figures.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;

class EditorGrafico {
    public static void main(String[] args) {
        PackFrame frame = new PackFrame();
        frame.setVisible(true);
    }
}

class PackFrame extends JFrame {
    // ArrayList<Figure> figs = new ArrayList<Figure>();
    List<Figure> figs = new CopyOnWriteArrayList<Figure>();
    Figure focus = null;
    Point mousePosition = new Point(0, 0);
    Random rand = new Random();

    PackFrame() {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                focus = null;
                if (evt.getButton() == 1) {
                    Point mousePosition = new Point(evt.getX(), evt.getY());
                    for (Figure figure : figs) {
                        if (figure.clicado(mousePosition) == true) {
                            focus = figure;
                        }

                        if (focus != null) {
                            figs.remove(focus);
                            figs.add(focus);
                        }
                    }
                    mouseMoved(evt);
                    repaint();
                }
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent evt) { // https://docs.oracle.com/javase/tutorial/uiswing/events/mousemotionlistener.html
                mousePosition.x = evt.getX();
                mousePosition.y = evt.getY();
            }

            public void mouseDragged(MouseEvent evt) {
                if (focus != null) {
                    int dx = evt.getX() - mousePosition.x;
                    int dy = evt.getY() - mousePosition.y;
                    focus.drag(dx, dy);
                }
                mouseMoved(evt);
                repaint();
            }

        });
        // https://precisoestudarsempre.blogspot.com/2016/01/gerando-cores-aleatorias-com-java.html
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent keyEvent) {
                int r = rand.nextInt(255);
                int g = rand.nextInt(255);
                int b = rand.nextInt(255);
                int rContorno = rand.nextInt(255);
                int gContorno = rand.nextInt(255);
                int bContorno = rand.nextInt(255);

                if (keyEvent.getKeyChar() == 'R' || keyEvent.getKeyChar() == 'r') {
                    Rect rectangle = new Rect(mousePosition.x, mousePosition.y, 100, 100, 255, 255, 255, 0, 0, 0, 3);
                    figs.add(rectangle);
                } else if (keyEvent.getKeyChar() == 'E' || keyEvent.getKeyChar() == 'e') {
                    Ellipse ellipse = new Ellipse(mousePosition.x, mousePosition.y, 100, 100, 255, 255, 255, 0, 0, 0,
                            3);
                    figs.add(ellipse);
                } else if (keyEvent.getKeyChar() == 'T' || keyEvent.getKeyChar() == 't') {
                    Triangle triangle = new Triangle(mousePosition.x, mousePosition.y, 100, 100, 255, 255, 255, 0, 0, 0,
                            3);
                    figs.add(triangle);
                } else if (keyEvent.getKeyChar() == 'L' || keyEvent.getKeyChar() == 'l') {
                    Line line = new Line(mousePosition.x, mousePosition.y, 100, 100, 255, 255, 255, 0, 0, 0, 3);
                    figs.add(line);
                } else if (focus != null) {
                    if (keyEvent.getKeyChar() == '/') {
                        focus.PreenchimentoAleatorio(r, g, b);

                    } else if (keyEvent.getKeyChar() == '*') {
                        focus.ContornoAleatorio(rContorno, gContorno, bContorno);
                    } else if (keyEvent.getKeyChar() == '+') {
                        focus.AumentarOuDiminuir(4, 4);
                    } else if (keyEvent.getKeyChar() == '-') {
                        focus.AumentarOuDiminuir(-4, -4);
                    } else if (keyEvent.getKeyCode() == KeyEvent.VK_UP) { // https://www.demo2s.com/java/java-keyevent-vk-up.html
                        focus.drag(0, -10);
                    } else if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
                        focus.drag(0, 10);
                    } else if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
                        focus.drag(-10, 0);
                    } else if (keyEvent.getKeyCode() == KeyEvent.VK_DELETE) {
                        figs.remove(focus);
                    } else if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                        focus.drag(10, 0);
                    }
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
            if (fig == focus) {
                focus.focusdafigura(g);
            }
        }
    }

}
