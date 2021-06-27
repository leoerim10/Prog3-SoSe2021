package baelle;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * ein hüpfender Ball
 * @author Doro
 *
 */
public class Ball implements Runnable {

    private Object obj;

    /**
     * Zeit für ein Ball zu huepfen
     */
    private int time;

    /**
     * prueft, ob der Ball gerade heupft
     */
    private boolean moving = false;

    /**
     * den Ball anzuhalten
     */
    private boolean stop = false;

    /**
     * erstellt einen Ball, der in das angegebene Panel gezeichnet wird
     * @param b Die Zeichenfläche
     */
    public Ball(JPanel b, int time) {
        this.box = b;
        this.time = time;
        this.obj = new Object();
        Thread t = new Thread(this);
        t.start();
    }

    /**
     * zeichnet den Ball an seiner aktuellen Position
     */
    public void draw() {
        Graphics g = box.getGraphics();
        g.fillOval(x, y, XSIZE, YSIZE);
        g.dispose();
    }

    /**
     * löscht den Ball von der Oberfläche
     */
    public void loeschen()
    {
        Graphics g = box.getGraphics();
        g.setXORMode(box.getBackground());
        g.fillOval(x, y, XSIZE, YSIZE);
    }

    /**
     * bewegt den Ball einen Schritt weiter
     */
    public void move() {
        if (!box.isVisible())
            return;
        Graphics g = box.getGraphics();
        g.setXORMode(box.getBackground());
        g.fillOval(x, y, XSIZE, YSIZE);
        x += dx;
        y += dy;
        Dimension d = box.getSize();
        if (x < 0) {
            x = 0;
            dx = -dx;
        }
        if (x + XSIZE >= d.width) {
            x = d.width - XSIZE;
            dx = -dx;
        }
        if (y < 0) {
            y = 0;
            dy = -dy;
        }
        if (y + YSIZE >= d.height) {
            y = d.height - YSIZE;
            dy = -dy;
        }
        g.fillOval(x, y, XSIZE, YSIZE);
        g.dispose();
    }

    /**
     * bewegt den Ball dauer viele Schritte weiter in der Oberfläche. Um eine angenehme Animation
     * zu erhalten, wird nach jedem Schritt eine Pause eingelegt.
     */
    public void huepfen()
    {
        this.draw();
        for (int i = 1; i <= time; i++) {
            if(stop == true){
                break;
            }
            synchronized (obj){
                while(!moving){
                    try{
                        obj.wait();
                    } catch (InterruptedException e){

                    }
                }
            }
            this.move();

            try{
                Thread.sleep(10);
            }catch(InterruptedException e) {

            }
        }
        this.loeschen();
    }

    private JPanel box;
    private static final int XSIZE = 10;
    private static final int YSIZE = 10;
    private int x = 0;
    private int y = 0;
    private int dx = 2;
    private int dy = 2;

    @Override
    public void run() {
        moving = true;
        huepfen();
    }

    /**
     * halt das Heupfen an
     */
    public void pause(){
        moving = false;
    }

    /**
     * Setzt stop auf true
     */
    public void shutdown(){
        resume();
        stop = true;
    }

    /**
     * heupft den Ball weiter
     */
    public void resume(){
        moving = true;
        synchronized (obj){
            obj.notifyAll();
        }
    }
}