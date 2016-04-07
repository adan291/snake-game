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
     /**
     * Muestra el mensaje de game over
     */
    public void gameOver()
    {

        lienzo.setForegroundColor(Color.RED);
        int posX = (int)(lienzo.getSize().getWidth())/2 - 30;
        int posY = (int)(lienzo.getSize().getHeight())/2 - 10;
        lienzo.drawString("GAME OVER",posX, posY);
    }
    /**
     * Metodo que crea una galleta de forma aleatoria, en posiciones validas
     */
    public Galleta creaGalleta()
    {
        Galleta galleta = null;
        // Genera coordenadas
        int coordX = 0;
        int coordY = 0;
        int lado;
        boolean validas = true;
        boolean seleccionadas = false;
        // Comprueba si las coordenadas las tiene la serpiente u otra galleta. Usa un indice para darle
        // una salida posible y evitar bucles infinitos si se queda sin coordenadas validas
        int indice = 0;
        while(!seleccionadas && indice < 10)
        {
            validas = true;
            // Genera coordenadas para X e Y
            int tempX = (int)lienzo.getSize().getWidth() - Snake.SIZE - 1;
            int tempY = (int)lienzo.getSize().getHeight() - Snake.SIZE - 1;
            coordX = rand.nextInt(tempX) + Snake.SIZE;
            coordY = rand.nextInt(tempY) + Snake.SIZE;
            // Deben ser multiplos del size de los segmentos, para que la serpiente pueda comerlas
            coordX = coordX - (coordX%Snake.SIZE);
            coordY = coordY - (coordY%Snake.SIZE);
            // Ahora comprueba que no se genere en ninguna posicion de la serpiente, ni en ninguna
            // posicion de las galletas
            int index = 0;
            // Bucle para las galletas
            while(index < galletas.size() && (validas))
            {
                Galleta temp = galletas.get(index);
                if((temp.getXPos() == coordX) && (temp.getYPos() == coordY))
                {
                    validas = false;
                }
                index++;
            }
            // Comprueba la serpiente
            index = 0;
            if(serpiente != null && validas)
            {
                int indiSerp = 0;
                // Recorre todas las serpientes
                while(indiSerp < serpiente.size() && (validas))
                {
                    // Recorre cada segmento de la serpiente, y comprueba los puntos inicial y final
                    ArrayList<Segment> segmentos = new ArrayList<Segment>(serpiente.get(indiSerp).getSerpiente());
                    while(index < serpiente.size() && (validas))
                    {
                        Segment segment = segmentos.get(index);
                        if(((segment.getPosicionX() == coordX) && (segment.getPosicionY() == coordY)) ||
                        ((segment.getPosicionFinalX() == coordX) && (segment.getPosicionFinalY() == coordY)))
                        {
                            validas = false;
                        }
                        index++;
                    }
                    indiSerp++;
                }
            }
            indice++;
            seleccionadas = validas;
        }
        if(seleccionadas)
        {
            lado = rand.nextInt(Snake.SIZE/2) + 1;
            galleta = new Galleta(coordX, coordY, lado, lienzo);
        }
        return galleta;
    }
    
    /**
     * Metodo que comprueba si la galleta es comida por la serpiente
     */
    private Galleta compruebaGalletas(Snake serp)
    {
        Galleta galletaComida = null;
        if((snake != null) && (galletas.size() > 0))
        {
            // Toma el ultimo segmento de la serpiente
            ArrayList<Segment> segmentos = new ArrayList<Segment>(serp.getSerpiente());
            Segment segment = segmentos.get(serpiente.size() - 1);
            int index = 0;
            // Recorre la arraylist de galletas para ver si concide alguna coordenada
            while(index < galletas.size() && (galletaComida == null))
            {
                Galleta galleta = galletas.get(index);
                if(((segment.getPosicionX() == galleta.getXPos()) && (segment.getPosicionY() == galleta.getYPos())) ||
                ((segment.getPosicionFinalX() == galleta.getXPos()) && (segment.getPosicionFinalY()) == galleta.getYPos()))
                {
                    // Guarda la galleta que se come y la borra
                    galletaComida = galleta;
                    galletas.remove(galleta);
                }
                index++;
            }
        }
        return galletaComida;
    }

    /**
     * Metodo que pinta las galletas por pantalla
     */
    private void pintaGalletas()
    {
        for(int i = 0; i < galletas.size(); i++)
        {
            galletas.get(i).drawGalleta();
        }
    }

    

}