package by.epam.javawebtraining.yermalovich.task05.util;

import by.epam.javawebtraining.yermalovich.task05.model.resource.Operator;

import java.util.LinkedList;

public class CompanyGenerator {

    private static final int MAX_PARKING_PLACE_NUMBER = 5;

    public static LinkedList<Operator> createOperator() {

        LinkedList<Operator> list = new LinkedList<>();

        for (int i = 1; i <= MAX_PARKING_PLACE_NUMBER; i++) {
            list.add(new Operator(i));
        }
        return list;
    }

}
