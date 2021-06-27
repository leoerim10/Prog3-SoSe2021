import static org.junit.jupiter.api.Assertions.*;

import bankprojekt.verarbeitung.Bank;
import bankprojekt.verarbeitung.GesperrtException;
import bankprojekt.verarbeitung.Konto;
import bankprojekt.verarbeitung.KontonummerNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.List;

/**
 * tests for class Bank using mockito
 */
public class BankTest{

    /**
     *test to check if all the information from all acounts is delivered
     */
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


    /**
     * test to get the account numbers of all accounts
     */
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


    /**
     * test to delete an account
     */
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

    /**
     * test to get the info on account status
     */
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
        } catch (NoSuchElementException | KontonummerNotFoundException e){
            fail();
        }
    }

    /**
     *test to deposit a sum in an account
     */
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
        } catch (NoSuchElementException | IllegalArgumentException | KontonummerNotFoundException e){
            fail();
        }
    }

    /**
     * test to withdraw some amount from an account
     */
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
        } catch(IllegalArgumentException | NoSuchElementException | GesperrtException | KontonummerNotFoundException e){
            fail();
        }
    }


    /**
     * test to transfer money from one account to another
     */
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
            boolean result = b.geldUeberweisen(num2, num1, 300.00, "title");
            assertTrue(result);
        } catch (IllegalArgumentException | GesperrtException e){
            fail();
        }
    }


}