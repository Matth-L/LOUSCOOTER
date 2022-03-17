import java.util.ArrayList;
import java.util.Scanner;

public class ParcAuto extends BaseDonne{
    static Scanner scan = new Scanner(System.in);
    static Menu affiche = new Menu(); // affiche le menu

    static Scooter getScooter(ArrayList<Scooter> tabScooter ,int id) {
        for (int i = 0; i < tabScooter.size(); i++) {
            if (id == tabScooter.get(i).getId()) {
                return tabScooter.get(i);
            }
        }
        return null;
    }

    static public int demandeId(){
        int id;
        System.out.println("Veillez rentrer l'id du scouteur: ");
        id=scan.nextInt();
        return id;
    }
    
    static void afficheScooter3(ArrayList<Scooter> tab,int id) {

        Scooter scooterDemande = getScooter(tab,id);
        if (scooterDemande == null) {
            // fonction demanderID
            System.out.println("ce scooter n'est pas dans la base de donnée");
            int newId = demandeId();
            afficheScooter3(tab, newId);
        }
        System.out.println("id :" + scooterDemande.getId());
        System.out.println("marque : " + scooterDemande.getMarque());
        System.out.println("modele : " + scooterDemande.getModele());
        System.out.println("kiolmetrage : " + scooterDemande.getKilometrage());
        if (scooterDemande.getEtat()) {
            System.out.println("etat : Libre");
        } else {
            System.out.println("etat : Occupé");
        }
    }

    void afficheAll4() {
        // affiche tous les scooters disopnibles ou non.
    }

    void afficheStat5() {
        // permet d'afficher :
        // Le Nombre total de scooters
        // Le Nombre de scooters en location et leur N° d’identification,
        // Le Nombre de scooters disponibles et leur N° d’identification,
        // Le kilométrage moyen de l’ensemble des scooter
    }
    static void choix1(ArrayList<Scooter> tabScooter){
        Scooter S;
        S=getScooter(tabScooter,demandeId());
        if (S!=null){
            S.louer();
        }else{
            int choix; // si id fausse!
            System.out.println("Ce scouteur n'est pas dans la base de donnée");
            System.err.println("Que voulez-vous faire: \n 1) rentrer une autre id \n 2) retourner au menu \n 3) quitter ");
            choix=scan.nextInt();
            switch (choix) {
                case 1:
                    choix1(tabScooter);
                    break;
                case 2:
                    //retourner au menu
                    affiche.menu();
                    break;
                case 3:
                    //quitter
                    break;
                default:
                    affiche.menu();
                    System.out.println("valeurs rentrée incorecte, retour au menu.");
                    break;
            }
        }
    }
    static void choix2 (ArrayList<Scooter> tabScooter){
        Scooter S;
        S=getScooter(tabScooter,demandeId());
        if (S!=null){
            S.retour();
        }else{
            int choix;
            System.out.println("Ce scouteur n'est pas dans la base de donnée");
            System.err.println("Que voulez-vous faire: \n 1) rentrer une autre id \n 2) retourner au menu \n 3) quitter ");
            choix=scan.nextInt();
            switch (choix) {
                case 1:
                    choix1(tabScooter);
                    break;
                case 2:
                    //retourner au menu
                    affiche.menu();
                    break;
                case 3:
                    //quitter
                    break;
                default:
                    affiche.menu();
                    System.out.println("valeurs rentrée incorecte, retour au menu.");
                    break;
            }
        }
    }
    static void choix3 (ArrayList<Scooter> tabScooter){
        Scooter S;
        S=getScooter(tabScooter,demandeId());
        if (S!=null){
            if(S.getEtat()){
                System.out.println("Etat: louer");
            }else{
                System.out.println("Etat: disponible");
            }
        }else{
            int choix;
            System.out.println("Ce scouteur n'est pas dans la base de donnée");
            System.err.println("Que voulez-vous faire: \n 1) rentrer une autre id \n 2) retourner au menu \n 3) quitter ");
            choix=scan.nextInt();
            switch (choix) {
                case 1:
                    choix1(tabScooter);
                    break;
                case 2:
                    //retourner au menu
                    affiche.menu();
                    break;
                case 3:
                    //quitter
                    break;
                default:
                    affiche.menu();
                    System.out.println("valeurs rentrée incorecte, retour au menu.");
                    break;
            }
        }
    }
    public static void main(String[] args) {
        // pour ajouter des scooters dans la base de donnée il faut faire
        ArrayList<Scooter> tabScooter = new ArrayList<Scooter>();
        // tabScooter.add()
        
        int val = 1;
        //!tabScooter.add(a);
        afficheScooter3(tabScooter, 0);
        // nettoie la console
        System.out.print("\033[H\033[2J");
        System.out.flush();
        // menu
        affiche.menu();
        System.out.print("Entrez un nombre : ");
        val = scan.nextInt();
        switch (val) {
            case 1: // louer le scooter
            choix1(tabScooter);
                break;
            case 2:// retour scooter
                choix2(tabScooter);
                break; 
            case 3: /// etat
                choix3(tabScooter);
                break;
            case 4: // afficher état du parc des scooters
            case 5: // saisie du parc des scooters
                // si c'est 6 ça close de base si on le rappel pas
        }

    }

}