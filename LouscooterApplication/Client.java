package LouscooterApplication;

import java.util.Scanner;

public class Client {
    Scanner scan = new Scanner(System.in);
    boolean admin = false;
    String nom, prenom, mail, mdp;

    // pas besoin de getter et setter pour les clients
    Client(String n, String p, String m, String psw) {
        this.nom = n;
        this.prenom = p;
        this.mail = m;
        this.mdp = psw;
    }

    // le client sera déja trouvé par son adresse mail
    boolean isClient(String psw) {
        if (psw != mdp) {
            System.out.println("Mot de passe incorrecte\nRetour au menu ... ");
            return false;
        }
        return true;
    }

    String getName() {
        return nom;
    }

    String getPrenom() {
        return prenom;
    }

    String getMail() {
        return mail;
    }

    String getMdp() {
        return mdp;
    }

    // on vérifie que c'est un admin par son mail et son mdp
    void isAdmin() {
        System.out.println("Entrez votre mail : ");
        if (scan.nextLine() == "louscooter@parcauto.fr") {
            System.out.println("Entrez votre mot de passe ");
            if (scan.nextLine() == "LOUSCOOTER") {
                admin = true;
            }
        }
        System.err.println("Mot de passe incorrect");
    }
}