package co.za.testing.aspect.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdderAfterReturnAspect {

    private final Logger logger
            = LoggerFactory.getLogger(AdderAfterReturnAspect.class);

    public void afterReturn(final Object returnValue) throws Throwable {
        logger.info("value return was {}",  returnValue);
    }
}
