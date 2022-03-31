package consoleSupp;

public class Client {
    boolean admin = false;
    String nom, prenom, mail, mdp;

    void Client(String n, String p, String m, String psw) {
        this.nom = n;
        this.prenom = p;
        this.mail = m;
        this.mdp = psw;
    }

    void isAdmin() {
        admin = true;
    }
}