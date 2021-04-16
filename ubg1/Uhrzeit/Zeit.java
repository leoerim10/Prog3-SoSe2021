
public class Zeit {
    public int stunden;
    public int minutes;
    public int seconds;
    
    public Zeit(int stunden, int minutes, int seconds){
        if(stunden < 1 || stunden > 23){
            throw new IllegalArgumentException("argument not valid");
        }

        if (minutes < 1 || minutes > 59){
            throw new IllegalArgumentException("argument not valid");
        }

        if (seconds < 1 || seconds > 59){
            throw new IllegalArgumentException("argument not valid");
        }

        this.stunden = stunden;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public String getTime(){
        return this.stunden + ":" + this.minutes + ":" + this.seconds;
    }

    public int getTotalSeconds(){
        int hoursInSeconds = this.stunden * 3600;
        int minutesInSeconds = this.minutes * 60;

        return hoursInSeconds + minutesInSeconds + this.seconds;
    }

    public void ausgebenDeutsch(){
        System.out.println(getTime());
    }

    public void ausgebenEnglisch(){
        if(this.stunden >= 12){
            if(this.stunden == 12){
                System.out.println(getTime() + " am");
            } else {
                int std=0;
                switch(this.stunden){
                    case 13: std=1; break;
                    case 14: std=2; break;
                    case 15: std=3; break;
                    case 16: std=4; break;
                    case 17: std=5; break;
                    case 18: std=6; break;
                    case 19: std=7; break;
                    case 20: std=8; break;
                    case 21: std=9; break;
                    case 22: std=10; break;
                    case 23: std=11; break;
                }

                System.out.println(std + ":" + this.minutes + ":" + this.seconds + " pm");
            }
        } else {
            System.out.println(getTime() + " am");
        }
    }

    public int differenz(Zeit t2){
        int x1 = this.getTotalSeconds();
        int x2 = t2.getTotalSeconds();

        int result = x1 - x2;

        if(result < 0){
            return result * -1;
        }

        return result;
    }
}