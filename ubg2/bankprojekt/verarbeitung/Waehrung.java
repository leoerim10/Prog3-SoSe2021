/**
 *ordnet die Waehrungen zu und rechnet sie um
 * @author Sameer Dhimal 569076,  Wojciech Maximilan Frackowski 576278
 */
public enum Waehrung {
    EUR(1.0), BGN(1.95583), KM(1.95583), LTL(3.4528);

    private double x;
    Waehrung(double x){
        this.x = x;
    }

    /**
     *rechnet in Euro angegebenen Betrag in die jeweilige Waehrung um
     * @param betrag betrag umzurechnen
     * @return umgerechnete Betrag
     */
    public double euroInWaehrungUmrechnen(double betrag){
        return betrag * this.x;
    }

    /**
     *rechnet in this-Waehrung angegebenen Betrag in Euro um
     * @param betrag gegebene Betrag umzurechnen
     * @return umgerechnete Betrag
     */
    public double waehrungInEuroUmrechnen(double betrag){
        return betrag / this.x;
    }
}