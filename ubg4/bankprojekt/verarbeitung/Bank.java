import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class Bank {
    private long bankleitzahl;
    private Map<Long, Konto> konten;

    public Bank(long bankleitzahl){
        this.bankleitzahl = bankleitzahl;
    }

    public long getBankleitzahl(){
        return this.bankleitzahl;
    }

    public void setBankleitzahl(long bankleitzahl){
        this.bankleitzahl = bankleitzahl;
    }

    public long girokontoErstellen(Kunde inhaber){
        long num = konten.size() + 1;
        Girokonto g = new Girokonto(inhaber, num, 500.00);
        konten.put(num, g);
        return num;
    }

    public long sparbuchErstellen(Kunde inhaber){
        long num = konten.size() + 1;
        Sparbuch s = new Sparbuch(inhaber, num);
        konten.put(num, s);
        return num;
    }

    public String getAlleKonten(){
        String msg = "";
        for(Konto k: konten.values()){
            msg += String.format("Kontonummer: %10d, Kontostand: %10d\n", k.getKontonummer(), k.getKontostand());
        }

        return msg;
    }

    public List<Long> getAlleKontonummern(){
        List<Long> l;
        for(Long num: konten.keySet()){
            l.add(num);
        }

        return l;
    }

    public boolean geldAbheben(long von, double betrag){
        if (betrag < 0 || Double.isNaN(betrag)) {
			throw new IllegalArgumentException("Betrag ungültig");
		}
        if(!konten.containsKey(von)){
            throw new NoSuchElementException("Konto with given Kontonummer not found");
        }

        return konten.get(von).abheben(betrag);
    }


    public void geldEinzahlen(long auf, double betrag){
        if (betrag < 0 || Double.isNaN(betrag)) {
			throw new IllegalArgumentException("Betrag ungültig");
		}
        if(!konten.containsKey(auf)){
            throw new NoSuchElementException("Konto with given Kontonummer not found");
        }

        konten.get(auf).einzahlen(betrag);
    }

    public boolean kontoLoeschen(long nummer){
        if(!konten.containsKey(nummer)){
            return false;
        }

        konten.remove(nummer);
        return true;
    }

    public double getKontostand(long nummer){
        if(!konten.containsKey(nummer)){
            throw new NoSuchElementException("Konto with given Kontonummer not found");
        }

        return konten.get(nummer).getKontostand();
    }


    public boolean geldUberweisen(long von, long nach, double betrag, String verwendungszweck){
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
        if(vonKonto(von).getKontostand() - betrag >= vonKonto(von).getDispo()){
            vonKonto.setKontostand(vonKonto.getKontostand() - betrag);
            nachKonto.setKontostand(nachKonto.getKontostand() + betrag);
            return true;
        } else {
            return false;
        }
        
    }

}