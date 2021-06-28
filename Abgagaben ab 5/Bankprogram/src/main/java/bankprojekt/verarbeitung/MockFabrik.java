package bankprojekt.verarbeitung;

import org.mockito.Mockito;

/**
 * erzeugt Konto-Mock-Objekte
 */
public class MockFabrik extends Kontofabrik{
    private Konto neu;
    public Kontoart art = Kontoart.GIROKONTO;


    @Override
    public Konto erstellen(Kunde inhaber, long kontonr) {
        switch (art){
            case SPARBUCH:
                neu = Mockito.mock(Sparbuch.class);
                break;
            case GIROKONTO:
                neu = Mockito.mock(Girokonto.class);
                break;
            default:
                throw new IllegalStateException();
        }
        Mockito.when(neu.getInhaber()).thenReturn(inhaber);
        Mockito.when(neu.getKontonummer()).thenReturn(kontonr);
        return neu;
    }
}
