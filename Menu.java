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

    void flushS() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    void clearBoard() {
        System.out.println("appuyer sur n'importe quelle touche pour retourner au menu");
        scan.nextLine();
        scan.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();

    }

    void louerScooter(ArrayList<Scooter> tabScooter) throws IOException {
        Scooter S = getScooter(tabScooter, demandeId());
        if (S != null) {
            S.louer();
            choixMenu(tabScooter);
        } else {
            System.out.println("Ce scooter n'est pas dans la base de donnée");
            System.err.println(
                    "Que voulez-vous faire: \n 1) rentrer une autre id \n 2) retourner au menu \n 3) Quittez le programme ");
            switch (scan.nextInt()) {
                case 1:
                    louerScooter(tabScooter);
                    break;
                case 2:
                    // retourner au menu
                    choixMenu(tabScooter);
                    break;
                case 3:

                default:
                    choixMenu(tabScooter);
                    System.out.println("valeurs rentrée incorecte, retour au menu.");
                    break;
            }
        }
    }

    void retourScooter(ArrayList<Scooter> tabScooter) throws IOException {
        Scooter S;
        S = getScooter(tabScooter, demandeId());
        if (S != null) {

            S.retour();
            choixMenu(tabScooter);
        } else {
            System.out.println("Ce scooter n'est pas dans la base de donnée");
            System.err.println(
                    "Que voulez-vous faire: \n 1) rentrer une autre id \n 2) retourner au menu \n 3) quitter ");
            switch (scan.nextInt()) {
                case 1:
                    retourScooter(tabScooter);
                    break;
                case 2:
                    // retourner au menu
                    choixMenu(tabScooter);
                    break;
                case 3:
                    // quitter
                    break;
                default:
                    choixMenu(tabScooter);
                    System.out.println("valeurs rentrée incorecte, retour au menu.");
                    break;
            }
        }
    }

    void afficheScooter(ArrayList<Scooter> tabScooter) throws IOException {
        Scooter S = getScooter(tabScooter, demandeId());
        if (S != null) {
            choixMenu(tabScooter);
            clearBoard();
        } else {
            System.out.println("Ce scooter n'est pas dans la base de donnée");
            System.err.println(
                    "Que voulez-vous faire: \n 1) rentrer une autre id \n 2) retourner au menu \n 3) quitter ");
            switch (scan.nextInt()) {
                case 1:
                    afficheScooter(tabScooter);
                    break;
                case 2:
                    // retourner au menu
                    choixMenu(tabScooter);
                    break;
                case 3:
                    // quitter
                    break;
                default:
                    choixMenu(tabScooter);
                    System.out.println("valeurs rentrée incorecte, retour au menu.");
                    break;
            }
        }
    }

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

    void afficheParc(ArrayList<Scooter> tabScooter) throws IOException {
        // affiche tous les scooters
        for (int i = 0; i < tabScooter.size(); i++) {
            infoScooter(tabScooter.get(i));

        }
        choixMenu(tabScooter);
    }

    void afficheStatParc(ArrayList<Scooter> tabScooter) throws IOException {
        int louer = 0;
        int kilometrage = 0;
        // Le Nombre total de scooters
        System.out.println("Nombre total de scooter : " + tabScooter.size());
        // Le Nombre de scooters en location et leur N° d’identification,
        for (int i = 0; i < tabScooter.size(); i++) {
            if (tabScooter.get(i).getEtat()) {
                louer++;
            }
            kilometrage += tabScooter.get(i).getKilometrage();
        }
        System.out.println("Nombre de scooter en location :" + louer);
        for (int i = 0; i < tabScooter.size(); i++) {
            if (tabScooter.get(i).getEtat() == true) {
                System.out.println("    id : " + tabScooter.get(i).getId());
            }
        }
        System.out.println();
        // Le Nombre de scooters disponibles et leur N° d’identification
        System.out.println("Nombre de scooter disponible :" + (tabScooter.size() - louer));
        for (int i = 0; i < tabScooter.size(); i++) {
            if (tabScooter.get(i).getEtat() == false) {
                System.out.println("    id : " + tabScooter.get(i).getId());
            }
        }
        // Le kilométrage moyen de l’ensemble des scooter
        System.out.println("Kilometrage moyen : " + (kilometrage / tabScooter.size()));
        choixMenu(tabScooter);
    }
}
