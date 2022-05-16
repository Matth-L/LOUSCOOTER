package GraphiqueBorne.model;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class Scooter {
    static int countScoot = 0;
    private int id;
    private int kilometrage;
    private String marque;
    private String modele;
    private boolean enReparation = false; // si true -> en réparation, sinon non

    // un tableau contenant les locations du scooters
    ArrayList<Location> tabLocation = new ArrayList<Location>();

    Scooter() {
        countScoot++;
        id = countScoot;
        kilometrage = 0;
    }

    public Scooter(int nb, int km, String mar, String mod) {
        id = nb;
        kilometrage = km;
        marque = mar;
        modele = mod;
    }

    /*
     * filtre pour la disponibilité d'un scouteur
     * getDate -> si true renvoie la premiere date si fausse la 2e
     */
    public boolean isDispo(Date debut, Date fin) {
        for (Location l : tabLocation) {
            /*
             * dateInter | true si entre , faux sinon
             * l.dateInter(l.getDate(true),l.getDate(false))
             */
            if (l.dateInter(debut, fin)) {
                return false;
            }
        }
        return true;
    }

    // si le scooter est dispo -> true
    public boolean isDispoActual() {
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

    // rend la location
    Location getLocation() {
        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        Date dateF = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        for (Location l : tabLocation) {
            if (l.scootId == this.id && l.dateTest(dateF)) { // permet d'avoir la bonne location
                return l;
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

    public boolean getEnreparation() {
        return enReparation;
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

    public void setEnreparation(boolean x) {
        this.enReparation = x;
    }
}