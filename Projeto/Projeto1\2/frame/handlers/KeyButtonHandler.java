package frame.handlers;

import figures.*;

import java.awt.event.*;
import java.awt.Point;

import java.util.ArrayList;

public class KeyButtonHandler {
    private static final int defaultSize = 100;

    private class KeyButtons {
        private static final int DEL = 127;
        private static final int TAB = 9;
        private static final int UP = 38;
        private static final int DOWN = 40;
        private static final int LEFT = 37;
        private static final int RIGHT = 39;
        private static final int F1 = 112;
        private static final int F2 = 113;
        private static final int PERIOD = 46;
        private static final int COMMA = 44;
    }

    public static Figure KeyButtonPressed(KeyEvent keyEvent, ArrayList<Figure> figures, Figure FiguraSelecionada,
            Point mousePointPosition) {
        if (keyEvent.getKeyChar() == 'r') {
            Rect rectangle = new Rect(mousePointPosition.x, mousePointPosition.y, defaultSize, defaultSize);
            figures.add(rectangle);
        } else if (keyEvent.getKeyChar() == 'e') {
            Ellipse ellipse = new Ellipse(mousePointPosition.x, mousePointPosition.y, defaultSize, defaultSize);
            figures.add(ellipse);
        } else if (keyEvent.getKeyChar() == 't') {
            Triangle triangle = new Triangle(mousePointPosition.x, mousePointPosition.y, defaultSize, defaultSize);
            figures.add(triangle);
        } else if (keyEvent.getKeyChar() == 'l') {
            LineSegment line = new LineSegment(mousePointPosition.x, mousePointPosition.y, defaultSize);
            figures.add(line);
        } else if (keyEvent.getKeyCode() == KeyButtons.TAB) {
            if (FiguraSelecionada == null) {
                if (figures.size() > 0) {
                    FiguraSelecionada = figures.get(0);
                }
            } else {
                FiguraSelecionada = figures.get((figures.indexOf(FiguraSelecionada) + 1) % figures.size());
            }
        } else if (FiguraSelecionada != null) {

            if (keyEvent.getKeyCode() == KeyButtons.UP) {
                FiguraSelecionada.move(0, -10);
            } else if (keyEvent.getKeyCode() == KeyButtons.DOWN) {
                FiguraSelecionada.move(0, 10);
            } else if (keyEvent.getKeyCode() == KeyButtons.LEFT) {
                FiguraSelecionada.move(-10, 0);
            } else if (keyEvent.getKeyCode() == KeyButtons.RIGHT) {
                FiguraSelecionada.move(10, 0);
            } else if (keyEvent.getKeyCode() == KeyButtons.DEL) {
                figures.remove(FiguraSelecionada);
                FiguraSelecionada = null;
            } else if (keyEvent.getKeyCode() == KeyButtons.F1) {
                FiguraSelecionada.fillColorIndex++;

                if (FiguraSelecionada.fillColorIndex > 10) {
                    FiguraSelecionada.fillColorIndex %= 11;
                }

                FiguraSelecionada.applyFillColorChange();
            } else if (keyEvent.getKeyCode() == KeyButtons.F2) {
                FiguraSelecionada.fillColorIndex--;

                if (FiguraSelecionada.fillColorIndex < 0) {
                    FiguraSelecionada.fillColorIndex += 11;
                }

                FiguraSelecionada.applyFillColorChange();
            } else if (keyEvent.getKeyCode() == KeyButtons.PERIOD) {
                FiguraSelecionada.borderColorIndex++;

                if (FiguraSelecionada.borderColorIndex > 10) {
                    FiguraSelecionada.borderColorIndex %= 11;
                }

                FiguraSelecionada.applyBorderColorChange();
            } else if (keyEvent.getKeyCode() == KeyButtons.COMMA) {
                FiguraSelecionada.borderColorIndex--;

                if (FiguraSelecionada.borderColorIndex < 0) {
                    FiguraSelecionada.borderColorIndex += 11;
                }

                FiguraSelecionada.applyBorderColorChange();
            } else if (keyEvent.getKeyChar() == '+') {
                FiguraSelecionada.scale(6, 6);
            } else if (keyEvent.getKeyChar() == '-') {
                FiguraSelecionada.scale(-6, -6);
            }

        }

        return FiguraSelecionada;
    }
}
