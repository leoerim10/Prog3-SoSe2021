package bank;

public class Kontofabrik {
	
	public Kontofabrik() {}
	
    public Konto erzeugen(int auswahl, Kunde inhaber, long nummer){
        Konto k = null;
        switch(auswahl){
            case 1: k = new Sparbuch(inhaber, nummer);
            break;
            case 2: k = new Girokonto(inhaber, nummer, 500.00);
            break;
            default:
        }

        return k;
    };
}