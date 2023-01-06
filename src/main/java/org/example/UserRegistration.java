package org.example;

import java.util.Scanner;

public class UserRegistration {
    private NameService nameService;

    public UserRegistration(NameService nameService) {
        this.nameService = nameService;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter 1 to register a new nickname, 2 to look up a PIN, or 0 to exit.");
            int choice = scanner.nextInt();
            if (choice == 0) {
                break;
            }
            if (choice == 1) {
                System.out.println("Enter the nickname:");
                String nickname = scanner.next();
                System.out.println("Enter the PIN:");
                int pin = scanner.nextInt();
                nameService.registerNickname(nickname, pin);
            } else if (choice == 2) {
                System.out.println("Enter the nickname:");
                String nickname = scanner.next();
                int pin = nameService.getPin(nickname);
                System.out.println("The PIN for the given nickname is: " + pin);
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }
}
