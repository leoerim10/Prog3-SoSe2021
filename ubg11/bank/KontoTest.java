import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * enthaelt die Tests für die Klasse Girokonto
 */
public class KontoTest {

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

    @Test
    void testObserver() {
        KontoObserver observer = new KontoObserver();
        Girokonto konto = new Girokonto();
        konto.anmelden(observer);
        konto.einzahlen(500.00);
        Kunde newOwner = new Kunde("Julie", "Mueller", "Some-Addresse", LocalDate.now());
        try {
        konto.setInhaber(newOwner);
        } catch(GesperrtException e) {
        	e.printStackTrace();
        }
        try {
			konto.abheben(300.00);
		} catch (GesperrtException e) {
			e.printStackTrace();
		}
       }

}

