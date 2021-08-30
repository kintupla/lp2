package figures;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

public class Ellipse3 {
    int x, y;
    int w, h;
    int x1, x2, x3, d1, d2, d3;
    public Ellipse3 (int x, int y, int w, int h, int x1, int x2, int x3, int d1, int d2, int d3) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
	this.x1=x1;
	this.x2=x2;
	this.x3=x3;
	this.d1=d1;
	this.d2=d2;
	this.d3=d3;
    }

    public void print () {
        System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n",
            this.w, this.h, this.x, this.y);
    }

    public void paint (Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(new Ellipse2D.Double(this.x,this.y, this.w,this.h));
	g.setColor(new Color(this.x1, this.x2, this.x3));
	g.fillOval(this.x, this.y, this.w, this.h);
	g.setColor(new Color(this.d1, this.d2, this.d3));
	g.drawOval(this.x, this.y, this.w, this.h);
    }
}