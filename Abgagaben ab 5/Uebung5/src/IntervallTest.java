
import java.sql.Time;
import java.util.Date;

public class IntervallTest {

    public static void main(String[] args) {

        /**
         * testing String interval for all three methods
         */
        Intervall<String> i1 = new Intervall<String>("B", "E");
        System.out.println("is empty: " + i1.isLeer());
        System.out.println("contains " + i1.enthaelt("B"));

        Intervall<String> i2 = new Intervall<String>("C", "F");
        System.out.println(i1.schnitt(i2));

        Intervall<String> i3 = new Intervall<String>("A", "F");
        System.out.println(i1.schnitt(i3));

        Intervall<String> i4 = new Intervall<String>("A", "D");
        Intervall<String> i5 = new Intervall<String>("A", "C");
        System.out.println("test for A-D and A-C:  " +i4.schnitt(i5));

        /**
         * Date intervals
         */
        Date d1 = new Date(1000000000);
        Date d2 = new Date();
        Date d3 = new Date(2000000000);
        Intervall<Date> d1d2 = new Intervall<Date>(d1,d2);
        Intervall<Date> d3d2 = new Intervall<Date>(d3,d2);
        Intervall<Date> Dateintervall = d1d2.schnitt(d3d2);
        System.out.println("Overlap between two date intervals: " + Dateintervall);

        /**
         * Date and time intervals
         */
        Time t1 = new Time(5000);
        Time t2 = new Time(7000);
        Intervall<Time> TimeInterval = new Intervall<Time>(t1, t2);
        System.out.println("The interval between date and time: " + Dateintervall.schnitt(TimeInterval));
    }
}
