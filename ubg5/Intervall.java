/**
 *stellt ein mathematisches Intervall auf einer linear geordneten Menge
 * @param <T> Typ der Elemente des Intervalls
 * @author Sameer Dhimal 569076, Wojciech Maximilan Frackowski 576278
 */

public class Intervall<T extends Comparable<? super T>>{

     /**
     * untere Grenze des Intevalls
     */
    private T unterGrenze;
    /**
     * obere Grenze des Intervalls
     */
    private T oberGrenze;

    public Intervall(T unterGrenze, T oberGrenze){
        this.unterGrenze = unterGrenze;
        this.oberGrenze = oberGrenze;
    }

    public void setUnterGrenze(T unterGrenze){
        this.unterGrenze = unterGrenze;
    }

    public T getUnterGrenze(){
        return this.unterGrenze;
    }

    public void setOberGrenze(T oberGrenze){
        this.oberGrenze = oberGrenze;
    }

    public T getOberGrenze(){
        return this.oberGrenze;
    }
    /**
     * prueft, ob das Intervall leer ist
     * @return true, wenn es leer ist
     */
    public boolean isLeer(){
        if(this.unterGrenze == null || this.oberGrenze == null){
            return true;
        }

        if(this.oberGrenze.compareTo(this.unterGrenze) < 0 ){
            return true;
        } 
        return false;
    }
    /**
     * ab wert im Intervall enthalten ist,
     * d.h. ob wert größer als die untere Grenze des Intervalles ist und kleiner als die obere
     * @param wert
     * @return
     */
    public boolean enthaelt(T wert){
        if(this.unterGrenze.compareTo(wert) <= 0  && this.oberGrenze.compareTo(wert) >= 0){
            return true;
        }

        return false;
    }
    
     /**
     * bildet ein Schnittmengen-Intervall von this und anderes
     * @param anderes
     * @param <T1>
     * @return
     */
    public <T1 extends T> Intervall<T> schnitt(Intervall<T1> anderes){
        if(this.unterGrenze.compareTo(anderes.unterGrenze) <=0 && this.oberGrenze.compareTo(anderes.oberGrenze) >= 0 ){
            return new Intervall<T>(anderes.unterGrenze,anderes.oberGrenze);
        }
        if(this.unterGrenze.compareTo(anderes.unterGrenze) >= 0 && this.oberGrenze.compareTo(anderes.oberGrenze) >= 0 ){
            return new Intervall<T>(this.unterGrenze,anderes.oberGrenze);
        }
        if(this.unterGrenze.compareTo(anderes.unterGrenze) <=0 && this.oberGrenze.compareTo(anderes.oberGrenze) <=0 ){
            return new Intervall<T>(anderes.unterGrenze,this.oberGrenze);
        }
        if( this.unterGrenze.compareTo(anderes.unterGrenze) >= 0 && this.oberGrenze.compareTo(anderes.oberGrenze) <=0 ){
            return new Intervall<T>(this.unterGrenze,this.oberGrenze);
        }
        return null;
    }
    
    
    public String toString(){
        return "From: " + this.unterGrenze + " To: " + this.oberGrenze;
    }

}
