package frame.handlers;

import java.awt.event.*;
import java.awt.Point;

import java.util.ArrayList;

import figures.*;

import buttons.*;

public class MouseButtonHandler {
    private static final int defaultSize = 100;

    private class MouseButtons {
        private static final int MOUSE1 = 1;
    }

    public static Figure FiguraSelecionada(MouseEvent mouseEvent, ArrayList<Figure> figures, Figure FiguraSelecionada) {
        FiguraSelecionada = null;

        if (mouseEvent.getButton() == MouseButtons.MOUSE1) {
            Point mousePointPosition = new Point(mouseEvent.getX(), mouseEvent.getY());

            for (Figure figure : figures) {
                if (figure.DentroFigura(mousePointPosition) == true) {
                    FiguraSelecionada = figure;
                }
            }

            if (FiguraSelecionada != null) {
                figures.remove(FiguraSelecionada);
                figures.add(FiguraSelecionada);
            }
        }

        return FiguraSelecionada;
    }

    public static Button SelectButton(MouseEvent mouseEvent, ArrayList<Button> buttons, Button BotaoSelecionado) {
        BotaoSelecionado = null;

        if (mouseEvent.getButton() == MouseButtons.MOUSE1) {
            Point mousePointPosition = new Point(mouseEvent.getX(), mouseEvent.getY());

            for (Button button : buttons) {
                if (button.DentroFigura(mousePointPosition) == true) {
                    BotaoSelecionado = button;
                }
            }
        }

        return BotaoSelecionado;
    }

    public static Button CreateFigureByButton(MouseEvent mouseEvent, ArrayList<Button> buttons, Button BotaoSelecionado,
            ArrayList<Figure> figures) {
        if (BotaoSelecionado != null && mouseEvent.getButton() == MouseButtons.MOUSE1) {
            Point mousePointPosition = new Point(mouseEvent.getX(), mouseEvent.getY());
            boolean isClickingInButton = false;

            for (Button button : buttons) {
                if (button.DentroFigura(mousePointPosition) == true) {
                    isClickingInButton = true;
                }
            }

            if (isClickingInButton == false) {
                switch (BotaoSelecionado.buttonIndex) {
                    case 0:
                        Rect rectangle = new Rect(mousePointPosition.x, mousePointPosition.y, defaultSize, defaultSize);
                        figures.add(rectangle);
                        break;
                    case 1:
                        Ellipse ellipse = new Ellipse(mousePointPosition.x, mousePointPosition.y, defaultSize,
                                defaultSize);
                        figures.add(ellipse);
                        break;
                    case 2:
                        Triangle triangle = new Triangle(mousePointPosition.x, mousePointPosition.y, defaultSize,
                                defaultSize);
                        figures.add(triangle);
                        break;
                    case 3:
                        LineSegment line = new LineSegment(mousePointPosition.x, mousePointPosition.y, defaultSize);
                        figures.add(line);
                        break;
                    default:
                }
            }

            BotaoSelecionado = null;
        }
        return BotaoSelecionado;
    }

    public static Figure SelectAndDragFigure(MouseEvent mouseEvent, ArrayList<Figure> figures, Figure FiguraSelecionada,
            Point mousePointPosition) {
        if (FiguraSelecionada != null) {
            FiguraSelecionada.dragFigure(mousePointPosition, mouseEvent.getX() - mousePointPosition.x,
                    mouseEvent.getY() - mousePointPosition.y);
        }

        return FiguraSelecionada;
    }
}
