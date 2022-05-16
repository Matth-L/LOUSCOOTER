package GraphiqueBorne.src.model;

import java.util.HashMap;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import java.util.Scanner;

public class IDandPassord {
    HashMap<String, String> logininfo = new HashMap<String, String>();

    public IDandPassord() throws IOException {
        getDB();
    }

    public HashMap<String, String> getLogininfo() {
        return logininfo;
    }

    public static String encrypt(String value) {
        try {
            /*
             * permet de crypter le texte rendtré en base 64, dans un format UFT8.
             */
            return Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8.toString()));
        } catch (Exception e) {
            /*
             * en cas d'erreur.
             */
            return null;
        }
    }

    public void getDB() throws IOException {
        File file = new File("GraphiqueBorne/src/model/baseDonne/bdID.txt");
        Scanner sc = new Scanner(file);
        /*
         * charge l'identifiant et le mot passe stocké dans le fichier texte.
         */
        while ((sc.hasNextLine()) && !(sc.hasNext("EOF"))) {
            /*
             * formulaire de connexion contenant l'id et le mot de passe.
             */
            logininfo.put(sc.nextLine(), sc.nextLine());

        }
        sc.close();
    }
}
