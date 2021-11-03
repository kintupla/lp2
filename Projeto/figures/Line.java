package figures;

import java.awt.*;

public class Line extends Figure {

    public Line(int x, int y, int w, int h, int r, int g, int b, int rContorno, int gContorno, int bContorno,
            int Contorno) {
        super(x, y, w, h, r, g, b, rContorno, gContorno, bContorno, Contorno);
    }

    public void Paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(this.rContorno, this.gContorno, this.bContorno));
        g2d.setStroke(new BasicStroke(this.Contorno));
        g2d.drawLine(this.x, this.y, this.w + this.x, this.y);

    }

    public void AumentarOuDiminuir(int ADw, int ADh) {
        this.w += ADw;
        this.h += ADh;
    }

    public boolean clicado(Point mousePointPosition) {
        return (mousePointPosition.x <= this.x + this.w) && (mousePointPosition.x >= this.x)
                && (mousePointPosition.y >= this.y) && (mousePointPosition.y <= this.y + this.Contorno);
    }

    public void focusdafigura(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.RED);
        g2d.drawRect(this.x - 3, this.y - 3, this.w + 3, 3);

    }

}
