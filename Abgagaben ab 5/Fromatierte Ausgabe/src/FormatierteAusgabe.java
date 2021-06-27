import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Scanner;

public class FormatierteAusgabe {
    public static void main(String[] args) throws IOException{
        try{
            FileWriter fileWriter = new FileWriter("output.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);

            // 1. ganze Zahl
            Scanner s = new Scanner(System.in);
            System.out.print("Enter an Integer: ");
            Long num = s.nextLong();
            printWriter.printf("%d\n", num);

            // 2. zahl mit 10 Stellen
            printWriter.printf("%010d\n", num);

            // 3. Vorzeichen und Tausendertrennzeichen
            printWriter.printf("%,010d\n", num);

            // 4. Zahl mit Nachkommaanteil
            System.out.print("Enter a Double: ");
            double d = s.nextDouble();
            printWriter.printf("%f\n", d);

            // 5. Vorzeichen mit 2 Nachkommastellen
            printWriter.printf("%.2f\n", d);

            // 6. Angabe des Exponents
            printWriter.printf("%e\n", d);

            // 7. Punkt als Dezimaltrennzeichen
            printWriter.printf("%.3f\n", d);

            // 8. Prozentzeichen
            printWriter.printf("%%\n");

            // 9. aktuelles Datum
            LocalDate heute = LocalDate.now();
            printWriter.printf("%s\n", heute.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));

            // 10. aktuelles Datum auf Italienisch
            printWriter.printf("%s\n", heute.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.ITALIAN)));

            // 11. aktuelle Uhrzeit
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