package BorneConsole;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
// * extends erreur de location dans un fichier ErreurMnenu

//tout ce qui est dans le menu ou affiche est la 
public class Menu extends ParcAuto {

    public void mainMenu() {
        String[] option = { "1 : Louer un scooter", "2 : Retour d'un scooter ", "3 : Etat d'un scooter",
                "4 : Affichage de l'état du parc des scooters ", "5 : Saisie du parc des scooters ",
                "6 : Quitter le programme " };
        for (int i = 0; i < option.length; i++) {
            System.out.println(option[i]);
        }
    }

    void invalideIdLocation(ArrayList<Scooter> tabScooter) throws IOException {
        System.out.println(
                "Id invalide\n vous pouvez : 1) rentrer un autre id \n 2) rentourner au menu \n ");
        switch (scan.nextInt()) {
            case 1:
                flushS();
                louerScooter(tabScooter);
                break;
            case 2:
                // retourner au menu
                flushS();
                choixMenu(tabScooter);
                break;
            default:
                flushS();
                choixMenu(tabScooter);
                System.out.println("valeurs rentrée incorecte, retour au menu.");
                break;
        }
    }

    void invalideDateLocation(Scooter S, ArrayList<Scooter> tabScooter) throws IOException {
        System.out.println(
                "non disponible à la date rentrée ou date invalide.\n vous pouvez : 1) rentrer une autre date \n 2) rentrer un autre id \n 3) retourner au menu");
        switch (scan.nextInt()) {
            case 1:
                // rentrer une nvelle date
                flushS();
                louerDate(S, tabScooter);
                break;
            case 2:
                // rentrer un nouvel id
                flushS();
                louerScooter(tabScooter);
                break;
            case 3:
                // retourner au menu
                flushS();
                choixMenu(tabScooter);
                break;
            default:
                flushS();
                choixMenu(tabScooter);
                System.out.println("valeurs rentrées incorecte, retour au menu.");
                break;
        }
    }

    void invalideNumRetour(Scooter S, ArrayList<Scooter> tabScooter) throws IOException {
        System.out.println(
                "numéro de location invalide pour le scouteur rentré \n vous pouvez : 1) rentrer un autre numéro \n 2) rentrer un autre id \n 3) retourner au menu");
        switch (scan.nextInt()) {
            case 1:
                // rentrer une nvelle date
                flushS();
                retourDate(S, tabScooter);
                break;
            case 2:
                // rentrer un nouvel id
                flushS();
                louerScooter(tabScooter);
                break;
            case 3:
                // retourner au menu
                flushS();
                choixMenu(tabScooter);
                break;
            default:
                flushS();
                choixMenu(tabScooter);
                System.out.println("valeurs rentrées incorecte, retour au menu.");
                break;
        }
    }

    void flushS() {// se mettre au plus bas dans l'interface de commande(retire le texte des
                   // actions précédentes)
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    void clearBoard() { // retire le texte des actions précédantes ap que l'utilisateur est appuyé sur
                        // un touche sauf espace puis affiche le menu.
        System.out.println("appuyer sur n'importe quelle touche pour retourner au menu sauf espace");
        scan.nextLine();
        scan.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    void louerDate(Scooter S, ArrayList<Scooter> tabScooter) throws IOException {

        System.out.println("entrer 2 dates dans le format mm/jj/ann");
        System.out.printf("date de location : ");

        Date debutDate = Location.stringToDate(scan.next());

        System.out.println();
        System.out.printf("date de retour : ");

        Date finDate = Location.stringToDate(scan.next());

        if (S.isDispo(debutDate, finDate) && (debutDate != null || finDate != null)) {
            S.tabLocation.add(new Location(debutDate, finDate, S.getId()));

        } else {
            invalideDateLocation(S, tabScooter);
        }

    }

    void retourDate(Scooter S, ArrayList<Scooter> tabScooter) throws IOException {
        System.out.println("entrez le numéro de location");
        Location l;
        Date finDate = null;
        l = S.verifNumR(scan.nextInt());
        if (l != null) {
            System.out.printf("date de retour : ");
            while (finDate == null) {

                finDate = Location.stringToDate(scan.next());
                if (finDate == null) {
                    System.out.println("veillez rentrer une date valide :");
                }
            }
            l.setDateFin(finDate);
        } else {
            invalideNumRetour(S, tabScooter);
        }
    }

    // louer un scouteur avec une id rentré par l'utilisateur
    void louerScooter(ArrayList<Scooter> tabScooter) throws IOException {
        Scooter S = getScooter(tabScooter, demandeId());
        if (S != null) {
            louerDate(S, tabScooter);

            clearBoard();
            choixMenu(tabScooter);

        } else {
            // On traite le cas d'une mauvaise touche comme un retour au menu
            invalideIdLocation(tabScooter);
        }
    }

    // fct qui permet de rendre un scouteur ap la location en donnant l'id du
    // scouteur puis en rentrant les km parcourus.
    void retourScooter(ArrayList<Scooter> tabScooter) throws IOException {
        Scooter S;
        S = getScooter(tabScooter, demandeId());
        if (S != null) {
            // retour
            retourDate(S, tabScooter);
            clearBoard();
            choixMenu(tabScooter);
        } else {
            invalideScootRetour(tabScooter);
        }
    }

    // message d'erreur
    void invalideScootRetour(ArrayList<Scooter> tabScooter) throws IOException {
        System.out.println("Ce scooter n'est pas dans la base de donnée");
        System.err.println(
                "Que voulez-vous faire: \n 1) rentrer une autre id \n 2) retourner au menu \n");
        switch (scan.nextInt()) {
            case 1:
                flushS();
                retourScooter(tabScooter);
                break;
            case 2:
                // retourner au menu
                flushS();
                choixMenu(tabScooter);
                break;
            default:
                flushS();
                choixMenu(tabScooter);
                System.out.println("valeurs rentrée incorecte, retour au menu.");
                break;
        }

    }

    void afficheScooter(ArrayList<Scooter> tabScooter) throws IOException {
        Scooter scoot = getScooter(tabScooter, demandeId());
        if (scoot != null) {
            infoScooter(scoot);
            clearBoard();
            choixMenu(tabScooter);

        } else {
            System.out.println("Ce scooter n'est pas dans la base de donnée");
            System.err.println(
                    "Que voulez-vous faire: \n 1) rentrer une autre id \n 2) retourner au menu \n 3) quitter ");
            switch (scan.nextInt()) {
                case 1:
                    flushS();
                    afficheScooter(tabScooter);
                    break;
                case 2:
                    flushS();
                    // retourner au menu
                    choixMenu(tabScooter);
                    break;
                default:
                    flushS();
                    choixMenu(tabScooter);
                    System.out.println("valeurs rentrée incorecte, retour au menu.");
                    break;
            }
        }
    }

    // affiche les info du couteur renté en param.
    void infoScooter(Scooter scooterDemande) {
        System.out.println("id :" + scooterDemande.getId());
        System.out.println("marque : " + scooterDemande.getMarque());
        System.out.println("modèle : " + scooterDemande.getModele());
        System.out.println("kilométrage : " + scooterDemande.getKilometrage());
        // affiche les différentes dates
        for (Location l : scooterDemande.tabLocation) {
            // a modifier l'affichage des dates par la suite
            System.out.printf("date de location : %s\n", l.dateDebut.toString());
            System.out.printf("date de retour : %s\n", l.dateFin.toString());
        }
    }

    // affiche les info de tous les scouteurs
    void afficheParc(ArrayList<Scooter> tabScooter) throws IOException {
        for (Scooter s : tabScooter) {
            infoScooter(s);
        }
        clearBoard();
        choixMenu(tabScooter);
    }

    // affiche les statistiques du parc de scouteur
    void afficheStatParc(ArrayList<Scooter> tabScooter) throws IOException {
        int louer = 0;
        int kilometrage = 0;
        System.out.println("Nombre total de scooter : " + tabScooter.size());
        for (Scooter scoot : tabScooter) {
            kilometrage += scoot.getKilometrage();
        }

        // Le Nombre de scooters en location et leur N° d’identification,
        for (Scooter s : tabScooter) {
            // liste des id pour les scooter dispo
            if (!s.isDispoActual()) {
                louer++;
            }
        }

        System.out.println("Nombre de scooter en location :" + louer);
        for (Scooter s : tabScooter) {
            // liste des id pour les scooter louer
            if (!s.isDispoActual()) {
                System.out.println(s.getId());
            }
        }

        System.out.println();

        // Le Nombre de scooters disponibles et leur N° d’identification
        System.out.println("Nombre de scooter disponible :" + (tabScooter.size() - louer));
        for (Scooter s : tabScooter) {
            // liste des id pour les scooter dispo
            if (s.isDispoActual()) {
                System.out.println(s.getId());
            }
        }

        // Le kilométrage moyen de l’ensemble des scooter
        System.out.println("Kilometrage moyen : " + (kilometrage / tabScooter.size()));
        clearBoard();
        choixMenu(tabScooter);
    }

    // liste des scooters disponible en fct d'un intervalle de 2 date
    void listeScooterDate(ArrayList<Scooter> tabScooter) {
        for (Scooter t : tabScooter) {

        }
    }
}
