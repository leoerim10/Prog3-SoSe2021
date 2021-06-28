package bankprojekt.verarbeitung;

/**
 * eine Kontofabrik, die Girokonten erstellen kann
 */
public class GiroFabrik extends Kontofabrik{


    @Override
    public Konto erstellen(Kunde inhaber, long kontonr) {
        Konto neu;
        neu = new Girokonto(inhaber, kontonr, 500);
        return neu;
    }
}
