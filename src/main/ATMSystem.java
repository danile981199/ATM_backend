package main;

import services.ATMService;
import java.util.Scanner;

public class ATMSystem {
    public static void main(String[] args) {
        ATMService atmService = new ATMService();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter login: ");
        String login = scanner.next();
        System.out.print("Enter Pin code: ");
        int pin = scanner.nextInt();
        atmService.processLogin(login, pin);
    }
}
