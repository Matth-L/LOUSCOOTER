import java.util.ArrayList;
import java.util.Scanner;
// import java.util.concurrent.TimeUnit;

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
    
    static void afficheScooter3(Scooter scooterDemande) {

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

    static void afficheAll4(ArrayList<Scooter> tabScooter) {
        // affiche tous les scooters
        for( int i = 0 ; i<tabScooter.size();i++)
        {
            afficheScooter3(tabScooter.get(i));
        }
        aMenu(tabScooter);
    }

    static void afficheStat5(ArrayList<Scooter> tabScooter) {
        
        int louer=0;
        int kilometrage=0;
        // Le Nombre total de scooters
        System.out.println("Nombre total de scooters : " + tabScooter.size());
        // Le Nombre de scooters en location et leur N° d’identification,
        for( int i = 0 ; i<tabScooter.size();i++)
        {
            if (tabScooter.get(i).getEtat()){
                louer++;
            }
            kilometrage+=tabScooter.get(i).getKilometrage();
        }
        System.out.println("Nombre de scouteur en location :" + louer);
        // Le Nombre de scooters disponibles et leur N° d’identification
        System.out.println("Nombre de scooter disponible :" + (tabScooter.size()-louer) );
        // Le kilométrage moyen de l’ensemble des scooter
        System.out.println("Kilometrage moyen : " + (kilometrage/tabScooter.size()));
        aMenu(tabScooter);
    }
    static void choix1(ArrayList<Scooter> tabScooter){
        Scooter S;
        S=getScooter(tabScooter,demandeId());
        if (S!=null){
            S.louer();
            aMenu(tabScooter);
        }else{
            int choix; // si id fausse!
            System.out.println("Ce scooter n'est pas dans la base de donnée");
            System.err.println("Que voulez-vous faire: \n 1) rentrer une autre id \n 2) retourner au menu \n 3) quitter ");
            choix=scan.nextInt();
            switch (choix) {
                case 1:
                    choix1(tabScooter);
                    break;
                case 2:
                    //retourner au menu
                    aMenu(tabScooter);
                    break;
                case 3:
                    //quitter
                    break;
                default:
                    aMenu(tabScooter);
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
            aMenu(tabScooter);
        }else{
            int choix;
            System.out.println("Ce scouteur n'est pas dans la base de donnée");
            System.err.println("Que voulez-vous faire: \n 1) rentrer une autre id \n 2) retourner au menu \n 3) quitter ");
            choix=scan.nextInt();
            switch (choix) {
                case 1:
                    choix2(tabScooter);
                    break;
                case 2:
                    //retourner au menu
                    aMenu(tabScooter);
                    break;
                case 3:
                    //quitter
                    break;
                default:
                    aMenu(tabScooter);
                    System.out.println("valeurs rentrée incorecte, retour au menu.");
                    break;
            }
        }
    }
    static void choix3 (ArrayList<Scooter> tabScooter) {
        Scooter S;
        S=getScooter(tabScooter,demandeId());
        if (S!=null){
            afficheScooter3(S);
            aMenu(tabScooter);            
        }else{
            int choix;
            System.out.println("Ce scooter n'est pas dans la base de donnée");
            System.err.println("Que voulez-vous faire: \n 1) rentrer une autre id \n 2) retourner au menu \n 3) quitter ");
            choix=scan.nextInt();
            switch (choix) {
                case 1:
                    choix3(tabScooter);
                    break;
                case 2:
                    //retourner au menu
                    aMenu(tabScooter);
                    break;
                case 3:
                    //quitter
                    break;
                default:
                    aMenu(tabScooter);
                    System.out.println("valeurs rentrée incorecte, retour au menu.");
                    break;
            }
        }
    }

static void flush(){
                System.out.print("\033[H\033[2J");
                System.out.flush();
}
    static void aMenu (ArrayList<Scooter> tabScooter){
         int valInput = 1;
        // menu
        affiche.menu();
        System.out.print("Entrez un nombre : ");
        valInput = scan.nextInt();
        flush();
        switch (valInput) {
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
                afficheAll4(tabScooter);
                break;
            case 5: // saisie du parc des scooters
                afficheStat5(tabScooter);
                break;
            case 6 : 

        }
    }
    public static void main(String[] args) {
        // pour ajouter des scooters dans la base de donnée il faut faire
        ArrayList<Scooter> tabScooter = new ArrayList<Scooter>();
        // test de la base de donnée 
        tabScooter.add(a);
        tabScooter.add(b);
        tabScooter.add(c);
        tabScooter.add(f);
        tabScooter.add(e);
        tabScooter.add(k);
        // nettoie la console
        flush();
        aMenu(tabScooter);
        flush();
    }

}