package GraphiqueBorne.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.time.ZoneId;
import java.time.LocalDateTime;
import java.util.Date;

//! ce qui est demander par l'énoncé 
public class Menu2Option extends Menu1Method {

    // ! j'ai supprimé afficheStatParc on l'utilise pas
    public static int louerDate(int G, ArrayList<Scooter> tabScooter, String dateDeb, String dateFin)
            throws IOException {
        Scooter S;
        if ((S = getScooter(tabScooter, G)) == null) {
            return 1;
        }

        Date debutDate = Location.stringToDate(dateDeb);
        Date finDate = Location.stringToDate(dateFin);

        if (debutDate == null || finDate == null) {
            return 2;
        }
        if (!S.isDispoActual()) {
            return 2;
        }
        if (S.isDispo(debutDate, finDate)) {
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
        // met la date de retour dans l'arraylist
        // ! dernier changement effecuté but -> mettre ça dans les autres fonction qui
        // !ont besoin de date pour que les heure ne soit pas compté
        Calendar calendar = Calendar.getInstance();
        Location l = S.getLocation();
        l.setDateFin(Location.stringToDate(
                calendar.get(Calendar.DATE) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/"
                        + calendar.get(Calendar.YEAR)));
        return true;
    }
}
