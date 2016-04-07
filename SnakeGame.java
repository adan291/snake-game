import java.util.ArrayList;
import java.awt.Color;
import java.util.Random;
/**
 * Write a description of class SnakeGame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class SnakeGame
{
    private Canvas lienzo;
    private Snake snake;
    private ArrayList<Snake> serpiente;
    private static final int ANCHO_LIENZO = 500;
    private static final int ALTO_LIENZO = 500;
    private Random rand;
    private ArrayList<Galleta> galletas;

    /**
     * Constructor de la clase Snake
     */
    public SnakeGame()
    {
        lienzo = new Canvas("Snake game", ANCHO_LIENZO, ALTO_LIENZO);
        serpiente = new ArrayList<Snake>();
        rand = new Random();
        galletas = new ArrayList<Galleta>();
    }

    /**
     * Dibuja una serpiente en la pantalla
     */
    public void drawSnake()
    {
        snake = new Snake(ANCHO_LIENZO,ALTO_LIENZO);
        lienzo.erase();
        snake.dibujar(lienzo);

    }

    /**
     * Metodo para borrar la pantalla
     */
    public void erase(){
        lienzo.erase();
    }
    
    

    /**
     * Metodo para dar movimiento a la serpiente
     */
    public boolean animateSnake()
    {
        boolean moverse = false;
        int index = 0;
        if(serpiente.size() > 0)
        {
            moverse = true;
            Galleta galletaComida = null;
            while(moverse)
            {
                lienzo.wait(100);

                erase();
                while(moverse && index < serpiente.size())
                {
                    moverse = serpiente.get(index).mover();

                    galletaComida = compruebaGalletas(serpiente.get(index));
                    if(galletaComida != null)
                    {

                        serpiente.get(index).addSegment();
                        galletaComida = null;
                    }
                    drawSnake();
                    index++;
                }
                index = 0;
                // Pinta las galletas y los puntos
                pintaGalletas();

            }
        }
        return moverse;
    }
    

}