/**
 *stellt ein mathematisches Intervall auf einer linear geordneten Menge
 * @param <T> Typ der Elemente des Intervalls
 * @author Sameer Dhimal, 569076 &  Wojciech Maximilan Frackowski, 576278
 */
public class Intervall<T extends Comparable<? super T>> {

    /**
     * untere Grenze des Intevalls
     */
    private T untereGrenze;

    /**
     * obere Grenze des Intervalls
     */
    private T obereGrenze;

    /**
     * erstellt ein Intervall mit
     * @param untereGrenze untere Grenze
     * @param obereGrenze obere Grenze
     */
    public Intervall(T untereGrenze, T obereGrenze){
        this.untereGrenze = untereGrenze;
        this.obereGrenze = obereGrenze;
    }

    /**
     * setzt eine neue untere Grenze
     * @param untereGrenze untere Grenze
     */
    public void setUntereGrenze(T untereGrenze){
        this.untereGrenze = untereGrenze;
    }

    /**
     * @return aktuelle untere Grenze
     */
    public T getUntereGrenze(){
        return this.untereGrenze;
    }

    /**
     * setzt eine neue obere Grenze
     * @param obereGrenze obereGrenze
     */
    public void setObereGrenze(T obereGrenze){
        this.obereGrenze = obereGrenze;
    }

    /**
     * @return aktuelle obere Grenze
     */
    public T getObereGrenze(){
        return this.obereGrenze;
    }

    /**
     * prueft, ob das Intervall leer ist
     * @return true, wenn es leer ist
     */
    public boolean isLeer(){
        if(this.untereGrenze == null || this.obereGrenze == null){
            return true;
        }
        return this.obereGrenze.compareTo(this.untereGrenze) < 0;
    }


    /**
     * ab wert im Intervall enthalten ist,
     * d.h. ob wert größer als die untere Grenze des Intervalles ist und kleiner als die obere
     * @param wert der Wert
     * @return true, wenn gefunden ist
     */
    public boolean enthaelt(T wert){
        if(this.untereGrenze.compareTo(wert) <= 0  && this.obereGrenze.compareTo(wert) >= 0){
            return true;
        }
        return false;
    }


    /**
     * bildet ein Schnittmengen-Intervall von this und anderes
     * @param anderes zweite Intervall
     * @param <T1> Datentyp des Intervalls
     * @return
     */

    public <T1 extends T> Intervall<T> schnitt(Intervall<T1> anderes){
        if(this.untereGrenze.compareTo(anderes.untereGrenze) <=0 && this.obereGrenze.compareTo(anderes.obereGrenze) >=0 ){
            return new Intervall<T>(anderes.untereGrenze,anderes.obereGrenze);
        }
        if(this.untereGrenze.compareTo(anderes.untereGrenze) >=0 && this.obereGrenze.compareTo(anderes.obereGrenze) >=0 ){
            return new Intervall<T>(this.untereGrenze,anderes.obereGrenze);
        }
        if(this.untereGrenze.compareTo(anderes.untereGrenze) <=0 && this.obereGrenze.compareTo(anderes.obereGrenze) <=0 ){
            return new Intervall<T>(anderes.untereGrenze,this.obereGrenze);
        }
        if( this.untereGrenze.compareTo(anderes.untereGrenze) >=0 && this.obereGrenze.compareTo(anderes.obereGrenze) <=0 ){
            return new Intervall<T>(this.untereGrenze,this.obereGrenze);
        }
        return null;
    }


    @Override
    public String toString() {
        return "Intervall: ("+ untereGrenze + ", " + obereGrenze + ")";
    }
}
