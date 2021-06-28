package bankprojekt.verarbeitung;

/**
 * bietet das Erstellen con neuen Konten an
 */
public abstract class Kontofabrik {

    /**
     * erstellt ein nueues Konto
     * @param inhaber des Kontos (als Kundenobjekt
     * @param kontonr die zu vergebene Kontonummer des neuen Kontos
     * @return das neue Konto
     * @throws IllegalArgumentException, wenn inhaber null ist oder Kontoart nicht vorhanden ist
     */
    public abstract Konto erstellen(Kunde inhaber, long kontonr);
}