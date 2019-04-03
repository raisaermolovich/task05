package by.epam.javawebtraining.yermalovich.task05.model.thread;


import by.epam.javawebtraining.yermalovich.task05.model.resource.Company;
import by.epam.javawebtraining.yermalovich.task05.model.resource.Operator;
import by.epam.javawebtraining.yermalovich.task05.util.ClientStatus;
import by.epam.javawebtraining.yermalovich.task05.view.LogPrinter;

import java.util.Objects;
import java.util.Random;


public class Client implements Runnable {

    private static final int WAITING_TIME = 5000;

    private Company company;
    private Thread thread;
    private volatile Status status;
    private boolean hasFinished;

    private long waitingTime;
    private long callingTime;

    public enum Status {
        WAITING, RECALL, DISCONNECTING
    }


    {
        hasFinished = false;
        status = Status.WAITING;
    }

    public Client(Company company, long waitingTime, long callingTime) {
        this.company = company;
        this.waitingTime = waitingTime;
        this.callingTime = callingTime;
        thread = new Thread(this);
        thread.start();
    }

    public long getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(long waitingTime) {
        if (waitingTime > 0) {
            this.waitingTime = waitingTime;
        }
    }

    public long getCallingTime() {
        return callingTime;
    }

    public void setCallingTime(long callingTime) {
        if (callingTime > 0) {
            this.callingTime = callingTime;
        }
    }

    public boolean hasFinished() {
        return hasFinished;
    }

    public Thread getThread() {
        return thread;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        if (status != null) {
            this.status = status;
        } else {
            LogPrinter.LOGGER.warn("Attempt to set Null Status for client N" + thread.getId() + ".");
        }
    }

    @Override
    public void run() {
        Operator operator = null;

        try {
            while (status == Status.RECALL || !hasFinished) {
                operator = company.connectCall(waitingTime);

                if (operator != null) {
                    LogPrinter.LOGGER.info("Client N"
                            + thread.getId()
                            + " has connected to operator N" + operator.getOperatorNmber() + ".");

                    operator.clientWaiting(callingTime);
                    hasFinished = true;

                } else {

                    status = ClientStatus.randomEnum(Status.class);

                    switch (status) {
                        case WAITING: {
                            LogPrinter.LOGGER.info("Client N" + thread.getId()
                                    + " continues to wait for connection.");
                            Random random = new Random();
                            try {
                                Thread.sleep(random.nextInt(WAITING_TIME));
                            } catch (InterruptedException e) {
                                LogPrinter.LOGGER.info("InterruptedException in Cient N" + thread.getId() + ".");
                                status = Status.DISCONNECTING;
                            }

                            break;
                        }

                        case RECALL:
                            LogPrinter.LOGGER.info("Client N" + thread.getId()
                                    + " has re-called.");
                            break;


                        case DISCONNECTING: {
                            LogPrinter.LOGGER.info("Client N" + thread.getId()
                                    + " stops waiting for connection and disconnects.");
                            hasFinished = true;
                            break;

                        }
                    }
                }
            }

        } finally {
            if (operator != null && hasFinished) {

                LogPrinter.LOGGER.info("Client N" + thread.getId() + " has finished call with operator N"
                        + operator.getOperatorNmber() + ".");

                status = Status.DISCONNECTING;
                company.disconnectCall(operator);
            }

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return hasFinished == client.hasFinished &&
                waitingTime == client.waitingTime &&
                callingTime == client.callingTime &&
                Objects.equals(company, client.company) &&
                Objects.equals(thread, client.thread) &&
                status == client.status;
    }

    @Override
    public int hashCode() {

        return Objects.hash(company, thread, status, hasFinished, waitingTime, callingTime);
    }

    @Override
    public String toString() {
        return "Client{" +
                "company=" + company +
                ", thread=" + thread +
                ", status=" + status +
                ", hasFinished=" + hasFinished +
                ", waitingTime=" + waitingTime +
                ", callingTime=" + callingTime +
                '}';
    }
}
