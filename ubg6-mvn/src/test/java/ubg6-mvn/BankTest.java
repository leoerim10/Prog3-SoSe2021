import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import java.util.List;
import java.util.NoSuchElementException;
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
    public void testGetAlleKontonummern(){
        System.out.println("test getAlleKontonummern()");
        Bank b = new Bank(123456L);
        Konto k1 = Mockito.mock(Konto.class);
        Konto k2 = Mockito.mock(Konto.class);
        Konto k3 = Mockito.mock(Konto.class);
        Mockito.when(k1.getKontonummer()).thenReturn(1L);
        Mockito.when(k2.getKontonummer()).thenReturn(2L);
        Mockito.when(k3.getKontonummer()).thenReturn(3L);

        b.mockEinfuegen(k1);
        b.mockEinfuegen(k2);
        b.mockEinfuegen(k3);

        List<Long> l = b.getAlleKontonummern();
        assertEquals(3, l.size());
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
        assertTrue(geklappt);
    }

    @Test
    public void testGetKontoStand() throws Exception{
        System.out.println("Test getKontostand()");
        Bank b = new Bank(123456L);
        Konto k = Mockito.mock(Konto.class);
        Mockito.when(k.getKontonummer()).thenReturn(1L);
        Mockito.when(k.getKontostand()).thenReturn(500.00);
        long num = b.mockEinfuegen(k);
        try {
            double kontostand = b.getKontostand(num);
            assertEquals(500.00, b.getKontostand(num));
        } catch (NoSuchElementException e){
            fail();
        }
    }

    @Test
    public void testGeldEinzahlen() throws Exception{
        System.out.println("Test geldEinzahlen()");
        Bank b = new Bank(123456L);
        Konto k = Mockito.mock(Konto.class);
        Mockito.when(k.getKontonummer()).thenReturn(1L);
        Mockito.when(k.getKontostand()).thenReturn(500.00);
        long num = b.mockEinfuegen(k);
        try{
            b.geldEinzahlen(num, 300.00);
        } catch (NoSuchElementException | IllegalArgumentException e){
            fail();
        }
    }

    @Test
    public void testGeldAbheben() throws Exception {
        System.out.println("Test geldAbheben()");
        Bank b = new Bank(123456L);
        Konto k = Mockito.mock(Konto.class);
        Mockito.when(k.getKontonummer()).thenReturn(1L);
        Mockito.when(k.getKontostand()).thenReturn(500.00);
        long num = b.mockEinfuegen(k);
        try {
            assertFalse(b.geldAbheben(num, 300.00));
        } catch(IllegalArgumentException | NoSuchElementException | GesperrtException e){
            fail();
        }        
    }


    @Test
    public void testGeldUeberweisen(){
        System.out.println("Test geldAbheben()");
        Bank b = new Bank(123456L);
        Konto k = Mockito.mock(Konto.class);
        Mockito.when(k.getKontonummer()).thenReturn(1L);
        Mockito.when(k.getKontostand()).thenReturn(500.00);
        Konto k1 = Mockito.mock(Konto.class);
        Mockito.when(k1.getKontonummer()).thenReturn(2L);
        Mockito.when(k1.getKontostand()).thenReturn(1000.00);
        long num1 = b.mockEinfuegen(k);
        long num2 = b.mockEinfuegen(k1);
        try {
            boolean result = b.geldUberweisen(num2, num1, 300.00, "title");
            assertTrue(result, true);
        } catch (IllegalArgumentException e){
            fail();
        }
    }
}