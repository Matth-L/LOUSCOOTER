package GraphiqueBorne.model;

import java.io.IOException;
import java.time.ZoneId;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

// possède tt les fonction ou le mot invalide est dedans 
public class Menu3Erreur extends Menu2Option {

    static Scanner scan = new Scanner(System.in);

    public static int louerDate(int G, ArrayList<Scooter> tabScooter, String dateDeb, String dateFin)
            throws IOException {
        Scooter S;
        if ((S = getScooter(tabScooter, G)) == null) {
            return 1;
        }

        Date debutDate = Location.stringToDate(dateDeb);
        Date finDate = Location.stringToDate(dateFin);

        if (S.isDispo(debutDate, finDate) && (debutDate != null || finDate != null)) {
            S.tabLocation.add(new Location(debutDate, finDate, S.getId()));
            return 0;
        } else {
            return 2;
        }

    }

    public static boolean retourDate(int id, ArrayList<Scooter> tabScooter) throws IOException {
        Scooter S;
        // vérification de l'existence du scooter
        if ((S = getScooter(tabScooter, id)) == null) {
            return false;
        }
        // converti temps actuelle en date
        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        Date dateRetour = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        // vérification du numéro de retour
        // Location l = S.verifNumR(numR);
        Location l = S.getLocation();
        l.setDateFin(dateRetour);
        return true;
    }
}