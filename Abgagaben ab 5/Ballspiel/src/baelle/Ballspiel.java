package baelle;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Steuerungsklasse für eine Ball-Animation
 * @author Doro
 *
 */
public class Ballspiel {
    /**
     * Ballframe
     */
    private BallFrame f;

    /**
     * list aller Baelle
     */
    private List<Ball> balls;

    /**
     * erstellt die Steuerungsklasse für die angegebene Oberfläche
     * @param f
     */
    public Ballspiel(BallFrame f)
    {
        this.f = f;
        this.balls = new LinkedList<>();
    }

    /**
     * startet einen Ball auf der Oberfläche und lässt ihn hüpfen
     */
    public void ballStarten()
    {
        Random r = new Random();
        int dauer = r.nextInt(500) + 1000; //Zufallszahl zwischen 1000 und 1500
        Ball b = new Ball(f.getZeichenflaeche(), dauer);
        balls.add(b);
    }

    /**
     * hält alle Bälle auf der Oberfläche an, so dass sie an ihrer aktuellen Position
     * stehen bleiben
     */
    public void baelleStoppen() {
        for (Ball b : balls){
            b.pause();
        }
    }

    /**
     * lässt alle angehaltenen Bälle wieder weiter hüpfen
     */
    public void baelleWeiter() {
        for(Ball b : balls){
            b.resume();
        }

    }

    /**
     * löscht alle Bälle von der Oberfläche
     */
    public void alleLoeschen() {
     for (Ball b : balls){
         b.shutdown();
     }
     balls.clear();
    }
}




