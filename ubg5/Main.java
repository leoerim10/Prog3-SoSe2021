/*
* @author Sameer Dhimal 569076, Wojciech Maximilan Frackowski 576278
*/

import java.sql.Time;
import java.util.Date;

public class Main {
    public static void main(String[] args) {    
        String s1 = "A";
        String s2 = "D";
        Intervall<String> i1 = new Intervall<String>(s1, s2);
        Intervall<String> test1 = new Intervall<String>("A", "C");

        System.out.println("*** Intervall 1 ***");
        System.out.println(i1);
        System.out.println("IsLeer: " + i1.isLeer());
        System.out.println("Enthaelt E: " + i1.enthaelt("E"));
        System.out.println("Schnitt: " + i1.schnitt(test1));

        Date d1 = new Date(1000000000);
        Date d2 = new Date();
        Date d3 = new Date(2000000000);
        Date d4 = new Date(1000);

        Intervall<Date> i2 = new Intervall<Date>(d1, d2);
        Intervall<Date> test2 = new Intervall<Date>(d1, d4);
        System.out.println("*** Intervall 2 ***");
        System.out.println(i2);
        System.out.println("IsLeer: " + i2.isLeer());
        System.out.println("Enthaelt d3: " + i2.enthaelt(d3));
        System.out.println("Schnitt: " + i2.schnitt(test2));

        
        Time t1 = new Time(1000);
        Time t2 = new Time(5000);
        Time t3 = new Time(3000);
        Time t4 = new Time(6000);
        Intervall<Time> i3 = new Intervall<Time>(t1, t2);
        Intervall<Time> test3 = new Intervall<Time>(t1, t4);
        System.out.println("*** Intervall 3 ***");
        System.out.println(i3);
        System.out.println("IsLeer: " + i3.isLeer());
        System.out.println("Enthaelt t3: " + i3.enthaelt(t3));
        System.out.println("Schnitt: " + i3.schnitt(test3));


    }
}