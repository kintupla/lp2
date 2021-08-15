import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Hello2DApp {
    public static void main (String[] args) {
        Hello2DFrame frame = new Hello2DFrame();
    }
}

class Hello2DFrame extends JFrame {
    public Hello2DFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Java2D - Hello World! Modificado");
        this.setSize(550, 550);
        this.setVisible(true);
    }

    public void paint (Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.white);
        int w = getWidth();
        int h = getHeight();
        g2d.drawLine(0,0, w,h);
        g2d.drawLine(0,h, w,0);
	    g.drawRect(getWidth()/10 , getHeight()/10,100,100);
	    g.drawRect( (getWidth()/10) + 50, (getHeight()/10) + 50, 100, 100);
	    g.drawLine(getWidth()/10, getHeight()/10, (getWidth()/10) + 50, (getHeight()/10) + 50);
	    g.drawLine(getWidth()/10, (getHeight()/10) + 100, (getWidth()/10) + 50, (getHeight()/10) + 50 + 100);
	    g.drawLine(  (getWidth()/10) + 100, (getHeight()/10) + 100, (getWidth()/10) + 100 + 50, (getHeight()/10) + 100 + 50);
	    g.drawLine( (getWidth()/10) + 100, getHeight()/10, (getWidth()/10) + 50 + 100, (getHeight()/10) + 50);
	    getContentPane().setBackground(new Color(106,90,205));
	

    }
}
