package bankprojekt.verarbeitung;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BankTest {

    /**
     * Kontoerzeugung Test
     */
    @Test
    public void Kontofabriktest() {
        Kontofabrik fabrik = new Kontofabrik();
        Bank bank = new Bank(1L);
        Kunde kunde = new Kunde();
        bank.kontoErstellen(fabrik, kunde, 1); // fuer Sparbuch
        bank.kontoErstellen(fabrik, kunde, 2); // fuer GiroKonto

        assertEquals(2, bank.getKonten().size());
    }
}