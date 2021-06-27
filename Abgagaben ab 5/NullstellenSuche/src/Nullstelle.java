import java.util.function.Function;

/**
 * rechnet der Nullstelle einer Funktion
 * @author Sameer Dhimal 569076, Wojciech Maximilan Frackowski 576278
 */
public class Nullstelle {

    /**
     * sucht die Nullstelle einer Funktion
     * @param a Intervall a
     * @param b Intervall b
     * @param function gegebene Funktion und deren Nullstelle wird gesucht
     * @return Nullstell der Funktion
     */
    public static double nullstelleSuchen(double a, double b, Function<Double, Double> function) throws Exception {
        if(function.apply(a) * function.apply(b) >= 0){
            // here exception should be thrown but could not think of any
            throw new Exception("couldn't find the conversion point, so this function does not have any root");
        }
        double m = (a+b)/2;
        while(Math.abs(b-a) >= 0.01){
            m = (a+b)/2;
            if (function.apply(m) == 0){
                return m;
            }
            else if (function.apply(m) * function.apply(a) < 0){
                b = m;
            }
            else{
                a = m;
            }
        }
        return Math.round(m*100.0)/100.0;
    }

}
