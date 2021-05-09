import java.text.ParseException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import bankprojekt.verarbeitung.Girokonto;
import bankprojekt.verarbeitung.Konto;
import bankprojekt.verarbeitung.Kunde;
import bankprojekt.verarbeitung.Sparbuch;

/**
 * Spielereien mit Collections
 * @author Doro
 *
 */
public class Collectiontest {

	/**
	 * Speichert Wörter in einer Collection 
	 * und Konten in einer Map
	 * @param args wird nicht genutzt
	 * @throws ParseException tritt nicht auf
	 */
	public static void main(String[] args) throws ParseException{
		List<String> woerter = new LinkedList<String>();
			//Man muss die zu erwartende Größe nicht angeben! Juchhu!
		woerter.add("Hallo");
		woerter.add("liebe");
		woerter.add("lustige");
		woerter.add("Studenten");
		//geht, ist aber evtl. ineffizient, langsam:
		for(int i = 0; i < woerter.size(); i++)
		{
			System.out.println(woerter.get(i));
		}
		
		//System.out.println(woerter.get(27));
		//Alternative für for:
		System.out.println("Noch mal anders:");
		for(String wort: woerter)
		{
			System.out.println(wort);
		}
//geht nicht:
		/*
		for(String wort: woerter)
		{
			if(wort.startsWith("l"))
				woerter.remove(wort);
		}
		*/
		Iterator<String> iter = woerter.iterator();
		while(iter.hasNext())
		{
			String wort = iter.next();
			if(wort.startsWith("l"))
				iter.remove();
		}
		System.out.println(woerter.size());
		
		
		Kunde ich = new Kunde("Dorothea", "Hubrich", "zuhause", "13.07.76");
		Girokonto g1 = new Girokonto(ich, 1234, 0);
		Girokonto g2 = new Girokonto(ich, 999, 1000);
		Sparbuch s1 = new Sparbuch(ich, 34567);
		s1.einzahlen(100);
		
		Map<Long, Konto> bank;  //Map ist ein Interface, 
							//und TreeMap implementiert das Interface
		bank = new TreeMap<>();
		
		bank.put(1234L, g1);   //long -> Long passiert automatisch
		bank.put(999L, g2);		//int -> Long geht nicht
		bank.put(34567L, s1);
		//lieber nicht: bank.put(999L, null);
/*		
		for(Konto k: bank) //Map ist KEINE Collection,
							//implementiert NICHT Iterable
							 *
*/
		for(Long l: bank.keySet())
		{
			System.out.println(l);
		}
		for(Konto k: bank.values())
		{
			System.out.println(k);
		}
		for(Map.Entry<Long, Konto> doppelpack: bank.entrySet())
		{
			System.out.println(doppelpack.getKey() + ": " 
					+ doppelpack.getValue().getKontostand());
		}
		
		Konto meins = bank.get(999L);
		meins.einzahlen(1000000000);
		meins = bank.get(3648436L);
			//meins ist jetzt null! keine Exception!
		boolean vorhanden = bank.containsKey(3648436L);
		
		List<Number> zahlen;
		//zahlen = new LinkedList<Integer>();
		//Keine Polymorphie beim Typparameter!!!
		//zahlen.add(3.1415);
		
		//total verpönt und unpraktisch:
		List nochEine;
		nochEine = new LinkedList<Integer>();
		
		List<?> tralala;
		tralala = new LinkedList<Integer>();
		int i = (int) tralala.get(0);
		// geht nicht: tralala.add(4863);
		
	}

}