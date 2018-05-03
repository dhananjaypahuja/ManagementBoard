import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;

public class TaskBoard implements Icon
{
    public int size;
    public Color color;

    public TaskBoard(int aSize, Color c)
    {
        size = aSize;
        color = c;
    }

    public int getIconWidth()
    {
        return size;
    }

    public int getIconHeight()
    {
        return size;
    }

    public void changeColor(Color c)
    {
        color = c;

    }

    public void changeSize(int s)
    {
        size  = s;
    }

    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D.Double planet = new Ellipse2D.Double(x, y,
                size, size);
        g2.setColor(Color.RED);
        g2.drawLine(100,0,100, 500);
        g2.setColor(color);
        g2.fill(planet);
    }
}
