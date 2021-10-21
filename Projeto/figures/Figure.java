package figures;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

public abstract class Figure {
	public int x, y;
	public int width, height;

	public Figure(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public abstract void Paint(Graphics g);

}
