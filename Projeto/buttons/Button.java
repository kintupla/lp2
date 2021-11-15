package buttons;

import java.awt.*;

import interfaces.*;
import figures.*;

public class Button implements IVisible {
    private int x, y;
    private int buttonID;
    private Figure figureButton;

    public Button(int buttonID, Figure figureButton) {
        this.figureButton = figureButton;
        this.buttonID = buttonID;
        this.y = 60 + 55 * buttonID;
        this.x = 40;

    }

    public int getterButtonID() {
        return this.buttonID;
    }

    public void Paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(3));
        g2d.setPaint(Color.WHITE);
        g2d.fillRect(this.x, this.y, 50, 50);

        g2d.setPaint(Color.BLACK);
        g2d.drawRect(this.x, this.y, 50, 50);

        this.figureButton.Paint(g);
    }

    public boolean clicado(Point mousePointPosition) {
        return (mousePointPosition.x >= this.x) && (mousePointPosition.x <= this.x + 50)
                && (mousePointPosition.y >= this.y) && (mousePointPosition.y <= this.y + 50);
    }

    public void focusdafigura(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4));
        g2d.setColor(Color.RED);
        g2d.drawRect(this.x, this.y, 50, 50);
    }
}
