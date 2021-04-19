import java.math.BigInteger;
/**
 * speichert ein mathematische Bruch
 * er besteht aus einem Zaehler und einem Nenner
 * @author Sameer Dhimal 569076,  Wojciech Maximilan Frackowski 576278
 */
public class Bruch implements Comparable<Bruch>{
    private int zahler;
    private int nenner;


    /**
     * Setzt die beiden Eigenschaften zaehler und nenner auf die angegebenen Werte
     * @param zaehler Zaehler
     * @param nenner Nenner
     * @throws IllegalArgumentException wenn der Nenner null ist
     */
    public Bruch(int zahler, int nenner){
        if(nenner == 0){
            throw new IllegalArgumentException("Nenner can not be 0");
        }

        this.zahler = zahler;
        this.nenner = nenner;
    }

    public int getZahler(){
        return this.zahler;
    }

    public int getNenner(){
        return this.nenner;
    }
    /**
     * multipliziert zwei Brueche
     * @param b gegebene Bruche
     * @return Ergebnis der Multiplikation
     */
    public Bruch multiplizieren(Bruch b){
        Bruch n = new Bruch(this.zahler*b.zahler, this.nenner*b.nenner);
        return n;
    }
     /**
     * rechnet den Bruch in eine Kommazahl um
     * @return das Ergebnis in ein Kommazahl
     */
    public double ausrechnen(){
        double div = (double)this.zahler/(double)this.nenner;
        return div;
    }
     /**
     * teilt Zaehler und Nenner durch GGT
     */
    public void kuerzen(){
        int gcd = getGCD();
        int newNenner = this.nenner / gcd;
        int newZahler = this.zahler / gcd;

        this.nenner = newNenner;
        this.zahler = newZahler;
    
    }
    /**
     * wandelt sich in Kehrwert um
     * @return liefert den Kehrwert
     */
    public Bruch kehrwert(){
        Bruch n = new Bruch(this.nenner, this.zahler);
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
        BigInteger b1 = BigInteger.valueOf(this.zahler);
        BigInteger b2 = BigInteger.valueOf(this.nenner);

        BigInteger gcd = b1.gcd(b2);
        return gcd.intValue();
    }

    /**
     * liefert eine String-Darstellung des Bruches
     * @return eine String-Darstellung des Bruches
     */
    public String toString(){
        return "Zahler: " + this.zahler + " Nenner: " + this.nenner;
    }
    
    @Override
    public int compareTo(Bruch obj){
        double x1 = this.ausrechnen();
        double x2 = obj.ausrechnen();

        if(x1==x2){
            return 0;
        } else if (x1 > x2){
            return -1;
        } else {
            return 1;
        }
    }

}