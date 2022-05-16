package GraphiqueBorne.src.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

//! ce qui est demander par l'énoncé 
public class Menu {
    static Scanner scan = new Scanner(System.in);

    public static Scooter getScooter(ArrayList<Scooter> tabScooter, int id) {
        for (Scooter s : tabScooter) { // boucle for Each
            if (id == s.getId()) {
                return s;
            }
        }
        return null; // si le scooter n'est pas trouver dans la bd
    }

    public static int louerDate(int id, ArrayList<Scooter> tabScooter, String dateDeb, String dateFin)
            throws IOException {
        Scooter S;
        if ((S = getScooter(tabScooter, id)) == null) {
            return 1;
        }

        Date debutDate = Location.stringToDate(dateDeb); // converti les date de type String en type date
        Date finDate = Location.stringToDate(dateFin);

        if (debutDate == null || finDate == null) { // si une erreur quelconque lors de la conversion
            return 2;
        }
        if (!S.isDispoActual() && S.getEnreparation()) { // vérification de la disponibilité
            return 3;
        } else if (!S.isDispoActual()) {
            return 2;
        }
        if (S.isDispo(debutDate, finDate) && !S.getEnreparation()) {
            S.tabLocation.add(new Location(debutDate, finDate, S.getId()));
            return 0;
        } else {
            if (S.getEnreparation()) {
                return 3;
            } else {
                return 2;
            }
        }

    }

    public static boolean retourDate(int id, ArrayList<Scooter> tabScooter) throws IOException {
        Scooter S;
        // vérification de l'existence du scooter
        if ((S = getScooter(tabScooter, id)) == null) {
            return false;
        }
        /*
         * converti temps actuelle en date
         * met la date de retour (celle du jour j) dans l'arraylist
         */
        Calendar calendar = Calendar.getInstance();
        Location l = S.getLocation();
        l.setDatefin(Location.stringToDate(
                calendar.get(Calendar.DATE) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/"
                        + calendar.get(Calendar.YEAR)));
        return true;
    }

    public static int suppLoc(int G, ArrayList<Scooter> tabScooter, String dateDeb, String dateFin) {
        Scooter S;
        if ((S = getScooter(tabScooter, G)) == null) {
            return 1;
        }
        ArrayList<Location> tabLocation = S.tabLocation;
        Date debutDate = Location.stringToDate(dateDeb);
        Date finDate = Location.stringToDate(dateFin);

        if (debutDate == null || finDate == null) {
            return 2;
        }

        for (Location l : tabLocation) { // boucle for Each
            if (l.getDate(true).equals(debutDate) && l.getDate(false).equals(finDate)) {
                tabLocation.remove(l);
                return 0;
            }
        }
        return 2;
    }
}
