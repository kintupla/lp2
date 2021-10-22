package figures;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

public abstract class Figure {
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

	public abstract boolean clicado(Point mousePointPosition);

}
