package bankprojekt.verarbeitung;


public class KontonummerNotFoundException extends Throwable {

    public KontonummerNotFoundException(long kontonummer)
    {
        super("Zugriff auf nicht gefundene Konto mit Kontonummer " + kontonummer);
    }

}
