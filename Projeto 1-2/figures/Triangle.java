package figures;

import java.awt.*;

public class Triangle extends Figure {
    Polygon triangle;
    int vetorX[], vetorY[];

    public Triangle(int x, int y, int w, int h, int r, int g, int b, int rContorno, int gContorno, int bContorno,
            int Contorno) {
        super(x, y, w, h, r, g, b, rContorno, gContorno, bContorno, Contorno);
    }

    public void Paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        int x = this.x;
        int y = this.y;

        int xPontos[] = { x, x, x + this.w };
        int yPontos[] = { y, y + this.h, y + this.h };
        this.vetorX = xPontos;
        this.vetorY = yPontos;
        this.triangle = new Polygon(vetorX, vetorY, 3);

        g2d.setStroke(new BasicStroke(this.Contorno));

        g2d.setColor(new Color(this.r, this.g, this.b));
        g2d.fillPolygon(this.triangle);

        g2d.setColor(new Color(this.gContorno, this.bContorno, this.Contorno));
        g2d.drawPolygon(this.triangle);

    }

    public boolean clicado(Point mousePointPosition) {
        return (mousePointPosition.x <= this.x + this.w) && (mousePointPosition.x >= this.x)
                && (mousePointPosition.y >= this.y) && (mousePointPosition.y <= this.y + this.h);
    }

    public void AumentarOuDiminuir(int ADw, int ADh) {
        this.w += ADw;
        this.h += ADh;
    }

    public void focusdafigura(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(this.Contorno));
        g2d.setColor(Color.RED);
        g2d.drawPolygon(this.vetorX, this.vetorY, this.Contorno);

    }

}
