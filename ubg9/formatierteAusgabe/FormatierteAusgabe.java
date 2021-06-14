import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Scanner;

public class FormatierteAusgabe {
    public static void main(String[] args) {
        try{
            FileWriter fileWriter = new FileWriter("./output.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);       
            
            // 1.
            Scanner s = new Scanner(System.in);
            System.out.print("Enter an Integer: ");
            int num = s.nextInt();
            
            printWriter.printf("%d\n", num);

            // 2.


            // 3.
            printWriter.printf("%,d\n", num);

            // 4.
            System.out.print("Enter a Double: ");
            double d = s.nextDouble();
            printWriter.printf("%f\n", d);

            // 5.
            printWriter.printf("%.2f\n", d);

            // 6.
            printWriter.printf("%e\n", d);

            // 7.
            printWriter.printf("%.3f\n", d);
            
            // 8.
            printWriter.printf("%%\n");

            // 9.
            LocalDate heute = LocalDate.now();
            printWriter.printf("%s\n", heute.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));

            // 10.
            printWriter.printf("%s\n", heute.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.ITALIAN)));

            // 11.
            LocalTime jetzt = LocalTime.now();
            DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("hh:mm:ss a");
            printWriter.printf("%s\n", jetzt.format(dtf3));

            s.close();
            printWriter.close();
            fileWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
       
    }
}