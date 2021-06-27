import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;

/**
 * TestKlasse fuer klasse Nullstelle
 * @author Sameer Dhimal 569076, Wojciech Maximilan Frackowski 576278
 */
public class NullstelleTest {

    /**
     * f(x) = x² - 5
     * @throws Exception wenn kein Vorzeichenwechsel der Funktion vorhanden ist
     */
    @Test
    public void testFirstFunction() throws Exception {
        Function<Double, Double> func = (x) -> Math.pow(x, 2) - 5;
        double res = Nullstelle.nullstelleSuchen(-5.0, 0, func);
        assertEquals(-2.24, res, 0.01);
        double res1 = Nullstelle.nullstelleSuchen(0, 5.0, func);
        assertEquals(2.24, res1, 0.01);
    }


    /**
     * g(x) = e^3x -7
     * @throws Exception wenn kein Vorzeichenwechsel der Funktion vorhanden ist
     */
    @Test
    public void testSecondFunction() throws Exception {
        Function<Double, Double> func = (x) -> Math.pow(Math.E, 3 * x) -7;
        double res = Nullstelle.nullstelleSuchen(0, 10.0,func);
        assertEquals(0.64, res, 0.02);
    }

    /**
     * h(x) = (5-x)/(x³ +2)
     * @throws Exception wenn kein Vorzeichenwechsel der Funktion vorhanden ist
     */
   @Test
    public void testThirdFunction() throws Exception {
        Function<Double, Double> func= (x) -> (5-x)/(Math.pow(x, 3) + 2);
        double res = Nullstelle.nullstelleSuchen(0, 10.0,func);
        assertEquals(5.0, res, 0.01);
    }

    /**
     * k(x) = x² +1
     * @throws Exception wenn kein Vorzeichenwechsel der Funktion vorhanden ist
     */
    @Test(expected = Exception.class)
    public void testFourthFunction() throws Exception {
        Function <Double, Double> func = (x) -> Math.pow(x, 2) + 1;
        double res = Nullstelle.nullstelleSuchen(0, 10.0,func);
    }

}
