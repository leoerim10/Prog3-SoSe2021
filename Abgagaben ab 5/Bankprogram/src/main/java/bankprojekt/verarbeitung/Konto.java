package bankprojekt.verarbeitung;

/**
 * stellt ein allgemeines Konto dar
 */
public abstract class Konto implements Comparable<Konto> {

    /**
     * der Kontoinhaber
     */
    private Kunde inhaber;

    /**
     * die Kontonummer
     */
    private final long nummer;

    /**
     * der aktuelle Kontostand
     */
    private double kontostand;

    /**
     * die Waehrung, die fuer dieses Konto verwendet wird
     */
    private Waehrung waehrung = Waehrung.EUR;

    /**
     * Liefert die vom Konto genutzte Waehrung zurueck
     *
     * @return die verwendete Waehrung
     */
    public Waehrung getAktuelleWaehrung() {
        return waehrung;
    }

    /**
     * setzt den aktuellen Kontostand
     * @param kontostand neuer Kontostand
     */
    protected void setKontostand(double kontostand) {
        this.kontostand = kontostand;
    }

    /**
     * Wenn das Konto gesperrt ist (gesperrt = true), können keine Aktionen daran mehr vorgenommen werden,
     * die zum Schaden des Kontoinhabers wären (abheben, Inhaberwechsel)
     */
    private boolean gesperrt;

    /**
     * Setzt die beiden Eigenschaften kontoinhaber und kontonummer auf die angegebenen Werte,
     * der anfängliche Kontostand wird auf 0 gesetzt.
     *
     * @param inhaber der Inhaber
     * @param kontonummer die gewünschte Kontonummer
     * @throws IllegalArgumentException wenn der Inhaber null
     */
    public Konto(Kunde inhaber, long kontonummer) {
        if(inhaber == null)
            throw new IllegalArgumentException("Inhaber darf nicht null sein!");
        this.inhaber = inhaber;
        this.nummer = kontonummer;
        this.kontostand = 0;
        this.gesperrt = false;
    }

    /**
     * setzt alle Eigenschaften des Kontos auf Standardwerte
     */
    public Konto() {
        this(Kunde.MUSTERMANN, 1234567);
    }

    /**
     * liefert den Kontoinhaber zurück
     * @return   der Inhaber
     */
    public final Kunde getInhaber() {
        return this.inhaber;
    }

    /**
     * setzt den Kontoinhaber
     * @param kinh   neuer Kontoinhaber
     * @throws GesperrtException wenn das Konto gesperrt ist
     * @throws IllegalArgumentException wenn kinh null ist
     */
    public final void setInhaber(Kunde kinh) throws GesperrtException{
        if (kinh == null)
            throw new IllegalArgumentException("Der Inhaber darf nicht null sein!");
        if(this.gesperrt)
            throw new GesperrtException(this.nummer);
        this.inhaber = kinh;

    }

    /**
     * liefert den aktuellen Kontostand
     * @return   double
     */
    public final double getKontostand() {
        return kontostand;
    }

    /**
     * liefert die Kontonummer zurück
     * @return   long
     */
    public final long getKontonummer() {
        return nummer;
    }

    /**
     * liefert zurück, ob das Konto gesperrt ist oder nicht
     * @return true, wenn das Konto gesperrt ist
     */
    public final boolean isGesperrt() {   //Getter, aber eben für booleschen Wert
        return gesperrt;
    }

    /**
     * Erhöht den Kontostand um den eingezahlten Betrag.
     *
     * @param betrag double
     * @throws IllegalArgumentException wenn der betrag negativ ist
     */
    public void einzahlen(double betrag) {
        if (betrag < 0 || Double.isNaN(betrag)) {
            throw new IllegalArgumentException("Falscher Betrag");
        }
        setKontostand(getKontostand() + betrag);
    }

    /**
     * Gibt eine Zeichenkettendarstellung der Kontodaten zurück.
     */
    @Override
    public String toString() {
        String ausgabe;
        ausgabe = "Kontonummer: " + this.getKontonummerFormatiert() + System.getProperty("line.separator");
        ausgabe += "Inhaber: " + this.inhaber;
        ausgabe += "Aktueller Kontostand: " + this.kontostand + " " + this.waehrung.name();
        ausgabe += this.getGesperrtText() + System.getProperty("line.separator");
        return ausgabe;
    }

    /**
     * dient rein didaktischen Zwecken, gehört hier eigentlich nicht her
     */
    public void ausgeben()
    {
        System.out.println(this.toString());
    }

    /**
     * Mit dieser Methode wird der geforderte Betrag vom Konto abgehoben, wenn es nicht gesperrt ist.
     *
     * @param betrag double
     * @throws GesperrtException wenn das Konto gesperrt ist
     * @throws IllegalArgumentException wenn der betrag negativ ist
     * @return true, wenn die Abhebung geklappt hat,
     * 		   false, wenn sie abgelehnt wurde
     */
    public boolean abheben(double betrag) throws GesperrtException{
        if (betrag < 0 || Double.isNaN(betrag)) {
            throw new IllegalArgumentException("Betrag ungültig");
        }
        if(this.isGesperrt())
        {
            GesperrtException e = new GesperrtException(this.getKontonummer());
            throw e;
        }
        if(abhebenGiro(betrag)){
            return true;
        }
        if (abhebenSpar(betrag)){
            return true;
        }
       return false;
    }

    /**
     * hebt den gegebenen Betraf vonm Girokonto ab
     * @param betrag Betrag
     * @return true, wenn die Abhebung geklappt hat
     */
    public abstract boolean abhebenGiro(double betrag);


    /**
     * hebt den gegebenen Betraf vonm Sparkonto ab
     * @param betrag gegebene Betrag
     * @return true, wenn die Abhebung geklappt hat
     */
    public abstract boolean abhebenSpar(double betrag);

    /**
     * sperrt das Konto, Aktionen zum Schaden des Benutzers sind nicht mehr möglich.
     */
    public final void sperren() {
        this.gesperrt = true;
    }

    /**
     * entsperrt das Konto, alle Kontoaktionen sind wieder möglich.
     */
    public final void entsperren() {
        this.gesperrt = false;
    }


    /**
     * liefert eine String-Ausgabe, wenn das Konto gesperrt ist
     * @return "GESPERRT", wenn das Konto gesperrt ist, ansonsten ""
     */
    public final String getGesperrtText()
    {
        if (this.gesperrt)
        {
            return "GESPERRT";
        }
        else
        {
            return "";
        }
    }

    /**
     * liefert die ordentlich formatierte Kontonummer
     * @return auf 10 Stellen formatierte Kontonummer
     */
    public String getKontonummerFormatiert()
    {
        return String.format("%10d", this.nummer);
    }

    /**
     * liefert den ordentlich formatierten Kontostand
     * @return formatierter Kontostand mit 2 Nachkommastellen und Währungssymbol
     */
    /**
     * liefert den ordentlich formatierten Kontostand
     *
     * @return formatierter Kontostand mit 2 Nachkommastellen und Wï¿½hrungssymbol ï¿½
     */
    public String getKontostandFormatiert() {
        return String.format("%10.2f "+ waehrung.name(), this.getKontostand());
    }

    /**
     * Vergleich von this mit other; Zwei Konten gelten als gleich,
     * wen sie die gleiche Kontonummer haben
     * @param other das Vergleichskonto
     * @return true, wenn beide Konten die gleiche Nummer haben
     */
    @Override
    public boolean equals(Object other)
    {
        if(this == other)
            return true;
        if(other == null)
            return false;
        if(this.getClass() != other.getClass())
            return false;
        if(this.nummer == ((Konto)other).nummer)
            return true;
        else
            return false;
    }

    @Override
    public int hashCode()
    {
        return 31 + (int) (this.nummer ^ (this.nummer >>> 32));
    }

    @Override
    public int compareTo(Konto other)
    {
        if(other.getKontonummer() > this.getKontonummer())
            return -1;
        if(other.getKontonummer() < this.getKontonummer())
            return 1;
        return 0;
    }


    /**
     * Erhoeht den Kontostand um den eingezahlten Betrag.
     * @param betrag double
     * @param waehrung die eingezahlt werden soll
     * @throws IllegalArgumentException wenn der betrag negativ ist
     * @throws NullPointerException wenn waehrung = null ist
     */
    public void einzahlen(double betrag, Waehrung waehrung) throws IllegalArgumentException {
        betrag = Waehrung.waehrungZuWaehrung(betrag, waehrung, this.waehrung);
        einzahlen(betrag);
    }


    /**
     * Mit dieser Methode wird der geforderte Betrag vom Konto abgehoben, wenn es nicht gesperrt ist.
     *
     * @param betrag double
     * @param waehrung die verwendet werden soll
     * @return true, wenn die Abhebung geklappt hat,
     * false, wenn sie abgelehnt wurde
     * @throws GesperrtException wenn das Konto gesperrt ist
     * @throws IllegalArgumentException wenn der betrag negativ ist
     * @throws NullPointerException wenn waehrung = null ist
     */
    public boolean abheben(double betrag, Waehrung waehrung) throws GesperrtException {
        betrag = Waehrung.waehrungZuWaehrung(betrag, waehrung, this.waehrung);
        return abheben(betrag);
    }

    /**
     * Aendert die Waehrung mit dem das Konto arbeitet
     *
     * @param neu die neue Waehrung
     * @throws NullPointerException wenn neu = null ist
     */
    public void waehrungswechsel(Waehrung neu) {
        kontostand = Waehrung.waehrungZuWaehrung(kontostand, waehrung, neu);
        this.waehrung = neu;
    }

}