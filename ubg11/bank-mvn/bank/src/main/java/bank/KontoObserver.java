package bank;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class KontoObserver implements PropertyChangeListener{


    @Override
    public void propertyChange(PropertyChangeEvent e){
        if(e.getPropertyName().equals("Kontostand")){
            double oldKontostand = (double) e.getOldValue();
            double newKontostand = (double) e.getNewValue();
            System.out.println("[*] Old Kontostand: " + oldKontostand + " New Kontostand: " + newKontostand);
        }
        if(e.getPropertyName().equals("Inhaber")) {
        	Kunde oldInhaber = (Kunde) e.getOldValue();
        	Kunde newInhaber = (Kunde) e.getNewValue();
        	System.out.println("[*] Konto owner changed from: " + oldInhaber.getName() + " to: " + newInhaber.getName());
        }
        if(e.getPropertyName().equals("Sperren")) {
        	Konto k = (Konto) e.getSource();
        	String status = "";
        	if (k.getGesperrtText() == "GESPERRT") {
        		status = "GESPERRT";
        	} else {
        		status = "NOT GESPERRT";
        	}
        	System.out.println("[*] Konto: " + k.getKontonummer() + "is " + status);
        }
        if(e.getPropertyName().equals("Waehrung")) {
        	Waehrung oldWaehrung = (Waehrung) e.getOldValue();
        	Waehrung newWaehrung = (Waehrung) e.getNewValue();
        	System.out.println("[*] Old Waehrung: " + oldWaehrung.name() + " New Waehrung: " + newWaehrung.name());
        }
    }
}