import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Write a description of class Snake here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Snake
{
    private int anchoLienzo;
    private int altoLienzo;
    private static final int NUMERO_SEGMENTOS_INICIALES = 13;
    private static final Color color = Color.BLACK;
    private ArrayList<Segment> segmentos;   
    private static final int DIFERENCIA_DE_GRADOS_ENTRE_DIRECCIONES = 90;
    private static final int MARGEN_LIENZO = 10;
    private static final int TAMANO_CABEZA = 8;
    public static final int SIZE = 25;
    /*
     * Constructor de la clase Snake
     */
    public Snake(int anchoLienzo, int altoLienzo)
    {
        this.anchoLienzo = anchoLienzo;
        this.altoLienzo = altoLienzo;
        segmentos = new ArrayList<>();
        for (int i = 0; i < NUMERO_SEGMENTOS_INICIALES; i++) {
            addSegment();
        }
    }
    
    /*
     * Dibuja la serpiente en el lienzo dado
     */
    public void dibujar(Canvas lienzo)
    {
        for (Segment segmento : segmentos) {
            segmento.dibujar(lienzo);
        }
        Segment cabeza = segmentos.get(segmentos.size() - 1);
        lienzo.setForegroundColor(color);
        lienzo.fillCircle(cabeza.getPosicionFinalX() - (TAMANO_CABEZA / 2),
                          cabeza.getPosicionFinalY() - (TAMANO_CABEZA / 2),
                          TAMANO_CABEZA);
    }
    
    /*
     * Borra la serpiente del lienzo dado
     */
    public void borrar(Canvas lienzo)
    {
        for (Segment segmento : segmentos) {
            segmento.borrar(lienzo);
        }        
        Segment cabeza = segmentos.get(segmentos.size() - 1);
        lienzo.eraseCircle(cabeza.getPosicionFinalX() - (TAMANO_CABEZA / 2),
                          cabeza.getPosicionFinalY() - (TAMANO_CABEZA / 2),
                          TAMANO_CABEZA);      
    }
    
    /*
     * Adiciona un segmento aleatorio a la serpiente
     */
    public boolean addSegment() 
    {
        boolean segmentoAdicionado = false;
        
        Random aleatorio = new Random();
        ArrayList<Integer> direcciones = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            direcciones.add(i * DIFERENCIA_DE_GRADOS_ENTRE_DIRECCIONES);
        }
        
        //Calculamos las coordenadas de inicio del segmento: si no habÃ­a
        //segmentos, lo ubicamos en una posicion aleatoria; si los habÃ­a, al final del ultimo
        //segmento
        int posicionOrigenX = aleatorio.nextInt(anchoLienzo - (2 * MARGEN_LIENZO)) 
                              + MARGEN_LIENZO + Segment.LONGITUD_SEGMENTO; 
        int posicionOrigenY = aleatorio.nextInt(altoLienzo - (2 * MARGEN_LIENZO)) 
                              + MARGEN_LIENZO + Segment.LONGITUD_SEGMENTO; 
        if (segmentos.size() != 0) {
            posicionOrigenX = segmentos.get(segmentos.size() - 1).getPosicionFinalX();
            posicionOrigenY = segmentos.get(segmentos.size() - 1).getPosicionFinalY();
        }
        
        //Probamos todos los segmentos posibles hasta que demos con uno valido
        //o hayamos probado los posibles 4 nuevos segmentos
        Segment posibleNuevoSegmento = null;
        boolean encontradoNuevoSegmentoValido = false;
        while (!direcciones.isEmpty() && !encontradoNuevoSegmentoValido) {
            int direccion = direcciones.remove(aleatorio.nextInt(direcciones.size()));
            posibleNuevoSegmento = new Segment(posicionOrigenX, posicionOrigenY, direccion, color);
            encontradoNuevoSegmentoValido = esSegmentoValido(posibleNuevoSegmento);                             
        }
        
        //Si hemos encontrado un segmento valido lo aÃ±adimos a la
        //serpiente; si no, informamos por pantalla
        if (encontradoNuevoSegmentoValido) {
            segmentos.add(posibleNuevoSegmento);
            segmentoAdicionado = true;
        }

        return segmentoAdicionado;
    }
    
    /**
     * Metodo que devuelve el arraylist de segmentos que componen la serpiente
     * @return El arraylist de segmentos que componen la serpiente
     */
    public ArrayList<Segment> getSerpiente()
    {
        return segmentos;
    }
    
    /*
     * Indica si un segmento es valido, es decir, si se puede adicionar
     * a la serpiente sin que colisione con otros segmentos de la serpiente
     * o con los bordes del lienzo
     */
    private boolean esSegmentoValido(Segment segmento)
    {
        return (!colisionaConOtrosSegmentos(segmento) && !colisionaConBordes(segmento));        
    }
    
    /*
     * Indica si el segmento dado colisiona con los bordes del lienzo
     */
    public boolean colisionaConBordes(Segment segmento)
    {
        boolean colisiona = false;
        if ((segmento.getPosicionFinalX() >= anchoLienzo - MARGEN_LIENZO) ||
            (segmento.getPosicionFinalY() >= altoLienzo - MARGEN_LIENZO) ||
            (segmento.getPosicionFinalX() <= MARGEN_LIENZO) ||
            (segmento.getPosicionFinalY() <= MARGEN_LIENZO)) {
                colisiona = true;
        }
        
        return colisiona;
    }
    
    /*
     * Indica si el segmento colisiona con otros segmentos de la serpiente 
     */
    public boolean colisionaConOtrosSegmentos(Segment segmento)
    {
        boolean colisiona = false;
        for (Segment segmentoSerpiente : segmentos) {
            if (segmentoSerpiente.colisiona(segmento)) {
                colisiona = true;
            }
        }
        return colisiona;
    }
    
    /**
     * Metodo que devuelve el arraylist de segmentos que componen la serpiente
     * @return El arraylist de segmentos que componen la serpiente
     */
    public ArrayList<Segment> getSegmentos()
    {
        return segmentos;

    }
   
    /**
     * Elimina el primer segmento de la serpiente
     */
    public void remove()
    {
        segmentos.remove(0);
    }

    
    /*
     * Adiciona un segmento a la serpiente si es posible; en caso contrario 
     * devuelve false
     */
    public boolean mover() 
    {
        boolean serpienteMovida = false;
        
        segmentos.remove(0);
        if (addSegment()) {
            serpienteMovida = true;
        }
        
        return serpienteMovida;
    }
}
