
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.HashMap; 
import java.util.Comparator;

/**
 * verwaltet verschiedene Kontoarten
 * @author Sameer Dhimal 569076, Wojciech Maximilan Frackowski 576278
 */
public class Bank {

    /**
     * die Bankleitzahl
     */
    private long bankleitzahl;

    public Map<Long, Konto> konten = new HashMap<Long, Konto>();

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
            kontoinfo += "Kontonummer: " + k.getKontonummer() + " Kontostand: " + k.getKontostand() + "\n";
        
        }
        return kontoinfo;
    }

    /**
     * liefert eine Liste aller gültigen Kontonummern in der Bank
     * @return die Liste
     */
    public List<Long> getAlleKontonummern(){
        List<Long> l = new ArrayList<Long>();
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
     * @throws NoSuchElementException fuer unqultigen Kontonummer
     */
    public boolean geldAbheben(long von, double betrag) throws IllegalArgumentException, GesperrtException, NoSuchElementException {
        if (betrag < 0 || Double.isNaN(betrag)) {
			throw new IllegalArgumentException("Betrag ungültig");
		}
        if(!konten.containsKey(von)){
            throw new NoSuchElementException("Konto with given Kontonummer not found");
        }

        boolean geklappt;
        Waehrung w = Waehrung.EUR;
        try{
            geklappt = konten.get(von).abheben(betrag,w);
        } catch (Exception e){
            throw new GesperrtException(von);
        }

        return geklappt;
    }


    /**
     * zahlt den angegebenen Betrag auf das Konto
     * @param auf Konto, auf das der Betrag eingezahlt wird
     * @param betrag Betrag
     * @throws IllegalArgumentException fuer ungueltigen Betrag
     * @throws NoSuchElementException fuer unqultigen Kontonummer
     */
    public void geldEinzahlen(long auf, double betrag) throws IllegalArgumentException, NoSuchElementException{
        if (betrag < 0 || Double.isNaN(betrag)) {
			throw new IllegalArgumentException("Betrag ungültig");
		}
        if(!konten.containsKey(auf)){
            throw new NoSuchElementException("Konto with given Kontonummer not found");
        }

        konten.get(auf).einzahlen(betrag, Waehrung.EUR);
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
    public boolean geldUberweisen(long von, long nach, double betrag, String verwendungszweck) throws IllegalArgumentException{
        if (betrag < 0 || Double.isNaN(betrag)) {
			throw new IllegalArgumentException("Betrag ungültig");
		}
        //check if both accounts exist in the bank
        if(!konten.containsKey(von) || !konten.containsKey(nach)){
            return false;
        }

        Konto vonKonto = this.konten.get(von);
        Konto nachKonto = this.konten.get(nach);

        //check if von has enough balance
        if(vonKonto.getKontostand() - betrag >= 0){
            vonKonto.setKontostand(vonKonto.getKontostand() - betrag);
            nachKonto.setKontostand(nachKonto.getKontostand() + betrag);
            return true;
        } else {
            return false;
        }
    }

    public long mockEinfuegen(Konto k){
        konten.put(k.getKontonummer(), k);
        return k.getKontonummer();
    }

    public void pleitegeierSperren(){
        konten.values().stream().forEach(konto -> {
            if(konto.getKontostand() < 0){
                konto.sperren();
            }
        });
    }

    public List<Kunde> getKundenMitVollemKonto(double minimum){
        List<Kunde> list = new ArrayList<Kunde>();
        konten.values().stream().filter(konto ->{
            if(konto.getKontostand() >= minimum){
                return true;
            } else {
                return false;
            }
        }).forEach(konto -> {
            list.add(konto.getInhaber());
        });
        return list;
    }

    
    public String getKundengeburtstage(){
        final String msg = "";
        //Comparator<Kunde> c = (kunde1, kunde2) -> kunde1.getGeburtstag().compareTo(kunde2.getGeburtstag());
        List<Kunde> list = konten.values().stream().map(k -> k.getInhaber()).collect(Collectors.toList());
        list.stream().distinct().sorted((kunde1, kunde2) -> kunde1.getGeburtstag().compareTo(kunde2.getGeburtstag())).forEach(k -> {
            msg = msg + k.toString() + "\n";
        });

        return msg;
    }

    
    
    public List<Long> getKontonummernLuecken(){
        List<Long> list = konten.values().stream().map(k -> k.getKontonummer()).collect(Collectors.toList());
        return list; 
    }

}