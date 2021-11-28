package co.za.testing.core.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Service;

@Service
public class DriverCreatedCondition implements Condition {

    private final Logger logger
            = LoggerFactory.getLogger(DriverCreatedCondition.class);

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        try {
            return ApplicationContext.getTestBean().driverCreated();
        }
        catch (NullPointerException exception){
            logger.error("Error on DriverCreatedCondition");
            return false;
        }
    }
}
