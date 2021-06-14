
import java.util.function.Function;

public class NullStellenSuche {
    public static double nullstelleSuchen(double a, double b, Function<Double, Double> function) throws Exception{
        if(function.apply(a) * function.apply(b) >= 0){
            throw new Exception("couldn't find the conversion point, so this function does not have any root");
        }
        double m = (a+b)/2;
        for(double i = a; i < b; i = i + 0.01){
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

    public static void main(String[] args) {
        Function <Double, Double> func = (x) -> Math.pow(x, 2) + 1;
        try{
            double res = NullStellenSuche.nullstelleSuchen(0, 10.0,func);
            System.out.println(res);
        } catch(Exception e){
            System.out.println(e);
        }
        
    }
}