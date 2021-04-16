
public class Main{
    public static void main(String[] args) {
        
        Zeit z1 = new Zeit(10, 10, 10);

        z1.ausgebenDeutsch();
        z1.ausgebenEnglisch();

        Zeit z2 = new Zeit(14, 59, 59);
        z2.ausgebenDeutsch();
        z2.ausgebenEnglisch();

        Zeit z3 = new Zeit(23, 59, 59);
        z3.ausgebenDeutsch();
        z3.ausgebenEnglisch();

        System.out.println("Difference between z1 and z2: " + z1.differenz(z2));
        System.out.println("Difference between z1 and z3: " + z1.differenz(z3));
        System.out.println("Difference between z2 and z3: " + z2.differenz(z3));

        Zeit z4 = new Zeit(-10, -110, -11);
    }
}