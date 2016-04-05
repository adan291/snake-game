import java.awt.Color;
import java.util.ArrayList;
/**
 * Write a description of class Segment here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Segment
{
    private int x;
    private int y;
    private int xF;
    private int yF;
    private int angle;
    private static final int SIZE = 10;
    public Segment(int x, int y, int angle)
    {
        this.angle = angle;
        this.x = x;
        this.y = y;
        switch (angle) 
        {
            case 1:  xF = x; 
                     yF = y + SIZE;                
                     break;
            case 2:  xF = x - SIZE; 
                     yF = y;
                     break;    
            case 3:  xF = x; 
                     yF = y - SIZE;
                     break;
            default: xF = x + SIZE; 
                     yF = y;    
                     break;
        }
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public int getXf()
    {
        return xF;
    }
    
    public int getYf()
    {
        return yF;
    }
    
    public static int getSize()
    {
        return SIZE;
    }
    
    public boolean segmentCollision(Segment segment)
    {
        return ((segment.getXf() == x || segment.getXf() == xF) &&
                (segment.getYf() == x || segment.getYf() == yF));
    }

    public boolean segmentInsideCanvas(Canvas canvas)
    {
        return (x >= 0 && xF < canvas.getSize().width && 
                y >= 0 && yF < canvas.getSize().height );
    }

    public void drawSegment(Canvas canvas)
    {
        canvas.drawLine(x, y, xF, yF);
    }

    public void eraseSegment(Canvas canvas)
    {
        Color currentColor = canvas.getForegroundColor();        
        canvas.setForegroundColor(canvas.getBackgroundColor());
        canvas.drawLine(x, y, xF, yF);
        canvas.setForegroundColor(currentColor);
    }

}