package by.epam.javawebtraining.yermalovich.task05.util;

import by.epam.javawebtraining.yermalovich.task05.model.resource.Company;
import by.epam.javawebtraining.yermalovich.task05.model.thread.Client;

import java.util.Random;

public class ClientGenerator {
    private static final int MAX_WAITING_TIME = 2_000;
    private static final int MAX_CALLING_TIME = 2_000;
    private static final int CLIENTS_COUNT = 25;
    private static Random random = new Random();

    public static Client[] createClients(Company company) {
        Client[] clients = new Client[CLIENTS_COUNT];

        for (int i = 0; i < clients.length; i++) {
            clients[i] = new Client(company, random.nextInt(MAX_WAITING_TIME), random.nextInt(MAX_CALLING_TIME));
        }
        return clients;
    }

}
