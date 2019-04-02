package by.epam.javawebtraining.yermalovich.task05.view;

import org.apache.log4j.Logger;

public class LogPrinter {

    public static final Logger LOGGER = Logger.getLogger("file");

    public void print(Object message) {
        LOGGER.info(message);
    }

}
