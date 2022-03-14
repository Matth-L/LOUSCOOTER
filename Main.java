import java.util.Scanner;

public class Main {
    public static void menu() {
        String[] option = { "1 : Louer", "2 : Retour ", "3: Etat", "4 : Quitter" };
        for (int i = 0; i < option.length; i++) {
            System.out.println(option[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int val = 1;
        while (val != 4) {
            menu();
            System.out.print("Entrez un nombre :");
            val = scan.nextInt();
        }
        scan.close();
    }

}