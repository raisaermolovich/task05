package by.epam.javawebtraining.yermalovich.task05.model.resource;

import by.epam.javawebtraining.yermalovich.task05.view.LogPrinter;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Company {
    private final static int OPERATORS_QUANTITY = 5;
    private final Semaphore semaphore = new Semaphore(OPERATORS_QUANTITY, true);
    private final Queue<Operator> operatorsList = new LinkedList<>();

    public Company(Queue<Operator> operatorsList) {
        this.operatorsList.addAll(operatorsList);

    }

    public Operator connectCall(long maxWaitMillis) {
        Operator currentOperator = null;
        try {
            if (semaphore.tryAcquire(maxWaitMillis, TimeUnit.MILLISECONDS)) {
                currentOperator = operatorsList.poll();
                return currentOperator;
            }

        } catch (InterruptedException e) {
            LogPrinter.LOGGER.error(e);
        }
        return currentOperator;

    }

    public void disconnectCall(Operator operator) {
        operatorsList.add(operator);
        semaphore.release();
    }

}
