import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InOrder;
import org.mockito.Mockito;
import java.util.List;

public class BankTest{

    @Test
    public void testGetAlleKonten(){
        System.out.println("Test getAlleKonten()");
        Bank b = new Bank(123456L);
        Konto k = Mockito.mock(Konto.class);
        Konto k1 = Mockito.mock(Konto.class);
        Mockito.when(k.getKontonummer()).thenReturn(1L);
        Mockito.when(k.getKontostand()).thenReturn(500.00);
        Mockito.when(k1.getKontonummer()).thenReturn(100L);
        Mockito.when(k1.getKontostand()).thenReturn(500.00);
        b.mockEinfuegen(k);
        b.mockEinfuegen(k1);
        System.out.println(b.getAlleKonten());
    }

    @Test
    public void testKontoLoeschen(){
        System.out.println("Test kontoLoeschen()");
        Bank b = new Bank(123456L);
        Konto k = Mockito.mock(Konto.class);
        Konto k1 = Mockito.mock(Konto.class);
        Mockito.when(k.getKontonummer()).thenReturn(1L);
        Mockito.when(k.getKontostand()).thenReturn(500.00);
        Mockito.when(k1.getKontonummer()).thenReturn(100L);
        Mockito.when(k1.getKontostand()).thenReturn(500.00);
        b.mockEinfuegen(k);
        b.mockEinfuegen(k1);

        boolean geklappt = b.kontoLoeschen(100L);
        System.out.println(b.getAlleKonten());
        assertTrue(geklappt);
    }

    @Test
    public void testGetKontoStand() throws Exception{
        System.out.println("Test getKontostand()");
        Bank b = new Bank(123456L);
        Konto k = Mockito.mock(Konto.class);
        Mockito.when(k.getKontonummer()).thenReturn(1L);
        Mockito.when(k.getKontostand()).thenReturn(500.00);
        b.mockEinfuegen(k);
        System.out.println(b.getKontostand(1L));
    }

   @Test
   public void testGeldEinzahlen() {
        System.out.println("Test geldEinzahlen()");
        Bank b = new Bank(123456L);
        Konto k = Mockito.mock(Konto.class);
        Mockito.when(k.getKontonummer()).thenReturn(1L);
        Mockito.when(k.getKontostand()).thenReturn(500.00);
        b.mockEinfuegen(k);
        b.gen
    }
}