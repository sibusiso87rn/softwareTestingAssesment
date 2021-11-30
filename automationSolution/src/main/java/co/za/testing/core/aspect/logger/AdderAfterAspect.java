package co.za.testing.core.aspect.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdderAfterAspect {

    private final Logger logger
            = LoggerFactory.getLogger(AdderAfterAspect.class);

    public void afterAdvice() throws Throwable {
        logger.info("I'm done calling the method");
    }
}
