import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import buttons.*;
import javax.swing.JFrame;

import figures.Ellipse;
import figures.Figure;
import figures.Line;
import figures.Rect;
import figures.Triangle;

class EditorGrafico {
    public static void main(String[] args) {
        PackFrame frame = new PackFrame();
        frame.setVisible(true);
    }
}

class PackFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    ArrayList<Button> buttons = new ArrayList<Button>() {
        {
            add(new Button(0, new Rect(50, 70, 30, 30, 255, 255, 255, 0, 0, 0, 3)));
            add(new Button(1, new Ellipse(50, 125, 30, 30, 255, 255, 255, 0, 0, 0, 3)));
            add(new Button(2, new Triangle(50, 180, 30, 30, 255, 255, 255, 0, 0, 0, 3)));
            add(new Button(3, new Line(50, 250, 30, 30, 255, 255, 255, 0, 0, 0, 3)));

        }
    };
    List<Figure> figs = new CopyOnWriteArrayList<Figure>();
    Figure focus = null;
    Button focusButton = null;

    Point mousePosition = new Point(0, 0);
    Random rand = new Random();

    @SuppressWarnings("unchecked")
    public PackFrame() {

        try {
            FileInputStream file = new FileInputStream("project.bin");
            ObjectInputStream object = new ObjectInputStream(file);

            this.figs = (List<Figure>) object.readObject();

            object.close();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                try {
                    FileOutputStream file = new FileOutputStream("project.bin");
                    ObjectOutputStream object = new ObjectOutputStream(file);

                    object.writeObject(figs);
                    object.flush();
                    object.close();
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }

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
                }

                if (focusButton != null && evt.getButton() == 1) {
                    Point mousePosition = new Point(evt.getX(), evt.getY());
                    boolean focused = false;

                    for (Button but : buttons) {
                        if (but.clicado(mousePosition) == true) {
                            focused = true;
                        }
                    }

                    if (focused == false) {
                        if (focusButton.getterButtonID() == 0) {
                            Rect rectangle = new Rect(mousePosition.x, mousePosition.y, 100, 100, 255, 255, 255, 0, 0,
                                    0, 3);
                            figs.add(rectangle);
                        } else if (focusButton.getterButtonID() == 1) {
                            Ellipse ellipse = new Ellipse(mousePosition.x, mousePosition.y, 100, 100, 255, 255, 255, 0,
                                    0, 0, 3);
                            figs.add(ellipse);
                        } else if (focusButton.getterButtonID() == 2) {
                            Triangle triangle = new Triangle(mousePosition.x, mousePosition.y, 100, 100, 255, 255, 255,
                                    0, 0, 0, 3);
                            figs.add(triangle);

                        } else if (focusButton.getterButtonID() == 3) {
                            Line line = new Line(mousePosition.x, mousePosition.y, 100, 100, 255, 255, 255, 0, 0, 0, 3);
                            figs.add(line);
                        }

                    }

                    focusButton = null;
                }

                if (evt.getButton() == 1) {
                    Point mousePointPosition = new Point(evt.getX(), evt.getY());

                    for (Button but : buttons) {
                        if (but.clicado(mousePointPosition) == true) {
                            focusButton = but;
                        }
                    }
                }

                if (focusButton != null) {
                    focus = null;
                }

                mouseMoved(evt);
                repaint();
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

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        int screenWidth = gd.getDisplayMode().getWidth();
        int screenHeight = gd.getDisplayMode().getHeight();
        this.setTitle("Editor Grafico");
        this.setSize(screenWidth, screenHeight);
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (Figure fig : this.figs) {
            fig.Paint(g);
            if (fig == focus) {
                focus.focusdafigura(g);
            }
        }
        for (Button but : this.buttons) {
            but.Paint(g);

        }
        if (focusButton != null) {
            focusButton.focusdafigura(g);
        }
    }

}
