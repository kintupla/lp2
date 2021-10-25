package figures;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
import interfaces.*;

public abstract class Figure implements IVisible {
	public int x, y;
	public int w, h;

	public Figure(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;

	}

	public abstract void Paint(Graphics g);

	public abstract void focusdafigura(Graphics g);

	public boolean clicado(Point mousePointPosition) {
		return (mousePointPosition.x <= this.x + this.w) && (mousePointPosition.x >= this.x)
				&& (mousePointPosition.y >= this.y) && (mousePointPosition.y <= this.y + this.h);
	}

	public void drag(int DX, int DY) {
		this.x += DX;
		this.y += DY;
	}

}
