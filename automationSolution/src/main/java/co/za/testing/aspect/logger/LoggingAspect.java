package co.za.testing.aspect.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Aspect
public class LoggingAspect {

    private final Logger logger
            = LoggerFactory.getLogger(LoggingAspect.class);

    private ThreadLocal<SimpleDateFormat> sdf = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("[yyyy-mm-dd hh:mm:ss:SSS]");
        }
    };


    @Pointcut("within(co.za.testing..*) && @annotation(co.za.testing.aspect.annotations.Loggable)")
    public void loggableMethods() {
    }

    @Before("repositoryMethods()")
    public void logMethodCall(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        logger.info(sdf.get().format(new Date()) + methodName);
    }

    @Before("loggableMethods()")
    public void logMethod(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        logger.info("Executing method: " + methodName);
    }

}
