import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import bank.*;


/**
 * enthaelt die Tests für die Klasse Girokonto
 */
public class KontoTest {

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
    
    @Test
    void testObserverMock() {
    	
    }
}

