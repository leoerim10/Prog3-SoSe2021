package bankprojekt.verarbeitung;

/**
 * Eine Liste der zur Verfügung stehendehn Währungen mit festen Euro-Wechselkurs
 */

public enum Waehrung {
    /** Euro */
    EUR(1),
    /** Bulgarische Leva */
    BGN(1.95583),
    /** Litauische Litas */
    LTL(3.4528),
    /** Konvertible Mark */
    KM(1.95583);

    private double kursZuEuro;

    private Waehrung(double kursZuEuro) {
        this.kursZuEuro = kursZuEuro;
    }

    /**
     * Berechnet Euro -> andere Waehrung
     *
     * @param betrag gegebene Betrag
     * @return der umgerechnete Betrag
     */
    public double euroInWaehrungUmrechnen(double betrag) {
        return betrag * kursZuEuro;
    }

    /**
     * Berechnet Waehrung -> zu Euro
     *
     * @param betrag der Geldbetrag
     * @return der umgerechnete Geldbetrag
     */
    public double waehrungInEuroUmrechnen(double betrag) {
        return betrag / kursZuEuro;
    }

    /**
     * Ein kleiner Wrapper fuer euroInWaehrungUmrechnen, waehrungInEuroUmrechnen, der es einfacher gestaltet
     * einen Betrag in eine beliebige Waehrung umzurechnen.
     * Mach die beiden Methoden zwar obsolet, sie werden jedoch von der Aufgarbe gefordert.
     *
     * @param betrag der umgerechnet werden soll
     * @param von Waehrung von der umgerechnet werden soll
     * @param zu Waehrung in die umgerechnet werden soll
     * @return das Ergebnis
     * @throws NullPointerException wenn von oder zu = null ist
     */
    public static double waehrungZuWaehrung(double betrag, Waehrung von, Waehrung zu) throws NullPointerException{
        if(von == null || zu == null){
            throw new NullPointerException();
        }
        return zu.euroInWaehrungUmrechnen(von.waehrungInEuroUmrechnen(betrag));
    }
}
