package bankprojekt.verarbeitung;

import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.lang.Cloneable;

/**
 * verwaltet verschiedene Kontoarten
 * @author Sameer Dhimal 569076, Wojciech Maximilan Frackowski 576278
 */
public class Bank implements Cloneable, Serializable{
    /**
     * erste verfuegbare Kontonummer
     */
    private static final long FIRST_KONTONUMMER = 0L;

    /**
     * letzte verfuegbare Kontonummer
     */
    private static final long LAST_KONTONUMMER = 10L;

    /**
     * die Bankleitzahl
     */
    private long bankleitzahl;

    /**
     * alle Konten in einer TreehMap (immer sortiert -> lastKey() moeglich)
     */
    private TreeMap<Long, Konto> konten;

    /**
     * Erstellt eine neue Bank
     *
     * @param bankleitzahl
     * Bankleitzahl der Bank
     */
    public Bank(long bankleitzahl) {
        this.bankleitzahl = bankleitzahl;
        this.konten = new TreeMap<>();
    }

    /**
     * aktuelle Banktleitzahl
     */
    public long getBankleitzahl() {
        return bankleitzahl;
    }

    public TreeMap<Long, Konto> getKonten() {
        return konten;
    }

/**
     * erstellt ein Girokonto fuer den angegebenen Kunden
     * @param inhaber Kunde
     * @return die neue vergebene Griokontonummer
     */
   /* public long girokontoErstellen(Kunde inhaber) {
        long neueKontonummer = getNeueKontonummer();
        Girokonto girokonto = new Girokonto(inhaber, neueKontonummer, 1000);
        konten.put(neueKontonummer, girokonto);
        return neueKontonummer;
    }*/

    /**
     * gibt eine neue Kontonummer zurueck
     *
     * @return neue Kontonummer
     */
    private long getNeueKontonummer() {
        if (konten.isEmpty())
            return FIRST_KONTONUMMER;
        else
            return konten.lastKey() + 1;
    }

    /**
     * erstellt ein Sparbuch fuer den angegebenen Kunden
     * @param inhaber Kunde
     * @return die neue vergebene Sparkontonummer
     */
   /* public long sparbuchErstellen(Kunde inhaber){
        long neueKontonummer = getNeueKontonummer();
        Sparbuch sparbuch = new Sparbuch(inhaber, neueKontonummer);
        konten.put(neueKontonummer, sparbuch);
        return neueKontonummer;
    }*/

    /**
     * liefert eine Auflistung von Kontoinformationen aller Konten
     */
    public String getAlleKonten(){
        StringBuilder sb = new StringBuilder();
        for (Konto konto : konten.values()) {
            sb.append(konto.getKontonummer() + ":" + konto.getKontostand() + System.lineSeparator());
        }
        return sb.toString();
    }


    /**
     * liefert eine Auflistung von Kontoinformationen aller Konten (mindestens
     * Kontonummer und Kontostand) mittels der Stream API
     *
     * @return Auflistung der Kontoinformationen aller Konten
     */
    public String getAlleKonten2() {
        StringBuilder sb = new StringBuilder();
        BinaryOperator<String> reduceToString = (a, b) -> a + b + System.getProperty("line.separator");
        sb.append(System.lineSeparator());
        sb.append(
                konten
                .values()
                .stream()
                .map(konto -> konto.toString())
                .reduce("", reduceToString));
        return sb.toString();
    }



    /**
     * liefert eine Liste aller gültigen Kontonummern in der Bank
     * @return die Liste
     */
    public List<Long> getAlleKontonummern(){
        return new LinkedList(konten.keySet());
    }


    /**
     * Testet ob eine Kontonummer in der Kontenliste konten existiert, falls nciht
     * wird eine KontonummerNotFoundException geworfen
     *
     * @param kontonummer
     * Kontonummer die getestet werden soll
     * @throws KontonummerNotFoundException, wenn Kontonummer nicht gefunden wurde
     */
    private void testIfKontonummerExists(long kontonummer) throws KontonummerNotFoundException {
        if (!konten.containsKey(kontonummer)) {
            throw new KontonummerNotFoundException(kontonummer);
        }
    }


    /**
     * hebt den Betrag vom Konto mit der Nummer
     * @param vonKontonummer Konto, von dem der Betrag abgehoben wird
     * @param betrag Betrag
     * @return true wenn die Abhenung geklappt hat
     * @throws IllegalArgumentException fuer ungueltigen Betrag
     */
    public boolean geldAbheben(long vonKontonummer, double betrag)
            throws KontonummerNotFoundException, GesperrtException {
        testIfKontonummerExists(vonKontonummer);
        Konto k = konten.get(vonKontonummer);
        return k.abheben(betrag);
    }


    /**
     * zahlt den angegebenen Betrag auf das Konto
     * @param aufKontonummer Konto, auf das der Betrag eingezahlt wird
     * @param betrag Betrag
     * @throws IllegalArgumentException fuer ungueltigen Betrag
     */
    public void geldEinzahlen(long aufKontonummer, double betrag) throws IllegalArgumentException, KontonummerNotFoundException {
        testIfKontonummerExists(aufKontonummer);
        Konto k = konten.get(aufKontonummer);
        k.einzahlen(betrag);
    }

    /**
     * loescht das Konto mit der angegebenen nummer
     * @param kontonummer nummer die Nummer
     * @return true, wenn das Konto erfolgreich geloescht wird
     */
    public boolean kontoLoeschen(long kontonummer) {
        if (!konten.containsKey(kontonummer)) {
            return false;
        }
        konten.remove(kontonummer);
        return true;
    }

    /**
     * liefert den Kontostand des Kontos
     * @param kontonummer mit dieser Nummer
     * @return Kontostand
     */
    public double getKontostand(long kontonummer) throws KontonummerNotFoundException {
        testIfKontonummerExists(kontonummer);
        Konto k = konten.get(kontonummer);
        return k.getKontostand();
    }

    /**
     * Ueberweist den genannten Betrag vom Girokonto mit der Nummer vonKontonummer
     * zum Girokonto mit der Nummer nachKontonummer und gibt zurueck, ob die
     * Ueberweisung geklappt hat (nur bankinterne Ueberweisungen!)
     *
     * @param vonKontonummer
     * Kontonummer von dem der Betrag ueberwiesen werden soll
     * @param nachKontonummer
     * Kontonummer auf den der Betrag ueberwiesen werden soll
     * @param betrag
     * betrag der ueberwiesen werden soll
     * @param verwendungszweck
     * Verwendungszweck der Ueberweisung
     * @return true, wenn Ueberweisung geklappt hat
     * @throws GesperrtException
     * wenn eines der Konten gesperrt ist
     * @throws IllegalArgumentException
     * wenn Betrag ungültig ist oder der Verwednungszweck null
     */
    public boolean geldUeberweisen(long vonKontonummer, long nachKontonummer, double betrag, String verwendungszweck) throws GesperrtException {
        if (!konten.containsKey(vonKontonummer) || !konten.containsKey(nachKontonummer)) {
            return false;
        }
        // hole die Konten aus der Kontenliste
        Konto tmpKontoVon = konten.get(vonKontonummer);
        Konto tmpKontoNach = konten.get(nachKontonummer);
        // pruefe ob es sich bei beiden Konten um überweisungsfähige // Konten handelt
        if (!(tmpKontoVon instanceof Ueberweisungsfaehig) || !(tmpKontoNach instanceof Ueberweisungsfaehig)) {
            return false;
        }
        Ueberweisungsfaehig kontoVon = (Ueberweisungsfaehig) tmpKontoVon;
        Ueberweisungsfaehig kontoNach = (Ueberweisungsfaehig) tmpKontoNach;

        if (!kontoVon.ueberweisungAbsenden(betrag, tmpKontoNach.getInhaber().getName(), nachKontonummer, getBankleitzahl(), verwendungszweck)) {
            return false;
        }
        // ueberweisen hat funktioniert, fuehre ueberweisungEmpfangen beim Empfaenger aus und gib true zurueck
        kontoNach.ueberweisungEmpfangen(betrag, tmpKontoVon.getInhaber().getName(), vonKontonummer, getBankleitzahl(),
                verwendungszweck);
        return true;
    }

    /**
     * Diese Methode gibt zurueck ob eine Kontonummer gesperrt ist
     *
     * @param kontonummer
     * Kontonummer die geprueft werden soll
     * @return true wenn Konto gesperrt ist
     * @throws KontonummerNotFoundException
     * wenn Kontonummer nicht gefunden wurde
     */
    public boolean isGesperrt(long kontonummer) throws KontonummerNotFoundException {
        testIfKontonummerExists(kontonummer);
        Konto k = konten.get(kontonummer);
        return k.isGesperrt();
    }

   public long mockEinfuegen(Konto k){
        konten.put(k.getKontonummer(), k);
        return k.getKontonummer();
    }



    /**
     * sperrt alle Konten, deren Kontostand im Minus ist
     */
    public void pleitegeierSperren() {
        Predicate<Konto> filterKontostandImMinus = konto -> konto.getKontostand() < 0;
        Consumer<Konto> actionKontoSperren = konto -> konto.sperren();
        konten
                .values()
                .stream()
                .filter(filterKontostandImMinus)
                .forEach(actionKontoSperren);
    }


    /**
     * liefert eine Liste aller Kunden, die auf einem Konto einen Konto-stand haben, der mindestens minimum beträgt
     * @param minimum minimaler Kontostand
     * @return Liste von Kunden mit minimalen Kontostand
     */
    public List<Kunde> getKundenMitVollemKonto(double minimum){
        Predicate<Konto> filterKontostandMindestensMinimum =
                konto -> konto.getKontostand() >= minimum;
        List<Kunde> kundenliste = konten
                .values()
                .stream()
                .filter(filterKontostandMindestensMinimum)
                .map(Konto::getInhaber)
                .collect(Collectors.toList());
        return kundenliste;
    }


    /**
     * liefert die Namen und Geburtstage aller Kunden der Bank
     * sotiert die Liste nach dem Geburtsdatum
     * @return Geburtstag Liste als String
     */
    public String getKundengeburtstage(){
        String reduceStartStr = "Geburtstagliste" +
                System.getProperty("line.separator");
        BinaryOperator<String> reduceToString =
                (alt, neu) -> alt + neu + System.getProperty("line.separator");
        Comparator<Kunde> compare =
                (kunde1, kunde2) ->
                        kunde1.getGeburtstag().compareTo(kunde2.getGeburtstag());
        Function<Kunde, String> mapKundeToString =
                kunde -> kunde.getName() + " (" + kunde.getGeburtstag() + ")";
        String geburtstagsliste = konten
                .values()
                .stream()
                .map(Konto::getInhaber)
                .distinct() // nur verschiedene Kunden
                .sorted(compare)
                .map(mapKundeToString)
                .reduce(reduceStartStr, reduceToString);
        return geburtstagsliste;
    }

    /**
     * liefert eine Liste aller freien Kontonummern, die im vergebenen Bereich liegen
     * @return Liste aller freien Kontonummern
     */
    public List<Long> getKontonummernLuecken(){
        List<Long> aktuelleKontonummern = getAlleKontonummern();
        LongPredicate filterOutVorhandeneKontonummern =
                nummer -> !aktuelleKontonummern.contains(nummer);
        List<Long> freieKontonummern = LongStream
                .range(FIRST_KONTONUMMER, LAST_KONTONUMMER)
                .filter(filterOutVorhandeneKontonummern)
                .boxed()
                .collect(Collectors.toList());
        return freieKontonummern;
    }

    /**
     * alternative loesung for getKontonummerLuecken
     * @return
     */
    public List<Long> getKontonummernLuecken2() {
        return Stream.iterate(0L, x -> x+1)
                .limit(LAST_KONTONUMMER)
                .filter(x -> !konten.containsKey(x))
                .collect(Collectors.toList());
    }

    /**
     * alternative loesung for getKontonummerLuecken
     * @return
     */
    public List<Long> getKontonummernLuecken3() {
        Long[] array = new Long[(int) LAST_KONTONUMMER];
        Arrays.setAll(array, i -> new Long(i));
        return Arrays.stream(array)
                .filter(x ->
                        !konten.containsKey(x))
                .collect(Collectors.toList());
    }

    /**
     * Die Methode liefert eine Liste aller Kunden, deren Gesamteinlage auf all
     * ihren Konten (das kann mehr als eins sein) mehr als minimum betraegt.
     *
     * @param minimum
     * minimale Geldeinlage Gesamt auf allen Konten
     * @return Liste aller Kunden
     */
    public List<Kunde> getAlleReichenKunden(double minimum) {
        BinaryOperator<Double> mergeFunction =
                (kontostandAlt, kontostandNeu) -> kontostandAlt += kontostandNeu;
        Predicate<Map.Entry<Kunde, Double>> filterMinimum =
                entry -> entry.getValue() > minimum;
        Map<Kunde, Double> kundenMap = konten
                .values()
                .stream()
                .collect(Collectors.toMap(Konto::getInhaber,
                        Konto::getKontostand, mergeFunction));
        List<Kunde> alleReichenKunden = kundenMap
                .entrySet()
                .stream()
                .filter(filterMinimum)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return alleReichenKunden;
    }

    /**
     * alternative Loesung fuer getAlleReichenKunde
     * @param minimum
     * @return
     */
    public List<Kunde> getAlleReichenKunden2(double minimum) {
        return konten.values().stream().map(Konto::getInhaber)
                .distinct()
                .filter(aktuellerKunde ->
                        konten.values().stream()
                                .filter(k -> k.getInhaber() == aktuellerKunde)
                                .map(k -> k.getKontostand())
                                .reduce(0.0, (a, b) -> a + b) > minimum)
                .collect(Collectors.toList());
    }

    /**
     * @throws CloneNotSupportedException, wenn die Bank nicht serialiserbare Konten verwaltet, die den Clone-Vorgang stoeren
     */
    @Override
    public Bank clone() throws CloneNotSupportedException {
        Bank bankCopy = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bytes = null;
        try(ObjectOutputStream out = new ObjectOutputStream(byteArrayOutputStream)) {
            out.writeObject(this);
            out.flush();
            bytes = byteArrayOutputStream.toByteArray();
        }
        catch (NotSerializableException e2)
        {
            throw new CloneNotSupportedException();}
        catch (IOException e1) { }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        try (ObjectInputStream in = new ObjectInputStream(byteArrayInputStream)){
            bankCopy = (Bank) in.readObject();
        } catch (IOException | ClassNotFoundException e1){ }
        return bankCopy;
    }


    /**
     * erstellt eine neue Konto
     * @param fabrik ein Konto
     * @param inhaber Inhaber
     * @param auswahl Sparbuch or GiroKonto
     * @return
     */
    public long kontoErstellen(Kontofabrik fabrik, Kunde inhaber, int auswahl){
        long num = konten.size() + 1;
        Konto k = fabrik.erzeugen(auswahl, inhaber, num);
        if (k != null) {
            konten.put(num, k);
        }
        return num;
    }
}



