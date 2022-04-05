package BorneConsole;

import java.io.IOException;
import java.util.ArrayList;

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

    // fct qui permet de louer un scouteur avec une id rentré par l'utilisateur
    void louerScooter(ArrayList<Scooter> tabScooter) throws IOException {
        Scooter S = getScooter(tabScooter, demandeId());
        if (S != null) {// on vérifie que l'id du scouteur existe
            S.louer();
            clearBoard();
            choixMenu(tabScooter);
        } else { // l'id n'existe pas, l'utilisateur à le choix entre rentré une nvelle id ou
                 // retourné au menu. On traite le cas d'une mauvaise touche comme un retour au
                 // menu.
            System.out.println("Ce scooter n'est pas dans la base de donnée");
            System.err.println(
                    "Que voulez-vous faire: \n 1) rentrer une autre id \n 2) retourner au menu \n");
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
    }

    // fct qui permet de rendre un scouteur ap la location en donnant l'id du
    // scouteur puis en rentrant les km parcourus.
    void retourScooter(ArrayList<Scooter> tabScooter) throws IOException {
        Scooter S;
        S = getScooter(tabScooter, demandeId());
        if (S != null) {

            S.retour();
            clearBoard();
            choixMenu(tabScooter);
        } else {
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
        if (scooterDemande.getEtat()) {
            System.out.println("état : Occupé");
        } else {
            System.out.println("état : Libre");
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
        // Le Nombre de scooters en location et leur N° d’identification,
        for (Scooter scoot : tabScooter) {
            if (scoot.getEtat()) {
                louer++;
            }
            kilometrage += scoot.getKilometrage();
        }
        System.out.println("Nombre de scooter en location :" + louer);
        for (Scooter scoot : tabScooter) {
            if (scoot.getEtat() == true) {
                System.out.println("    id : " + scoot.getId());
            }
        }
        System.out.println();
        // Le Nombre de scooters disponibles et leur N° d’identification
        System.out.println("Nombre de scooter disponible :" + (tabScooter.size() - louer));
        for (Scooter s : tabScooter) {
            if (s.getEtat() == false) {
                System.out.println("    id : " + s.getId());
            }
        }
        // Le kilométrage moyen de l’ensemble des scooter
        System.out.println("Kilometrage moyen : " + (kilometrage / tabScooter.size()));
        clearBoard();
        choixMenu(tabScooter);
    }
}
