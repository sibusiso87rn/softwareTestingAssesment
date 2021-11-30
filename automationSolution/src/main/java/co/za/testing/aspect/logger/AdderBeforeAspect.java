package co.za.testing.aspect.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdderBeforeAspect {

    private final Logger logger
            = LoggerFactory.getLogger(AdderBeforeAspect.class);

    public void beforeAdvice() throws Throwable {
        logger.info("I would be executed just before method starts");
    }
}
