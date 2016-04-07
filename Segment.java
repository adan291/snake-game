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
    private int posicionX;
    private int posicionY;
    private int direccion;
    private Color color;
    public static final int LONGITUD_SEGMENTO = 10;

    /*
     * Constructor de la clase Segment
     */
    public Segment(int posX, int posY, int direccion, Color color)
    {
        posicionX = posX;
        posicionY = posY;
        this.direccion = direccion;
        this.color = color;
    }

    /*
     * Devuelve la coordenada del eje X donde acaba el segmento
     */
    public int getPosicionFinalX()
    {
        int posicionFinalX = posicionX;
        if(direccion == 0){
            posicionFinalX = posicionX + LONGITUD_SEGMENTO;
        }
        else if(direccion == 180){
            posicionFinalX = posicionX - LONGITUD_SEGMENTO;
        }
        return posicionFinalX;
    }
    
    /*
     * Devuelve la coordenada del eje X
     */
    public int getPosicionX()
    {
       
        return posicionX;
    }
    /*
     * Devuelve la coordenada del eje Y
     */
    public int getPosicionY()
    {
       
        return posicionY;
    }


    /*
     * Devuelve la coordenada del eje Y donde acaba el segmento
     */
    public int getPosicionFinalY()
    {
        int finaly = posicionY;
        if (direccion == 90)
        {
            finaly = posicionY + LONGITUD_SEGMENTO;
        }
        else if(direccion == 270)
        {
            finaly = posicionY - LONGITUD_SEGMENTO;
        }
        return finaly;
    }

    /*
     * Dibuja el segmento en el lienzo
     */
    public void dibujar(Canvas lienzo)
    {
        lienzo.setForegroundColor(color);
        lienzo.drawLine(posicionX,posicionY,getPosicionFinalX(),getPosicionFinalY());
    }

    /*
     * Borra el segmento del lienzo
     */
    public void borrar(Canvas lienzo)
    {
        lienzo.setForegroundColor(lienzo.getBackgroundColor());
        lienzo.drawLine(posicionX,posicionY,getPosicionFinalX(),getPosicionFinalY());
    }

    /*
     * Devuelve la coordenada del eje X donde empieza el segmento
     */
    public int getPosicionInicialX()
    {
        return posicionX;
    }

    /*
     * Devuelve la coordenada del eje Y donde empieza el segmento
     */
    public int getPosicionInicialY()
    {
        return posicionY;
    }    

    /*
     * Devuelve la direccion que tiene el segmento
     */
    public int getDireccion()
    {
        return direccion;
    }

     /*
     * Devuelve true si el segmento pasado como parametro colisiona con el segmento sobre el
     * que se invoca el metodo, teniendo en cuenta que el pasado como parametro se supone que
     * es un nuevo segmento de la serpiente; false en otro caso
     */
    public boolean colisiona(Segment segmento)
    {
        return (posicionX == segmento.getPosicionFinalX() && posicionY == segmento.getPosicionFinalY());
    }

}