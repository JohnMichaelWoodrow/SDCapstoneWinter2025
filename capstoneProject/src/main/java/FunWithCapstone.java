import java.util.Scanner;
import java.io.IOException;

public class FunWithCapstone {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        try {
            do {
                System.out.println("\nMenu:");
                System.out.println("1. Option 1");
                System.out.println("2. Option 2");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (choice == 1) {
                    System.out.println("You chose Option 1.");
                } else if (choice == 2) {
                    System.out.println("You chose Option 2.");
                } else if (choice == 3) {
                    System.out.println("Exiting program...");
                    running = false;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } while (running);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}


