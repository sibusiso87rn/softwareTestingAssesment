package co.za.testing.aspect.performance;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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

        if(convert==0){
            logger.info("Class Name: "+ point.getSignature().getDeclaringTypeName() +". Method Name: "+ methodName + ". Time taken for Execution is : " + (endtime-startTime) +" milliseconds");
        }else{
            logger.info("Class Name: "+ point.getSignature().getDeclaringTypeName() +". Method Name: "+ methodName + ". Time taken for Execution is : " + convert +" seconds");
        }
        return retval;
    }

}
