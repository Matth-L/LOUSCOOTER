package BorneConsole;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Scooter {
    static int countScoot = 0;
    private int id;
    private int kilometrage;
    private String marque;
    private String modele;
    // un tableau contenant les locations du scooters
    ArrayList<Location> tabLocation = new ArrayList<Location>();

    // verifier si la date se chevauche pour la meme id
    Scooter() {
        countScoot++;
        id = countScoot;
        kilometrage = 0;
    }

    Scooter(int nb, int km, String mar, String mod) {
        id = nb;
        kilometrage = km;
        marque = mar;
        modele = mod;
    }

    // filtre pour la disponibilité d'un scouteur
    boolean isDispo(Date debut, Date fin) {
        for (Location l : tabLocation) {
            if (l.scootId == this.id && l.dateInter(l.dateDebut, l.dateFin)) {
                return false;

            }
        }
        return true;
    }

    boolean isDispoActual() {
        SimpleDateFormat dateFrt = new SimpleDateFormat("dd/MM/yyyy");
        Date t = new Date(System.currentTimeMillis());
        dateFrt.format(t);
        for (Location l : tabLocation) {
            if (l.scootId == this.id && l.dateTest(t)) {
                return false;
            }
        }
        return true;
    }

    Location verifNumR(int num) {
        for (Location l : tabLocation) {
            if (l.scootId == this.id) {
                if (l.numR == num) {
                    return l;
                }
            }
        }
        return null;

    }

    // getteur\\
    public int getId() {
        return this.id;
    }

    public int getKilometrage() {
        return this.kilometrage;
    }

    public String getMarque() {
        return this.marque;
    }

    public String getModele() {
        return this.modele;
    }

    // setteur \\
    public void setId(int x) {
        this.id = x;
    }

    public void setKilometrage(int x) {
        this.kilometrage = x;
    }

    public void setMarque(String x) {
        this.marque = x;
    }

    public void setModele(String x) {
        this.modele = x;
    }
}