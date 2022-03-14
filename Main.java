import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Menu affiche = new Menu();
        int val = 1;
        while (val != 4) {
            affiche.menu();
            System.out.print("Entrez un nombre : ");
            val = scan.nextInt();
        }
        scan.close();
    }

}