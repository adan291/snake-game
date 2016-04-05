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
    public static final int SIZE = 25;
    public Segment(int x, int y, int angulo)
    {
        this.angulo = angulo;
        this.x = x;
        this.y = y;
        this.color = color;
    }
    
    public void draw(Canvas lienzo)
    {
        lienzo.drawLine(x, y, getPosicionFinalX(), getPosicionFinalY());
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
    
    public int getPosicionFinalX()
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
    
    public int getPosicionFinalY()
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
    
    
    public boolean colisionaCon(Canvas canvas)
    {
        return (x >= 0 && getPosicionFinalX() < canvas.getSize().width && 
                y >= 0 && getPosicionFinalY() < canvas.getSize().height );
    }
   

}