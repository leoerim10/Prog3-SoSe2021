import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

public class Test {
 
    @Test
    public void testFirstFunction(){
        Function<Double, Double> func = (x) -> Math.pow(x, 2) - 5;
        double res = NullStellenSuche.nullstelleSuchen(-5.0, 0, func);
        assertEquals(-2.24, res, 0.01);
        double res1 = NullStellenSuche.nullstelleSuchen(0, 5.0, func);
        assertEquals(2.24, res1, 0.01);
        
    }

    @Test
    public void testSecondFunction(){
        Function<Double, Double> func = (x) -> Math.pow(Math.E, 3 * x) -7;
        double res = NullStellenSuche.nullstelleSuchen(0, 10.0,func);
        assertEquals(0.64, res, 0.01);
    }

    @Test
    public void testThirdFunction() {
        Function<Double, Double> func= (x) -> (5-x)/(Math.pow(x, 3) + 2);
        double res = NullStellenSuche.nullstelleSuchen(0, 10.0,func);
        assertEquals(5.0, res, 0.01);
    }

    @Test(expected = Exception.class)
    public void testFourthFunction(){
        Function <Double, Double> func = (x) -> Math.pow(x, 2) + 1;
        double res = NullStellenSuche.nullstelleSuchen(0, 10.0,func);
    }
}
