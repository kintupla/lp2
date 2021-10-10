package frame;

import java.awt.Color;
import java.awt.event.*;
import java.awt.Point;
import java.awt.GraphicsEnvironment;
import java.awt.Graphics;
import java.awt.GraphicsDevice;

import java.io.*;

import java.util.ArrayList;

import javax.swing.*;

import figures.*;
import frame.handlers.*;
import buttons.*;

public class Frame extends JFrame {
    private static final long serialVersionUID = 1L;

    ArrayList<Figure> figures = new ArrayList<Figure>();

    ArrayList<Button> buttons = new ArrayList<Button>() {
        {
            add(new Button(new Rect(40, 60, 30, 30), 0));
            add(new Button(new Ellipse(40, 115, 30, 30), 1));
            add(new Button(new Triangle(40, 170, 30, 30), 2));
            add(new Button(new LineSegment(40, 240, 15), 3));
        }
    };

    Figure FiguraSelecionada = null;
    Button BotaoSelecionado = null;

    Point mousePointPosition = new Point(0, 0);

    public Frame() {
        try {
            FileInputStream file = new FileInputStream("projeto.bin");
            ObjectInputStream object = new ObjectInputStream(file);

            this.figures = (ArrayList<Figure>) object.readObject();

            object.close();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        this.setFocusTraversalKeysEnabled(false);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                try {
                    FileOutputStream file = new FileOutputStream("projeto.bin");
                    ObjectOutputStream object = new ObjectOutputStream(file);

                    object.writeObject(figures);
                    object.flush();
                    object.close();
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }

                System.exit(0);
            }
        });

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                FiguraSelecionada = MouseButtonHandler.FiguraSelecionada(mouseEvent, figures, FiguraSelecionada);

                BotaoSelecionado = MouseButtonHandler.CreateFigureByButton(mouseEvent, buttons, BotaoSelecionado,
                        figures);

                BotaoSelecionado = MouseButtonHandler.SelectButton(mouseEvent, buttons, BotaoSelecionado);

                if (BotaoSelecionado != null) {
                    FiguraSelecionada = null;
                }

                mouseMoved(mouseEvent);
                repaint();
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseMoved(MouseEvent mouseEvent) {
                mousePointPosition.x = mouseEvent.getX();
                mousePointPosition.y = mouseEvent.getY();
            }

            public void mouseDragged(MouseEvent mouseEvent) {
                FiguraSelecionada = MouseButtonHandler.SelectAndDragFigure(mouseEvent, figures, FiguraSelecionada,
                        mousePointPosition);
                mouseMoved(mouseEvent);
                repaint();
            }
        });

        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent keyEvent) {
                FiguraSelecionada = KeyButtonHandler.KeyButtonPressed(keyEvent, figures, FiguraSelecionada,
                        mousePointPosition);
                repaint();
            }
        });

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        int userWidth = gd.getDisplayMode().getWidth();
        int userHeight = gd.getDisplayMode().getHeight();

        this.setTitle("Editor Vetorial");
        this.setSize(userWidth, userHeight);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Figure figure : this.figures) {
            figure.Paint(g);
        }

        for (Button button : this.buttons) {
            button.Paint(g);
        }

        if (FiguraSelecionada != null) {
            FiguraSelecionada.RedSelection(g);
        }

        if (BotaoSelecionado != null) {
            BotaoSelecionado.RedSelection(g);
        }

    }
}
