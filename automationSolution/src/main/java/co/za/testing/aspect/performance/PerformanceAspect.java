package co.za.testing.aspect.performance;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.TimeUnit;

public class PerformanceAspect {

    private final Logger logger
            = LoggerFactory.getLogger(PerformanceAspect.class);

    public Object measureMethodExecutionTime(ProceedingJoinPoint point) throws Throwable {
        long startTime    = System.currentTimeMillis();
        Object retval     = point.proceed();
        long endtime      = System.currentTimeMillis();

        String methodName = point.getSignature().getName();
        double convert    = TimeUnit.SECONDS.convert((endtime-startTime), TimeUnit.MILLISECONDS);

        logger.info("Class Name: "+ point.getSignature().getDeclaringTypeName() +". Method Name: "+ methodName + ". Time taken for Execution is : " + (endtime-startTime) +" milliseconds");

        return retval;
    }

}
