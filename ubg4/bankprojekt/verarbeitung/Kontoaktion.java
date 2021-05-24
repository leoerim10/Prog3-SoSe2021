import java.util.Date;

public class Kontoaktion{
 
    private String description;
    private double betrag;
    private Date datum;

    public Kontoaktion(String description){
        this.description = description;
        this.betrag = 0;
        this.datum = new Date();
    }

    public Kontoaktion(String description, double betrag){
        this.description = description;
        this.betrag = betrag;
        this.datum = new Date();
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public double getBetrag(){
        return this.betrag;
    }

    public void setBetrag(double betrag){
        this.betrag = betrag;
    }

    public String getDatum(){
        return this.datum.toString();
    }

    public void setDatum(Date datum){
        this.datum = datum;
    }
}