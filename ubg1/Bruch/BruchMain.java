import java.util.ArrayList;
import java.util.Collections;

public class BruchMain{
    public static void main(String[] args) {

        ArrayList<Bruch> list = new ArrayList<Bruch>();
        Bruch b1 = new Bruch(1,6);
        Bruch b2 = new Bruch(1,2);
        Bruch b3 = new Bruch(1,4);
        Bruch b4 = new Bruch(1,3);
        Bruch b5 = new Bruch(1,5);

        list.add(b1);
        list.add(b2);
        list.add(b3);
        list.add(b4);
        list.add(b5);


        // sortiert aufsteigend  //
        System.out.println("First Comparator:");


        Collections.sort(list, new FirstComparator());

        for(Bruch b: list){
            System.out.println(b);
        }


        // sortiert absteigend //
        System.out.println("Second Comparator:");

        Collections.sort(list, new SecondComparator());

        for(Bruch b: list){
            System.out.println(b);
        }

    }
}