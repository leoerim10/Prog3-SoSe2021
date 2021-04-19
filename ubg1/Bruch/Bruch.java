import java.math.BigInteger;
/**
 * speichert ein mathematische Bruch
 * er besteht aus einem Zaehler und einem Nenner
 * @author Sameer Dhimal 569076,  Wojciech Maximilan Frackowski 576278
 */
public class Bruch implements Comparable<Bruch>{

    private int zaehler;
    private int nenner;

    /**
     * Setzt die beiden Eigenschaften zaehler und nenner auf die angegebenen Werte
     * @param zaehler Zaehler
     * @param nenner Nenner
     * @throws IllegalArgumentException wenn der Nenner null ist
     */
    public Bruch(int zaehler, int nenner) throws IllegalArgumentException {
        if (nenner == 0)
            throw new IllegalArgumentException("Nenner darf niemals null sein!!!");
        this.zaehler = zaehler;
        this.nenner = nenner;
    }

    public int getZaehler() {
        return zaehler;
    }

    public int getNenner() {
        return nenner;
    }

    /**
     * liefert eine String-Darstellung des Bruches
     * @return eine String-Darstellung des Bruches
     */
    @Override
    public String toString() {
        return "Zaehler: " + this.zaehler + " Nenner: " + this.nenner + "\n";
    }

    /**
     * multipliziert zwei Brueche
     * @param b gegebene Bruche
     * @return Ergebnis der Multiplikation
     */
    public Bruch multiplizieren(Bruch b){
        Bruch n = new Bruch(this.getZaehler()*b.zaehler, this.getNenner()*b.nenner);
        return n;
    }


    /**
     * rechnet den Bruch in eine Kommazahl um
     * @return das Ergebnis in ein Kommazahl
     */
    public double ausrechnen(){
        return this.zaehler/this.nenner;
    }

    /**
     * teilt Zaehler und Nenner durch GGT
     */
    public void kuerzen(){
        int gcd = getGCD();
        int newNenner = this.nenner / gcd;
        int newZahler = this.zaehler / gcd;

        this.nenner = newNenner;
        this.zaehler = newZahler;
    }


    /**
     * wandelt sich in Kehrwert um
     * @return liefert den Kehrwert
     */
    public Bruch kehrwert(){
        Bruch n = new Bruch (this.getNenner(), this.getZaehler());
        return n;
    }

    /**
     * teilt this. Bruch durch gegebene Bruch
     * @param b gegebene Bruch
     * @return das Ergebnis der Teilung
     */
    public Bruch dividieren(Bruch b){
        return multiplizieren(kehrwert());
    }


    /**
     * @return Wert der groeste gemeinsame Teiler
     */
    public int getGCD(){
        BigInteger b1 = BigInteger.valueOf(this.zaehler);
        BigInteger b2 = BigInteger.valueOf(this.nenner);

        BigInteger gcd = b1.gcd(b2);
        return gcd.intValue();
    }

    @Override
    public int compareTo(Bruch o) {
        double x1 = this.ausrechnen();
        double x2 = o.ausrechnen();

        if(x1==x2){
            return 0;
        } else if (x1 > x2){
            return 1;
        } else {
            return -1;
        }
    }


}

