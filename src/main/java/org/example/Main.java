package org.example;

public class Main {
    public static void main(String[] args) {
        NameService nameService = new NameService();
        UserRegistration userRegistration = new UserRegistration(nameService);
        MessageExchange messageExchange = new MessageExchange(nameService, 8005);

        userRegistration.run();
        messageExchange.run();
    }
}