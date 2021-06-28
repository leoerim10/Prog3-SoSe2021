package bank;

public enum Waehrung {
    EUR(1.0), BGN(1.95583), KM(1.95583), LTL(3.4528);

    private double x;
    private Waehrung(double x){
        this.x = x;
    }

    public double euroInWaehrungUmrechnen(double betrag){
        return betrag * this.x;
    }

    public double waehrungInEuroUmrechnen(double betrag){
        return betrag / this.x;  
    }
    
    public static double waehrungZuWaehrung(double betrag, Waehrung von, Waehrung zu) throws NullPointerException{
        if(von == null || zu == null){
            throw new NullPointerException();
        }
        return zu.euroInWaehrungUmrechnen(von.waehrungInEuroUmrechnen(betrag));
    }
}