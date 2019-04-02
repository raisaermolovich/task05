package by.epam.javawebtraining.yermalovich.task05.util;

import by.epam.javawebtraining.yermalovich.task05.model.thread.Client;


import java.util.Random;


public class ClientStatus {

    public static <T extends Enum<Client.Status>> T randomEnum(Class<T> clazz) {
        Random random = new Random();///
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

}
