package by.epam.javawebtraining.yermalovich.task05.controller;

import by.epam.javawebtraining.yermalovich.task05.model.resource.Company;
import by.epam.javawebtraining.yermalovich.task05.model.resource.Operator;
import by.epam.javawebtraining.yermalovich.task05.model.thread.Client;
import by.epam.javawebtraining.yermalovich.task05.util.CompanyGenerator;
import by.epam.javawebtraining.yermalovich.task05.util.ClientGenerator;
import by.epam.javawebtraining.yermalovich.task05.view.LogPrinter;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {

        LogPrinter.LOGGER.info("Start main thread!");
        LinkedList<Operator> list = CompanyGenerator.createOperator();


        Company company = new Company(list);

        Client[] clients = ClientGenerator.createClients(company);

        for (Client c : clients) {
            try {
                c.getThread().join();
            } catch (InterruptedException e) {
                LogPrinter.LOGGER.error(e);
            }
        }

        LogPrinter.LOGGER.info("Finish main thread!");

    }
}





