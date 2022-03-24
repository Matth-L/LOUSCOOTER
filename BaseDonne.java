import java.util.ArrayList;

public class BaseDonne {
    static Scooter a = new Scooter(1, true, 40, "Honda", "A");
    static Scooter b = new Scooter(2, true, 40, "Yamaha", "A");
    static Scooter c = new Scooter(3, false, 40, "Honda", "A");
    static Scooter d = new Scooter(4, true, 40, "Yamaha", "A");
    static Scooter e = new Scooter(5, false, 40, "Yamaha", "A");
    static Scooter k = new Scooter(14, false, 40, "Honda", "A");
    static Scooter i = new Scooter(25, false, 40, "Honda", "A");
    static Scooter g = new Scooter(72, true, 40, "Yamaha", "A");
    static Scooter f = new Scooter(88, false, 40, "Honda", "A");

    static void setScooterInDB(ArrayList<Scooter> tab) { // pour rajouter les scooters aux tab
        tab.add(a);
        tab.add(b);
        tab.add(c);
        tab.add(d);
        tab.add(e);
        tab.add(f);
        tab.add(g);
        tab.add(i);
    }
}