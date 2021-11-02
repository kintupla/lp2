package figures;

import java.awt.*;
import java.awt.Graphics;
import java.io.*;
import interfaces.*;

public abstract class Figure implements IVisible {
	public int x, y;
	public int w, h;
	public int r, g, b;
	public int rContorno, gContorno, bContorno;
	public int Contorno;

	public Figure(int x, int y, int w, int h, int r, int g, int b, int rContorno, int gContorno, int bContorno,
			int Contorno) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.r = r;
		this.g = g;
		this.b = b;
		this.rContorno = rContorno;
		this.gContorno = gContorno;
		this.bContorno = bContorno;
		this.Contorno = Contorno;

	}

	public abstract void Paint(Graphics g);

	public abstract void focusdafigura(Graphics g);

	public abstract boolean clicado(Point mousePointPosition);

	public void drag(int DX, int DY) {
		this.x += DX;
		this.y += DY;
	}

	public abstract void AumentarOuDiminuir(int ADw, int ADh);

	public void PreenchimentoAleatorio(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public void ContornoAleatorio(int rContorno, int gContorno, int bContorno) {
		this.rContorno = rContorno;
		this.gContorno = gContorno;
		this.bContorno = bContorno;
	}
}
