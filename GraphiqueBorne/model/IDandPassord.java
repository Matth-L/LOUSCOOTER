package GraphiqueBorne.model;

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
            return Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8.toString()));
        } catch (Exception e) {
            return null;
        }
    }

    public void getDB() throws IOException {
        File file = new File("GraphiqueBorne/model/baseDonne/bdID.txt");
        Scanner sc = new Scanner(file);
        while ((sc.hasNextLine()) && !(sc.hasNext("EOF"))) {
            logininfo.put(sc.nextLine(), sc.nextLine());

        }
    }
}
