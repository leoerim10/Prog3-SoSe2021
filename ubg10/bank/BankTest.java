import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTest {

    private Bank b;

    @BeforeEach
    public void setUp() throws Exception {
    	Kunde k = new Kunde();
    	Kunde k1 = new Kunde();
        b = new Bank(1234L);
        b.girokontoErstellen(k);
        b.girokontoErstellen(k1);
        
    }

    @Test
    public void testClone(){
        Bank bClone = b.clone();
        assertEquals(1234L, bClone.getBankleitzahl());
        bClone.geldEinzahlen(1, 10);
        assertEquals(10.0, bClone.getKontostand(1L), 1.0);
        assertEquals(0.0, b.getKontostand(1L), 1.0);
        
    }
}