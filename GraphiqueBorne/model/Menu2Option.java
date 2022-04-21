package GraphiqueBorne.model;

import java.io.IOException;
import java.util.ArrayList;
import java.time.ZoneId;
import java.time.LocalDateTime;
import java.util.Date;

//! ce qui est demander par l'énoncé 
public class Menu2Option extends Menu1Method {

    public void mainMenu() {
        String[] option = { "1 : Louer un scooter", "2 : Retour d'un scooter ", "3 : Etat d'un scooter",
                "4 : Affichage de l'état du parc des scooters ", "5 : Saisie du parc des scooters ",
                "6 : Quitter le programme " };
        for (int i = 0; i < option.length; i++) {
            System.out.println(option[i]);
        }
    }

    void afficheScooter(ArrayList<Scooter> tabScooter) throws IOException {
        Scooter scoot = getScooter(tabScooter, demandeId());
        if (scoot != null) {
            infoScooter(scoot);
            clearBoard();
        } else {
            System.out.println("Ce scooter n'est pas dans la base de donnée");
            System.err.println(
                    "Que voulez-vous faire: \n 1) rentrer une autre id \n 2) retourner au menu \n 3) quitter ");
            switch (scan.nextInt()) {
                case 1:
                    flushS();
                    break;
                case 2:
                    flushS();
                    // retourner au menu
                    break;
                default:
                    flushS();
                    System.out.println("valeurs rentrée incorecte, retour au menu.");
                    break;
            }
        }
    }

    // affiche les info de tous les scouteurs
    void afficheParc(ArrayList<Scooter> tabScooter) throws IOException {
        for (Scooter s : tabScooter) {
            infoScooter(s);
        }
        clearBoard();
    }

    // ! j'ai supprimé afficheStatParc on l'utilise pas

    public static int louerDate(int G, ArrayList<Scooter> tabScooter, String dateDeb, String dateFin)
            throws IOException {
        Scooter S;
        if ((S = getScooter(tabScooter, G)) == null) {
            return 1;
        }

        Date debutDate = Location.stringToDate(dateDeb);
        Date finDate = Location.stringToDate(dateFin);
        if (debutDate == null || finDate == null){
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
