package bankprojekt.verarbeitung;

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
}