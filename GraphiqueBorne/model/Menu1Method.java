package GraphiqueBorne.model;

import java.util.ArrayList;
import java.util.Scanner;

//! MenuMethod -> OptionMenu -> ErreurMenu -> FinalMenu
public class Menu1Method {
    static Scanner scan = new Scanner(System.in);

    public static Scooter getScooter(ArrayList<Scooter> tabScooter, int id) {
        for (Scooter s : tabScooter) { // boucle for Each
            if (id == s.getId()) {
                return s;
            }
        }
        return null;
    }

}
