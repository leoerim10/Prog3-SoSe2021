import java.util.Comparator;

public class SecondComparator implements Comparator<Bruch>{
    public int compare(Bruch a, Bruch b){
        double x1 = a.ausrechnen();
        double x2 = b.ausrechnen();

        if(x1==x2){
            return 0;
        } else if (x1 > x2){
            return -1;
        } else {
            return 1;
        }
    }
} 