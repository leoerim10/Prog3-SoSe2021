
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import bankprojekt.verarbeitung.GesperrtException;
import bankprojekt.verarbeitung.Girokonto;
import bankprojekt.verarbeitung.Waehrung;

/**
 * enthaelt die Tests für die Klasse Girokonto
 */
public class GiroTest {

    /**
     * prueft den aktuellen Dispo
     */
    @Test
    void testGetDispo() {
        Girokonto g = new Girokonto();
        double dispo = g.getDispo();
        Assertions.assertTrue(dispo == 500);
    }

    /**
     * prueft, on die aktuelle Waehrung auf BGN ist
     */
    @Test
    void testWaehrungsWechsel() {
        Girokonto g = new Girokonto();
        g.waehrungswechsel(Waehrung.BGN);
        Assertions.assertTrue(g.getAktuelleWaehrung() == Waehrung.BGN);
    }

    /**
     * zahlt zuerst 500 auf das Konto
     * hebt 500 dann ab
     * gibt true zurueck, wenn der angegebene Betrag erfolgreich abgehoben wurde
     * @throws GesperrtException wenn Konto gesperrt ist
     */
    @Test
    void testAbheben1() throws GesperrtException {
        Girokonto g = new Girokonto();
        g.einzahlen(500);
        Assertions.assertTrue(g.abheben(500) == true);
    }

    /**
     * prueft, ob es moeglich ist, 500 abzuheben
     * gibt true zurueck, wenn es erfolgreich abgehoben wurde
     * @throws GesperrtException wenn Konto gesperrt ist
     */
    @Test
    void testAbheben2() throws GesperrtException {
        Girokonto g = new Girokonto();
        Assertions.assertTrue(g.abheben(500) == true);
    }

}