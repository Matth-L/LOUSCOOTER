import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Menu affiche = new Menu();
        int val = 1;
        while (val != 4) {
            // nettoie la console
            System.out.print("\033[H\033[2J");
            System.out.flush();
            // menu
            affiche.menu();
            System.out.print("Entrez un nombre : ");
            val = scan.nextInt();
        }
        scan.close();
    }

}