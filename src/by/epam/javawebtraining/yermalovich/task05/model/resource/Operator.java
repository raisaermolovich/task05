package by.epam.javawebtraining.yermalovich.task05.model.resource;

import by.epam.javawebtraining.yermalovich.task05.view.LogPrinter;

import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class Operator {

    private int operatorNumber;

    public Operator(int operatorNmber) {
        this.operatorNumber = operatorNmber;
    }

    public int getOperatorNmber() {
        return operatorNumber;
    }

    public void setOperatorNmber(int operatorNumber) {
        if (operatorNumber > 0) {
            this.operatorNumber = operatorNumber;
        }
    }

    public void clientWaiting(long waitingTime) {

        try {
            TimeUnit.MILLISECONDS.sleep(waitingTime);
        } catch (InterruptedException e) {
            LogPrinter.LOGGER.error(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operator operator = (Operator) o;
        return operatorNumber == operator.operatorNumber;
    }

    @Override
    public int hashCode() {

        return Objects.hash(operatorNumber);
    }

    @Override
    public String toString() {
        return "Operator{" +
                "operatorNumber=" + operatorNumber +
                '}';
    }
}
