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

public class Frame extends JFrame {
    private static final long serialVersionUID = 1L;

    ArrayList<Figure> figures = new ArrayList<Figure>();

    Figure FiguraSelecionada = null;

    Point mousePointPosition = new Point(0, 0);

    public Frame() {

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                FiguraSelecionada = MouseButtonHandler.FiguraSelecionada(mouseEvent, figures, FiguraSelecionada);

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

        if (FiguraSelecionada != null) {
            FiguraSelecionada.RedSelection(g);
        }

    }
}
