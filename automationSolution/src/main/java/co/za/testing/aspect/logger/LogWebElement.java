package co.za.testing.aspect.logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogWebElement {

    private final Logger logger
            = LoggerFactory.getLogger(LogWebElement.class);

    public Object logElementInfo(ProceedingJoinPoint point) throws Throwable {
        Object result     = point.proceed();
        String methodName = point.getSignature().getName();
        logger.info("ElementInfo: "+ (WebElement)result +". Method Name: "+ methodName);
        return result;
    }
}
