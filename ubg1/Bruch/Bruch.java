import java.math.BigInteger;

public class Bruch {
    private int zahler;
    private int nenner;

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

    public Bruch multiplizieren(Bruch b){
        Bruch n = new Bruch(this.zahler*b.zahler, this.nenner*b.nenner);
        return n;
    }

    public double ausrechnen(){
        double div = (double)this.zahler/(double)this.nenner;
        return div;
    }

    public void kuerzen(){
        int gcd = getGCD();
        int newNenner = this.nenner / gcd;
        int newZahler = this.zahler / gcd;

        this.nenner = newNenner;
        this.zahler = newZahler;
    
    }

    public Bruch kehrwert(){
        Bruch n = new Bruch(this.nenner, this.zahler);
        return n;
    }

    public Bruch dividieren(Bruch b){
        return multiplizieren(kehrwert());
    }

    public int getGCD(){
        BigInteger b1 = BigInteger.valueOf(this.zahler);
        BigInteger b2 = BigInteger.valueOf(this.nenner);

        BigInteger gcd = b1.gcd(b2);
        return gcd.intValue();
    }

    public String toString(){
        return "Zahler: " + this.zahler + " Nenner: " + this.nenner;
    }



}