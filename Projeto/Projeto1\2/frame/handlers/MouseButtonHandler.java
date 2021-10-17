package frame.handlers;

import java.awt.event.*;
import java.awt.Point;

import java.util.ArrayList;

import figures.*;

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

    public static Figure SelectAndDragFigure(MouseEvent mouseEvent, ArrayList<Figure> figures, Figure FiguraSelecionada,
            Point mousePointPosition) {
        if (FiguraSelecionada != null) {
            FiguraSelecionada.dragFigure(mousePointPosition, mouseEvent.getX() - mousePointPosition.x,
                    mouseEvent.getY() - mousePointPosition.y);
        }

        return FiguraSelecionada;
    }
}
