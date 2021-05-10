package bankprojekt.verarbeitung;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * verwaltet verschiedene Kontoarten
 * @author Sameer Dhimal 569076, Wojciech Maximilan Frackowski 576278
 */
public class Bank {

    /**
     * die Bankleitzahl
     */
    private long bankleitzahl;

    private Map<Long, Konto> konten;

    public Bank(long bankleitzahl){
        this.bankleitzahl = bankleitzahl;
    }

    /**
     * aktuelle Banktleitzahl
     */
    public long getBankleitzahl() {
        return bankleitzahl;
    }

    /**
     * setzt die Bankleitzahl
     */
    public void setBankleitzahl(long bankleitzahl) {
        this.bankleitzahl = bankleitzahl;
    }


    /**
     * erstellt ein Girokonto fuer den angegebenen Kunden
     * @param inhaber Kunde
     * @return die neue vergebene Griokontonummer
     */
    public long girokontoErstellen(Kunde inhaber){
        long num = konten.size() + 1;  //TODO: size test
        Girokonto g = new Girokonto(inhaber, num, 500.00);
        konten.put(num, g);
        return num;
    }

    /**
     * erstellt ein Sparbuch fuer den angegebenen Kunden
     * @param inhaber Kunde
     * @return die neue vergebene Sparkontonummer
     */
    public long sparbuchErstellen(Kunde inhaber){
        long num = konten.size() + 1;
        Sparbuch s = new Sparbuch(inhaber, num);
        konten.put(num, s);
        return num;
    }

    /**
     * liefert eine Auflistung von Kontoinformationen aller Konten
     */
    public String getAlleKonten(){
        String kontoinfo= "";
        for(Konto k: konten.values()){
            kontoinfo += String.format("Kontonummer: %10d, Kontostand: %10d\n", k.getKontonummer(), k.getKontostand());
        }
        return kontoinfo;
    }

    /**
     * liefert eine Liste aller gültigen Kontonummern in der Bank
     * @return die Liste
     */
    public List<Long> getAlleKontonummern(){
        List<Long> l = null;
        for(Long num: konten.keySet()){
           l.add(num);
        }
        return l;
    }

    /**
     * hebt den Betrag vom Konto mit der Nummer
     * @param von Konto, von dem der Betrag abgehoben wird
     * @param betrag Betrag
     * @return true wenn die Abhenung geklappt hat
     * @throws IllegalArgumentException fuer ungueltigen Betrag
     */
    public boolean geldAbheben(long von, double betrag) throws IllegalArgumentException, GesperrtException {
        if (betrag <= 0 ) {
			throw new IllegalArgumentException("Betrag ungültig");
		}
        if(!konten.containsKey(von)){
            throw new NoSuchElementException("Konto with given Kontonummer not found");
        }
        return konten.get(von).abheben(betrag);
    }


    /**
     * zahlt den angegebenen Betrag auf das Konto
     * @param auf Konto, auf das der Betrag eingezahlt wird
     * @param betrag Betrag
     * @throws IllegalArgumentException fuer ungueltigen Betrag
     */
    public void geldEinzahlen(long auf, double betrag) throws IllegalArgumentException{
        if (betrag < 0) {
			throw new IllegalArgumentException("Betrag ungültig");
		}
        if(!konten.containsKey(auf)){
            throw new NoSuchElementException("Konto with given Kontonummer not found");
        }
        konten.get(auf).einzahlen(betrag);
    }

    /**
     * loescht das Konto mit der angegebenen nummer
     * @param nummer die Nummer
     * @return true, wenn das Konto erfolgreich geloescht wird
     */
    public boolean kontoLoeschen(long nummer){
        if(!konten.containsKey(nummer)){
            return false;
        }
        konten.remove(nummer);
        return true;
    }

    /**
     * liefert den Kontostand des Kontos
     * @param nummer mit dieser Nummer
     * @return Kontostand
     */
    public double getKontostand(long nummer){
        if(!konten.containsKey(nummer)){
            throw new NoSuchElementException("Konto with given Kontonummer not found");
        }
        return konten.get(nummer).getKontostand();
    }

    /**
     * ueberweist den genannten Betrag von einem Konto zum anderem
     * @param vonKontonr Sender
     * @param nachKontonr Empfaenger
     * @param betrag Betrag zu überweisen
     * @param verwendungszweck Info/Verwendungszweck
     * @return true, wenn erfolgreich das Betrag ueberwiesen ist
     * @throws IllegalArgumentException fuer ungueltigen Betrag
     * @throws GesperrtException fuer ueberweisungsunfaehigen Konto
     */
    public boolean geldUberweisen(long vonKontonr, long nachKontonr, double betrag, String verwendungszweck) throws IllegalArgumentException, GesperrtException{
        if(betrag <= 0){
            throw new IllegalArgumentException("Betrag ungueltig!!!!");
        }
        if(!konten.containsKey(vonKontonr) || !konten.containsKey(nachKontonr)){
            return false;
            //TODO: working on it
        }
        return true;
    }
}
