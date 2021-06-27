package bankprojekt.verarbeitung;

/**
 * erstellt ein neues Bankkonto, entweder Giro oder Sparkonto
 */
public class Kontofabrik {
    private final int SPARBUCH = 1;
    private final int GIROKONTO = 2;

    /**
     * erzeugt ein neues Sparbuch or GiroKonto
     * @param auswahl Auswahl für Kontoart
     * @param inhaber Ihnaber
     * @param nummer Konto nummer
     * @return ein neues Konto
     */
    public Konto erzeugen(int auswahl, Kunde inhaber, long nummer) {
        Konto k = null;
        switch (auswahl) {
            case SPARBUCH:
                k = new Sparbuch(inhaber, nummer);
                break;
            case GIROKONTO:
                k = new Girokonto(inhaber, nummer, 500.00);
                break;
            default:
        }

        return k;
    }
}