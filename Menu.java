public class Menu extends Main {
    public void menu() {
        String[] option = { "1 : Louer", "2 : Retour ", "3 : Etat", "4 : Quitter" };
        for (int i = 0; i < option.length; i++) {
            System.out.println(option[i]);
        }
    }
}
