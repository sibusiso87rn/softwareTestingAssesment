package co.za.testing.core.bean;

import co.za.testing.core.components.ScenarioContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ApplicationContext implements ApplicationContextAware {

    private static org.springframework.context.ApplicationContext CONTEXT;
    private static final String TEST_BEAN_NAME  = "testBean";
    private static final String SCENARIOCONTEXT = "scenarioContext";


    private static final Logger logger = LoggerFactory.getLogger(ApplicationContext.class);

    public void setApplicationContext(org.springframework.context.ApplicationContext context) throws BeansException {
        CONTEXT = context;
    }

    //Returns the bean if it exists in the context, returns null if the bean definition doesn't exist
    private static Object getBean(String beanName) {
        return CONTEXT.getBean(beanName);
    }

    //Removes a bean from the context during runtime
    private static void removeBean(String beanName){
        BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) CONTEXT;
        // Removing the bean from container
        beanDefinitionRegistry.removeBeanDefinition(beanName);
    }

    //Checks if the bean exists in the context, returns true if it does
    private static boolean beanExists(String beanName){
            try {
                return Objects.nonNull(getBean(beanName));
            }catch (NoSuchBeanDefinitionException e){
                logger.error(e.getMessage());
            }
            return false;
    }

    private static void registerBean(String beanName, Class clazz){
        logger.info("------------------------------");
        logger.info("Registering Bean Definition   " + beanName);
        logger.info("------------------------------");

        // Creating definition
        BeanDefinition beanDefinition = new RootBeanDefinition(clazz);

        //Registering bean and refreshing the context
        BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) CONTEXT;
        beanDefinitionRegistry.registerBeanDefinition(beanName,beanDefinition);

    }

    //Gets the test driver singleton from the context
    public static TestBean getTestBean(){
        return  (TestBean) ApplicationContext.getBean(TEST_BEAN_NAME);
    }

    //Gets the test driver singleton from the context
    public static ScenarioContext getScenarioContext(){
        return  (ScenarioContext) ApplicationContext.getBean(SCENARIOCONTEXT);
    }

    public static Object getComponent(Class clazz){
        return (clazz.cast(CONTEXT.getBean(clazz)));
    }

    public static void printBeans(){
        String[] beanNames = CONTEXT.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            logger.info("Printing Bean Details "+ beanName + " : " + CONTEXT.getBean(beanName).getClass().toString());
        }
    }

}
