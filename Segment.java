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
    private int angulo;
    private Color color;
    public static final int SIZE = 10;
    public Segment(int x, int y, int angulo, Color color)
    {
        this.angulo = angulo;
        this.x = x;
        this.y = y;
        this.color = color;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public int getAngulo()
    {
        return angulo;
    }
    public int getXFinal()
    {
         int xPosFinal = x;
        if(angulo == 180)
        {
            xPosFinal -= SIZE;
        }
        else if(angulo == 0)
        {
            xPosFinal += SIZE;
        }
        return xPosFinal;
    }
    public int getYFinal()
    {
        int yPosFinal = y;
        if(angulo == 270)
        {
            yPosFinal -= SIZE;
        }
        else if(angulo == 90)
        {
            yPosFinal += SIZE;
        }
        return yPosFinal;
    }
   
    
    public static int getSize()
    {
        return SIZE;
    }
    
   

}