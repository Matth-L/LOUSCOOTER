package LOUSCOOTERapplication;

public class Client {
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

    // on vérifie que c'est un admin par son mail et son mdp
    void isAdmin(String m) {
        if (mail == m) {
            admin = true;
        }
    }
}