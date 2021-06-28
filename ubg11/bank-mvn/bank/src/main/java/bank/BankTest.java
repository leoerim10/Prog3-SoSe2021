import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BankTest {

	@Test
	public void Kontofabriktest() {
		Kontofabrik fabrik = new Kontofabrik();
		Bank bank = new Bank(1L);
		Kunde kunde = new Kunde();
		bank.kontoErstellen(fabrik, kunde, 1);
		bank.kontoErstellen(fabrik, kunde, 2);
		
		assertEquals(2, bank.konten.size());
	}
}
