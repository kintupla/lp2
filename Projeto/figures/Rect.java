package figures;

import java.awt.*;

public class Rect extends Figure {
    int width, height;

    public Rect (int x, int y, int width, int height, int rFill, int gFill, int bFill, int stroke, int rStroke, int gStroke, int bStroke) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rFill = rFill;
        this.gFill = gFill;
        this.bFill = bFill;
        this.stroke = stroke;
        this.rStroke = rStroke;
        this.gStroke = gStroke;
        this.bStroke = bStroke;
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(this.rFill, this.gFill, this.bFill));
        g2d.fillRect(this.x,this.y, this.width,this.height);

        g2d.setColor(new Color(this.rStroke, this.gStroke, this.bStroke));
        g2d.setStroke(new BasicStroke(this.stroke));
        g2d.drawRect(this.x,this.y, this.width,this.height);
    }
}
